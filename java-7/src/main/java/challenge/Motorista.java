package challenge;


import java.util.Objects;

public class Motorista {

    private final String nome;

    private final int idade;

    private final int pontos;

    private final String habilitacao;

    private Motorista(String nome, int idade, int pontos, String habilitacao) {
        this.nome = hasName(nome);
        this.idade = idade;
        this.pontos = pontos;
        this.habilitacao = hasDriverLicense(habilitacao);
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    private String hasName(String nome) {
        if (nome == null) {
            throw new NullPointerException("Motorista não tem nome.");
        }
        return nome;
    }

    private String hasDriverLicense(String habilitacao) {
        if (habilitacao == null) {
            throw new NullPointerException("Motorista não tem habilitação.");
        }
        return habilitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pontos=" + pontos +
                ", habilitacao='" + habilitacao + '\'' +
                '}';
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }

    public static class MotoristaBuilder {

        private String nome;

        private int idade;

        private int pontos;

        private String habilitacao;

        public MotoristaBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            this.idade = hasInvalidAge(idade);
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            this.pontos = hasInvalidScore(pontos);
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            this.habilitacao = habilitacao;
            return this;
        }

        public Motorista build() {
            return new Motorista(nome, idade, pontos, habilitacao);
        }

        private int hasInvalidAge(int idade) {
            if (idade < 0) {
                throw new IllegalArgumentException("\n\nMotorista com idade inválida.");
            }
            return idade;
        }

        private int hasInvalidScore(int pontos) {
            if (pontos < 0) {
                throw new IllegalArgumentException("Motorista com pontuação inválida.");
            }
            return pontos;
        }
    }
}
