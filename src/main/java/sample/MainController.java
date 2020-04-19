package sample;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lombok.Getter;
import sample.oneDimensionalSimulation.Controller1d;
import sample.twoDimensionalGrainGrowth.Controller2d;

@Getter
class MainController {
	private TabPane tabPane;
	private Tab tab1D;
	private Tab tab2D;

	MainController() {
		this.tabPane = new TabPane();
		this.tab1D = new Tab("Simulation 1D");
		this.tab2D = new Tab("Grain Growth 2D");
		this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		this.tab1D.setContent(new Controller1d().getStageGrid());
		this.tab2D.setContent(new Controller2d().getStageGrid());
		this.tabPane.getTabs().setAll(this.tab1D,this.tab2D);
	}
}
