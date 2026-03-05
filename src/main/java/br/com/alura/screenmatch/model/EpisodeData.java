package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData (String title,
                           Integer episode,
                           @JsonAlias("imdbRating") String rating,
                           @JsonAlias("release") String releaseDate) {
}