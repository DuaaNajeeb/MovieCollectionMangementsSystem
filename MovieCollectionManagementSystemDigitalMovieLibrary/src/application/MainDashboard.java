package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainDashboard extends StackPane {
	Image imBackGround = new Image(
			"file:/C:/Users/HP/eclipse-workspace/MovieCollectionManagementSystemDigitalMovieLibrary/src/Screenshot%202025-10-03%20064232.png");
	ImageView ivBackGroung = new ImageView(imBackGround);
	static ArrayList<Movie> updatedMovie = new ArrayList<Movie>();
	static FileChooser fileChooser = new FileChooser();
	static File moviesFile;
	static File updateMovies = new File("updateMovies.txt");

	public MainDashboard(Stage stage) {
		/// "movies.txt" moviesFile
		Image imOpera = new Image(
				"file:/C:/Users/HP/eclipse-workspace/MovieCollectionManagementSystemDigitalMovieLibrary/src/Screenshot%202025-10-10%20213940.png");
		ImageView ivOpera = new ImageView(imOpera);
		ivOpera.setFitWidth(200);
		ivOpera.setFitHeight(150);
		Button btOpera = new Button("", ivOpera);

		Label lbWelcom = new Label(" Welcome to the Cinema ");
		lbWelcom.setFont(Font.font("Georgia", FontWeight.BOLD, 50));
		lbWelcom.setTextFill(Color.BISQUE);

		VBox vbWelcom = new VBox(lbWelcom);
		vbWelcom.setAlignment(Pos.TOP_CENTER);
		vbWelcom.setTranslateY(140);

		Image imReadFile = new Image(
				"file:/C:/Users/HP/eclipse-workspace/MovieCollectionManagementSystemDigitalMovieLibrary/src/Screenshot%202025-10-03%20081123.png");
		ImageView ivReadFile = new ImageView(imReadFile);
		ivReadFile.setFitWidth(200);
		ivReadFile.setFitHeight(150);
		ivReadFile.setPreserveRatio(true);
		Button btReadFile = new Button("", ivReadFile);

		btReadFile.setOnAction(e -> {
			readFile(stage);

			////////////////////////////////////////////////////////

		});

		////////////////////////////////////////////////////////////////////////
		// write file buttom

		Image imWriteFile = new Image(
				"file:/C:/Users/HP/eclipse-workspace/MovieCollectionManagementSystemDigitalMovieLibrary/src/Screenshot%202025-10-03%20081226.png");
		ImageView ivWriteFile = new ImageView(imWriteFile);
		ivWriteFile.setFitWidth(200);
		ivWriteFile.setFitHeight(150);
		ivWriteFile.setPreserveRatio(true);
		Button btWriteFile = new Button("", ivWriteFile);

		btWriteFile.setOnAction(w -> {
			printFile(stage);
		});

		//////////////////////////////////////////////
		/// Insert Movie

		Image imInsertMovie = new Image(
				"file:/C:/Users/HP/eclipse-workspace/MovieCollectionManagementSystemDigitalMovieLibrary/src/Screenshot%202025-10-03%20081725.png");
		ImageView ivInsert = new ImageView(imInsertMovie);
		ivInsert.setFitWidth(200);
		ivInsert.setFitHeight(150);
		ivInsert.setPreserveRatio(true);
		Button btInsert = new Button("", ivInsert);

		btInsert.setOnAction(q -> {
			InsertMovies l = new InsertMovies(stage);
			Scene s = new Scene(l);
			l.prefWidthProperty().bind(s.widthProperty());
			l.prefHeightProperty().bind(s.heightProperty());
			stage.setMaximized(true);

			stage.setScene(s);

		});

		btOpera.setOnAction(s -> {
			ViewMovies op = new ViewMovies(stage);
			Scene sc = new Scene(op);
			op.prefWidthProperty().bind(sc.widthProperty());
			op.prefHeightProperty().bind(sc.heightProperty());
			stage.setMaximized(true);
			stage.setScene(sc);

		});
		
		Button stat =new Button("display statistics ");
		stat.setPrefWidth(150);
		stat.setPrefHeight(40);
		stat.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
        VBox vbstat=new VBox();
        vbstat.getChildren().addAll(stat);
      
        
        stat.setOnAction(u->{
        	Statistics s=new Statistics(stage);
        	Scene sc=new Scene(s);
        	s.prefWidthProperty().bind(sc.widthProperty());
			s.prefHeightProperty().bind(sc.heightProperty());
			stage.setMaximized(true);
			stage.setScene(sc);
        	
        });
      
		
		
		

		HBox btCollection = new HBox(btReadFile, btWriteFile, btInsert, btOpera);
		btCollection.setSpacing(130);

		btCollection.setAlignment(Pos.CENTER);

		Label lbshow = new Label("show");
		lbshow.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
		lbshow.setTextFill(Color.DARKRED);

		Label readFile = new Label(" Read File ");
		readFile.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
		readFile.setTextFill(Color.DARKRED);

		Label writeFile = new Label(" write File ");
		writeFile.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
		writeFile.setTextFill(Color.DARKRED);

		Label InsertMovie = new Label(" Add a movie");
		InsertMovie.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
		InsertMovie.setTextFill(Color.DARKRED);

		HBox fileCollection = new HBox(readFile, writeFile, InsertMovie, lbshow);
		fileCollection.setSpacing(150);
		fileCollection.setAlignment(Pos.CENTER);

		VBox setCollection = new VBox(fileCollection, btCollection);
		setCollection.setAlignment(Pos.CENTER);

		 
		this.getChildren().addAll(ivBackGroung, vbWelcom, setCollection );
		//  this.getChildren().add(vbstat);

	}

	public void putbackGroundToScene(Scene scene) {
		ivBackGroung.fitHeightProperty().bind(scene.heightProperty());
		ivBackGroung.fitWidthProperty().bind(scene.widthProperty());

	}
	
	

	public static void readFile(Stage stage) {

		moviesFile = fileChooser.showOpenDialog(stage);
		System.out.println("---------------------------\n" + moviesFile);

		try (Scanner scan = new Scanner(moviesFile)) {
			int lineCounter = 1;
			while (scan.hasNextLine()) {
				String arrayOfLine = scan.nextLine();
				String[] arrayOfAttr = arrayOfLine.split(",");
				if (arrayOfAttr[0].trim().equalsIgnoreCase("MovieID")) {
					continue;

				}

				int errorCounter = 0;

				if (!Movie.cheakMovieId(arrayOfAttr[0].trim())) {
					errorCounter++;
					Movie.alertType("movie id should contain only number and it should be uniqe !",
							"Invalid movie id :( in line :  " + lineCounter);

				}

				if (!Movie.isValidTitle(arrayOfAttr[1].trim())) {
					errorCounter++;
					Movie.alertType("invalid title", "inValidTitle in line : " + lineCounter);
				}

				if (!Movie.isValidDirector(arrayOfAttr[2].trim())) {
					errorCounter++;
					Movie.alertType("the name of Director should only contain latter",
							"InvalidName in line : " + lineCounter);
				}

				if (!Movie.isValidGenre(arrayOfAttr[3].trim())) {
					errorCounter++;
					Movie.alertType("None valid Genre ",
							" Invalid Genr it should contain only alphabet \n and Adhere to the existing classification \n in line : "
									+ lineCounter);
				}

				if (!Movie.isValidYear(arrayOfAttr[4].trim())) {
					errorCounter++;
					Movie.alertType(" in valid year ", " in Vlaid year in line : " + lineCounter);

				}

				if (!Movie.isValidRating(arrayOfAttr[5].trim())) {
					errorCounter++;
					Movie.alertType("inVlid Rating ",
							" rate just from 1 - 5 and it just accept number \n the error in line : " + lineCounter);

				}
				lineCounter++;
				if (errorCounter == 0) {
					System.out.println(" hello everyone !!!!!!!!!!!!!!!!!!!!!");
					Main.list.add(new Movie(arrayOfAttr[0].trim(), arrayOfAttr[1].trim(), arrayOfAttr[2].trim(),
							arrayOfAttr[3].trim(), arrayOfAttr[4].trim(), arrayOfAttr[5].trim()));

				}
				errorCounter = 0;
				///////////////////

			}
			Movie.alertType("all data has been read :) ", " look ! ");

			for (int i = 0; i < Main.list.size(); i++) {
				System.out.println(Main.list.get(i).toString());
				System.out.println(Main.list.get(i).getDirector());

			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void printFile(Stage stage) {

		if (updateMovies != null) {
			try (PrintWriter write = new PrintWriter(updateMovies);) {

				if (!Main.list.isEmpty()) {

					for (int i = 0; i < Main.list.size(); i++) {
//						
						write.println(Main.list.get(i).toString());
					}
					Movie.alertType("all data is updated :) in all the files ", " look ! ");

				} else
					Movie.alertType(" there is no Movies to write te it in the file ", "LOOK");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else
			Movie.alertType("this file does not exist ", "you should to choose file");

	}
	
	public static void alertType(String massege, String header) {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(" look !!! ");
		alert.setHeaderText(header);
		alert.setContentText(massege);
		Image image = new Image(
				"file:/C:/Users/HP/eclipse-workspace/MedicalPatientMonitoringandTreatmentTracker/src/Screenshot%202025-11-13%20202533.png");

		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		imageView.setPreserveRatio(true);

		alert.getDialogPane().setGraphic(imageView);

		alert.showAndWait();

	}

}
