package aiss.model.repository;

import java.util.List;

import aiss.model.Serie;

public interface ISerieRepository {
	void init();

	Serie getSerie(String serieId);

	void deleteSerie(Serie ser);

	List<Serie> getAllSeries();

	void addSerie(Serie ser);
}
