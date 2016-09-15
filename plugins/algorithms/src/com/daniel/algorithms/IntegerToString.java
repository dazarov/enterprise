package com.daniel.algorithms;

public class IntegerToString {
	
	public String intToString(int num, int base){
		boolean negative = false;
		char[] str = new char[10];
		if(num < 0){
			negative = true;
			num = -num;
		}
		int index = 0;
		while (num != 0){
			int rem = num % base;
			str[index++] = (char)((rem > 9) ? ((rem-10) + 'a') : (rem + '0'));
			num = num/base;
		}
		if(negative){
			str[index++] = '-';
		}
		// int length == index
		reverseArray(str);
		
		return new String(str).substring(0, index);
	}
	
	public int stringToInt(String str){
		int index = 0;
		int num = 0;
        boolean negative = false;
        int len = str.length();
        
        if( str.charAt(0) == '-' ){
            negative = true;
            index = 1;
        }
        while(index < len){
            num *= 10;
            num += ( str.charAt(index++) - '0');
        }
        if(negative){
        	num = -num;
        }
        return num;
	}
	
	public char[] reverseArray(char[] a){
		int left = 0;
		int right = a.length-1;
		while(left != right){
			char t = a[left];
			a[left] = a[right];
			a[right] = t;
			left++;
			right--;
		}
		return a;
	}
}
