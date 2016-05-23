package editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import editor.OriginalLine.VirtualLine;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Editor extends Application {
    private final Rectangle cursor;
    private int windowWidth = 500;
    private int windowHeight = 500;
    private Group displayRoot;
    private ScrollBar scrollBar; 

    private final int initialFontSize = 12;
    private int fontSize = initialFontSize;
    private String fontName = "Verdana";
    private Text fontHeightText = new Text(0,0,"");
    private double fontHeight;
    
    // file model
    private ArrayList<OriginalLine> originalLines = new ArrayList<OriginalLine>();
    
    // editor model
    private ArrayList<VirtualLine> virtualLines = new ArrayList<VirtualLine>();
    
    private Position cursorPosition = new Position(0,0);
    
    private String fileName;
    
    private UndoManager undoManager;

    public Editor() {
        cursor = new Rectangle(0, 0);
        undoManager = new UndoManager(this);
    }
    
    public ArrayList<OriginalLine> getLines(){
    	return originalLines;
    }
    
    public String getFontName(){
    	return fontName;
    }
    
    public int getFontSize(){
    	return fontSize;
    }
    
    public double getFontHeight(){
    	return fontHeight;
    }
    
    public Group getRoot(){
    	return displayRoot;
    }
    
    public void setOriginalCursorPosition(Position position){
    	if(position.getX() != 0 || position.getY() != 0){
        	OriginalLine originalLine = originalLines.get(position.getY());
        	if(originalLine != null){
	        	VirtualLine virtualLine = originalLine.getByOriginalIndex(position.getX());
	        	if(virtualLine != null){
	        		setCursorPosition(virtualLine.getLocalIndex(position.getX()), virtualLines.indexOf(virtualLine));
	        	}
        	}
        }
    }
    
    public void forward(int times){
    	for(int i = 0; i < times; i++){
    		setCursorPosition(cursorPosition.getX()+1, cursorPosition.getY());
    	}
    }
    
    @Override
    public void init() throws Exception {
        super.init();
        Parameters params = getParameters();
        if(params.getUnnamed().size() > 0){
        	fileName = params.getUnnamed().get(0);
        	File file = new File(fileName);
        	if(file.exists()){
	        	try {
	                FileReader reader = new FileReader(file);
	                BufferedReader bufferedReader = new BufferedReader(reader);
	                while(bufferedReader.ready()){
	               	 	String line = bufferedReader.readLine();
	               	 	OriginalLine lineList = new OriginalLine();
	               	 	for(int i = 0; i < line.length(); i++){
	               	 		Text text = new Text(0,0, new String(new char[]{line.charAt(i)}));
	               	 		text.setTextOrigin(VPos.TOP);
	               	 		text.setFont(Font.font(fontName, fontSize));
	               	 		lineList.add(text);
	               	 	}
	               	 	originalLines.add(lineList);
	                }
	                bufferedReader.close();
	                reader.close();
	            } catch (FileNotFoundException fileNotFoundException) {
	                System.out.println("File not found! Exception was: " + fileNotFoundException);
	            } catch (IOException ex){
	           	 System.out.println(ex);
	            }
        	}else{
        		OriginalLine lineList = new OriginalLine();
            	originalLines.add(lineList);
        	}
        }else{
        	System.out.println("File must be specified!");
        	System.exit(0);
        }
    }
    
    private void saveFile(){
    	StringBuilder builder = new StringBuilder();
    	for(OriginalLine line: originalLines){
    		for(int i = 0; i < line.size(); i++){
    			Text text = line.get(i);
    			builder.append(text.getText());
    		}
    		builder.append("\n");
    	}
    	String fileText = builder.toString();
    	try {
			FileWriter writer = new FileWriter(fileName);
			writer.write(fileText);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private class MouseEventHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent mouseEvent) {
			if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
				double x = mouseEvent.getX()+5;
				double y = (scrollBar.getValue()+mouseEvent.getY())/fontHeight;
				setCursorPosition(cursorPosition.getX(), (int)y);
				VirtualLine lineList = virtualLines.get(cursorPosition.getY());
				if(lineList != null){
					for(int i = 0; i < lineList.size(); i++){
						Text text = lineList.get(i);
						if(x > text.getX() && x < (text.getX()+ text.getLayoutBounds().getWidth())){
							setCursorPosition(i,(int)y);
							return;
						}
					}
					setCursorPosition(lineList.size(),(int)y);
					return;
				}
			}
		}
    	
    }

    private class KeyEventHandler implements EventHandler<KeyEvent> {
        
        private void arrowUp() {
        	setCursorPosition(cursorPosition.getX(), cursorPosition.getY()-1);
        }
        
        private void arrowDown() {
        	setCursorPosition(cursorPosition.getX(), cursorPosition.getY()+1);
        }
        
        private void arrowRight() {
        	setCursorPosition(cursorPosition.getX()+1, cursorPosition.getY());
        }
        
        private void arrowLeft() {
        	setCursorPosition(cursorPosition.getX()-1, cursorPosition.getY());
        }
        
        private void setFontSize(int size){
        	fontSize = Math.max(2, size);
        	
            fontHeightText.setFont(Font.font(fontName, fontSize));
            fontHeight = fontHeightText.getLayoutBounds().getHeight();
            
            updateTypedTextPositions();
            
            renderText();
            updateCursor();
        }
        
        private void decreaseFontSize() {
        	setFontSize(fontSize - 4);
        }

        private void increaseFontSize() {
        	setFontSize(fontSize + 4);
        }
        
        boolean isTypedText = false;

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
            	keyEvent.consume();
                if (isTypedText) {
                	
                	Position originalPosition = getOriginalPosition(cursorPosition);
                	
                	Insert insert = new Insert(originalPosition, keyEvent.getCharacter());
                	undoManager.doChange(insert);
                }

            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                 

                KeyCode code = keyEvent.getCode();
                isTypedText = false;
                
                // All shortcuts go here
                if(keyEvent.isShortcutDown()){
                	if (code == KeyCode.PLUS || code == KeyCode.EQUALS) {
                		increaseFontSize();
                	}else if (code == KeyCode.MINUS) {
                		decreaseFontSize();
                	}else if(code == KeyCode.S){
                		// save
                		saveFile();
                	}else if(code == KeyCode.Z){
                		// undo
                		undoManager.undo();
                	}else if(code == KeyCode.Y){
                		// redo
                		undoManager.redo();
                	}else if(code == KeyCode.P){
                		// print the current position
                		System.out.println((int)cursor.getX()+", "+(int)cursor.getY());
                	}
                }else{
                
	                if (code == KeyCode.UP) {
                        arrowUp();
	                } else if (code == KeyCode.DOWN) {
                        arrowDown();
	                } else if (code == KeyCode.BACK_SPACE) {
	                	if(cursorPosition.getX() != 0 || cursorPosition.getY() != 0){
		                	setCursorPosition(cursorPosition.getX()-1, cursorPosition.getY());
		                	delete();
	                	}
	                }else if(code == KeyCode.DELETE){
	                	//delete();
	                } else if (code == KeyCode.ENTER) {
	                	Position originalPosition = getOriginalPosition(cursorPosition);
	                	
	                	Insert insert = new Insert(originalPosition, "\n");
	                	undoManager.doChange(insert);
	                } else if (code == KeyCode.LEFT) {
	                    arrowLeft();
	                } else if (code == KeyCode.RIGHT) {
	                    arrowRight();
	                } else if(code == KeyCode.HOME){
	                	setCursorPosition(0, cursorPosition.getY());
	                } else if(code == KeyCode.END){
	                	VirtualLine lineTyped = virtualLines.get(cursorPosition.getY());
	                	setCursorPosition(lineTyped.size(), cursorPosition.getY());
	                }else if(code == KeyCode.ESCAPE){
	                	// DO nothing
	                } else if(!code.isFunctionKey() && !code.isMediaKey() && !code.isNavigationKey()){
	                	isTypedText = true;
	                }
                }
            }
        }
    }
    
    private void delete(){
    	Position originalPosition = getOriginalPosition(cursorPosition);
    	
    	Remove remove = new Remove(originalPosition, 1);
    	undoManager.doChange(remove);
    }
    
    private class RectangleBlinkEventHandler implements EventHandler<ActionEvent> {
        private int currentColorIndex = 0;
        private Color[] boxColors =
                {Color.BLACK, Color.WHITE};

        RectangleBlinkEventHandler() {
            // Set the color to be the first color in the list.
            changeColor();
        }

        private void changeColor() {
            cursor.setFill(boxColors[currentColorIndex]);
            currentColorIndex = (currentColorIndex + 1) % boxColors.length;
        }

        @Override
        public void handle(ActionEvent event) {
            changeColor();
        }
    }
    
    private void setCursorPosition(int x, int y){
    	if (y < 0) {
            y = 0;
        }

        if(y >= virtualLines.size()) {
            y = virtualLines.size() - 1;
        }
        
        int size = virtualLines.get(y).size();

        if(x == size+1 && y < virtualLines.size()-1){
    		x = 0;
    		y++;
    		size = virtualLines.get(y).size();
        }else if(x > size) {
            x = size;
        }else if(x < 0 && y > 0){
        	y--;
        	size = virtualLines.get(y).size();
        	x = size;
        }

        if(x > size) {
            x = size;
        }
        
        if(x < 0){
        	x = 0;
        }
    	
    	cursorPosition.setX(x);
    	cursorPosition.setY(y);
    	updateCursor();
    	makeCursorVisible();
    }
    
    private void makeCursorVisible(){
    	double value = scrollBar.getValue();
    	
    	if((cursor.getY()+cursor.getHeight()+5) > (value+windowHeight)){
    		value += (cursor.getY()+cursor.getHeight()+5)-(value+windowHeight);
    		if(value > scrollBar.getMax()){
    			value = scrollBar.getMax();
    		}
    		scrollBar.setValue(value);
    	}else if(value > cursor.getY()){
    		value -= value-cursor.getY();
    		if(value < 0.0){
    			value = 0.0;
    		}
    		scrollBar.setValue(value);
    	}
    }

    /** Makes the text bounding box change color periodically. */
    public void makeRectangleColorChange() {
        // Create a Timeline that will call the "handle" function of RectangleBlinkEventHandler
        // every 1 second.
        final Timeline timeline = new Timeline();
        // The rectangle should continue blinking forever.
        timeline.setCycleCount(Timeline.INDEFINITE);
        RectangleBlinkEventHandler cursorChange = new RectangleBlinkEventHandler();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), cursorChange);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    @Override
    public void start(final Stage primaryStage) {
        Group root = new Group();
        
        Group textRoot = new Group();
        textRoot.setCursor(Cursor.TEXT);
        
        displayRoot = textRoot;
        root.getChildren().add(textRoot);
        
        cursor.setX(5);
        cursor.setY(0);
        cursor.setWidth(1);
        cursor.setHeight(7);
        displayRoot.getChildren().add(cursor);
        displayRoot.getChildren().add(fontHeightText);
        fontHeightText.setFont(Font.font(fontName, fontSize));
        fontHeight = fontHeightText.getLayoutBounds().getHeight();

        // Create a Node that will be the parent of all things displayed on the screen.

        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, windowWidth, windowHeight, Color.WHITE);
        // Make a vertical scroll bar on the right side of the screen.
        scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        // Set the height of the scroll bar so that it fills the whole window.
        scrollBar.setPrefHeight(windowHeight);
        scrollBar.setMin(0);
        scrollBar.setUnitIncrement(10.0);
        scrollBar.setBlockIncrement(100.0);
        root.getChildren().add(scrollBar);

        double usableScreenWidth = windowWidth - scrollBar.getLayoutBounds().getWidth();
        scrollBar.setLayoutX(usableScreenWidth);

        scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
            	textRoot.setLayoutY(-newValue.doubleValue());
            }
        });

        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler = new KeyEventHandler();
        
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);
        
        EventHandler<MouseEvent> mouseEventHandler =  new MouseEventHandler();
        
        // Register the event handler to be called for all Mouse events.
        scene.setOnMouseClicked(mouseEventHandler);
        scene.setOnMouseEntered(mouseEventHandler);
        scene.setOnMouseExited(mouseEventHandler);
        scene.setOnMouseMoved(mouseEventHandler);
        scene.setOnMousePressed(mouseEventHandler);
        scene.setOnMouseReleased(mouseEventHandler);
        
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
            	windowWidth = newSceneWidth.intValue();
            	double usableScreenWidth = windowWidth - scrollBar.getLayoutBounds().getWidth();
                scrollBar.setLayoutX(usableScreenWidth);
                if(primaryStage.isShowing()){
                	updateTypedTextPositions();
                }
            }

        });
        
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
            	windowHeight = newSceneHeight.intValue();
            	scrollBar.setPrefHeight(windowHeight);
            	scrollBar.setMax((virtualLines.size()*fontHeight)-windowHeight+5);
            }
        });
        

        makeRectangleColorChange();

        primaryStage.setTitle("Full Text Display");

        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
        
        for(OriginalLine lineList : originalLines){
        	for(int i = 0; i < lineList.size(); i++){
        		Text text = lineList.get(i);
        		displayRoot.getChildren().add(text);
                
        	}
        }
        updateTypedTextPositions();
        renderText();
        updateCursor();
    }
    
    
    
    public void renderText() {
        cursor.setHeight(fontHeight);
        cursor.setWidth(1);


        // Make sure the text appears in front of any other objects you might add.
        for(OriginalLine linkedList: originalLines) {
            for (int i = 0; i < linkedList.size(); i++){
                linkedList.get(i).toFront();
            }

        }
    }
    
    private Position getOriginalPosition(Position virtual){
    	int originalY = 0, originalX = 0;
    	if(virtual.getY() != 0 || virtual.getX() != 0){
    		VirtualLine cursorLine = virtualLines.get(virtual.getY());
    		if(cursorLine != null){
    			originalY = originalLines.indexOf(cursorLine.getOriginal());
    			if(originalY < 0){
    				originalY = 0;
    			}
    			originalX = cursorLine.getOriginalIndex(virtual.getX());
    		}
    	}
    	return new Position(originalX, originalY);
    }
    
    public void updateTypedTextPositions() {
    	
    	int startYIndex = 0;
    	
   	 	double	usableScreenWidth = windowWidth - scrollBar.getLayoutBounds().getWidth();
    	
    	// convert cursor position and selection from virtual to original
    	Position originalPosition = getOriginalPosition(cursorPosition);
    	
    	virtualLines.clear();
    	
        for(OriginalLine editorLine : originalLines){
        	editorLine.refreshVirtualLines(startYIndex, usableScreenWidth-5.0, fontName, fontSize, fontHeight);
        	ArrayList<VirtualLine> lines = editorLine.getVirtualLines();
        	virtualLines.addAll(lines);
        	startYIndex += lines.size();
        }
        
        //restore cursor position and selection from original to virtual
        setOriginalCursorPosition(originalPosition);
    	
        scrollBar.setMax((virtualLines.size()*fontHeight)-windowHeight+5);
        
    }
    
    private void updateCursor() {
    	VirtualLine linkedList = virtualLines.get(cursorPosition.getY());
        if(linkedList == null){
        	cursor.setX(5);
    		cursor.setY(0);
    		return;
        }
        if(linkedList.size() == 0){
        	cursor.setX(5);
    		cursor.setY(cursorPosition.getY()*fontHeight);
        }else if(cursorPosition.getX() == linkedList.size()){
        	Text text = linkedList.get(cursorPosition.getX()-1);
        	if(text != null){
        		cursor.setX(text.getX()+text.getLayoutBounds().getWidth());
        		cursor.setY(text.getY());
        	}
        }else{
        	Text text = linkedList.get(cursorPosition.getX());
        	if(text != null){
        		cursor.setX(text.getX());
        		cursor.setY(text.getY());
        	}
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}