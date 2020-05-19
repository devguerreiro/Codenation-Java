package br.com.codenation;

import java.time.LocalDate;
import java.util.*;

public class Team {

    private Long id;
    private String name;
    private LocalDate createdOn;
    private String mainUniformColor;
    private String substituteUniformColor;
    private Player captain;
    private List<Player> players;

    public Team(Long id, String name, LocalDate createdOn, String mainUniformColor, String substituteUniformColor) {
        this.id = id;
        this.name = name;
        this.createdOn = createdOn;
        this.mainUniformColor = mainUniformColor;
        this.substituteUniformColor = substituteUniformColor;
        this.players = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getMainUniformColor() {
        return mainUniformColor;
    }

    public String getSubstituteUniformColor() {
        return substituteUniformColor;
    }

    public Player getCaptain() {
        return captain;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdOn=" + createdOn +
                ", mainUniformColor='" + mainUniformColor + '\'' +
                ", substituteUniformColor='" + substituteUniformColor + '\'' +
                ", captain=" + captain +
                ", players=" + players +
                '}';
    }

    public static TeamBuilder builder() {
        return new TeamBuilder();
    }

    public static class TeamBuilder {

        private Long id;
        private String name;
        private LocalDate createdOn;
        private String mainUniformColor;
        private String substituteUniformColor;

        public TeamBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public TeamBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TeamBuilder createdOn(LocalDate createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public TeamBuilder withMainUniformColor(String mainUniformColor) {
            this.mainUniformColor = mainUniformColor;
            return this;
        }

        public TeamBuilder withSubstituteUniformColor(String substituteUniformColor) {
            this.substituteUniformColor = substituteUniformColor;
            return this;
        }

        public Team build() {
            return new Team(id, name, createdOn, mainUniformColor, substituteUniformColor);
        }
    }
}
