package com.onito.task.model;

public class GenreMoviesOfSubtotal {
	private String genres;
	private String primaryTtitle;
	private Integer numVotes;
	private Integer total;

	public GenreMoviesOfSubtotal(String genres, String primaryTtitle, Integer numVotes, Integer total) {
		this.genres = genres;
		this.primaryTtitle = primaryTtitle;
		this.numVotes = numVotes;
		this.total = total;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getPrimaryTtitle() {
		return primaryTtitle;
	}

	public void setPrimaryTtitle(String primaryTtitle) {
		this.primaryTtitle = primaryTtitle;
	}

	public Integer getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(Integer numVotes) {
		this.numVotes = numVotes;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
