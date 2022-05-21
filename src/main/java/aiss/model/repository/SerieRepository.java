package aiss.model.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import aiss.model.Genero;
import aiss.model.Serie;
import util.Helper;

public class SerieRepository implements ISerieRepository {
	List<Serie> series = new ArrayList<Serie>();
	private static ISerieRepository instance = null;

	public static ISerieRepository getInstance() {
		if (instance == null) {
			instance = new SerieRepository();
			instance.init();
		}

		return instance;
	}

	public SerieRepository() {

	}

	public void init() {
		File currentPath = new File(System.getProperty("user.dir"));
		File filePath = new File(
				currentPath.getParentFile().getParentFile().getAbsolutePath() + "\\data\\netflixSeries.csv");
		List<String> lines = Helper.leerLineas(filePath.toString());
		for (int i = 1; i < lines.size(); i++) {
			String s = lines.get(i);
			String[] trozos = s.split(";");
			String title = trozos[0].trim();
			String[] tmpGenres = trozos[1].split(" ");
			List<Genero> genres = new ArrayList<Genero>();
			for (String g : tmpGenres) {
				g = g.substring(0, 1).toUpperCase() + g.substring(1);
				genres.add(Genero.valueOf(g.trim()));
			}

			String premiere = trozos[2].trim();
			Integer numSeasons = Integer.valueOf(trozos[3].trim());
			Integer numEpisodes = Integer.valueOf(trozos[4].trim());

			series.add(new Serie(title, genres, premiere, numEpisodes, numSeasons));

		}
	}

	public Serie getSerie(String serieId) {
		try {
			return series.stream().filter(m -> m.getId().contains(serieId)).findFirst().get();
		} catch (Exception e) {
			return null;
		}

	}

	public void deleteSerie(Serie ser) {
		series.remove(ser);
	}

	public List<Serie> getAllSeries() {
		return new ArrayList<Serie>(series);
	}

	public void addSerie(Serie ser) {
		series.add(ser);
	}
}
