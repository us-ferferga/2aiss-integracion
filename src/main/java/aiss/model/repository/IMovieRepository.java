package aiss.model.repository;

import java.util.List;

import aiss.model.Movie;

public interface IMovieRepository {
	void init();

	Movie getMovie(String movieId);

	void deleteMovie(Movie mov);

	List<Movie> getAllMovies();

	void addMovie(Movie mov);
}