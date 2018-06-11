package ViewPackage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.*;  

import ControlPackage.DatabaseController;

@SuppressWarnings("restriction")

public class LoginView extends GridPane {
	Button LoginButton = new Button("Log in");
	Button NewAccountBuuton = new Button("Create");
	Label LoginLabel = new Label("Please enter your username and password");
	Label UserNameLabel = new Label("user name: ");
	TextField UserNameTextField = new TextField();
	Label UserPasswordLabel = new Label("password: ");
	TextField UserPasswordTextField = new TextField();
	Label NewAccountLabel = new Label("create your new account");
	Label NewAccountNameLabel = new Label("create your name: ");
	TextField NewAccountNameTextField = new TextField();
	Label NewAccountPasswordLabel = new Label("password: ");
	TextField NewAccountPasswordTextField = new TextField();
		
	public LoginView() {
		this.setHgap(20);
		this.setVgap(20);
		this.add(LoginLabel, 1, 0);
		this.add(UserNameLabel, 1, 1);
		this.add(UserNameTextField, 2, 1);
		this.add(UserPasswordLabel, 1, 2);
		this.add(UserPasswordTextField, 2, 2);
		this.add(LoginButton, 1, 3);	
		this.add(NewAccountLabel, 1, 5);
		this.add(NewAccountNameLabel, 1, 6);
		this.add(NewAccountNameTextField, 2, 6);
		this.add(NewAccountPasswordLabel,1,7);
		this.add(NewAccountPasswordTextField, 2, 7);
		this.add(NewAccountBuuton, 1, 8);
		
		LoginButton.setOnAction(e -> {
			String Sname = UserNameTextField.getText();
			String Spasswpord = UserPasswordTextField.getText();
			DatabaseController  dc = new DatabaseController();			
			try {
				if(dc.login(Sname,Spasswpord)==1){
					LoginButton.setText("log in successfully");
					RecipeShowView rsv = new RecipeShowView();
					try  
		            {  						
						Scene scene = new Scene(rsv,1000,600);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.show();						
		            }   
		            catch (Exception e1)  
		            {  
		                e1.printStackTrace();  
		            } 
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		});
		NewAccountBuuton.setOnAction(e -> {
			NewAccountBuuton.setText("create successfully");
			String Cname = NewAccountNameTextField.getText();
			String Cpassword1 = NewAccountPasswordTextField.getText();
			String Cpassword2 = "12345";
			DatabaseController  dc = new DatabaseController();
			try{
				if(dc.create(Cname, Cpassword1,Cpassword2)){
					NewAccountBuuton.setText("create successfully");
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});		
	}
}
	
