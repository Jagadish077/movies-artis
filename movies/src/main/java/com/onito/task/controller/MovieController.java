package com.onito.task.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.onito.task.dbconfig.ConnectionPool;
import com.onito.task.exceptionhandler.InvalidRequestException;
import com.onito.task.exceptionhandler.NoDataFoundException;
import com.onito.task.model.ErrorResponse;
import com.onito.task.model.GenericResponse;
import com.onito.task.model.GenericResponseModel;
import com.onito.task.model.LongestDurationMovies;
import com.onito.task.model.NewMovie;
import com.onito.task.model.TopRatedMovies;
import com.onito.task.service.MovieService;

import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping("/api/v1")
public class MovieController implements WebMvcConfigurer{
	
	@Autowired
	private ConnectionPool connectionPool;
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/longest-duration-movies")
	public GenericResponse<GenericResponseModel<List<LongestDurationMovies>, ErrorResponse>> getMovies() throws SQLException {
		try(Connection conn = connectionPool.getConnection()) {
			Optional<List<LongestDurationMovies>> optionalMovies = movieService.getTopTenLongDurationMovie(conn);
			if (optionalMovies.isPresent()) {
				return new GenericResponse<>(new GenericResponseModel<>(optionalMovies.get(), null), HttpStatus.OK);
			}
		}
		throw new NoDataFoundException("No movies found");
	}
	
	@PostMapping("/new-movie")
	public GenericResponse<GenericResponseModel<String, ErrorResponse>> addMovie(@Valid @RequestBody NewMovie movie) throws SQLException {
		try(Connection conn = connectionPool.getConnection()) {
			Boolean isMovieAdded = movieService.addNewMovie(movie, conn);
			if (isMovieAdded) {
				return new GenericResponse<>(new GenericResponseModel<>("success", null), HttpStatus.OK);
			}
		}
		throw new InvalidRequestException("Failed to add movie");
	}
	
	@PostMapping("/top-rated-movies")
	public GenericResponse<GenericResponseModel<List<TopRatedMovies>, ErrorResponse>> getTopRatedMovies(Double averageRating) throws SQLException {
		try(Connection conn = connectionPool.getConnection()) {
			Optional<List<TopRatedMovies>> optionalTopRatedMovies = movieService.getTopRatedMovies(averageRating, conn);
			if (optionalTopRatedMovies.isPresent()) {
				return new GenericResponse<>(new GenericResponseModel<>(optionalTopRatedMovies.get(), null), HttpStatus.OK);
			}
		}
		throw new NoDataFoundException("No Data found");
	}

}
