package editor;

import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Replace extends Change{
	private String newText = null;
	private String oldText = null;
	
	public Replace(Position position, int length, String text){
		super(position, length);
		newText = text;
	}

	@Override
	public void redo(Editor editor) {
		OriginalLine line = editor.getLines().get(position.getY());
		if(line != null){
			preRemove(editor);
			
			StringBuilder builder = new StringBuilder();
			for(int i = 0;i < length; i++){
				Text item = line.remove(position.getX());
				builder.append(item.getText());
			}
			oldText = builder.toString();
			
			for(int i = 0;i < newText.length(); i++){
				Text item = new Text(new String(new char[]{newText.charAt(i)}));
				item.setTextOrigin(VPos.TOP);
				item.setFont(Font.font(editor.getFontName(), editor.getFontSize()));
				line.add(position.getX()+i, item);
			}
			
			//postInsert(editor, newText.length());
		}else{
			throw new RuntimeException("Line is not found");
		}
	}

	@Override
	public void undo(Editor editor) {
		if(oldText == null){
			throw new IllegalStateException("Illegal state of change Remove ");
		}
		OriginalLine line = editor.getLines().get(position.getY());
		if(line != null){
			preRemove(editor, newText.length());
			
			for(int i = 0;i < newText.length(); i++){
				line.remove(position.getX());
			}
			
			for(int i = 0;i < oldText.length(); i++){
				Text item = new Text(new String(new char[]{oldText.charAt(i)}));
				item.setTextOrigin(VPos.TOP);
				item.setFont(Font.font(editor.getFontName(), editor.getFontSize()));
				line.add(position.getX()+i, item);
			}
			
			//postInsert(editor);
		}else{
			throw new RuntimeException("Line is not found");
		}
	}
}
