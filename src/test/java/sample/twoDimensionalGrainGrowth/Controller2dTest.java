package sample.twoDimensionalGrainGrowth;

import javafx.scene.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import sample.TestFXBase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.loadui.testfx.controls.TextInputControls.clearTextIn;
import static org.testfx.api.FxAssert.verifyThat;

class Controller2dTest extends TestFXBase {

	@BeforeEach
	void setSecondPane(){
		clickOn("#tab2D");
	}

	@Test
	void ktFieldTest() {
		assertAll(
				() -> {
					clickOn("#ktField");
					clearTextIn("#ktField");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#ktField");
					clearTextIn("#ktField");
					write("0");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#ktField");
					clearTextIn("#ktField");
					write("7");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#ktField");
					clearTextIn("#ktField");
					write("-1");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#ktField");
					write("TEST");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				}
		);
		clickOn("#ktField");
		clearTextIn("#ktField");
		write("0.1");
	}

	@Test
	void randomWithRadiusFieldsTest(){
		clickOn("#nucleationComboBox");
		clickOn("With Radius");
		assertAll(
				()->verifyThat("#changeableFirstField", NodeMatchers.isVisible()),
				()->verifyThat("#changeableFirstLabel", LabeledMatchers.hasText("~Amount: ")),
				()->verifyThat("#changeableSecondField",NodeMatchers.isVisible()),
				()->verifyThat("#changeableSecondLabel", LabeledMatchers.hasText("Radius: "))
		);
	}

	@Test
	void randomFieldsTest(){
		clickOn("#nucleationComboBox");
		clickOn("Random");
		assertAll(
				()->verifyThat("#changeableFirstField", NodeMatchers.isVisible()),
				()->verifyThat("#changeableFirstLabel", LabeledMatchers.hasText("~Amount: ")),
				()->verifyThat("#changeableSecondField",NodeMatchers.isInvisible()),
				()->verifyThat("#changeableSecondLabel", LabeledMatchers.hasText(""))
		);
	}

	@Test
	void ownStatesFieldsTest(){
		clickOn("#nucleationComboBox");
		clickOn("Own");
		assertAll(
				()->verifyThat("#changeableFirstField", NodeMatchers.isInvisible()),
				()->verifyThat("#changeableFirstLabel", LabeledMatchers.hasText("")),
				()->verifyThat("#changeableSecondField",NodeMatchers.isInvisible()),
		()->verifyThat("#changeableSecondLabel", LabeledMatchers.hasText(""))
		);
	}

	@Test
	void homogeneousNucleationFieldsTest(){
		clickOn("#nucleationComboBox");
		clickOn("Homogeneous");
		assertAll(
				()->verifyThat("#changeableFirstField", Node::isVisible),
				()->verifyThat("#changeableFirstLabel", LabeledMatchers.hasText("~Amount in column: ")),
				()->verifyThat("#changeableSecondField",Node::isVisible),
				()->verifyThat("#changeableSecondLabel", LabeledMatchers.hasText("~Amount in row: "))
		);
	}

	@Test
	void mcIterationsField() {
		assertAll(
				() -> {
					clickOn("#mcIterationsField");
					clearTextIn("#mcIterationsField");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#mcIterationsField");
					clearTextIn("#mcIterationsField");
					write("0");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#mcIterationsField");
					clearTextIn("#mcIterationsField");
					write("0.2");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#mcIterationsField");
					clearTextIn("#mcIterationsField");
					write("-1");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#mcIterationsField");
					write("TEST");
					clickOn("START MONTE \nCARLO ALGORITHM");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				}
		);
		clickOn("#mcIterationsField");
		clearTextIn("#mcIterationsField");
		write("5");
	}

	@Test
	void setInitialFirstFieldTest() {
		clickOn("#nucleationComboBox");
		clickOn("Homogeneous");
		assertAll(
				() -> {
					clickOn("#changeableFirstField");
					clearTextIn("#changeableFirstField");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#changeableFirstField");
					clearTextIn("#changeableFirstField");
					write("0");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#changeableFirstField");
					clearTextIn("#changeableFirstField");
					write("0.2");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#changeableFirstField");
					clearTextIn("#changeableFirstField");
					write("-1");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#changeableFirstField");
					write("TEST");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				}
		);
		clickOn("#changeableFirstField");
		clearTextIn("#changeableFirstField");
		write("3");
	}

	@Test
	void setInitialSecondFieldTest() {
		clickOn("#nucleationComboBox");
		clickOn("Homogeneous");
		assertAll(
				() -> {
					clickOn("#changeableSecondField");
					clearTextIn("#changeableSecondField");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#changeableSecondField");
					clearTextIn("#changeableSecondField");
					write("0");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#changeableSecondField");
					clearTextIn("#changeableSecondField");
					write("0.2");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#changeableSecondField");
					clearTextIn("#changeableSecondField");
					write("-1");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				},
				() -> {
					clickOn("#changeableSecondField");
					write("TEST");
					clickOn("SET INITIALS");
					assertEquals(listWindows().size(), 2);
					clickOn("OK");
				}
		);
		clickOn("#changeableSecondField");
		clearTextIn("#changeableSecondField");
		write("3");
	}







}

