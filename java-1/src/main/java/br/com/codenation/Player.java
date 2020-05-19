package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Player {

    private Long id;
    private Long idTeam;
    private String name;
    private LocalDate dateBorn;
    private int habilityLevel;
    private BigDecimal salary;
    private boolean isCaptain;

    public Player(Long id, Long idTeam, String name, LocalDate dateBorn, int habilityLevel, BigDecimal salary) {
        this.id = id;
        this.idTeam = idTeam;
        this.name = name;
        this.dateBorn = dateBorn;
        this.habilityLevel = habilityLevel;
        this.salary = salary;
        this.isCaptain = false;
    }

    public Long getId() {
        return this.id;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public String getName() {
        return name;
    }

    public int getHabilityLevel() {
        return habilityLevel;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Long getAgeInDays() {
        return ChronoUnit.DAYS.between(dateBorn, LocalDate.now());
    }

    public void changeCaptainStatus() {
        this.isCaptain = !this.isCaptain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", idTeam=" + idTeam +
                ", name='" + name + '\'' +
                ", dateBorn=" + dateBorn +
                ", habilityLevel=" + habilityLevel +
                ", salary=" + salary +
                ", isCaptain=" + isCaptain +
                '}';
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    public static class PlayerBuilder {

        private Long id;
        private Long idTeam;
        private String name;
        private LocalDate dateBorn;
        private int habilityLevel;
        private BigDecimal salary;

        public PlayerBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PlayerBuilder ofTeam(Long idTeam) {
            this.idTeam = idTeam;
            return this;
        }

        public PlayerBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PlayerBuilder bornOn(LocalDate dateBorn) {
            this.dateBorn = dateBorn;
            return this;
        }

        public PlayerBuilder withHabilityLevel(int habilityLevel) {
            this.habilityLevel = habilityLevel;
            return this;
        }

        public PlayerBuilder withSalary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Player build() {
            return new Player(id, idTeam, name, dateBorn, habilityLevel, salary);
        }
    }
}
