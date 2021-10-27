package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {

	private Map<String, Integer> map;

	public MultiWordCounter(String[] words) {
		map = new HashMap<String, Integer>();

		for (String s : words) {
			map.put(s, 0);
		}
	}

	@Override
	public void process(String w) {
		if (map.containsKey(w)) {
			map.replace(w, map.get(w) + 1);
		}
	}

	@Override
	public void report() {
		for (String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key));
		}
	}
}
