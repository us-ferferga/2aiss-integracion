package aiss.model.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import aiss.model.Genero;
import aiss.model.Idioma;
import aiss.model.Movie;
import util.Helper;

public class MovieRepository implements IMovieRepository {

	List<Movie> movies = new ArrayList<Movie>();
	private static IMovieRepository instance = null;

	public static IMovieRepository getInstance() {
		if (instance == null) {
			instance = new MovieRepository();
			instance.init();
		}

		return instance;
	}

	public MovieRepository() {

	}

	public void init() {
		File filePath = new File("WEB-INF/data/NetflixOriginals.csv");
		List<String> lines = Helper.leerLineas(filePath.toString());
		for (int i = 1; i < lines.size(); i++) {
			String s = lines.get(i);

			String[] trozos = s.split(";");
			String title = trozos[0].trim();
			String[] tmpGenres = trozos[1].split("/");
			List<Genero> genres = new ArrayList<Genero>();
			for (String g : tmpGenres) {
				List<String> tmpGenres2 = Arrays.asList(g.trim().split(" ")).stream().map(t -> t.replace("-", " "))
						.collect(Collectors.toList());

				for (String ss : tmpGenres2) {
					if (ss.trim().contains(" ")) {
						for (String sss : ss.trim().split(" ")) {
							sss = sss.substring(0, 1).toUpperCase() + sss.substring(1);
							genres.add(Genero.valueOf(sss));
						}
					} else {
						ss = ss.substring(0, 1).toUpperCase() + ss.substring(1);
						genres.add(Genero.valueOf(ss));
					}
				}
			}

			String premiere = trozos[2];
			Integer runtime = Integer.valueOf(trozos[3].trim());
			Double imdbScore = Double.valueOf(trozos[4].trim());
			String[] tmpLanguages = trozos[5].split("/");
			List<Idioma> languages = new ArrayList<Idioma>();
			for (String l : tmpLanguages) {
				languages.add(Idioma.valueOf(l));
			}

			movies.add(new Movie(title, genres, languages, premiere, runtime, imdbScore));
		}
	}

	@Override
	public Movie getMovie(String movieId) {
		try {
			return movies.stream().filter(m -> m.getId().contains(movieId)).findFirst().get();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void deleteMovie(Movie mov) {
		movies.remove(mov);
	}

	@Override
	public List<Movie> getAllMovies() {
		return new ArrayList<Movie>(movies);
	}

	public void addMovie(Movie mov) {
		movies.add(mov);
	}
}
