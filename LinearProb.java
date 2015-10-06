class Hash {
	String key;
	int value;
	public Hash(String key,int value) {
		this.key = key;
		this.value = value;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey() {
		return key;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
class LinearProb {
	int[] array;
	int size;
	public LinearProb() {
		size = 10;
		array = new int[size];
	}
	public void put(int key,int value) {
		int hash = key%size;
		//System.out.println(hash);
		while(array[hash]!=0 && (hash!=key)) {
			hash = (hash + 1) % size;
		}
		array[hash] = value;
		//System.out.println(array[hash].getValue());
	}
	public int get(int key) {
		int hash = key%size;
			while(array[hash]!=0 && (hash!=key))
				hash = (hash + 1) % size;
				if(array[hash] == 0) {
					return -1;
				}
				else {
					return array[hash];
				}
	}
	public void remove(int value) {
		int j = 0;
		int k = 0;
		int temp = 0;
		int i = 0;
		for(i = 0;i<array.length;i++) {
			if(array[i]!=0) {
				if(array[i] == value) {
					array[i] = 0;
					break;
				}
			}
		}
		k = i+1;
		j = array[i]%size;
		while(array[k] != 0) {
			if(between(i,j,k)) {
				array[i] = array[k];
				array[k] = 0;
				i = k;
			}
			if(k == size-1) 
				k = (k+1)%size;
			else
				k++;
			if(array[k]!=0) {
				j = array[k]%size;
			}
		}

	}
	public boolean between(int i,int j,int k) {
		while(j%size!=k) {
			if(i == j%size) {
				return true;
			}
			j++;
		}
		return false;
	}
	public void print() {
		for (int i = 0;i<array.length;i++) {
			if(array[i]!=0) 
				System.out.println(i+" "+array[i]);
		}
	}
}
import java.util.*;
import java.io.*;
class Main {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		LinearProb lp = new LinearProb();
    	int i = 0;
    	do {
    		int ch = Integer.parseInt(sc.nextLine());
			switch(ch) {
				case 1:
				//String k = sc.nextLine();
				int v = Integer.parseInt(sc.nextLine());
				lp.put(v,v);
				break;
				case 2:
				 v = Integer.parseInt(sc.nextLine());
				lp.remove(v);
				break;
				case 3:
				lp.print();
				System.exit(0);
			}
		}while(true);
	}
}
