import java.util.*;
import java.io.*;
class Main {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		LinearProb lp = new LinearProb();
    	String str = sc.nextLine();
    	int k = 0;
    	for(int i = 0;i<str.length();i++) {
    		String op = "";
    		while(str.charAt(k)!=','&& k!=str.length()-1) {
    			op = op + str.charAt(k);
    			k++;
    		}
    		if(str.charAt(k-1)!='?') {
    			op = op + str.charAt(k);
    		}
    		lp.put(op);
    		if(k == str.length()-1) {
    			break;
    		}
    		k++;
		}
		lp.print();
	}
}
import java.util.*;
class LinearProb {
	String[] array;
	int size;
	String[] similarArray;
	Hash h;
	public LinearProb() {
		size = 10;
		h = new Hash();
		array = new String[size];
		similarArray = new String[size];
	}
	public void put(String key) {
		int hash = getHash(key);
		//System.out.println(hash);
		if(array[hash]!=null) {
			for(int i =0;i<array.length;i++) {
				if(array[i]!=null) {
					if (array[i].equals(key)) {
						similarArray[hash] = key;	
					}
				}			
			}
		}
		else {
			array[hash] = key;
		}
	}
	public int getHash(String key) {
		int hash;
		int a = 0;
		for (int i = 0;i<key.length();i++) {
			a = a + (int)key.charAt(i); 
		}
		hash = a%size;
		return hash;
	}
	public void print() {
		for (int i = similarArray.length-1;i >= 0;i--) {
				if(similarArray[i]!=null) 
					System.out.print(similarArray[i]);
			}
	}
}
