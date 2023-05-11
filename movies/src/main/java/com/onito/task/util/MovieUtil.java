package com.onito.task.util;

public interface MovieUtil {

	String GET_LONG_DURATION_MOVIES = "SELECT tconst, primaryTitle, runtimeMinutes, genres FROM movies order by runtimeMinutes DESC limit 10";
	String ADD_NEW_MOVIE = "INSERT INTO movies(tconst, titleType, primaryTitle, runtimeMinutes, genres) values (?,?,?,?,?)";
	String GET_LIST_OF_MOVIES_ON_AVERAGE_RATINGS = "SELECT m.tconst, m.primaryTitle, m.genres, r.averageRating FROM movies m JOIN ratings r ON r.tconst = m.tconst WHERE r.averageRating > ? ORDER BY r.averageRating ASC";
	String INCREMENT_MOVIES = "UPDATE movies set runtimeMinutes = runtimeMinutes + case when genres = 'Documentary' THEN 15 when genres = 'Animation' THEN 30 ELSE 45 end";
	String GENRE_MOVIE_WITH_SUB_TOTAL = "SELECT m.genres, m.primaryTitle, r.numVotes, SUM(r.numVotes) OVER (PARTITION BY m.genres) as total FROM movies m JOIN ratings r ON m.tconst = r.tconst ORDER BY m.genres ASC, total DESC;";
}
