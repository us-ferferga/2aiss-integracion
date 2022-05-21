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
import aiss.model.Idioma;
import aiss.model.Movie;
import aiss.model.repository.IMovieRepository;
import aiss.model.repository.MovieRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/movies")
public class MovieResource {

	public static MovieResource _instance = null;
	IMovieRepository repository;

	private MovieResource() {
		repository = MovieRepository.getInstance();
	}

	public static MovieResource getInstance() {
		if (_instance == null) {
			_instance = new MovieResource();
		}

		return _instance;
	}

	@GET
	@Produces("application/json")
	public Collection<Movie> getAll(@QueryParam("q") String q, @QueryParam("sortBy") String order,
			@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset,
			@QueryParam("hasLanguages") String languagesStr,
			@QueryParam("hasGenres") String genresStr) {
		List<Idioma> languages = new ArrayList<Idioma>();
		List<Genero> genres = new ArrayList<Genero>();
		List<Movie> result = new ArrayList<Movie>();
		List<Movie> movies = repository.getAllMovies();
		int start = offset == null ? 0 : offset - 1;
		int end = limit == null ? movies.size() : start + limit;
		
		if (languagesStr != null) {
			languages = Arrays.asList(languagesStr.split(",")).stream()
				.map(s -> Idioma.valueOf(s)).collect(Collectors.toList());
		}
		
		if (genresStr != null) {
			genres = Arrays.asList(genresStr.split(",")).stream()
				.map(s -> Genero.valueOf(s)).collect(Collectors.toList());
		}

		for (int i = start; i < end; i++) {
			Movie m = movies.get(i);
			
			if (languages.size() > 0 && !m.getLanguages().containsAll(languages)) {
				continue;
			}
			
			if (genres.size() > 0 && !m.getGenres().containsAll(genres)) {
				continue;
			}

			if (q == null) {
				result.add(m);
			} else if (m.getTitle().contains(q) || m.getPremiere().contains(q)) {
				result.add(m);
			}
		}

		if (order != null) {
			if (order.equals("title"))
				Collections.sort(result, Comparator.comparing(Movie::getTitle));
			else if (order.equals("-title"))
				Collections.sort(result, Comparator.comparing(Movie::getTitle).reversed());
			else if (order.equals("premiere"))
				Collections.sort(result, Comparator.comparing(Movie::getPremiere));
			else if (order.equals("-premiere"))
				Collections.sort(result, Comparator.comparing(Movie::getPremiere).reversed());
			if (order.equals("runtime"))
				Collections.sort(result, Comparator.comparing(Movie::getRuntime));
			else if (order.equals("-runtime"))
				Collections.sort(result, Comparator.comparing(Movie::getRuntime).reversed());
		}
		return result;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response get(@PathParam("id") String movieId) {
		ResponseBuilder resp = Response.ok();
		Movie movie = repository.getMovie(movieId);

		if (movie == null) {
			resp = Response.status(404);
		} else {
			resp.entity(movie);
		}

		return resp.build();
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addMovie(@Context UriInfo uriInfo, Movie movie) {
		if (movie.getTitle() == null || "".equals(movie.getTitle())) {
			throw new NotFoundException("The title of the movie must not be null");
		}	

		repository.addMovie(movie);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(movie.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(movie);
		return resp.build();
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updateMovie(@PathParam("id") String movieId, Movie movie) {

		Movie originalMovie = repository.getMovie(movieId);
		if (originalMovie == null) {
			throw new NotFoundException("The movie with id=" + movieId + " was not found");
		}

		// Update title
		if (movie.getTitle() != null) {
			originalMovie.setTitle(movie.getTitle());
		}

		// Update premiere
		if (movie.getPremiere() != null) {
			originalMovie.setPremiere(movie.getPremiere());
		}

		// Update runtime
		if (movie.getRuntime() != null) {
			originalMovie.setRuntime(movie.getRuntime());
		}

		// Update imdbScore
		if (movie.getImdbScore() != null) {
			originalMovie.setImdbScore(movie.getImdbScore());
		}

		// Update genres
		if (movie.getGenres() != null) {
			originalMovie.setGenres(movie.getGenres());
		}

		// Update languages
		if (movie.getLanguages() != null) {
			originalMovie.setLanguages(movie.getLanguages());
		}

		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeMovie(@PathParam("id") String movieId) {
		Movie toberemoved = repository.getMovie(movieId);
		if (toberemoved == null) {
			throw new NotFoundException("The movie with id=" + movieId + " was not found");
		} else {
			repository.deleteMovie(toberemoved);
		}

		return Response.noContent().build();
	}
}
