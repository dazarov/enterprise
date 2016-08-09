package com.daniel.algorithms;

public class StringMaxCount {
	void printMaxCount(String str){
		System.out.println("\nString: "+str);
		
		if(str.isEmpty()){
			return;
		}
		
		int count = 1;
		int maxCount = 1;
		int number = str.length();
		char last = str.charAt(0);
		
		for(int i = 1; i < str.length(); i++){
			char ch = str.charAt(i);
			if(ch == last){
				count++;
				if(count > maxCount){
					maxCount = count;
					number = 0;
				}
				if(count == maxCount){
					number++;
				}
			}else{
				count = 1;
			}
			
			last = ch;
		}
		
		for(int i = 0; i < number; i++){
			System.out.print(maxCount+" ");
		}
	}
	
	public static void main(String[] args){
		StringMaxCount a = new StringMaxCount();
		
		a.printMaxCount("");
		
		a.printMaxCount("F");
		
		a.printMaxCount("abcdefgh");
		
		a.printMaxCount("aabbccddeeffgghh");
		
		a.printMaxCount("abbcdeffgh");
		
		a.printMaxCount("abbcdeeeffgh");
		
		a.printMaxCount("aaaabbcdeeeffgh");
	}
}
