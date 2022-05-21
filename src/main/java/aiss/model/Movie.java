package aiss.model;

import java.util.List;
import java.util.UUID;

public class Movie {

	private String id = UUID.randomUUID().toString();

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genres=" + genres + ", languages=" + languages
				+ ", premiere=" + premiere + ", runtime=" + runtime + ", imdbScore=" + imdbScore + "]";
	}

	private String title;
	private List<Genero> genres;
	private List<Idioma> languages;
	private String premiere;
	private Integer runtime;
	private Double imdbScore;

	public Movie() {

	}

	public Movie(String title, List<Genero> genres, List<Idioma> languages, String premiere, Integer runtime,
			Double imdbScore) {
		this.title = title;
		this.genres = genres;
		this.languages = languages;
		this.premiere = premiere;
		this.runtime = runtime;
		this.imdbScore = imdbScore;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<Genero> getGenres() {
		return genres;
	}

	public List<Idioma> getLanguages() {
		return languages;
	}

	public String getPremiere() {
		return premiere;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public Double getImdbScore() {
		return imdbScore;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenres(List<Genero> genres) {
		this.genres = genres;
	}

	public void setLanguages(List<Idioma> languages) {
		this.languages = languages;
	}

	public void setPremiere(String premiere) {
		this.premiere = premiere;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public void setImdbScore(Double imdbScore) {
		this.imdbScore = imdbScore;
	}

}
