package ca.cours5b5.williamsarrazin.IA;

public class Coups {

    private final int colonne;
    private final int score;

    public Coups(int colonne, int score) {

        this.colonne = colonne;
        this.score = score;

    }

    public int getColonne() {
        return this.colonne;
    }

    public int getScore() {
        return this.score;
    }

}
