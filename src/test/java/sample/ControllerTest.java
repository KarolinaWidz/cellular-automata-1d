package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ControllerTest extends ApplicationTest {


	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("Cellular automata");
		Parent sceneRoot = Board.getInstance().initBoard();
		primaryStage.setScene(new Scene(sceneRoot, 900, 500));
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	@Test
	void ruleConverterTest() {

		assertAll(
				() -> assertEquals(Cell.DEAD, Controller.ruleConverter('0')),
				() -> assertEquals(Cell.ALIVE, Controller.ruleConverter('1')),
				() -> assertThrows(IllegalArgumentException.class, () -> Controller.ruleConverter('2'))
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
