package com.onito.task.service;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onito.task.dao.MoviesDao;
import com.onito.task.model.GenreMoviesOfSubtotal;
import com.onito.task.model.LongestDurationMovies;
import com.onito.task.model.NewMovie;
import com.onito.task.model.TopRatedMovies;

@Service
public class MovieService {
	
	@Autowired
	private MoviesDao moviesDao;
	
	public Optional<List<LongestDurationMovies>> getTopTenLongDurationMovie(Connection conn) {
		return moviesDao.getLongDurationMovies(conn);
	}
	
	public Boolean addNewMovie(NewMovie newMovie, Connection conn) {
		return moviesDao.addNewMovie(newMovie, conn);
	}

	public Optional<List<TopRatedMovies>> getTopRatedMovies(Double averageRating, Connection conn) {
		return moviesDao.getMoviesOnAverageRating(averageRating, conn);
	}
	
	public Optional<List<GenreMoviesOfSubtotal>> getGenreMoviesWithSubtotal(Connection conn) {
		return moviesDao.genreMoviesWithSubtotal(conn);
	}
	
	public Boolean incrementMovies(Connection conn) {
		return moviesDao.incrementMovies(conn);
	}
}
