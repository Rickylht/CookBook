package ViewPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestView extends Application  {
	public void start(Stage stage){
		MainView rsv = new MainView();		
		Scene scene = new Scene(rsv,1000,600);
		stage.setScene(scene);
		stage.show();		
	}
	public static void main(String args[]) {
		launch();
	}
	
}
