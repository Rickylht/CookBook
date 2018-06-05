package ViewPackage;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@SuppressWarnings("restriction")

public class RecipeEditionView extends GridPane {
	private Label RecipeNameLabel = new Label("Name:");
	public TextField RecipeNameTextField = new TextField();
	private Label PreparationTimeLabel = new Label("Preparation Time:");
	public TextField PreparationTimeTextField = new TextField();
	private Label CookTimeLabel = new Label("Cook Time:");
	public TextField CookTimeTextField = new TextField();
	private Label CategoryLabel = new Label("Category:");
	public TextField CategoryTextField = new TextField();
	private Label IngredientsLabel = new Label("Ingredients:");
	public TextArea IngredientsTextArea = new TextArea();
	private Label StepsLabel = new Label("Steps:");
	public TextArea StepsTextArea = new TextArea();
	private Button CancelButton = new Button("Cancel:");
	private Button ConfirmButton = new Button("Confirm:");
	RecipeEditionView(){	
		
		this.setHgap(20);
		this.setVgap(20);		
		this.add(RecipeNameLabel, 0, 0,1,1);
		this.add(RecipeNameTextField, 0, 1,1,1);
		this.add(PreparationTimeLabel, 0, 2,1,1);
		this.add(PreparationTimeTextField, 0, 3,1,1);
		this.add(CookTimeLabel, 1, 2,1,1);
		this.add(CookTimeTextField, 1, 3,1,1);
		this.add(CategoryLabel, 2, 2,1,1);
		this.add(CategoryTextField, 2, 3,1,1);
		this.add(IngredientsLabel, 0, 4,1,1);
		this.add(IngredientsTextArea, 0, 5,1,1);
		this.add(StepsLabel, 0, 6,1,1);
		this.add(StepsTextArea, 0, 7,1,1);
		this.add(CancelButton, 0, 8,1,1);
		this.add(ConfirmButton, 1, 8,1,1);
		
	}

}
