package br.com.alura.screenmatch.model;

import jakarta.persistence.*;
import java.util.OptionalDouble;

@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String title;
    private Integer totalSeasons;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String actors;
    private String poster;
    private String synopsis;

    public Show() {}

    public Show(ShowData showData) {
        this.title = showData.title();
        this.totalSeasons = showData.totalSeasons();
        this.rating = OptionalDouble.of(Double.valueOf(showData.rating())).orElse(0);
        this.genre = Genre.fromString(showData.genre().split(",")[0].trim());
        this.actors = showData.actors();
        this.poster = showData.poster();
        this.synopsis = showData.synopsis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return "Genre: " + genre +
                ", Title='" + title +
                ", Total Seasons=" + totalSeasons +
                ", Rating=" + rating +
                ", Actors='" + actors +
                ", Poster='" + poster +
                ", Synopsis='" + synopsis;
    }
}