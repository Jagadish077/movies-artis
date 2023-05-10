package com.onito.task.util;

public interface MovieUtil {

	String GET_LONG_DURATION_MOVIES = "SELECT tconst, primaryTitle, runtimeMinutes, genres FROM movies order by runtimeMinutes DESC limit 10";
	String ADD_NEW_MOVIE = "INSERT INTO movies(tconst, titleType, primaryTitle, runtimeMinutes, genres) values (?,?,?,?,?)";
	
}
