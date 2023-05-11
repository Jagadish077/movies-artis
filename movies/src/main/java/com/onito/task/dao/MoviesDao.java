package com.onito.task.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.onito.task.exceptionhandler.InvalidRequestException;
import com.onito.task.model.GenreMoviesOfSubtotal;
import com.onito.task.model.LongestDurationMovies;
import com.onito.task.model.NewMovie;
import com.onito.task.model.TopRatedMovies;
import com.onito.task.util.Keys;
import com.onito.task.util.MovieUtil;

@Component
public class MoviesDao {
	public Optional<List<LongestDurationMovies>> getLongDurationMovies(Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(MovieUtil.GET_LONG_DURATION_MOVIES)) {
			try (ResultSet rs = ps.executeQuery()) {
				List<LongestDurationMovies> movies = new ArrayList<>();
				while (rs.next()) {
					movies.add(new LongestDurationMovies(rs.getString(Keys.tconst), rs.getString(Keys.primaryTitle),
							rs.getLong(Keys.runtimeMinutes), rs.getString(Keys.genres)));
				}
				return movies.size() > 0 ? Optional.of(movies) : Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Boolean addNewMovie(NewMovie newMovie, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(MovieUtil.ADD_NEW_MOVIE)) {
			ps.setString(1, newMovie.tconst());
			ps.setString(2, newMovie.titleType());
			ps.setString(3, newMovie.primaryTitle());
			ps.setLong(4, newMovie.runtimeMinutes());
			ps.setString(5, newMovie.genres());
			Integer isNewMovieAdded = ps.executeUpdate();
			if (isNewMovieAdded > 0) {
				return true;
			}
		} catch (SQLIntegrityConstraintViolationException unique) {
			throw new InvalidRequestException("Duplicate tconst, data already exist");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Optional<List<TopRatedMovies>> getMoviesOnAverageRating(Double averageRating, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(MovieUtil.GET_LIST_OF_MOVIES_ON_AVERAGE_RATINGS)) {
			ps.setDouble(1, averageRating);
			try (ResultSet rs = ps.executeQuery()) {

				List<TopRatedMovies> topRatedMovies = new ArrayList<>();
				while (rs.next()) {
					topRatedMovies.add(new TopRatedMovies(rs.getString(Keys.tconst), rs.getString(Keys.primaryTitle),
							rs.getString(Keys.genres), rs.getDouble(Keys.averageRating)));
				}
				return topRatedMovies.size() > 0 ? Optional.of(topRatedMovies) : Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Boolean incrementMovies(Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(MovieUtil.INCREMENT_MOVIES)) {
			return ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Optional<List<GenreMoviesOfSubtotal>> genreMoviesWithSubtotal(Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(MovieUtil.GENRE_MOVIE_WITH_SUB_TOTAL);
				ResultSet rs = ps.executeQuery()) {
			List<GenreMoviesOfSubtotal> genreMoviesOfSubtotals = new ArrayList<>();
			while (rs.next()) {
				genreMoviesOfSubtotals.add(new GenreMoviesOfSubtotal(rs.getString(Keys.genres),
						rs.getString(Keys.primaryTitle), rs.getInt(Keys.numVotes), rs.getInt(Keys.total)));
			}
			return genreMoviesOfSubtotals.size() > 0 ? Optional.of(genreMoviesOfSubtotals) : Optional.empty();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
