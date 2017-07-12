/******************************************************************************
 * HashMapSC.java
 * Written by Robert Sedgewick and Kevin Wayne
 *****************************************************************************/


public class HashMapSC<Key, Value> {
	private static final int INITIAL_CAPACTIY = 4;

	private int n;
	private int m;
	private SequentialSearchST<Key, Value>[] st;


	public HashMapSC() {
		this(INITIAL_CAPACTIY);
	}


	public HashMapSC(int capacity) {
		m = capacity;
		n = 0;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}


	private void resize(int chains) {
		HashMapSC<Key, Value> temp = new HashMapSC<Key, Value>(chains);
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys())
				temp.put(key, st[i].get(key));
		}
		this.m  = temp.m;
		this.n  = temp.n;
		this.st = temp.st;
	}


	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}


	public int size() {
		return n;
	}


	public boolean isEmpty() {
		return size() == 0;
	}


	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}


	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");

		int i = hash(key);
		return st[i].get(key);
	}


	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		if (val == null) {
			delete(key);
			return;
		}

		if (n >= 10*m) resize(2*m);

		int i = hash(key);
		if (!st[i].contains(key)) n++;
		st[i].put(key, val);
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");

		int i = hash(key);
		if (st[i].contains(key)) n--;
		st[i].delete(key);

		if (m > INITIAL_CAPACTIY && n <= 2 * m) resize(m/2);
	}


	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	}
}