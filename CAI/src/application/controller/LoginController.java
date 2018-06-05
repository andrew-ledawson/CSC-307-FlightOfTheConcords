package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.DB;
import application.model.Flight;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController {

	@FXML TextField username;
	@FXML TextField password;
	
	public void HandleLoginButtonClick(ActionEvent event) throws Exception {
		
		Pane root;
		ObservableList<String> data = FXCollections.observableArrayList();
		try {
			DB db = DB.getDB();
			User user = db.checkAccount(username.getText(), password.getText());
			if (user != null) {
				ArrayList<Flight> flights = db.getFlights();
				Flight obj;
				for (int i=0; i<flights.size(); i++) {
					obj = flights.get(i);
					data.add(obj.toString());
				}
				ListView<String> listView = new ListView<String>(data);
				root = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
				root.getChildren().add(listView);
				Stage stage = new Stage();
				stage.setTitle("Menu");
				stage.setScene(new Scene(root, 450, 450));
				stage.show();
			} else {
				
			}
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	

	
	public void HandleRegisterButtonClick(ActionEvent event) throws IOException {
		try {
				Parent root = FXMLLoader.load(getClass().getResource("../view/Register.fxml")); 
				Stage stage = new Stage();
				stage.setTitle("Register");
				stage.setScene(new Scene(root, 450, 450));
				stage.show();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}