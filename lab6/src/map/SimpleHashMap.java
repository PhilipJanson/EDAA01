package map;

import java.util.Random;

public class SimpleHashMap<K, V> implements Map<K, V> {

	private Entry<K, V>[] table;
	private float loadFactor;
	private float currLoadFactor;
	private int size;

	public SimpleHashMap() {
		this(16);
	}

	public SimpleHashMap(int capacity) {
		table = (Entry<K, V>[]) new Entry[capacity];
		loadFactor = 0.75F;
		size = 0;
	}

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<Integer, Integer>(10);
		Random rand = new Random();
		
		for (int i = 0; i < 20; i++) {
			int r = rand.nextInt(200) - 100;
			
			map.put(r, r);
		}
		
		System.out.println(map.show());
		
		for (int i = 0; i < 20; i++) {
			int r = rand.nextInt(200) - 100;
			
			map.put(r, r);
		}
		
		System.out.println(map.show());
	}

	@Override
	public V get(Object obj) {
		Entry<K, V> entry = find(index((K) obj), (K) obj);

		if (entry != null) {
			return entry.getValue();
		}

		return null;
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);
		Entry<K, V> entry = find(index, key);

		if (entry != null) {
			return entry.setValue(value);
		} else {
			if (table[index] == null) {
				table[index] = new Entry<K, V>(key, value);
			} else {
				entry = table[index];

				while (entry.hasNext()) {
					entry = entry.getNext();
				}

				entry.setNext(new Entry<K, V>(key, value));
			}

			size++;
			currLoadFactor = (float) size / (float) table.length;

			if (currLoadFactor > loadFactor) {
				rehash();
			}
		}

		return null;
	}

	private void rehash() {
		size = 0;
		Entry<K, V>[] oldTable = table;
		table = (Entry<K, V>[]) new Entry[oldTable.length * 2];

		for (Entry<K, V> entry : oldTable) {
			if (entry != null) {
				while (entry.getNext() != null) {
					put(entry.getKey(), entry.getValue());
					entry = entry.getNext();
				}

				put(entry.getKey(), entry.getValue());
			}
		}

		currLoadFactor = (float) size / (float) table.length;
	}

	@Override
	public V remove(Object obj) {
		if (!isEmpty()) {
			int index = index((K) obj);
			Entry<K, V> entry = find(index, (K) obj);

			if (entry != null) {
				V value = entry.getValue();

				if (testKeys(entry.getKey(), table[index].getKey())) {
					if (entry.hasNext()) {
						table[index] = entry.getNext();
						size--;

						return value;
					} else {
						table[index] = null;
						size--;

						return value;
					}
				}

				Entry<K, V> temp = table[index];

				while (temp.hasNext()) {
					if (testKeys(temp.getNext().getKey(), entry.getKey())) {
						temp.setNext(entry.getNext());
						size--;

						return value;
					} else {
						temp = temp.getNext();
					}
				}
			}
		}

		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public String show() {
		String s = "";

		for (int i = 0; i < table.length; i++) {
			Entry<K, V> entry = table[i];

			s += i + ": ";
			
			while (entry != null) {
				s += entry + (entry.getNext() == null ? "\n" : " ");
				entry = entry.getNext();
			}
		}

		return s;
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> entry = table[index];

		while (entry != null) {
			if (testKeys(entry.getKey(), key)) {
				return entry;
			}

			entry = entry.getNext();
		}

		return null;
	}

	private boolean testKeys(K k1, K k2) {
		return k1.hashCode() == k2.hashCode() && k1.equals(k2);
	}

	private int index(K key) {
		return key.hashCode() & (table.length - 1);
	}

	public float getCurrentLoadFactor() {
		return currLoadFactor;
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {

		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K k, V v) {
			key = k;
			value = v;
			next = null;
		}

		public void setNext(Entry<K, V> entry) {
			next = entry;
		}

		public Entry<K, V> getNext() {
			return next;
		}

		public boolean hasNext() {
			return next != null;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V v) {
			V temp = value;
			value = v;
			return temp;
		}

		@Override
		public String toString() {
			return key + "=" + value;
		}
	}
}
