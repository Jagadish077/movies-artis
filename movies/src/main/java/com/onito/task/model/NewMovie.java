package com.onito.task.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewMovie (@NotNull @NotBlank @NotEmpty String tconst, @NotNull @NotBlank @NotEmpty String titleType, @NotNull @NotBlank @NotEmpty String primaryTitle, @Min(value = 1) Long runtimeMinutes, @NotNull @NotBlank @NotEmpty String genres) {
	
}
