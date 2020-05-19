package challenge;

public class Player {

    private String nationality, club, fullName, releaseClause, wage, birthDate, age;

    public Player(String nationality, String club, String fullName, String releaseClause, String wage, String birthDate, String age) {
        this.nationality = nationality;
        this.club = club;
        this.fullName = fullName;
        this.releaseClause = releaseClause;
        this.wage = wage;
        this.birthDate = birthDate;
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public String getClub() {
        return club;
    }

    public String getFullName() {
        return fullName;
    }

    public Double getReleaseClause() {
        return !releaseClause.isEmpty() ? Double.parseDouble(releaseClause) : 0;
    }

    public String getWage() {
        return wage;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return Integer.parseInt(age);
    }

    @Override
    public String toString() {
        return "Player{" +
                "nationality='" + nationality + '\'' +
                ", club='" + club + '\'' +
                ", fullName='" + fullName + '\'' +
                ", releaseClause='" + releaseClause + '\'' +
                ", wage='" + wage + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return nationality.equals(player.nationality) &&
                club.equals(player.club) &&
                fullName.equals(player.fullName) &&
                releaseClause.equals(player.releaseClause) &&
                wage.equals(player.wage) &&
                birthDate.equals(player.birthDate) &&
                age.equals(player.age);
    }
}
