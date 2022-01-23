package Testing;

import static org.junit.jupiter.api.Assertions.*;

class Test {

	@org.junit.jupiter.api.Test
	void test() {
		JUnitTesting test = new JUnitTesting();
		int output = test.square(5);
		assertEquals(25, output);
	}

}
