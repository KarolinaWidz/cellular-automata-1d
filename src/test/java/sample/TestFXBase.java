package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.TimeoutException;

@ExtendWith(ApplicationExtension.class)
public class TestFXBase extends ApplicationTest  {

	@BeforeEach
	void setupClass() throws Exception{
		ApplicationTest.launch(sample.Main.class);
	}

	@AfterEach
	void afterTesting() throws TimeoutException {
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}

}