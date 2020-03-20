package sample;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ControllerTest {

	@Test
	void ruleConverterTest() {
		assertAll(
			()->assertEquals(Cell.DEAD, Controller.ruleConverter('0')),
			()->assertEquals(Cell.ALIVE, Controller.ruleConverter('1'))
		);
	}
}
