package challenge;

public class CriptografiaCesariana implements Criptografia {

    private int jump = 3;

    @Override
    public String criptografar(String texto) {
        return criptografiaGenerica(texto, +jump);
    }

    @Override
    public String descriptografar(String texto) {
        return criptografiaGenerica(texto, -jump);
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    private String criptografiaGenerica(String texto, int jump) {

        textIsEmpty(texto);

        char newLetter;

        texto = texto.toLowerCase();
        StringBuilder newText = new StringBuilder();

        for(char letter : texto.toCharArray()) {
            if (letter >= 97 && letter <= 122) {
                newLetter = defineNextLetter(letter, jump);
                newText.append(newLetter);
            } else {
                newText.append(letter);
            }
        }
        return newText.toString();
    }

    private char defineNextLetter(char letter, int jump) {
        int calcNextChar = (letter + jump);

        if (calcNextChar < 97) {
            return (char) (123 - (97 - calcNextChar));
        } else if (calcNextChar > 122) {
            return (char) (96 + (calcNextChar - 122));
        }
        return (char) calcNextChar;
    }

    private void textIsEmpty(String text) {
        if(text.isEmpty()) {
            throw new IllegalArgumentException("Parâmetro vazio.");
        }
    }
}
