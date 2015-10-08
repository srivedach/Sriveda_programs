import java.util.*;
import java.io.*;
class Main {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);
		HashTable ht = new HashTable();
		BufferedReader in = new BufferedReader(new FileReader("people.txt"));
    	String str;
		while((str = in.readLine()) != null){
			String[] res = str.split(" ");
			for (int i = 0;i < res.length;i++) {
			//System.out.println(res[i]);
				if(!ht.containsKey(res[i])) {
					ht.put(res[i],1);
				}		
				else {
				int count = ht.get(res[i])+1;
				ht.put(res[i],count);
				}
			}
		}
		ht.insertIntoHeap();
	}
}
class HashTable {
	Hash[] array;
	int size;
	MaxHeap w;
	public HashTable() {
		size = 100;
		array = new Hash[size];
		w = new MaxHeap();
	}
	public void put(String key,int value) {
		int hash = getHash(key);
		//System.out.println(hash);
		while(array[hash]!=null && (!array[hash].getKey().equals(key))) {
			hash = (hash + 1) % size;
		}
		array[hash] = new Hash(key,value);
		//System.out.println(array[hash].getValue());
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
	public int get(String key) {
		int hash = getHash(key);
			while(array[hash]!=null && (!array[hash].getKey().equals(key)))
				hash = (hash + 1) % size;
				if(array[hash] == null) {
					return -1;
				}
				else {
					return array[hash].getValue();
				}
	}
	public boolean containsKey(String key) {
		int hash = getHash(key);
		if(array[hash] == null) {
			return false;
		}
		else if(array[hash].getKey().equals(key)) {
			return true;
		}
		else {
			return false;
		}
	}
	public void insertIntoHeap() {
		//System.out.println("hi");
		for (int i = 0;i<size;i++) {
			//System.out.println(array[i].getValue());
			if(array[i]!=null) 
			w.insert(array[i]);
		}
		w.display();
	}
}
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
import java.util.*;
class MaxHeap {
    Hash[] Heap ;
    int size;
    int current;
    int FRONT;
    int i;
    public MaxHeap() {
        FRONT =0;
        current=0;
        size =1;
        i = 0;
        Heap = new Hash[size];
    }
    public int parent(int position) {
        return (position-1)/2;
    }
    public int left(int position) {
        return position*2+1;
    }
    public int right(int position) {
        return (position*2)+2;
    }
    public void swap(int fpos,int spos) {
        Hash temp;
        temp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = temp;
    }
    public void insert(Hash element) {
        if(size==1) {
            Heap[i] = element;
            size++;
            Heap = Arrays.copyOf(Heap,size);
            i++;
        }
        else {
            Heap[i] = element;
            current = i;
            size++;
            Heap = Arrays.copyOf(Heap,size);
            while((Heap[current]).getValue()>(Heap[parent(current)]).getValue()) {
                swap(current,parent(current));
                current = parent(current);
            }
            i++;
        }   
    }
    public void heapify(int position) {
         int left, right, min;  
         Hash tmp;
        left = left(position);  
        right = right(position);   
        if(left < (size-1) && (Heap[left].getValue()>(Heap[position]).getValue()))
                min = left;         
        else
            min = position;

        if(right < (size-1) && Heap[right].getValue() > Heap[min].getValue())
                min = right; 

        if(min != position) {
            tmp = Heap[position]; 
            Heap[position] = Heap[min];
            Heap[min] = tmp;
            heapify(min); 
        }
    }
    public Hash remove() {
        Hash popped = Heap[FRONT];
        Hash temp = Heap[size-2]; 
         Heap[FRONT] = temp;
         size--;
        Heap = Arrays.copyOf(Heap,size);
        heapify(FRONT);
        return popped;
    }
    public void display() {
        for (int i = 0; i <Heap.length; i++) {
                Hash h = remove();
                System.out.println(h.getKey()+"---------->"+h.getValue());
        }
        Hash h = remove();
        System.out.println(h.getKey()+"--------->"+h.getValue());
    }
}
