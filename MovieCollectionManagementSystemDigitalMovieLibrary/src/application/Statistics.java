package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Statistics extends BorderPane {

	public Statistics(Stage stage) {
		VBox attrubute=new VBox(10);
		HBox hxvb=new HBox(30);
		

		Label numberBasedAttribute=new Label("Number Based Attribute");
		
		numberBasedAttribute.setTextFill(Color.DARKRED);
		numberBasedAttribute.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		TextField resultByGenere=new TextField();
		
		
		ComboBox<String> numberOfGenre = new ComboBox<String>();
		numberOfGenre.getItems().addAll("TV Show", "Documentary", "Short Film", "Animation", "Sci-Fi");
		numberOfGenre.setValue("TV Show");
		attrubute.getChildren().addAll(numberBasedAttribute , numberOfGenre ,resultByGenere );

		numberOfGenre.setOnAction(v -> {

			switch (numberOfGenre.getValue()) {
			case "TV Show": {
				int count=0;
				for(int i=0;i<Main.m.size();i++ ) {
					if(Main.m.get(i).getGenre().equalsIgnoreCase("TV Show")) {
						System.out.println("--------------------------------" );
						System.out.println(Main.m.get(i).getTitle());
						count++;
						
					}
				}
				
				resultByGenere.setText(Integer.valueOf(count).toString());
				count=0;

			}
				break;

			case "Documentary": {
				int count=0;
				for(int i=0;i<Main.m.size();i++ ) {
					if(Main.m.get(i).getGenre().equalsIgnoreCase("Documentary")) {
						System.out.println("--------------------------------"+ "Documentary");
						System.out.println(Main.m.get(i).getTitle());
						count++;
						
					}
					
				}
				resultByGenere.setText(Integer.valueOf(count).toString());
				count=0;

			}
				break;

			case "Short Film": {
				int count=0;
				for(int i=0;i<Main.m.size();i++ ) {
					if(Main.m.get(i).getGenre().equalsIgnoreCase("Short Film")) {
						System.out.println("--------------------------------"+ "short film ");
						System.out.println(Main.m.get(i).getTitle());
						count++;
						
					}
				}
				resultByGenere.setText(Integer.valueOf(count).toString());
				count=0;

			}
				break;

			case "Animation": {
				int count=0;
				for(int i=0;i<Main.m.size();i++ ) {
					if(Main.m.get(i).getGenre().equalsIgnoreCase("Animation")) {
						System.out.println(" Animation"+"\n--------------------------------" );
						System.out.println(Main.m.get(i).getTitle());
						count++;
						
					}
					
				}
				resultByGenere.setText(Integer.valueOf(count).toString());
				count=0;

			}
				break;

			case "Sci-Fi": {
				int count=0;
				for(int i=0;i<Main.m.size();i++ ) {
					if(Main.m.get(i).getGenre().equalsIgnoreCase("Sci-Fi")) {
						System.out.println("sci_ fi"+ "\n --------------------------------");
						System.out.println(Main.m.get(i).getTitle());
						count++;
						
					}
				}
				resultByGenere.setText(Integer.valueOf(count).toString());
				count=0;
			}
				break;

			}
		});

		Button back = new Button("Back");
		back.setPrefWidth(150);
		back.setPrefHeight(40);
		back.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");

		back.setOnAction(d -> {

			MainDashboard m = new MainDashboard(stage);
			Scene s = new Scene(m);

			m.putbackGroundToScene(s);
			stage.setScene(s);

			stage.setMaximized(true);
			stage.setFullScreen(true);
			stage.show();
		});
		
		Label nameDorector =new Label("Name Of Director : ");
		nameDorector.setTextFill(Color.DARKRED);
		nameDorector.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		TextField txDirector=new TextField();
		TextField txResult=new TextField();
		
		Button searchDirector=new Button("search by Director");
		searchDirector.setTextFill(Color.DARKRED);
		searchDirector.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		VBox vbDirector=new VBox(10);
		vbDirector.getChildren().addAll( nameDorector ,txDirector ,searchDirector,  txResult );
		
		
		
		searchDirector.setOnAction(f->{
			int count=0;
			for(int i=0;i< Main.m.size();i++) {
				if(Main.m.get(i).getDirector().trim().equalsIgnoreCase(txDirector.getText().trim())) {
					System.out.println();
					count++;
				}
				
			}
			
			txResult.setText(Integer.valueOf(count).toString());
			count=0;
		});
		
		Label EnterYear=new Label(" Enter the year : ");
		EnterYear.setTextFill(Color.DARKRED);
		EnterYear.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		TextField txYear=new TextField();
		
		Button searchNumberOfYear=new Button("seach by year : ");
		searchNumberOfYear.setTextFill(Color.DARKRED);
		searchNumberOfYear.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
		TextField resultYear=new TextField();
		
		searchNumberOfYear.setOnAction(q->{
			int count=0;
			
			for(int i=0;i<Main.m.size();i++) {
				if(Main.m.get(i).getReleaseYear().equalsIgnoreCase(txYear.getText())) {
					count++;
				}
				
			}
			resultYear.setText(Integer.valueOf(count).toString());
			count=0;
			
		});
		
		VBox vbYear=new VBox(10);
		vbYear.getChildren().addAll(EnterYear ,txYear , searchNumberOfYear ,resultYear, back  );
		
		
		
		
		
		hxvb.getChildren().addAll(attrubute , vbDirector , vbYear );
		this.setCenter(hxvb);;
		
		

	}

}
