package web;

import java.util.ArrayList;
import java.util.List;
import metier.Genre;

public class GenreModel {
	List<Genre> Genre = new ArrayList<>();

	public List<Genre>  getGenre() {
		return Genre;
	}

	public void setGenre(List<Genre> Genre) {
		this.Genre = Genre;
	}
}