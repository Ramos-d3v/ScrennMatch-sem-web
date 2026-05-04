package br.com.alura.ScreenMatch;

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
		Scanner input = new Scanner(System.in);

		System.out.println("Digite o filme que quer ver as informações");
		String filme = input.nextLine();
		var endereco = "https://www.omdbapi.com/?t=" + filme.replace(" ", "+") + API_KEY;

		var json = consumoApi.obterDados(endereco);
		System.out.println(json);
		ConverteDados converser = new ConverteDados();
		DadosSerie dados = converser.obterDados(json, DadosSerie.class);
		System.out.println(dados);

		input.close();
	}

}
