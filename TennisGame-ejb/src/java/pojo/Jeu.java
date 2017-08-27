package pojo;

public class Jeu {
    
    private int score;
    
    private String egAv;

    public Jeu() {
    }

    public Jeu(int score) {
        this.score = score;
    }

    public Jeu(String egAv) {
        this.egAv = egAv;
    }

    public Jeu(int score, String egAv) {
        this.score = score;
        this.egAv = egAv;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEgAv() {
        return egAv;
    }

    public void setEgAv(String egAv) {
        this.egAv = egAv;
    }

    @Override
    public String toString() {
        return "Jeu{" + "score=" + score + ", egAv=" + egAv + '}';
    }
    
    
    
}
