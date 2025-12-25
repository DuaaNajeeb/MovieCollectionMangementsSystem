package application;

import javafx.scene.control.Alert;

public class Movie {
	private String movieID;
	private String title;
	private String director;
	private String genre;
	private String releaseYear;
	private String rating;

	public Movie(String movieID, String title, String director, String genre, String releaseYear, String rating) {
		setMovieId(movieID.trim());
		setTitle(title.trim());
		setDiretor(director.trim());
		setGenre(genre.trim());
		setYear(releaseYear.trim());
		setRating(rating.trim());

	}

	public Movie() {

	}

	public static boolean cheakMovieId(String id) {
		if (id.isEmpty()) {
			return false;
		}
		for (int i = 0; i < id.length(); i++) {
			if (!Character.isDigit(id.charAt(i))) {
				return false; // wrong entery

			}

		}
		return true; // write entery
	}

	public void setMovieId(String id) {
		if (cheakMovieId(id)) {
			this.movieID = id;
		}

	}

	public String getMovieID() {
		return movieID;
	}



	public static boolean isValidTitle(String Movietitle) {
		if (Movietitle == null || Movietitle.trim().isEmpty()) {
			return false;
		}

		for (int i = 0; i <Movietitle.length(); i++) {
			char c = Movietitle.charAt(i);
			if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
				return false;
			}
		}

		return true;
	}

	public void setTitle(String title) {
		if (isValidTitle(title)) {
			this.title = title;
		}

	}

	public String getTitle() {
		return title;
	}

	public static boolean isValidDirector(String director) {
		if (director == null || director.trim().isEmpty()) {
			return false;
		}

		for (int i = 0; i < director.length(); i++) {
			if (!(Character.isLetter(director.charAt(i)) || director.charAt(i) == ' ')) {

				return false; // if the user enter special char not alloweded
			}

		}
		return true;

	}

	public void setDiretor(String director) {
		if (isValidDirector(director)) {
			this.director = director;
		}

	}

	public String getDirector() {
		return director;
	}

	public static boolean isValidGenre(String genre) {
		if (genre.equalsIgnoreCase("TV Show") || genre.equalsIgnoreCase("Documentary")
				|| genre.equalsIgnoreCase("Short Film") || genre.equalsIgnoreCase("Animation")
				|| genre.equalsIgnoreCase("Sci-Fi")) {
			return true;

		}

		if (genre.isEmpty()) {
			return false;
		}
		return false;

	}

	public void setGenre(String genre) {
		if (isValidGenre(genre)) {
			this.genre = genre;
		}

	}

	public String getGenre() {
		return genre;
	}

	public static boolean isValidYear(String year) {
		if (year.length() != 4) {
			return false;
		}
		if (year.isEmpty()) {
			return false;
		}
		for (int i = 0; i < year.length(); i++) {
			if (!Character.isDigit(year.charAt(i))) {
				return false;
			}

		}
		return true;

	}

	public void setYear(String year) {
		if (isValidYear(year)) {
			this.releaseYear = year;
		}

	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public static boolean isValidRating(String rate) {
		if (rate.isEmpty()) {
			return false;

		}
		return rate.equals("1") || rate.equals("2") || rate.equals("3") || rate.equals("4") || rate.equals("5");
	}

	public void setRating(String rate) {
		if (isValidRating(rate)) {
			this.rating = rate;
		}

	}

	public String getRating() {
		return rating;
	}

	@Override
	public String toString() {
		return getMovieID() + "," + getTitle() + "," + getDirector() + "," + getGenre() + "," + getReleaseYear() + ","
				+ getRating();
	}

	public static void alertType(String massege, String header) {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(" look !!! ");
		alert.setHeaderText(header);
		alert.setContentText(massege);
		alert.showAndWait();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		Movie m = (Movie) obj;
		return movieID != null && m.getMovieID().trim().equalsIgnoreCase(movieID) && title != null
				&& m.getTitle().trim().equalsIgnoreCase(title) && director != null
				&& m.getDirector().trim().equalsIgnoreCase(director) && genre != null
				&& m.getGenre().trim().equalsIgnoreCase(genre) && releaseYear != null
				&& m.getReleaseYear().trim().equalsIgnoreCase(releaseYear) && rating != null
				&& m.getRating().trim().equalsIgnoreCase(rating);

	}

	public boolean duplicateID(Object obj) {

		if (obj == null) {
			return false;
		}
		Movie m = (Movie) obj;
		return movieID != null && m.getMovieID() != null && m.getMovieID().trim().equalsIgnoreCase(movieID.trim());

	}

	public boolean duplicateid(String id) {
		return movieID != null && id != null && movieID.trim().equalsIgnoreCase(id.trim());

	}

}
