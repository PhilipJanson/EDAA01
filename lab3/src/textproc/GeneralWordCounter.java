package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {

	private Map<String, Integer> map;
	private Set<String> set;

	public GeneralWordCounter(Set<String> words) {
		set = words;
		map = new HashMap<String, Integer>();
	}

	@Override
	public void process(String w) {
		if (set.contains(w)) {
			return;
		}

		if (map.containsKey(w)) {
			map.replace(w, map.get(w) + 1);
		} else {
			map.put(w, 1);
		}
	}

	@Override
	public void report() {
	}

	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
	}
}
