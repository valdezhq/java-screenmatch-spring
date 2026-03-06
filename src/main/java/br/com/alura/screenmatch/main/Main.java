package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.model.SeriesData;
import br.com.alura.screenmatch.model.EpisodeData;
import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.service.APIConsumption;
import br.com.alura.screenmatch.service.DataConverter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private APIConsumption consumption = new APIConsumption();
    private DataConverter converter = new DataConverter();

    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e2f5558";

    public void displayMenu() {
        System.out.println("Type here to search for a TV Show: ");
        var seriesName = scanner.nextLine();
        var json = consumption.obtainData(URL + seriesName.replace(" ", "+") + API_KEY);
        SeriesData data = converter.obtainData(json, SeriesData.class);
        System.out.println(data);

        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= data.totalSeasons(); i++) {
            json = consumption.obtainData(URL + seriesName.replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData SeasonData = converter.obtainData(json, SeasonData.class);
            seasons.add(SeasonData);
        }
        seasons.forEach(System.out::println);

        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));

        List<EpisodeData> episodesData = seasons.stream()
                .flatMap(t -> t.episodes().stream())
                .collect(Collectors.toList());

        List<Episode> episodes = seasons.stream()
                .flatMap(t -> t.episodes().stream()
                .map(d -> new Episode(t.season(), d)))
                .collect(Collectors.toList());

        episodes.forEach(System.out::println);

        Map<Integer, Double> ratingPerSeason = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason, Collectors.averagingDouble(Episode::getRating)));
        System.out.println(ratingPerSeason);

        DoubleSummaryStatistics est = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getRating));

        System.out.println("Average: " + est.getAverage());
        System.out.println("Best episode: " + est.getMax());
        System.out.println("Worse episode: " + est.getMin());
        System.out.println("Number of episodes: " + est.getCount());
    }
}