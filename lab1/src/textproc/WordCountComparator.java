package textproc;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		int i = o2.getValue() - o1.getValue();

		return i == 0 ? o1.getKey().compareToIgnoreCase(o2.getKey()) : i;
	}
}
