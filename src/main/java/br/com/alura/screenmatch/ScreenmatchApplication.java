package br.com.alura.screenmatch;

import br.com.alura.screenmatch.service.APIConsumption;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        var apiConsumption = new APIConsumption();
        var json = apiConsumption.obtainData("");
        System.out.println(json);
    }
}
