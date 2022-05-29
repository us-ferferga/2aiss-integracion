package aiss.model;

import java.util.List;
import java.util.UUID;

public class Serie {

	private String id = UUID.randomUUID().toString();

	public String toString() {
		return "Series [id=" + id + ", title=" + title + ", genres=" + genres + ", premiere=" + premiere
				+ ", numEpisodes=" + numEpisodes + ", numSeasons=" + numSeasons + "]";
	}

	private String title;
	private List<Genero> genres;
	private String premiere;
	private Integer numEpisodes;
	private Integer numSeasons;
	
	public Serie() {
		
	}

	public Serie(String title, List<Genero> genres, String premiere, Integer numEpisodes, Integer numSeasons) {
		this.title = title;
		this.genres = genres;
		this.premiere = premiere;
		this.numEpisodes = numEpisodes;
		this.numSeasons = numSeasons;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Genero> getGenres() {
		return genres;
	}

	public void setGenres(List<Genero> genres) {
		this.genres = genres;
	}

	public String getPremiere() {
		return premiere;
	}

	public void setPremiere(String premiere) {
		this.premiere = premiere;
	}

	public Integer getNumEpisodes() {
		return numEpisodes;
	}

	public void setNumEpisodes(Integer numEpisodes) {
		this.numEpisodes = numEpisodes;
	}

	public Integer getNumSeasons() {
		return numSeasons;
	}

	public void setNumSeasons(Integer numSeasons) {
		this.numSeasons = numSeasons;
	}
}
