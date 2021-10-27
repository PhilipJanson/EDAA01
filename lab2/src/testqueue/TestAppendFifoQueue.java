package testqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {

	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@BeforeEach
	void setUp() {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() {
		q1 = null;
		q2 = null;
	}

	@Test
	void testIndentical() {
		q1.add(1);
		q1.add(2);
		assertThrows(IllegalArgumentException.class, () -> q1.append(q1));
	}

	@Test
	void testTwoEmpty() {
		q1.append(q2);
		assertEquals(null, q1.poll(), "Poll of empty queue should return null");
		assertEquals(null, q2.poll(), "Poll of empty queue should return null");
	}
	
	@Test
	void testOneEmptyOneFull() {
		q2.add(1);
		q2.add(2);
		q2.add(3);
		q1.append(q2);
		
		assertEquals(3, q1.size(), "Size not equal to q2 original size");
		assertEquals(1, q1.poll(), "Integer 1 expected");
		assertEquals(2, q1.poll(), "Integer 2 expected");
		assertEquals(3, q1.poll(), "Integer 3 expected");
		assertEquals(null, q2.poll(), "Queue not empty");
	}
	
	@Test
	void testFullEmptyOneEmpty() {
		q1.add(1);
		q1.add(2);
		q1.add(3);
		q1.append(q2);
		
		assertEquals(3, q1.size(), "Size not equal to q1 original size");
		assertEquals(1, q1.poll(), "Integer 1 expected");
		assertEquals(2, q1.poll(), "Integer 2 expected");
		assertEquals(3, q1.poll(), "Integer 3 expected");
	}
	
	@Test
	void testTwoFull() {
		q1.add(1);
		q1.add(2);
		q1.add(3);
		
		q2.add(4);
		q2.add(5);
		q2.add(6);
		
		q1.append(q2);
		
		assertEquals(6, q1.size(), "Size not equal to q1 + q2");
		assertEquals(1, q1.poll(), "Integer 1 expected");
		assertEquals(2, q1.poll(), "Integer 2 expected");
		assertEquals(3, q1.poll(), "Integer 3 expected");
		assertEquals(4, q1.poll(), "Integer 4 expected");
		assertEquals(5, q1.poll(), "Integer 5 expected");
		assertEquals(6, q1.poll(), "Integer 6 expected");
		assertEquals(null, q2.poll(), "Queue not empty");
	}
}
