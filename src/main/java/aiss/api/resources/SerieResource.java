package aiss.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.NotFoundException;

import javax.ws.rs.core.Response.ResponseBuilder;

import aiss.model.Genero;
import aiss.model.Serie;
import aiss.model.repository.ISerieRepository;
import aiss.model.repository.SerieRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/series")
public class SerieResource {

	public static SerieResource _instance = null;
	ISerieRepository repository;

	private SerieResource() {
		repository = SerieRepository.getInstance();
	}

	public static SerieResource getInstance() {
		if (_instance == null) {
			_instance = new SerieResource();
		}

		return _instance;
	}

	@GET
	@Produces("application/json")
	public Collection<Serie> getAll(@QueryParam("q") String q, @QueryParam("sortBy") String order,
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset,
			@QueryParam("hasGenres") String genresStr) {
		List<Genero> genres = new ArrayList<Genero>();
		List<Serie> result = new ArrayList<Serie>();
		List<Serie> series = repository.getAllSeries();
		int start = offset == null ? 0 : offset - 1;
		int end = limit == null ? series.size() : start + limit;
		
		if (genresStr != null) {
			genres = Arrays.asList(genresStr.split(",")).stream()
				.map(s -> Genero.valueOf(s)).collect(Collectors.toList());
		}

		for (int i = start; i < end; i++) {
			Serie s = series.get(i);
			
			if (genres.size() > 0 && !s.getGenres().containsAll(genres)) {
				continue;
			}

			if (q == null) {
				result.add(s);
			} else if (s.getTitle().contains(q) || s.getPremiere().contains(q)) {
				result.add(s);
			}
		}

		if (order != null) {
			if (order.equals("title"))
				Collections.sort(result, Comparator.comparing(Serie::getTitle));
			else if (order.equals("-title"))
				Collections.sort(result, Comparator.comparing(Serie::getTitle).reversed());
			else if (order.equals("premiere"))
				Collections.sort(result, Comparator.comparing(Serie::getPremiere));
			else if (order.equals("-premiere"))
				Collections.sort(result, Comparator.comparing(Serie::getPremiere).reversed());
		}
		return result;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response get(@PathParam("id") String serieId) {
		ResponseBuilder resp = Response.ok();
		Serie series = repository.getSerie(serieId);

		if (series == null) {
			resp = Response.status(404);
		} else {
			resp.entity(series);
		}

		return resp.build();
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSerie(@Context UriInfo uriInfo, Serie series) {
		if (series.getTitle() == null || "".equals(series.getTitle().trim())) {
			throw new NotFoundException("The title of the serie must not be null");
		}

		repository.addSerie(series);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(series.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(series);
		return resp.build();
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updateSerie(@PathParam("id") String serieId, Serie series) {

		Serie originalSerie = repository.getSerie(serieId);
		if (originalSerie == null) {
			throw new NotFoundException("The movie with id=" + serieId + " was not found");
		}

		// Update title
		if (series.getTitle() != null) {
			originalSerie.setTitle(series.getTitle());
		}

		// Update premiere
		if (series.getPremiere() != null) {
			originalSerie.setPremiere(series.getPremiere());
		}

		// Update numEpisodes
		if (series.getNumEpisodes() != null) {
			originalSerie.setNumEpisodes(series.getNumEpisodes());
		}

		// Update numSeasons
		if (series.getNumSeasons() != null) {
			originalSerie.setNumSeasons(series.getNumSeasons());
		}

		// Update genres
		if (series.getGenres() != null) {
			originalSerie.setGenres(series.getGenres());
		}

		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeSerie(@PathParam("id") String serieId) {
		Serie toberemoved = repository.getSerie(serieId);
		if (toberemoved == null) {
			throw new NotFoundException("The serie with id=" + serieId + " was not found");
		} else {
			repository.deleteSerie(toberemoved);
		}

		return Response.noContent().build();
	}
}
