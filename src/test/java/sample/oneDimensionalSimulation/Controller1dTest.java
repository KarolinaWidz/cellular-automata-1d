package sample.oneDimensionalSimulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.loadui.testfx.controls.TextInputControls.clearTextIn;
import static org.testfx.api.FxAssert.verifyThat;

class Controller1dTest extends sample.TestFXBase {

	@Test
	void ruleConverterTest() {
		assertAll(
				() -> Assertions.assertEquals(Cell.DEAD, Controller1d.ruleConverter('0')),
				() -> assertEquals(Cell.ALIVE, Controller1d.ruleConverter('1')),
				() -> assertThrows(IllegalArgumentException.class, () -> Controller1d.ruleConverter('2'))
		);
	}

	@Test
	void iterationsFieldTest() {
		assertAll(
				()->{
					clickOn("#iterationsField");
					clearTextIn("#iterationsField");
					clickOn("RUN");
					assertEquals(listWindows().size(),2);
					clickOn("OK");
				},
				()->{
					clickOn("#iterationsField");
					clearTextIn("#iterationsField");
					write("0");
					clickOn("RUN");
					assertEquals(listWindows().size(),2);
					clickOn("OK");
				},
				()->{
					clickOn("#iterationsField");
					clearTextIn("#iterationsField");
					write("0.2");
					clickOn("RUN");
					assertEquals(listWindows().size(),2);
					clickOn("OK");
				},
				()->{
					clickOn("#iterationsField");
					write("TEST");
					clickOn("RUN");
					assertEquals(listWindows().size(),2);
					clickOn("OK");
				}
		);
		clickOn("#iterationsField");
		clearTextIn("#iterationsField");
		write("11");
	}

	@Test
	void cellsFieldTest() {
		assertAll(
				()->{
					clickOn("#cellsNumberField");
					clearTextIn("#cellsNumberField");
					clickOn("RUN");
					assertEquals(listWindows().size(),2);
					clickOn("OK");
				},
				()->{
					clickOn("#cellsNumberField");
					clearTextIn("#cellsNumberField");
					write("0");
					clickOn("RUN");
					assertEquals(listWindows().size(),2);
					clickOn("OK");
				},
				()->{
					clickOn("#cellsNumberField");
					clearTextIn("#cellsNumberField");
					write("0.2");
					clickOn("RUN");
					assertEquals(listWindows().size(),2);
					clickOn("OK");
				},
				()->{
					clickOn("#cellsNumberField");
					write("TEST");
					clickOn("RUN");
					assertEquals(listWindows().size(),2);
					clickOn("OK");
				}
		);
		clickOn("#cellsNumberField");
		clearTextIn("#cellsNumberField");
		write("11");

	}

	@Test
	void runButtonTest() {
		clickOn("RUN");
		verifyThat("#cellsGrid", NodeMatchers.isNotNull());
	}
}
