package sample.twoDimensionalGrainGrowth;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.Getter;

@Getter
public class Board {

	private static Board instance;
	private GridPane cellsGrid;
	private TextField xSizeField;
	private TextField ySizeField;
	private TextField changeableFirstField;
	private TextField changeableSecondField;
	private Button oneStepButton;
	private Button setInitialsButton;
	private ComboBox<String> boundaryConditionComboBox;
	private ComboBox<String> nucleationComboBox;
	private ComboBox<String> neighbourComboBox;
	private Label changeableFirstLabel;
	private Label changeableSecondLabel;
	private GridPane stageGrid;
	private final int BOARD_SIZE = 700;
	private Button fiveStepsButton;
	private Button monteCarloSimulationButton;
	private TextField ktField;
	private TextField mcIterationsField;

	private Board(){
		this.stageGrid=initBoard();
	}

	private GridPane initBoard(){

		this.cellsGrid = new GridPane();
		this.stageGrid = new GridPane();
		GridPane caMenuGrid = new GridPane();
		GridPane mcMenuGrid = new GridPane();
		GridPane menuGrid = new GridPane();

		//labels
		Label xSizeLabel = new Label("X:  ");
		Label ySizeLabel = new Label("Y: ");
		Label boundaryConditionLabel = new Label("Boundary Condition: ");
		Label nucleationLabel = new Label("Nucleation: ");
		Label neighbourLabel = new Label("Neighbourhood: ");
		Label mcIterationsLabel = new Label("Number of iterations: ");
		Label ktLabel = new Label("kt: (0.1-6) ");
		this.changeableFirstLabel = new Label("~Amount in column: ");
		this.changeableSecondLabel = new Label("~Amount in row: ");

		//input
		this.xSizeField = new TextField("50");
		this.ySizeField = new TextField("50");
		this.changeableFirstField = new TextField("3");
		this.changeableSecondField = new TextField("3");
		this.ktField = new TextField("0.1");
		this.mcIterationsField = new TextField("5");
		this.changeableSecondField = new TextField("3");
		this.boundaryConditionComboBox = new ComboBox<>();
		this.nucleationComboBox = new ComboBox<>();
		this.neighbourComboBox = new ComboBox<>();
		this.boundaryConditionComboBox.getItems().addAll("Periodic","Absorbing");
		this.nucleationComboBox.getItems().addAll("Homogeneous","With Radius","Random","Own");
		this.neighbourComboBox.getItems().addAll("Von Neumann","Pentagonal","Hexagonal - Random","Hexagonal - Left","Hexagonal - Right","Moore");
		this.boundaryConditionComboBox.setValue("Periodic");
		this.nucleationComboBox.setValue("Homogeneous");
		this.neighbourComboBox.setValue("Von Neumann");

		//button
		this.oneStepButton = new Button("ONE STEP");
		this.setInitialsButton = new Button("SET INITIALS");
		this.fiveStepsButton = new Button("FIVE STEPS");
		this.monteCarloSimulationButton = new Button("START MONTE \nCARLO ALGORITHM");

		//settings
		this.boundaryConditionComboBox.setMaxSize(150,10);
		this.nucleationComboBox.setMaxSize(150,10);
		this.neighbourComboBox.setMaxSize(150,10);
		this.cellsGrid.setPadding(new Insets(10));
		this.oneStepButton.setMaxSize(100,10);
		this.fiveStepsButton.setMaxSize(100,10);
		caMenuGrid.setPadding(new Insets(10));
		caMenuGrid.setHgap(10);
		caMenuGrid.setVgap(10);
		mcMenuGrid.setPadding(new Insets(10));
		mcMenuGrid.setHgap(10);
		mcMenuGrid.setVgap(10);

		caMenuGrid.addColumn(0,xSizeLabel,ySizeLabel,boundaryConditionLabel,neighbourLabel,nucleationLabel,this.changeableFirstLabel,this.changeableSecondLabel,this.setInitialsButton);
		caMenuGrid.addColumn(1,this.xSizeField,this.ySizeField,this.boundaryConditionComboBox,this.neighbourComboBox,this.nucleationComboBox,changeableFirstField,changeableSecondField,this.oneStepButton,this.fiveStepsButton);
		mcMenuGrid.addColumn(0,ktLabel,mcIterationsLabel);
		mcMenuGrid.addColumn(1,this.ktField,this.mcIterationsField,this.monteCarloSimulationButton);
		menuGrid.addRow(0,caMenuGrid);
		menuGrid.addRow(1,mcMenuGrid);
		this.stageGrid.add(menuGrid,0,0);
		ScrollPane scrollPane = new ScrollPane(this.cellsGrid);
		this.stageGrid.add(scrollPane,1,0);

		neighbourLabel.setId("Neighbourhood");
		this.changeableFirstLabel.setId("changeableFirstLabel");
		this.changeableSecondLabel.setId("changeableSecondLabel");
		this.changeableFirstField.setId("changeableFirstField");
		this.changeableSecondField.setId("changeableSecondField");
		this.nucleationComboBox.setId("nucleationComboBox");
		this.neighbourComboBox.setId("neighbourhoodComboBox");
		this.ktField.setId("ktField");
		this.mcIterationsField.setId("mcIterationsField");
		this.xSizeField.setId("xSizeField");
		this.ySizeField.setId("ySizeField");

		return this.stageGrid;
	}
	public static Board getInstance(){
		if(instance==null) instance = new Board();
		return instance;
	}
}
