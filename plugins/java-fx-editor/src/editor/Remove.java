package editor;

import java.util.List;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Remove extends Change{
	private List<Integer> indexes = null;
	
	public Remove(Position position, int length){
		super(position, length);
	}

	@Override
	public void redo(Editor editor) {
		OriginalLine line = editor.getLines().get(position.getY());
		if(line != null){
			indexes = preRemove(editor);
			
			StringBuilder builder = new StringBuilder();
			for(int i = 0;i < length-indexes.size(); i++){
				Text item = line.remove(position.getX());
				editor.getRoot().getChildren().remove(item);
				builder.append(item.getText());
			}
			textToInsert = builder.toString();
			editor.updateTypedTextPositions();
            editor.renderText();
			editor.setOriginalCursorPosition(position);
		}else{
			System.out.println("Line is not found");
			Thread.dumpStack();
		}
	}

	@Override
	public void undo(Editor editor) {
		if(textToInsert == null){
			System.out.println("Illegal state of change Remove ");
			Thread.dumpStack();
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

	
	
}
