package com.musicbox;

import static com.musicbox.Utils.error;
import static com.musicbox.Utils.format;
import static com.musicbox.Utils.out;
import static com.musicbox.Utils.printErrors;
import static com.musicbox.Utils.waitForCommand;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagOptionSingleton;

public class MusicBoxValidator {
	private static final int MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER = 4;
	private static final String SONG_LIST_FILE = "SongList.txt";
	private static final String MBOX_SIGNATURE = "MBOX.ENTERPRISE.DANIEL";

	private final MusicBoxCollection collection;

	private int fileNumbers = 0;
	private long totalLength = 0;
	private List<String> artistFolders = new ArrayList<>();

	private List<Move> laterMoves = new ArrayList<>();

	static class Move {
		Path source, target;

		public Move(Path source, Path target) {
			this.source = source;
			this.target = target;
		}
	}

	void moveLater(Path source, Path target) {
		laterMoves.add(new Move(source, target));
	}

	void laterMove(Writer logFile) throws IOException {
		for (Move move : laterMoves) {
			String fileName = move.source.getFileName().toString();
			out(logFile, fileName
					+ " Song in All forder, but there is a specific folder for this artist! Moving...");
			Files.move(move.source, move.target);
			out(logFile, "File successfully moved!");
		}

		laterMoves.clear();
	}

	MusicBoxValidator(MusicBoxCollection collection) {
		this.collection = collection;
	}

	MusicBoxCollection getMusicCollection() {
		return collection;
	}

	void performeValidation(Writer log, Path root) throws IOException {
		LocalDateTime startDateTime = LocalDateTime.now();
		out(log, "\n\n################################    "
				+ format(startDateTime)
				+ "    ################################");
		out(log, "Validation...");

		if (Files.isDirectory(root)) {
			// Files.walk(root).filter(p ->
			// Files.isDirectory(p)).forEach(this::scanDirectory);
			Files.find(root, 20, (p, a) -> a.isDirectory())
					.forEach(this::scanDirectory);

			// Files.walk(root).filter(p -> Files.isRegularFile(p) &&
			// p.toString().endsWith(".mp3")).forEach(p -> processFile(log, p));
			Files.find(root, 20,
					(p, a) -> a.isRegularFile()
							&& p.toString().endsWith(".mp3"))
					.forEach(p -> processFile(log, root, p));

			String lang = TagOptionSingleton.getInstance().getLanguage();
			TagOptionSingleton.getInstance().setLanguage("rus");
			Files.find(root.resolve("Russian"), 20,
					(p, a) -> a.isRegularFile()
							&& p.toString().endsWith(".mp3"))
					.forEach(p -> updateFile(log, root, p));
			TagOptionSingleton.getInstance().setLanguage(lang);
		} else {
			out(log, root + " is not a folder!");
		}

		laterMove(log);

		moveToSeparateFolder(log);

		createSongListFile(format(startDateTime));

		out(log, "Files processed - " + fileNumbers);
		long hours = totalLength / (60 * 60);
		long min = (totalLength % (60 * 60)) / 60;
		long sec = (totalLength % (60 * 60)) % 60;

		out(log, hours + ":" + min + ":" + sec);

		out(log, "Artists: " + collection.getNumberOFArtists());

		collection.validateArtists(log);

		// print errors
		printErrors();

		Duration duration = Duration.between(startDateTime,
				LocalDateTime.now());
		out(log, "Processing time: " + format(duration));
	}

	private void scanDirectory(Path directory) {
		String artistFolder = null;
		if (!directory.getFileName().startsWith("All")) {
			artistFolder = directory.getFileName().toString();
			if (!artistFolders.contains(artistFolder.toLowerCase())) {
				artistFolders.add(artistFolder.toLowerCase());
			}
		}
	}

