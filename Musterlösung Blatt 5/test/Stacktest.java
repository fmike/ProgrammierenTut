import static org.junit.Assert.*;
import hanoi.DefaultStack;
import hanoi.HanoiDisk;
import hanoi.HanoiStack;

import org.junit.Test;


public class Stacktest {

	@Test
	public void testHanoiStack() {
		DefaultStack<HanoiDisk> hd = new HanoiStack();
		HanoiDisk d1 = new HanoiDisk(1);
		HanoiDisk d2 = new HanoiDisk(2);
		HanoiDisk d3 = new HanoiDisk(3);
		HanoiDisk d4 = new HanoiDisk(4);
		assertTrue(hd.push(d4));
		assertTrue(hd.push(d3));
		assertFalse(hd.push(d4));
		assertTrue(hd.push(d1));
		assertFalse(hd.push(d2));
		assertSame(d1, hd.pop());
		assertEquals(2, hd.size());
		// 4,3
		assertTrue(hd.push(d2));
		assertTrue(hd.push(d1));
		assertEquals(4, hd.size());
		assertSame(d1, hd.pop());
		assertSame(d2, hd.pop());
		assertSame(d3, hd.pop());
		assertTrue(hd.push(d1));
		assertEquals(2, hd.size());
		// 4,1
		assertFalse(hd.push(d2));
		assertFalse(hd.push(d3));
		assertEquals(2,hd.size());
		assertSame(d1, hd.pop());
		assertSame(d4, hd.pop());
		assertEquals(0,hd.size());
		assertTrue(hd.push(d1));
		assertFalse(hd.push(d2));
		assertFalse(hd.push(d3));
		assertFalse(hd.push(d4));
		assertEquals(1, hd.size());
		assertSame(d1, hd.pop());
		assertEquals(0, hd.size());
	}

}
