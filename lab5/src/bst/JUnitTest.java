package bst;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitTest {

	private BinarySearchTree<Integer> bstInt;
	private BinarySearchTree<Person> bstPerson;

	@BeforeEach
	public void setUp() {
		bstInt = new BinarySearchTree<Integer>();
		bstPerson = new BinarySearchTree<Person>((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
	}
	
	@AfterEach
	void tearDown(){
		bstInt = null;
		bstPerson = null;
	}

	@Test
	void testHeight() {
		assertTrue(bstInt.add(5));
		assertTrue(bstInt.add(10));
		assertTrue(bstInt.add(11));
		assertTrue(bstInt.add(13));
		assertTrue(bstInt.add(6));
		assertTrue(bstInt.add(7));

		assertTrue(bstInt.height() == 4);

		assertTrue(bstPerson.add(new Person("F")));
		assertTrue(bstPerson.add(new Person("A")));
		assertTrue(bstPerson.add(new Person("C")));
		assertTrue(bstPerson.add(new Person("B")));

		assertTrue(bstPerson.height() == 4);
	}

	@Test
	void testHeightWithClear() {
		assertTrue(bstInt.height() == 0);

		bstInt.add(2);
		bstInt.add(8);
		bstInt.add(9);
		bstInt.add(10);
		bstInt.clear();

		assertTrue(bstInt.height() == 0);
	}

	@Test
	void testSize() {
		assertTrue(bstInt.add(3));
		assertTrue(bstInt.add(2));
		assertTrue(bstInt.add(1));
		assertTrue(bstInt.add(4));
		assertTrue(bstInt.size() == 4);
		
		assertTrue(bstInt.size() == 4);

		assertTrue(bstPerson.add(new Person("A")));
		assertTrue(bstPerson.add(new Person("B")));
		assertTrue(bstPerson.add(new Person("C")));
		assertTrue(bstPerson.add(new Person("D")));
		assertTrue(bstPerson.add(new Person("E")));
		assertTrue(bstPerson.add(new Person("F")));

		assertTrue(bstPerson.size() == 6);
	}
	
	@Test
	void testSizeWithClear() {
		assertTrue(bstInt.size() == 0);

		bstInt.add(6);
		bstInt.add(5);
		bstInt.add(12);
		bstInt.add(14);
		bstInt.clear();

		assertTrue(bstInt.size() == 0);
	}

	@Test
	void testAdd() {
		assertTrue(bstInt.add(5));
		assertTrue(bstInt.add(10));
		assertTrue(bstInt.add(16));
		assertTrue(bstInt.add(8));
		assertTrue(bstInt.add(2));
		assertTrue(bstInt.root.element == 5);
		assertTrue(bstInt.root.right.element == 10);
		assertTrue(bstInt.root.right.right.element == 16);
		assertTrue(bstInt.root.right.left.element == 8);
		assertTrue(bstInt.root.left.element == 2);
		
		assertTrue(bstPerson.add(new Person("E")));
		assertTrue(bstPerson.add(new Person("B")));
		assertTrue(bstPerson.add(new Person("C")));
		assertTrue(bstPerson.add(new Person("A")));
		assertTrue(bstPerson.add(new Person("K")));
		assertTrue(bstPerson.add(new Person("W")));
		
		assertTrue(bstPerson.root.element.getName().equals("E"));
		assertTrue(bstPerson.root.left.element.getName().equals("B"));
		assertTrue(bstPerson.root.left.right.element.getName().equals("C"));
		assertTrue(bstPerson.root.left.left.element.getName().equals("A"));
		assertTrue(bstPerson.root.right.element.getName().equals("K"));
		assertTrue(bstPerson.root.right.right.element.getName().equals("W"));
	}

	@Test
	void testDuplicates() {
		assertTrue(bstInt.add(1));
		assertFalse(bstInt.add(1));
		
		assertTrue(bstPerson.add(new Person("A")));
		assertFalse(bstPerson.add(new Person("A")));
	}
	
	@Test
	void testPrintTree() {
		bstInt.add(5);
		bstInt.add(10);
		bstInt.add(16);
		bstInt.add(8);
		bstInt.add(2);
		bstPerson.add(new Person("E"));
		bstPerson.add(new Person("B"));
		bstPerson.add(new Person("C"));
		bstPerson.add(new Person("A"));
		bstPerson.add(new Person("K"));
		bstPerson.add(new Person("W"));
		
		System.out.println("Int Tree:");
		bstInt.printTree();
		System.out.println("\n\nPerson Tree:");
		bstPerson.printTree();
	}
}
