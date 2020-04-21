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

	private Board(){
		this.stageGrid=initBoard();
	}

	private GridPane initBoard(){

		this.cellsGrid = new GridPane();
		this.stageGrid = new GridPane();
		GridPane menuGrid = new GridPane();

		//labels
		Label xSizeLabel = new Label("X:  ");
		Label ySizeLabel = new Label("Y: ");
		Label boundaryConditionLabel = new Label("Boundary Condition: ");
		Label nucleationLabel = new Label("Nucleation: ");
		Label neighbourLabel = new Label("Neighbour: ");
		this.changeableFirstLabel = new Label("Amount in column: ");
		this.changeableSecondLabel = new Label("Amount in row: ");

		//input
		this.xSizeField = new TextField("50");
		this.ySizeField = new TextField("50");
		this.changeableFirstField = new TextField("3");
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

		//settings
		this.boundaryConditionComboBox.setMaxSize(150,10);
		this.nucleationComboBox.setMaxSize(150,10);
		this.neighbourComboBox.setMaxSize(150,10);
		this.cellsGrid.setPadding(new Insets(10));
		this.oneStepButton.setMaxSize(100,10);
		this.fiveStepsButton.setMaxSize(100,10);
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		menuGrid.setVgap(10);

		menuGrid.addColumn(0,xSizeLabel,ySizeLabel,boundaryConditionLabel,neighbourLabel,nucleationLabel,this.changeableFirstLabel,this.changeableSecondLabel,this.setInitialsButton);
		menuGrid.addColumn(1,this.xSizeField,this.ySizeField,this.boundaryConditionComboBox,this.neighbourComboBox,this.nucleationComboBox,changeableFirstField,changeableSecondField,this.oneStepButton,this.fiveStepsButton);

		this.stageGrid.add(menuGrid,0,0);
		ScrollPane scrollPane = new ScrollPane(this.cellsGrid);
		this.stageGrid.add(scrollPane,1,0);

		return this.stageGrid;
	}
	public static Board getInstance(){
		if(instance==null) instance = new Board();
		return instance;
	}
}
