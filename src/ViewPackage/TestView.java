package ViewPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")

public class TestView extends Application  {
	public void start(Stage stage){
		LoginView test = new LoginView();	
		Scene scene = new Scene(test,1000,600);
		stage.setScene(scene);
		stage.show();			
	}
	
	public static void main(String args[]) {
		launch();
	}	
}
