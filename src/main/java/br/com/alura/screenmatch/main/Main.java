package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.SeriesData;
import br.com.alura.screenmatch.model.EpisodeData;
import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.service.APIConsumption;
import br.com.alura.screenmatch.service.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        for (int i = 1; i <= data.totalSeasons(); i++){
            json = consumption.obtainData(URL + seriesName.replace(" ", "+") +"&season=" + i + API_KEY);
            SeasonData SeasonData = converter.obtainData(json, SeasonData.class);
            seasons.add(SeasonData);
        }
        seasons.forEach(System.out::println);

        seasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));
    }
}