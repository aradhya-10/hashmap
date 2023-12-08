import java.util.LinkedList;

class MyMapNode<K, V> {
    K key;
    V value;

    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class MyHashTable<K, V> {
    private LinkedList<MyMapNode<K, V>>[] buckets;
    private int capacity;

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        int index = getHash(key);
        LinkedList<MyMapNode<K, V>> bucket = buckets[index];

        for (MyMapNode<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                node.value = value; // Update value if key already exists
                return;
            }
        }

        bucket.add(new MyMapNode<>(key, value));
    }
    
	public void remove(K key) {
        int index = getHash(key);
        LinkedList<MyMapNode<K, V>> bucket = buckets[index];

        for (MyMapNode<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                bucket.remove(node);
                return;
            }
        }

		System.out.println("Word not present.");
    }

    public V get(K key) {
        int index = getHash(key);
        LinkedList<MyMapNode<K, V>> bucket = buckets[index];

        for (MyMapNode<K, V> node : bucket) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null; // Key not found
    }

	public void printMap(){
        System.out.println("Word frequency in sentence:");
		for (int i = 0; i < capacity; i++) {
			for (MyMapNode<K, V> node : buckets[i]) {
				System.out.print(node.getKey()+": ");
				System.out.println(node.getValue());
			}
		}
	}
}

public class HashMap {

	public static void getFrequencies(String sentence){
		String[] words = sentence.split(" ");
		
        MyHashTable<String, Integer> wordFrequency = new MyHashTable<>(10);
		
		System.out.println("Input: "+ sentence);
        for (String word : words) {
			word = word.toLowerCase();
            Integer frequency = wordFrequency.get(word);
            if (frequency == null) {
				wordFrequency.put(word, 1);
            } else {
				wordFrequency.put(word, frequency + 1);
            }
        }
		
		wordFrequency.printMap();
	}
	
	public static String removeWord(String sentence, String wordToRemove){
		System.out.println("\nParagraph after removing '" + wordToRemove + "':");
		String[] words = sentence.split(" ");
		String newSentence = "";

        for (String word : words) {
            if (!word.equals(wordToRemove)) {
                newSentence = newSentence + word + " ";
            }
        }
		return newSentence;
	}
    public static void main(String[] args) {
        // String sentence = "To be or not to be";
		String sentence = "Paranoids are not paranoid because they are paranoid but because they keep putting themselves deliberately into paranoid avoidable situations";

        getFrequencies(sentence);
		sentence = removeWord(sentence, "avoidable");
        getFrequencies(sentence);
    }
}