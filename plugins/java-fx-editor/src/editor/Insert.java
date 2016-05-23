package editor;

import java.util.List;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Insert extends Change{
	private List<Integer> indexes = null;
	
	public Insert(Position position, String text){
		super(position, text.length(), text);
	}

	@Override
	public void redo(Editor editor) {
		if(indexes == null){
			indexes = getNewLineIndexes();
		}
		OriginalLine line = editor.getLines().get(position.getY());
		if(line != null){
			for(int i = 0;i < textToInsert.length(); i++){
				Text item = new Text(new String(new char[]{textToInsert.charAt(i)}));
				item.setTextOrigin(VPos.TOP);
				item.setFont(Font.font(editor.getFontName(), editor.getFontSize()));
				line.add(position.getX()+i, item);
				editor.getRoot().getChildren().add(item);
			}
			createLines(editor, indexes);
			editor.updateTypedTextPositions();
            editor.renderText();
			editor.setOriginalCursorPosition(position);
			editor.forward(length);
		}else{
			System.out.println("Line is not found");
			Thread.dumpStack();
		}
	}

	@Override
	public void undo(Editor editor) {
		OriginalLine line = editor.getLines().get(position.getY());
		if(line != null){
			preRemove(editor);
			for(int i = 0;i < length-indexes.size(); i++){
				Text item = line.remove(position.getX());
				editor.getRoot().getChildren().remove(item);
			}
			editor.updateTypedTextPositions();
            editor.renderText();
			editor.setOriginalCursorPosition(position);
		}else{
			System.out.println("Line is not found");
			Thread.dumpStack();
		}
		
	}
}
