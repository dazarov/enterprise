package editor;

import java.util.ArrayList;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OriginalLine extends FastLinkedList<Text> {
	ArrayList<VirtualLine> virtualLines = new ArrayList<VirtualLine>();
	int oldFontSize = 12;
	
	public class VirtualLine{
		int startIndex;
		int endIndex;
		
		public VirtualLine(int startIndex, int endIndex){
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}
		
		public int size() {
			return endIndex - startIndex;
		}

		public boolean isEmpty() {
			return startIndex == endIndex;
		}
		
		public void clear() {
			startIndex = endIndex = 0;
		}
		
		public Text get(int index) {
			return OriginalLine.this.get(startIndex+index);
		}
		
		public void add(Text item) {
			OriginalLine.this.add(endIndex, item);
			notifyAdd(endIndex, 1);
		}
		
		public void add(int index, Text item) {
			OriginalLine.this.add(startIndex+index, item);
			notifyAdd(startIndex+index, 1);
		}
		
		public Text remove(int index) {
			Text text = OriginalLine.this.remove(startIndex+index);
			notifyDelete(startIndex+index, 1);
			return text;
		}

		public OriginalLine getOriginal() {
			return OriginalLine.this;
		}
		
		public int getOriginalIndex(int index){
			return startIndex+index;
		}
		
		public int getLocalIndex(int index){
			return index-startIndex;
		}
	}
	
	public ArrayList<VirtualLine> getVirtualLines(){
		return virtualLines;
	}
	
	public VirtualLine getByOriginalIndex(int index){
		for(VirtualLine line : virtualLines){
			if(line.startIndex <= index && line.endIndex > index){
				return line;
			}
		}
		return virtualLines.get(virtualLines.size()-1);
	}
	
	private void notifyAdd(int index, int length){
		for(VirtualLine line : virtualLines){
			if(line.startIndex > index){
				line.startIndex += length;
			}
			if(line.endIndex >= index){
				line.endIndex += length;
			}
		}
	}
	
	private void notifyDelete(int index, int length){
		for(VirtualLine line : virtualLines){
			if(line.startIndex > index){
				line.startIndex -= length;
			}
			if(line.endIndex > index){
				line.endIndex -= length;
			}
		}
	}
	
	private void setFont(String fontName, int fontSize){
		for(VirtualLine line : virtualLines){
            for (int i = 0; i < line.size(); i++) { //we need to change this. no for loop. get first node, update it, using node.next
                Text text = line.get(i);
                text.setFont(Font.font(fontName, fontSize));
            }
		}
	}
	
	private void setPositions(int startYIndex, double fontHight){
		double textCenterX = 5;
		double textCenterY = startYIndex * fontHight;
		for(VirtualLine line : virtualLines){
            for (int i = 0; i < line.size(); i++) { //we need to change this. no for loop. get first node, update it, using node.next
                Text text = line.get(i);
                
                text.setX(textCenterX);
                text.setY(textCenterY);
                
                textCenterX += text.getLayoutBounds().getWidth();
                
            }
            textCenterY += fontHight;
            textCenterX = 5;
		}
	}
	
	private static final String spaces=" .,\t-_!?";
	
	public void refreshVirtualLines(int startYIndex, double width, String fontName, int fontSize, double fontHight){
    	virtualLines.clear();
    	VirtualLine virtualLine = new VirtualLine(0, size());
    	virtualLines.add(virtualLine);
    	
		if(fontSize != oldFontSize){
			oldFontSize = fontSize;
			setFont(fontName, fontSize);
		}
    	
    	if(width > 50){
			double x=5;
			int wordNumber = 0;
			int lastWordIndex = 0;
			boolean withinWord = true;
			int virtualIndex = 0;
			for(int i = 0; i < size(); i++){
				Text text = get(i);
				String str = text.getText();
				if(spaces.indexOf(str) >= 0){
					if(withinWord && virtualIndex != 0){
						// first space character
						wordNumber++;
					}
					withinWord = false;
				}else{
					if(!withinWord){
						// begin of the word
						lastWordIndex = i;
					}
					withinWord = true;
				}
				x += text.getLayoutBounds().getWidth();
				if(x > width){
					if(wordNumber > 0 && lastWordIndex != 0){
						// move words to the next line
						virtualLine.endIndex = lastWordIndex;
						virtualLine = new VirtualLine(lastWordIndex, size());
						virtualLines.add(virtualLine);
						x = 5;
						i = lastWordIndex;
						wordNumber = 0;
						lastWordIndex = 0;
						withinWord = true;
						virtualIndex = 0;
						continue;
					}else{
						// breaking a long word
						i--;
						virtualLine.endIndex = i;
						virtualLine = new VirtualLine(i, size());
						virtualLines.add(virtualLine);
						x = 5;
						wordNumber = 0;
						lastWordIndex = 0;
						withinWord = true;
						virtualIndex = 0;
						continue;
					}
				}
				virtualIndex++;
			}
    	}
    	setPositions(startYIndex, fontHight);
	}
	
}
