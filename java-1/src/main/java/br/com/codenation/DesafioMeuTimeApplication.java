package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private HashSet<Team> teamsList = new HashSet<>();
	private HashSet<Player> playersList = new HashSet<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		Team newTeam = Team.builder()
				.withId(id)
				.withName(nome)
				.createdOn(dataCriacao)
				.withMainUniformColor(corUniformePrincipal)
				.withSubstituteUniformColor(corUniformeSecundario)
				.build();
		verifyTeamAlreadyExist(newTeam);

		teamsList.add(newTeam);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		Player newPlayer = Player.builder()
				.withId(id)
				.ofTeam(idTime)
				.withName(nome)
				.bornOn(dataNascimento)
				.withHabilityLevel(nivelHabilidade)
				.withSalary(salario)
				.build();
		verifyPlayerAlreadyExist(newPlayer);

		Team objectTeam = getTeam(idTime);
		objectTeam.addPlayer(newPlayer);

		playersList.add(newPlayer);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Player newCaptain = getPlayer(idJogador);
		newCaptain.changeCaptainStatus();

		Team team = getTeam(newCaptain.getIdTeam());
		Player oldCaptain = team.getCaptain();

		if (oldCaptain != null) {
			oldCaptain.changeCaptainStatus();
		}
		team.setCaptain(newCaptain);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Player captain = getTeam(idTime).getCaptain();
		if (captain != null) {
			return captain.getId();
		}
		throw new CapitaoNaoInformadoException();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return getPlayer(idJogador).getName();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return getTeam(idTime).getName();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Team team = getTeam(idTime);
		List<Player> playersList = team.getPlayers();

		return playersList.parallelStream().map(Player::getId).
				collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Team team = getTeam(idTime);
		List<Player> playersList = team.getPlayers();

		return playersList.parallelStream().
				max(Comparator.comparing(Player::getHabilityLevel)).get().getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Team team = getTeam(idTime);
		List<Player> playersList = team.getPlayers();

		if (playersList.size() == 0) {
			throw new TimeSemJogadorException();
		}
		return playersList.parallelStream().
				max(Comparator.comparing(Player::getAgeInDays)).get().getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {

		if (verifyHasTeam()) {
			return teamsList.parallelStream().map(Team::getId).
					collect(Collectors.toList());
		}
		return new ArrayList<Long>();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Team team = getTeam(idTime);
		List<Player> playersList = team.getPlayers();

		return playersList.parallelStream().max(Comparator.comparing(Player::getSalary)).get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return getPlayer(idJogador).getSalary();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		if (verifyHasPlayer()) {
			return playersList.parallelStream().
					sorted(Comparator.comparingInt(Player::getHabilityLevel).reversed().thenComparing(Player::getId))
					.map(Player::getId).limit(top).collect(Collectors.toList());
		}
		return new ArrayList<Long>();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Team houseTeam = getTeam(timeDaCasa);
		Team streetTeam = getTeam(timeDeFora);

		if (houseTeam.getMainUniformColor().equals(streetTeam.getMainUniformColor())) {
			return streetTeam.getSubstituteUniformColor();
		}
		return streetTeam.getMainUniformColor();
	}

	private Player getPlayer(Long id) {
		Player objectPlayer = playersList.parallelStream().
				filter(player -> player.getId().equals(id)).
				findFirst().orElse(null);

		if (objectPlayer != null) {
			return objectPlayer;
		}
		throw new JogadorNaoEncontradoException();
	}

	private Team getTeam(Long id) {
		Team objectTeam = teamsList.parallelStream().
				filter(team -> team.getId().equals(id)).
				findFirst().orElse(null);

		if (objectTeam != null) {
			return objectTeam;
		}
		throw new TimeNaoEncontradoException();
	}

	private void verifyTeamAlreadyExist(Team team) {
		if (teamsList.contains(team)) {
			throw new IdentificadorUtilizadoException();
		}
	}

	private void verifyPlayerAlreadyExist(Player player) {
		if (playersList.contains(player)) {
			throw new IdentificadorUtilizadoException();
		}
	}

	private boolean verifyHasTeam() {
		return teamsList.size() > 0;
	}

	private boolean verifyHasPlayer() {
		return playersList.size() > 0;
	}
}
