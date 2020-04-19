package sample.twoDimensionalGrainGrowth;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.Getter;

@Getter
class Board {

	private static Board instance;
	private GridPane cellsGrid;
	private TextField xSizeField;
	private TextField ySizeField;
	private Button oneStepButton;
	private Button setSizeButton;
	private ComboBox<String> boundaryConditionComboBox;
	private ComboBox<String> nucleationComboBox;
	private ComboBox<String> neighbourComboBox;
	private GridPane stageGrid;
	private final int BOARD_SIZE = 700;

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

		//input
		this.xSizeField = new TextField("50");
		this.ySizeField = new TextField("50");
		this.boundaryConditionComboBox = new ComboBox<>();
		this.nucleationComboBox = new ComboBox<>();
		this.neighbourComboBox = new ComboBox<>();
		this.boundaryConditionComboBox.getItems().addAll("Periodic","Absorbing");
		this.nucleationComboBox.getItems().addAll("Homogeneous","With Radius","Random","Own");
		this.neighbourComboBox.getItems().addAll("Von Neumann","Pentagonal","Hexagonal","Moore","With Radius");
		this.boundaryConditionComboBox.setValue("Periodic");
		this.nucleationComboBox.setValue("Homogeneous");
		this.neighbourComboBox.setValue("Von Neumann");

		//settings
		this.xSizeField.setMaxSize(100,10);
		this.ySizeField.setMaxSize(100,10);
		this.boundaryConditionComboBox.setMaxSize(100,10);
		this.nucleationComboBox.setMaxSize(100,10);
		this.neighbourComboBox.setMaxSize(100,10);
		this.cellsGrid.setPadding(new Insets(10));
		menuGrid.setPadding(new Insets(10));
		menuGrid.setHgap(10);
		menuGrid.setVgap(10);

		//button
		this.oneStepButton = new Button("RUN");
		this.setSizeButton = new Button("SET SIZE");

		menuGrid.addColumn(0,xSizeLabel,ySizeLabel,boundaryConditionLabel,nucleationLabel,neighbourLabel,this.setSizeButton);
		menuGrid.addColumn(1,this.xSizeField,this.ySizeField,this.boundaryConditionComboBox,this.nucleationComboBox,this.neighbourComboBox,this.oneStepButton);

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
