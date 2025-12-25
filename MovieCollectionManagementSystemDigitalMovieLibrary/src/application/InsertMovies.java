package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class InsertMovies extends BorderPane {
	private int errorCounter = 0;
	private int errorCombo = 0;

	public InsertMovies(Stage stage) {
		Label lbInsertMovies = new Label("Add the movie");
		lbInsertMovies.setTextFill(Color.DARKRED);
		lbInsertMovies.setFont(Font.font("Georgia", FontWeight.BOLD, 70));
		VBox vbIsertMovies = new VBox(lbInsertMovies);

		Label lbMovieId = new Label("Movie id : ");
		lbMovieId.setTextFill(Color.DARKRED);
		lbMovieId.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

		Label lbTitle = new Label("Title : ");
		lbTitle.setTextFill(Color.DARKRED);
		lbTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

		Label lbDirector = new Label("Director : ");
		lbDirector.setTextFill(Color.DARKRED);
		lbDirector.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

		Label lbGenre = new Label("Genre : ");
		lbGenre.setTextFill(Color.DARKRED);
		lbGenre.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

		Label lbReleaseYear = new Label("Release Year : ");
		lbReleaseYear.setTextFill(Color.DARKRED);
		lbReleaseYear.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

		Label lbRating = new Label(" Rating : ");
		lbRating.setTextFill(Color.DARKRED);
		lbRating.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

		TextField txMovieID = new TextField();
		TextField txTitle = new TextField();
		TextField txDirector = new TextField();
		ComboBox<String> coGenre = new ComboBox<>();
		coGenre.getItems().addAll("TV Show", "Documentary", "Short Film,", "Animation", "Sci-Fi");
		coGenre.setValue("TV Show");

		// TextField txReleaseYear = new TextField();
		DatePicker dataPicker = new DatePicker();

		dataPicker.setOnAction(e -> {
			LocalDate localDate = dataPicker.getValue();
			if (localDate != null) {

				System.out.println("selsected yea is : ");

				if (!Movie.isValidYear(Integer.valueOf(localDate.getYear()).toString().trim())) {
					errorCombo++;
					Movie.alertType(" in valid year ", " in Vlaid year in line : ");

				}

			}
		});

		coGenre.setOnAction(q -> {
			if (!Movie.isValidGenre(coGenre.getValue().trim())) {
				errorCombo++;
				Movie.alertType("None valid Genre ",
						" Invalid Genr it should contain only alphabet \n and Adhere to the existing classification \n in line : ");
			}
		});

		ComboBox<String> coRating = new ComboBox<>();
		coRating.getItems().addAll("1", "2", "3", "4", "5");
		coRating.setValue("1");
		coRating.setOnAction(i -> {
			if (!Movie.isValidRating(coRating.getValue().trim())) {
				errorCombo++;
				Movie.alertType("inVlid Rating ",
						" rate just from 1 - 5 and it just accept number \n the error in line : ");

			}
		});

		//////////////////// to update

		Image imAddMovie = new Image(
				"file:/C:/Users/HP/eclipse-workspace/MovieCollectionManagementSystemDigitalMovieLibrary/src/Screenshot%202025-10-03%20081725.png");

		ImageView ivAddMovie = new ImageView(imAddMovie);
		ivAddMovie.setFitWidth(100);
		ivAddMovie.setFitHeight(100);
		Button addMovi = new Button("", ivAddMovie);
		addMovi.setOnAction(d -> { 
			errorCounter = 0;

			if (!Movie.cheakMovieId(txMovieID.getText().trim())) {
				errorCounter++;
				Movie.alertType("movie id should contain only number and it should be uniqe !",
						"Invalid movie id :( in line :  ");

			}

			if (!Movie.isValidTitle(txTitle.getText().trim())) {
				errorCounter++;
				Movie.alertType("invalid title", "inValidTitle in line : ");
			}

			if (!Movie.isValidDirector(txDirector.getText().trim())) {
				errorCounter++;
				Movie.alertType("the name of Director should only contain latter", "InvalidName in line : ");
			}

			if (errorCounter == 0 && errorCombo == 0) {

				boolean cheack = false;

				Movie mov = new Movie(txMovieID.getText().trim(), txTitle.getText().trim(), txDirector.getText().trim(),
						coGenre.getValue().trim(), Integer.valueOf(dataPicker.getValue().getYear()).toString(),
						coRating.getValue().trim());

				for (int i = 0; i < Main.list.size(); i++) {
					Movie movie = Main.list.get(i);
					if (movie.equals(mov) || movie.duplicateID(mov)) {

						cheack = true;
						break;
					}
				}

				if (!cheack) {
					Main.list.add(mov);

					Movie.alertType("movie was added ", "good job");
				} else
					Movie.alertType("sorry !!!!!", " we can not add this movie :(");
				errorCounter = 0;
				errorCombo = 0;
//
//				

			}

			if (MainDashboard.updateMovies != null) {
				MainDashboard.printFile(stage);
			}

			else
				Movie.alertType("this file does not exist ", "please choose file");

//			if(MainDashboard.moviesFile!=null) {
//				try (PrintWriter write=new PrintWriter(MainDashboard.moviesFile);){
//					for(int i=0; i<Main.list.size();i++) {
//						write.print(Main.list.toString());
//						
//					}
//					
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				
//			}
//			

		});
		// clear button
		Image imClear = new Image(
				"file:/C:/Users/HP/eclipse-workspace/MovieCollectionManagementSystemDigitalMovieLibrary/src/Screenshot%202025-10-03%20081355.png");
		ImageView ivClear = new ImageView(imClear);
		ivClear.setFitWidth(100);
		ivClear.setFitHeight(100);
		Button btClear = new Button("", ivClear);

		btClear.setOnAction(f -> {
			txMovieID.clear();
			txTitle.clear();
			txDirector.clear();
//			coGenre.getSelectionModel().clearSelection();
//			coRating.getSelectionModel().clearSelection();
			dataPicker.setValue(null);

		});

		Image imBack = new Image(
				"file:/C:/Users/HP/eclipse-workspace/MovieCollectionManagementSystemDigitalMovieLibrary/src/Screenshot%202025-10-09%20083237.png");
		ImageView ivBack = new ImageView(imBack);
		ivBack.setFitWidth(100);
		ivBack.setFitHeight(100);
		Button btBack = new Button("", ivBack);
		btBack.setOnAction(e -> {
			MainDashboard m = new MainDashboard(stage);
			Scene s = new Scene(m);

			m.putbackGroundToScene(s);
			stage.setScene(s);

			stage.setMaximized(true);
			stage.setFullScreen(true);
			stage.show();

		});

		HBox operationIcon = new HBox(btClear, btBack, addMovi);
		operationIcon.setSpacing(40);
		VBox lbAttr = new VBox(lbMovieId, lbTitle, lbDirector, lbGenre, lbReleaseYear, lbRating, operationIcon);
		lbAttr.setSpacing(40);
		VBox txAttr = new VBox(txMovieID, txTitle, txDirector, coGenre, dataPicker, coRating);
		txAttr.setSpacing(50);
		HBox lbAttTxAtrr = new HBox(lbAttr, txAttr);
		lbAttTxAtrr.setSpacing(30);

		vbIsertMovies.setAlignment(Pos.CENTER);
		this.setTop(vbIsertMovies);
		this.setLeft(lbAttTxAtrr);
		this.setMargin(lbAttTxAtrr, new Insets(70, 0, 0, 0));
		lbAttTxAtrr.setPadding(new Insets(10, 30, 10, 30));

		// this.getChildren().addAll(vbIsertMovies);

		Label lbAddmovieDetails = new Label("Add information about the movie");

		this.setStyle("-fx-background-color: #D2B48C;");

	}

}
