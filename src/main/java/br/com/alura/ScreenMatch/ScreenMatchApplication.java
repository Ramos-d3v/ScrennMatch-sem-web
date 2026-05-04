package br.com.alura.ScreenMatch;

import br.com.alura.ScreenMatch.model.DadosEpisodio;
import br.com.alura.ScreenMatch.model.DadosSerie;
import br.com.alura.ScreenMatch.services.ConsumoApi;
import br.com.alura.ScreenMatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var API_KEY = "&apikey=ce09a206";
		var serie = "Breaking Bad";
		var endereco = "https://www.omdbapi.com/?t=" + serie.replace(" ", "+") + API_KEY;

		var json = consumoApi.obterDados(endereco);
		System.out.println(json);

		ConverteDados converser = new ConverteDados();
		DadosSerie dados = converser.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		
		// Corrigido o replace para colocar "+" no lugar dos espaços
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=" + dados.titulo().replace(" ", "+") + "&Season=1&episode=3" + API_KEY);
		DadosEpisodio dadosEpisodio = converser.obterDados(json, DadosEpisodio.class);

		System.out.println(dadosEpisodio);

	}

}
