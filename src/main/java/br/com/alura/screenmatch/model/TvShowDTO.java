package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record TvShowDTO(String title, Integer totalSeasons, @JsonAlias("imdbRating") String rating) {
}