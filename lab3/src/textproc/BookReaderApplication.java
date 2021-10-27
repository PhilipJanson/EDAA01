package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("undantagsord.txt"));
		GeneralWordCounter wordCounter = new GeneralWordCounter(generateSet(s));
		
		s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			wordCounter.process(word);
		}

		s.close();
		
		new BookReaderController(wordCounter);
	}

	private static Set<String> generateSet(Scanner s) {
		Set<String> stopwords = new HashSet<String>();

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			stopwords.add(word);
		}

		return stopwords;
	}
}