	private void createSongListFile(String dateString) {
		try (BufferedWriter song_list = Files
				.newBufferedWriter(Paths.get(SONG_LIST_FILE))) {
			out(song_list, dateString);
			collection.printAll(song_list);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void moveToSeparateFolder(Writer logFile) throws IOException {
		Collection<List<Path>> collection = this.collection.nonFolderArtists
				.values();
		for (List<Path> list : collection) {
			if (list.size() > MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER) {
				Path firstFile = list.get(0);
				SongFileInformation fileNameInfo = new SongFileInformation(
						firstFile.getFileName().toString(), logFile);
				String newFolderName = fileNameInfo.getAllArtists();
				out(logFile,
						"Creating folder for new artist - " + newFolderName);
				Path dir = firstFile.getParent().getParent();
				Path newDir = Paths.get(dir.toString(), newFolderName);
				out(logFile, "Path for new folder - " + newDir.toString());
				if (!Files.exists(newDir)) {
					Files.createDirectory(newDir);
					out(logFile, "Folder successfully created!");

					for (Path file : list) {
						Path newFile = Paths.get(newDir.toString(),
								file.getFileName().toString());
						out(logFile, "Moving file into new filder - "
								+ file.getFileName());
						Files.move(file, newFile);
						out(logFile, "File successfully moved!");
					}
				}
			}
		}
	}

	private void updateFile(Writer logFile, Path root, Path file) {

		String fileName = file.getFileName().toString();
		// out(logFile, fileName+" Updating file...");
		try {

			if (!Files.isWritable(file)) {
				error(logFile, fileName + " File is read-only!");
				return;
			}

			SongFileInformation fileInfo = new SongFileInformation(fileName,
					logFile);

			AudioFile audioFile = AudioFileIO.read(file.toFile());
			Tag tag = audioFile.getTag();
			if (tag == null
					|| !MBOX_SIGNATURE.equals(tag.getFirst(FieldKey.COMMENT))) {

				out(logFile, fileName + " Replacing a tag ...");
				tag = audioFile.createDefaultTag();
				audioFile.setTag(tag);
				tag.setField(FieldKey.ARTIST, fileInfo.getAllArtists());
				tag.setField(FieldKey.TITLE, fileInfo.getTitle());
				tag.setField(FieldKey.COMMENT, MBOX_SIGNATURE);
				try {
					AudioFileIO.write(audioFile);
				} catch (CannotWriteException e) {
					out(logFile, e.getMessage());
					e.printStackTrace();
				}
			}

		} catch (CannotReadException | IOException | TagException
				| ReadOnlyFileException | InvalidAudioFrameException e) {
			out(logFile, e.getMessage());
			e.printStackTrace();
		}
	}

	private void processFile(Writer logFile, Path root, Path file) {
		fileNumbers++;

		String folderArtist = file.getParent().getFileName().toString();
		if (folderArtist.startsWith("All")) {
			folderArtist = null;
		}

		String fileName = file.getFileName().toString();
		try {

			if (!Files.isWritable(file)) {
				error(logFile, fileName + " File is read-only!");
				return;
			}

			SongFileInformation fileNameInfo = new SongFileInformation(fileName,
					logFile);

			collection.addSong(logFile, fileNameInfo);

			AudioFile audioFile = AudioFileIO.read(file.toFile());
			Tag tag = audioFile.getTag();
			if (tag == null) {
				out(logFile, fileName + " TAG is null! Creating a new one...");
				tag = audioFile.createDefaultTag();
				audioFile.setTag(tag);
				tag.setField(FieldKey.ARTIST, fileNameInfo.allArtists);
				tag.setField(FieldKey.TITLE, fileNameInfo.title);

				try {
					AudioFileIO.write(audioFile);
				} catch (CannotWriteException e) {
					out(logFile, e.getMessage());
					e.printStackTrace();
				}
				return;
			}

			AudioHeader header = audioFile.getAudioHeader();
			if (header == null) {
				error(logFile, fileName + " HEADER is null!");
			} else {
				int trackLength = header.getTrackLength();
				totalLength += trackLength;
			}

			SongTagInformation tagInfo = new SongTagInformation(
					tag.getFirst(FieldKey.ARTIST),
					tag.getFirst(FieldKey.TITLE));

			if (!tagInfo.artist.equals(fileNameInfo.allArtists)
					|| !tagInfo.title.equals(fileNameInfo.title)
					|| (folderArtist != null && ((fileNameInfo.isMultiArtist()
							&& fileNameInfo.allArtists
									.indexOf(folderArtist) < 0)
							|| (!fileNameInfo.isMultiArtist()
									&& !fileNameInfo.allArtists
											.equals(folderArtist))))) {
				System.out.println("Which one is correct?");
				if ((folderArtist != null && ((fileNameInfo.isMultiArtist()
						&& fileNameInfo.allArtists.indexOf(folderArtist) < 0)
						|| (!fileNameInfo.isMultiArtist()
								&& !fileNameInfo.allArtists
										.equals(folderArtist))))) {
					System.out.println("1. Folder name - " + folderArtist);
				}
				System.out.println("2. File name - " + fileName);
				System.out.println("3. Tags name - " + tagInfo.artist + " - "
						+ tagInfo.title + ".mp3");
				System.out.println("4. Skip");
				System.out.println("0. Exit");

				int input = waitForCommand("Enter:");

				System.out.println("Input - " + input);

				if (input == 1) {
					tag.setField(FieldKey.ARTIST, folderArtist);
					try {
						AudioFileIO.write(audioFile);
					} catch (CannotWriteException e) {
						out(logFile, e.getMessage());
						e.printStackTrace();
					}
					Path newFile = Paths.get(file.getParent().toString(),
							folderArtist + " - " + tagInfo.title + ".mp3");
					Files.move(file, newFile);
				} else if (input == 2) {
					// tag = audioFile.createDefaultTag();
					// audioFile.setTag(tag);
					tag.setField(FieldKey.ARTIST, fileNameInfo.allArtists);
					tag.setField(FieldKey.TITLE, fileNameInfo.title);

					try {
						AudioFileIO.write(audioFile);
					} catch (CannotWriteException e) {
						out(logFile, e.getMessage());
						e.printStackTrace();
					}
				} else if (input == 3) {
					Path newFile = Paths.get(file.getParent().toString(),
							tagInfo.artist + " - " + tagInfo.title + ".mp3");
					Files.move(file, newFile);
				} else if (input == 0) {
					System.exit(0);
				}
			}

			if (folderArtist == null) {
				if (artistFolders
						.contains(fileNameInfo.allArtists.toLowerCase())) {

					Path dir = findArtistFolder(root,
							fileNameInfo.allArtists.toLowerCase());
					if (dir != null) {
						Path newFile = Paths.get(dir.toString(),
								fileNameInfo.allArtists + " - "
										+ fileNameInfo.title + ".mp3");
						moveLater(file, newFile);
					} else {
						error(logFile, fileNameInfo.allArtists.toLowerCase()
								+ " Internal error - folder not found!!!!");
					}
				}
				if (collection.nonFolderArtists
						.containsKey(fileNameInfo.allArtists.toLowerCase())) {
					List<Path> files = collection.nonFolderArtists
							.get(fileNameInfo.allArtists.toLowerCase());
					files.add(file);
					if (files.size() > MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER) {
						out(logFile, fileName
								+ " Song in All forder, but there are more then 4 songs of this artist!");
					}
				} else {
					List<Path> files = new ArrayList<>();
					files.add(file);
					collection.nonFolderArtists
							.put(fileNameInfo.allArtists.toLowerCase(), files);
				}

			}

		} catch (CannotReadException | IOException | TagException
				| ReadOnlyFileException | InvalidAudioFrameException e) {
			out(logFile, e.getMessage());
			e.printStackTrace();
		}
	}

	private Path findArtistFolder(Path rootDir, String folderName) {
		try {
			Optional<Path> result = Files
					.find(rootDir,
							20, (p, a) -> p.getFileName().toString()
									.toLowerCase().equals(folderName))
					.findFirst();
			if (result.isPresent()) {
				return result.get();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static class SongFileInformation {
		private final String fileName;
		private final String allArtists;
		private final String[] artists;
		private final String title;

		public SongFileInformation(String fileName, Writer logFile) {
			this.fileName = fileName;
			int delimeter = fileName.indexOf(" - ");
			int mp3Position = fileName.indexOf(".mp3");

			if (delimeter < 0 || mp3Position < 0) {
				error(logFile, fileName + " Delimiter or .mp3 not found!");
				this.allArtists = "";
				this.title = "";
				artists = null;
				return;
			}

			this.allArtists = fileName.substring(0, delimeter);
			// info.multiArtist = info.
			this.title = fileName.substring(delimeter + 3, mp3Position);

			if (this.allArtists.length() == 0) {
				error(logFile, fileName + " Artist is empty!");
				artists = null;
				return;
			}

			if (this.title.isEmpty()) {
				error(logFile, fileName + " Title is empty!");
				artists = null;
				return;
			}
			
			artists = StringUtils.splitByWholeSeparator(allArtists.replaceAll(" и ", " & "), " & ", 0);
		}
		
		public String getFileName() {
			return this.fileName;
		}

		public boolean isMultiArtist() {
		    return artists != null ? artists.length > 1 : false;
		}

		public String getTitle() {
			return this.title;
		}

		public String getAllArtists() {
			return allArtists;
		}
		
		public String[] getArtists() {
			return artists;
		}

	}

	public static final class SongTagInformation {
		private final String artist;
		private final String title;

		public SongTagInformation(String artist, String title) {
			this.artist = artist;
			this.title = title;
		}

		public String getArtist() {
			return artist;
		}

		public String getTitle() {
			return title;
		}

	}
}
