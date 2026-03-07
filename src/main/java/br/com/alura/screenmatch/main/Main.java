package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.Show;
import br.com.alura.screenmatch.model.ShowData;
import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.repository.ShowRepository;
import br.com.alura.screenmatch.service.APIConsumption;
import br.com.alura.screenmatch.service.DataConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private APIConsumption consumption = new APIConsumption();
    private DataConverter converter = new DataConverter();
    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e2f5558";
    private List<ShowData> showData = new ArrayList<>();

    private ShowRepository repository;

    public Main(ShowRepository repository) {
        this.repository = repository;
    }

    public void displayMenu() {
        var option = -1;
        while(option != 0) {
            var menu = """
                    1 - Search shows
                    2 - Search episodes
                    3 - List previously searched shows
                    0 - Quit
                    """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchShowWeb();
                    break;
                case 2:
                    searchEpisodePerShow();
                    break;
                case 3:
                    listSearchedShows();
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void searchShowWeb() {
        ShowData data = getShowData();
        Show show = new Show(data);
        repository.save(show);
        System.out.println(data);
    }

    private ShowData getShowData() {
        System.out.println("Search any show: ");
        var showName = scanner.nextLine();
        var json = consumption.obtainData(URL + showName.replace(" ", "+") + API_KEY);
        ShowData data = converter.obtainData(json, ShowData.class);
        return data;
    }

    private void searchEpisodePerShow(){
        ShowData showData = getShowData();
        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= showData.totalSeasons(); i++) {
            var json = consumption.obtainData(URL + showData.title().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonData seasonData = converter.obtainData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);
    }

    private void listSearchedShows(){
        List<Show> shows = new ArrayList<>();
        shows.stream()
                .sorted(Comparator.comparing(Show::getGenre))
                .forEach(System.out::println);
    }
}