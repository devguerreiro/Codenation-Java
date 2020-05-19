package challenge;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	private static final String FILE_PATH = "src/main/resources/data.csv";

	CSVReader csvReader;
	List<String> title_line;
	List<Player> allPlayers = new ArrayList<>();

	{
		try {
			csvReader = new CSVReader(new FileReader(FILE_PATH));
			title_line = Arrays.asList(csvReader.readNext());
			createAllPlayers();
			csvReader.close();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}

	private void createAllPlayers() {
		String[] currentLine;

		try {
			while ((currentLine = csvReader.readNext()) != null) {
				Player player = new Player(
						currentLine[getIndexTitleColumn("nationality")],
						currentLine[getIndexTitleColumn("club")],
						currentLine[getIndexTitleColumn("full_name")],
						currentLine[getIndexTitleColumn("eur_release_clause")],
						currentLine[getIndexTitleColumn("eur_wage")],
						currentLine[getIndexTitleColumn("birth_date")],
						currentLine[getIndexTitleColumn("age")]
				);
				allPlayers.add(player);
			};
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}

	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		return (int) allPlayers.parallelStream().map(Player::getNationality).distinct().count();
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		return (int) allPlayers.parallelStream().filter(player -> !player.getClub().isEmpty()).map(Player::getClub).distinct().count();
	}

	// Liste o nome completo (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		return allPlayers.parallelStream().map(Player::getFullName).limit(20).collect(Collectors.toList());
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		return allPlayers.parallelStream().sorted(Comparator.comparing(Player::getReleaseClause).reversed())
				.map(Player::getFullName).limit(10).collect(Collectors.toList());
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		return allPlayers.parallelStream().sorted(Comparator.comparingLong(this::getAgeInDays).
				thenComparing(Player::getWage).reversed()).map(Player::getFullName).limit(10).collect(Collectors.toList());
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		return allPlayers.parallelStream().collect(Collectors.groupingBy(Player::getAge, Collectors.reducing(0, e -> 1, Integer::sum)));
	}

	private Long getAgeInDays(Player player) {
		return ChronoUnit.DAYS.between(LocalDate.parse(player.getBirthDate()), LocalDate.now());
	}

	private int getIndexTitleColumn(String name_column) {
		try {
			return title_line.indexOf(name_column);
		} catch (NullPointerException e) {
			throw new NullPointerException("Coluna não encontrada.");
		}
	}
}
