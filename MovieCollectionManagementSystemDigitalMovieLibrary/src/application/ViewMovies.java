package application;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewMovies extends BorderPane {

	public ViewMovies(Stage stage) {
		// this for interface for view Movies

		// her is the tabel view and we need to fill it with movie attrubite
		TableView<Movie> tableView = new TableView<Movie>();
		TableColumn<Movie, String> movieID = new TableColumn<>("movieID");
		TableColumn<Movie, String> title = new TableColumn<>("title");
		TableColumn<Movie, String> director = new TableColumn<>("director");
		TableColumn<Movie, String> genre = new TableColumn<>("genre");
		TableColumn<Movie, String> releaseYear = new TableColumn<>("releaseYear");
		TableColumn<Movie, String> rating = new TableColumn<>("rating");

		movieID.setCellValueFactory(new PropertyValueFactory<>("movieID"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		director.setCellValueFactory(new PropertyValueFactory<>("director"));
		genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
		releaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
		rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
		tableView.getColumns().addAll(movieID, title, director, genre, releaseYear, rating);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableView.setItems(Main.m);
		Main.convert();
		tableView.setPrefWidth(600);
		tableView.setPrefHeight(600);
		tableView.setMinWidth(400);
		VBox button = new VBox(10);
		Button btBack = new Button("Back");
		btBack.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		btBack.setPrefWidth(150);
		btBack.setPrefHeight(40);

		// this button to get the previous page
		btBack.setOnAction(e -> {
			MainDashboard m = new MainDashboard(stage);
			Scene s = new Scene(m);

			m.putbackGroundToScene(s);
			stage.setScene(s);

			stage.setMaximized(true);
			stage.setFullScreen(true);
			stage.show();
		});

		//////// update////////////

		Label lbUpId = new Label(" search by movie ID");
		lbUpId.setTextFill(Color.DARKRED);
		lbUpId.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

		TextField txUpId = new TextField();

		Label lbMovieId = new Label("Movie id : ");
		lbMovieId.setTextFill(Color.DARKRED);
		lbMovieId.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

		Label lbTitle = new Label("Title : ");
		lbTitle.setTextFill(Color.DARKRED);
		lbTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

		Label lbDirector = new Label("Director : ");
		lbDirector.setTextFill(Color.DARKRED);
		lbDirector.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

		Label lbGenre = new Label("Genre : ");
		lbGenre.setTextFill(Color.DARKRED);
		lbGenre.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

		Label lbReleaseYear = new Label("Release Year : ");
		lbReleaseYear.setTextFill(Color.DARKRED);
		lbReleaseYear.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

		Label lbRating = new Label(" Rating : ");
		lbRating.setTextFill(Color.DARKRED);
		lbRating.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

		VBox vbUpdateLabel = new VBox(10);
		vbUpdateLabel.getChildren().addAll(lbUpId, lbMovieId, lbTitle, lbDirector, lbGenre, lbReleaseYear, lbRating);

		TextField txMovieID = new TextField();
		TextField txTitle = new TextField();
		TextField txDirector = new TextField();
		ComboBox<String> coGenre = new ComboBox<>();
		coGenre.getItems().addAll("TV Show", "Documentary", "Short Film", "Animation", "Sci-Fi");
		coGenre.setValue("TV Show");

		// TextField txReleaseYear = new TextField();
		DatePicker dataPicker = new DatePicker();

		dataPicker.setOnAction(e -> {
			LocalDate localDate = dataPicker.getValue();
			if (localDate != null) {

				System.out.println("selsected yea is : ");

				if (!Movie.isValidYear(Integer.valueOf(localDate.getYear()).toString().trim())) {

					Movie.alertType(" in valid year ", " in Vlaid year in line : ");

				}

			}
		});

		coGenre.setOnAction(q -> {
			if (!Movie.isValidGenre(coGenre.getValue().trim())) {

				Movie.alertType("None valid Genre ",
						" Invalid Genr it should contain only alphabet \n and Adhere to the existing classification \n in line : ");
			}
		});

		ComboBox<String> coRating = new ComboBox<>();
		coRating.getItems().addAll("1", "2", "3", "4", "5");
		coRating.setValue("1");
		coRating.setOnAction(i -> {
			if (!Movie.isValidRating(coRating.getValue().trim())) {

				Movie.alertType("inVlid Rating ",
						" rate just from 1 - 5 and it just accept number \n the error in line : ");

			}
		});
		/// go button//
		Button btGo = new Button("GO");
		btGo.setPrefWidth(150);
		btGo.setPrefHeight(40);
		btGo.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");

		btGo.setOnAction(i -> {

			// MainDashboard.printFile(stage);
			boolean cheackId = false;
			for (int j = 0; j < Main.m.size(); j++) {
				if (Main.m.get(j).getMovieID().trim().equalsIgnoreCase(txMovieID.getText().trim())) {
					cheackId = true;
					Main.m.get(j).setMovieId(txMovieID.getText());
					Main.m.get(j).setTitle(txTitle.getText());
					Main.m.get(j).setDiretor(txDirector.getText());
					Main.m.get(j).setGenre(coGenre.getValue());
					Main.m.get(j).setYear(Integer.valueOf(dataPicker.getValue().getYear()).toString());
					Main.m.get(j).setRating(coRating.getValue());
					break;
				}

			}

			if (!cheackId) {
				Movie.alertType("this movie id does not exsist", "in valid movie id");

			} else {
				Movie.alertType("this movie has been updated ", "updated !");
				Main.convert();
				MainDashboard.printFile(stage);

			}

		});

		VBox vbTextFielsUpdate = new VBox(10);
		vbTextFielsUpdate.getChildren().addAll(txUpId, txMovieID, txTitle, txDirector, coGenre, dataPicker, coRating,
				btGo);
		Button btsearch = new Button("search");
		btsearch.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		btsearch.setPrefWidth(150);
		btsearch.setPrefHeight(40);

		btsearch.setOnAction(r -> {
			boolean cheasearch = false;

			for (int i = 0; i < Main.m.size(); i++) {
				if (Main.m.get(i).getMovieID().trim().equalsIgnoreCase(txMovieID.getText().trim())) {
					txMovieID.setText(Main.m.get(i).getMovieID().trim());
					txTitle.setText(Main.m.get(i).getTitle().trim());
					txDirector.setText(Main.m.get(i).getDirector().trim());
					coGenre.setValue(Main.m.get(i).getGenre().trim());
					coRating.setValue(Main.m.get(i).getRating());
					LocalDate date = LocalDate.of(Integer.parseInt(Main.m.get(i).getReleaseYear()), 1, 1);
					dataPicker.setValue(date);
					cheasearch = true;
					break;

				}

			}

			if (!cheasearch) {
				Movie.alertType("this movie does not exist ", "in valid movie id");

			}
		});

		HBox hboxUpdate = new HBox(10);
		hboxUpdate.getChildren().addAll(vbUpdateLabel, vbTextFielsUpdate, btsearch);

		Button btUpdate = new Button("Update");
		btUpdate.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		btUpdate.setOnAction(x -> {
			if (button.isVisible()) {
				button.setVisible(false);
				button.setManaged(false);

			} else {
				hboxUpdate.setVisible(true);
				hboxUpdate.setManaged(true);
			}

			this.setCenter(hboxUpdate);
			hboxUpdate.setPadding(new Insets(150, 20, 0, 0));
			//////////////////////////////////////////////////

		});
		Button btremove = new Button("remove");
		btremove.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		btremove.setPrefWidth(150);
		btremove.setPrefHeight(40);

		button.getChildren().addAll(lbUpId, txUpId, btremove);
		button.setAlignment(Pos.CENTER);
		button.setTranslateX(30);
		button.setVisible(false);
		button.setManaged(false);

		Button btDelete = new Button("Delete");

		btDelete.setOnAction(x -> {
			hboxUpdate.setVisible(false);
			hboxUpdate.setManaged(false);

			button.setVisible(true);
			button.setManaged(true);

			this.setCenter(button);

			// button.getChildren().addAll(lbUpId, txUpId, btremove);
			this.setCenter(button);
			button.setTranslateX(30);
			button.setAlignment(Pos.CENTER);
			// button.setPadding(new Insets(150, 20, 0, 0));

		});

		btremove.setOnAction(k -> {

			boolean id = false;
			for (int i = 0; i < Main.list.size(); i++) {
				if (Main.list.get(i).getMovieID().trim().equalsIgnoreCase(txUpId.getText().trim())) {
					id = true;
					// Movie movie=
					Main.list.remove(i);
					i--;
					break;
				}

			}
			if (!id) {
				Movie.alertType("this movie id does not exist ", "enter correct movie id ");

			} else {
				Main.convert();
				MainDashboard.printFile(stage);
			}

		});

		btDelete.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");

		ComboBox<String> sorting = new ComboBox<String>();
		sorting.getItems().addAll("sort by Title", "sort By Director", "sort by year");
		sorting.setValue("specific search");
		sorting.setStyle(
				"-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: white;" + "-fx-pref-width: 200px;");
		sorting.setValue("sort by Title");

		sorting.setOnAction(w -> {

			switch (sorting.getValue()) {

			case "sort by Title": {
				for (int i = 0; i < Main.m.size() - 1; i++) {
					for (int j = 0; j < Main.m.size() - 1 - i; j++) {
						if (Main.m.get(j).getTitle().compareToIgnoreCase(Main.m.get(j + 1).getTitle()) > 0) {
							Movie temp = Main.m.get(j);
							Main.m.set(j, Main.m.get(j + 1));
							Main.m.set(j + 1, temp);

						}
					}
				}

			}
				break;

			case "sort By Director": {
				for (int i = 0; i < Main.m.size() - 1; i++) {
					for (int j = 0; j < Main.m.size() - 1 - i; j++) {
						if (Main.m.get(j).getDirector().compareToIgnoreCase(Main.m.get(j + 1).getDirector()) > 0) {
							Movie temp = Main.m.get(j);
							Main.m.set(j, Main.m.get(j + 1));
							Main.m.set(j + 1, temp);
						}
					}
				}

			}
				break;

			case "sort by year": {
				for (int i = 0; i < Main.m.size() - 1; i++) {
					for (int j = 0; j < Main.m.size() - 1 - i; j++) {
						int yearone = Integer.parseInt(Main.m.get(j).getReleaseYear());
						int yearTwo = Integer.parseInt(Main.m.get(j + 1).getReleaseYear());
						if (yearone > yearTwo) {

							Movie temp = Main.m.get(j);
							Main.m.set(j, Main.m.get(j + 1));
							Main.m.set(j + 1, temp);

						}
					}
				}

			}
				break;

			}

			tableView.setItems(FXCollections.observableArrayList(Main.m));

			MainDashboard.printFile(stage);
		});
		Button stat = new Button("display statistics ");
		stat.setPrefWidth(150);
		stat.setPrefHeight(40);
		stat.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		VBox vbstat = new VBox();
		vbstat.getChildren().addAll(stat);

		stat.setOnAction(u -> {
			Statistics s = new Statistics(stage);
			Scene sc = new Scene(s);
			s.prefWidthProperty().bind(sc.widthProperty());
			s.prefHeightProperty().bind(sc.heightProperty());
			stage.setMaximized(true);
			stage.setScene(sc);

		});

		ComboBox<String> search = new ComboBox<String>();

		ComboBox<String> ser = new ComboBox<String>();
		HBox combo = new HBox(10);
		combo.getChildren().addAll(btUpdate, btDelete, search, ser, sorting, stat);

		search.getItems().addAll("specific search", "portail search");
		search.setValue("specific search");
		search.setStyle(
				"-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: white;" + "-fx-pref-width: 200px;");

		Button goTosearchByID = new Button("Go to Search");
		goTosearchByID.setPrefWidth(150);
		goTosearchByID.setPrefHeight(40);
		goTosearchByID.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");

		TextField result = new TextField();
		Label enterID = new Label(" Enter Movie id : ");
		enterID.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		enterID.setPrefWidth(150);
		enterID.setPrefHeight(40);
		TextField txMovID = new TextField();
		VBox vbsearchNodeID = new VBox(10);
		vbsearchNodeID.getChildren().addAll(enterID, txMovID, goTosearchByID, result);

		vbsearchNodeID.setVisible(false);
//		this.setRight(vbsearchNodeID);

		/////////////////////// search by full title

		Label enterFullTitle = new Label("enter Full Title :");
		enterFullTitle.setPrefWidth(150);
		enterFullTitle.setPrefHeight(40);
		enterFullTitle.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");

		TextField txEnterFullTitle = new TextField();
		Button btSearchByFullTitle = new Button(" search by full title : ");
		btSearchByFullTitle.setPrefWidth(150);
		btSearchByFullTitle.setPrefHeight(40);
		btSearchByFullTitle.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		TextField resultOfMoviebttittle = new TextField();

		VBox vbsearchByTitle = new VBox();

		vbsearchByTitle.getChildren().addAll(enterFullTitle, txEnterFullTitle, btSearchByFullTitle,
				resultOfMoviebttittle);
		vbsearchByTitle.setVisible(false);
//		this.setRight(vbsearchByTitle);
		VBox vbsearchContainer = new VBox();
		vbsearchContainer.getChildren().addAll(vbsearchNodeID, vbsearchByTitle);
		this.setRight(vbsearchContainer);

		//////////// attribute portail search
		Label enterPortailTitle = new Label(" Enter Portail Of the title : ");
		enterPortailTitle.setPrefWidth(150);
		enterPortailTitle.setPrefHeight(40);
		enterPortailTitle.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		TextField txEnterPortailTitle = new TextField();
		Button btEnterPortialSearch = new Button(" enter portail title to search :");
		btEnterPortialSearch.setPrefWidth(150);
		btEnterPortialSearch.setPrefHeight(40);
		btEnterPortialSearch.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");

		TextField txresultOfPortailSearch = new TextField();
		VBox vbPortailOfTitle = new VBox();
		vbPortailOfTitle.getChildren().addAll(enterPortailTitle, txEnterPortailTitle, btEnterPortialSearch,
				txresultOfPortailSearch);
		vbPortailOfTitle.setVisible(false);

		Label enterDirectorName = new Label(" Enter Director Name : ");
		enterDirectorName.setPrefWidth(150);
		enterDirectorName.setPrefHeight(40);
		enterDirectorName.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		TextField txDirectorName = new TextField();
		Button btEnterDirectorName = new Button(" search by director name :  ");
		btEnterDirectorName.setPrefWidth(150);
		btEnterDirectorName.setPrefHeight(40);
		btEnterDirectorName.setStyle("-fx-text-fill: #800020; -fx-font-weight: bold; -fx-font-size: 14px;");
		TextField resultDirectorName = new TextField();

		VBox vbDirctorAttru = new VBox();
		vbDirctorAttru.getChildren().addAll(enterDirectorName, txDirectorName, btEnterDirectorName, resultDirectorName);
		vbDirctorAttru.setVisible(false);
		VBox portail = new VBox(10);
		portail.getChildren().addAll(vbPortailOfTitle, vbDirctorAttru);
		this.setBottom(portail);

		btEnterDirectorName.setOnAction(f -> {
			if (!Movie.isValidDirector(txDirectorName.getText().trim())) {

				Movie.alertType("the name of Director should only contain latter", "InvalidName in line : ");
			}

			else {
				boolean found = false;

				for (int i = 0; i < Main.m.size(); i++) {
					if (Main.m.get(i).getDirector().trim().equalsIgnoreCase(txDirectorName.getText().trim())) {
						resultDirectorName.appendText(Main.m.get(i).toString() + "\n");

						found = true;
						int length = Main.m.get(i).toString().length();

						resultDirectorName.setPrefColumnCount(Math.max(10, length + 2));
						break;
					}

				}

				if (!found) {
					Movie.alertType("does not exist ", "look!");

				}
			}
		});

		btEnterPortialSearch.setOnAction(e -> {

			if (!Movie.isValidTitle(txEnterPortailTitle.getText().trim())) {

				Movie.alertType("invalid title", "inValidTitle in line : ");
			} else {
				boolean exist = false;
				for (int i = 0; i < Main.m.size(); i++) {
					if (Main.m.get(i).getTitle().toLowerCase().trim()
							.contains(txEnterPortailTitle.getText().toLowerCase().trim())) {
						txresultOfPortailSearch.appendText(Main.m.get(i).toString() + "\n");
						exist = true;

						int length = Main.m.get(i).toString().length();

						txresultOfPortailSearch.setPrefColumnCount(Math.max(10, length + 2));
						exist = true;

					}

				}

				if (!exist) {
					Movie.alertType("Movie not found", "No movie with that title exists.");
					txresultOfPortailSearch.clear();
				}
			}
		});

		search.setOnAction(w -> {
			ser.getItems().clear();

			switch (search.getValue()) {
			case "specific search": {
				ser.getItems().addAll("Movie ID", "Title");
				ser.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: white;"
						+ "-fx-pref-width: 200px;");
				ser.setOnAction(z -> {

					switch (ser.getValue()) {
					case "Movie ID": {

						vbsearchNodeID.setVisible(true);
						vbsearchByTitle.setVisible(false);
						vbPortailOfTitle.setVisible(false);
						vbDirctorAttru.setVisible(false);

					}
						break;
					case "Title": {
						vbsearchNodeID.setVisible(false);
						vbsearchByTitle.setVisible(true);
						vbPortailOfTitle.setVisible(false);
						vbDirctorAttru.setVisible(false);

					}
						break;
					}

				});

			}
				break;

			case "portail search": {
				ser.getItems().addAll("portail of title", "Director");
				ser.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: white;"
						+ "-fx-pref-width: 200px;");

				ser.setOnAction(b -> {
					switch (ser.getValue()) {
					case "portail of title": {
						vbPortailOfTitle.setVisible(true);
						vbDirctorAttru.setVisible(false);
						vbsearchNodeID.setVisible(false);
						vbsearchByTitle.setVisible(false);

					}
						break;

					case "Director": {
						vbsearchNodeID.setVisible(false);
						vbsearchByTitle.setVisible(false);
						vbPortailOfTitle.setVisible(false);
						vbDirctorAttru.setVisible(true);

					}
						break;

					}

				});

			}
				break;

			}

		});

		btSearchByFullTitle.setOnAction(q -> {
			if (!Movie.isValidTitle(txEnterFullTitle.getText().trim())) {

				Movie.alertType("invalid title", "inValidTitle in line : ");
			} else {
				boolean exist = false;
				for (int i = 0; i < Main.m.size(); i++) {
					if (Main.m.get(i).getTitle().trim().equalsIgnoreCase(txEnterFullTitle.getText().trim())) {
						resultOfMoviebttittle.setText(Main.m.get(i).toString());
						int length = Main.m.get(i).toString().length();

						result.setPrefColumnCount(Math.max(10, length + 2));
						exist = true;
						break;

					}

				}

				if (!exist) {
					Movie.alertType("Movie not found", "No movie with that title exists.");
					resultOfMoviebttittle.clear();
				}
			}
		});

		goTosearchByID.setOnAction(v -> {

			if (!Movie.cheakMovieId(txMovID.getText().trim())) {

				Movie.alertType("movie id should contain only number and it should be uniqe !",
						"Invalid movie id :( in line :  ");

			} else {
				boolean test = false;
				for (int i = 0; i < Main.m.size(); i++) {
					if (txMovID.getText().trim().equalsIgnoreCase(Main.m.get(i).getMovieID().trim())) {
						test = true;
						result.setText(Main.m.get(i).toString());
						int length = Main.m.get(i).toString().length();

						result.setPrefColumnCount(Math.max(10, length + 2));
					}

				}

				if (!test) {
					Movie.alertType("this movie doesnt exist :(", "look !");

				}

			}

		});

		VBox tabelansbt = new VBox(10);
		tabelansbt.getChildren().addAll(combo, tableView, btBack);

		this.setLeft(tabelansbt);
		this.setMargin(tabelansbt, new Insets(50, 0, 0, 0));
		this.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #800020, #f5f5dc);");

	}

}
