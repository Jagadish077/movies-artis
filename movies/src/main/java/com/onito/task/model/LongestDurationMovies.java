package com.onito.task.model;

public class LongestDurationMovies {
	private String tconst;
	private String primaryTitle;
	private Long runtimeMinutes;
	private String genres;

	public LongestDurationMovies(String tconst, String primaryTitle, Long runtimeMinutes, String genres) {
		this.tconst = tconst;
		this.primaryTitle = primaryTitle;
		this.runtimeMinutes = runtimeMinutes;
		this.genres = genres;
	}

	public String getTconst() {
		return tconst;
	}

	public void setTconst(String tconst) {
		this.tconst = tconst;
	}

	public String getPrimaryTitle() {
		return primaryTitle;
	}

	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}

	public Long getRuntimeMinutes() {
		return runtimeMinutes;
	}

	public void setRuntimeMinutes(Long runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

}
