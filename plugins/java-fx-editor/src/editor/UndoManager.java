package editor;

public class UndoManager {
	private Stack<Change> undoStack = new Stack<Change>(100);
	private Stack<Change> redoStack = new Stack<Change>(100);
	
	private Editor editor;
	
	public UndoManager(Editor editor){
		this.editor = editor;
	}
	
	public void doChange(Change change){
		change.redo(editor);
		undoStack.push(change);
	}
	
	public void undo(){
		Change change = undoStack.pop();
		if(change != null){
			redoStack.push(change);
			change.undo(editor);
		}
	}
	
	public void redo(){
		Change change = redoStack.pop();
		if(change != null){
			undoStack.push(change);
			change.redo(editor);
		}
	}
}
