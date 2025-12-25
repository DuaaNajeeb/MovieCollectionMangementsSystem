package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	static MyList<Movie> list = new MyList<Movie>(100);
	static ObservableList<Movie> m = FXCollections.observableArrayList();

	@Override

	public void start(Stage primaryStage) {
		try {

			MainDashboard main = new MainDashboard(primaryStage);

			Scene scene = new Scene(main, 400, 400);
			main.putbackGroundToScene(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static  ArrayList<Movie>array(MyList<Movie> list){
		ArrayList<Movie>t=new ArrayList<Movie>();
		for(int i=0;i<list.size();i++) {
			t.add(list.get(i));
			
		}
		return t;
	}
	public static void convert() {
	    m.setAll(array(list)); 
	}


	public static void main(String[] args) {
		launch(args);
	}
}
