package sample.oneDimensionalSimulation;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class Controller2d1DTest extends ApplicationTest {


	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("Cellular automaton");
		Parent sceneRoot = Board.getInstance().initBoard();
		primaryStage.setScene(new Scene(sceneRoot, 900, 500));
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	@Test
	void ruleConverterTest() {

		assertAll(
				() -> Assertions.assertEquals(Cell.DEAD, Controller1d.ruleConverter('0')),
				() -> assertEquals(Cell.ALIVE, Controller1d.ruleConverter('1')),
				() -> assertThrows(IllegalArgumentException.class, () -> Controller1d.ruleConverter('2'))
		);
	}

	@Test
	void iterationsTextFieldTest() {
		assertAll(
				() -> FxAssert.verifyThat("#iterationsField", (TextField textfield) -> {
					String value = textfield.getText();
					return Pattern.matches("^\\d*$", value);
				}),
				() -> FxAssert.verifyThat("#iterationsField", (TextField textfield) -> {
					String value = textfield.getText();
					return !value.equals("");
				}));
	}

	@Test
	void cellsNumberFieldTest() {
		assertAll(
				() -> FxAssert.verifyThat("#iterationsField", (TextField textfield) -> {
					String value = textfield.getText();
					return Pattern.matches("^\\d*$", value);
				}),
				() -> FxAssert.verifyThat("#cellsNumberField", (TextField textfield) -> {
					String value = textfield.getText();
					return !value.equals("");
				}));
	}

	@Test
	void runButtonTest() {
		clickOn("RUN");
		FxAssert.verifyThat("#cellsGrid", NodeMatchers.isNotNull());
	}

	@Test
	void setSizeButtonTest() {
		clickOn("SET SIZE");
		FxAssert.verifyThat("#cellsGrid", NodeMatchers.isNotNull());
	}


}
