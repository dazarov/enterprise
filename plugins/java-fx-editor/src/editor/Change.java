package editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Text;

public abstract class Change {
	protected final Position position;
	protected final int length;
	protected String textToInsert;
	
	public Change(Position position, int length){
		this.position = position;
		this.length = length;
	}

	public Change(Position position, int length, String text){
		this(position, length);
		textToInsert = text;
	}
	
	abstract public void undo(Editor editor);
	
	abstract public void redo(Editor editor);
	
    protected void moveItemsToNextLine(int index, OriginalLine source, OriginalLine target){
    	while(true){
    		Text text = source.remove(index);
    		if(text == null){
    			break;
    		}
    		target.add(text);
    	}
    }
    
    protected List<Integer> getNewLineIndexes(){
    	List<Integer> indexes = new ArrayList<Integer>();
    	StringBuilder builder = new StringBuilder(textToInsert);
    	while(true){
    		int index = builder.indexOf("\n");
    		if(index >= 0){
    			indexes.add(index);
    			builder.replace(index, index+1, "");
    		}else{
    			break;
    		}
    	}
    	textToInsert = builder.toString();
    	//length = textToInsert.length();
    	
    	return indexes;
    }
    
    
    protected List<Integer> preRemove(Editor editor){
    	return preRemove(editor, length);
    }
    
    protected List<Integer> preRemove(Editor editor, int length){
    	List<Integer> indexes = getLineNumberToMerge(editor, length);
    	mergeLines(editor, indexes.size()+1);
    	return indexes;
    }
    
    protected List<Integer> getLineNumberToMerge(Editor editor, int length){
    	List<Integer> indexes = new ArrayList<Integer>();
    	OriginalLine line = editor.getLines().get(position.getY());
    	int rest = length;
    	rest -= line.size()-position.getX();
    	int lineIndex = position.getY()+1;
    	while(rest > 0){
    		if(lineIndex > editor.getLines().size()-1){
    			break;
    		}
    		line = editor.getLines().get(lineIndex);
    		indexes.add(length-rest);
    		rest -= line.size();
    		lineIndex++;
    	}
    	
    	return indexes;
    }
    
    // find /n, remove it, create new line, move elements to new line, repeat 
    protected void createLines(Editor editor, List<Integer> indexes){
    	OriginalLine line = editor.getLines().get(position.getY());
    	int i = 1;
		for(int index : indexes){
	    	OriginalLine newLine = new OriginalLine();
	        
	        moveItemsToNextLine(position.getX()+index, line, newLine);
	        
	        editor.getLines().add(position.getY()+i, newLine);
	        line = newLine;
	        i++;
	    }
    }
    
    // find all lines within the text range, merge them together 
    protected void mergeLines(Editor editor, int lineNumber){
    	if(lineNumber > 1){
    		OriginalLine line = editor.getLines().get(position.getY());
    		for(int i = 1; i < lineNumber; i++){
    			OriginalLine source = editor.getLines().get(position.getY()+i);
    			moveItemsToNextLine(0, source, line);
    			editor.getLines().remove(source);
    		}
    	}
    }

}
