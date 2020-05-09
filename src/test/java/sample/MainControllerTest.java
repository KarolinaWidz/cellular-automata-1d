package sample;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.loadui.testfx.GuiTest.find;
import static org.testfx.api.FxAssert.verifyThat;


class MainControllerTest extends TestFXBase{

	@Test
	void switchingPanes(){
		assertAll(
				()->{clickOn("#tab2D");
					verifyThat(find("#Neighbourhood"), Matchers.notNullValue());},
				()->{clickOn("#tab1D");
					verifyThat(find("#iterationsField"), Matchers.notNullValue());}
		);

	}

}