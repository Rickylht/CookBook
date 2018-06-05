package ViewPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")

public class TestView extends Application  {
	public void start(Stage stage){
		RecipeShowView test = new RecipeShowView();	
		test.control();
		Scene scene = new Scene(test,1000,600);
		stage.setScene(scene);
		stage.show();			
	}
	
	public static void main(String args[]) {
		launch();
	}
	
}
