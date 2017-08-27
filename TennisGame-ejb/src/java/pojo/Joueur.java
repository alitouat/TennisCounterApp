package pojo;

public class Joueur {
    
    private String nom;
    private Jeu score;
    private Set set;
    private int manche;
    private int tiebreak;

    public Joueur() {
    }

    public Joueur(String nom) {
        this.nom = nom;
    }

    public Joueur(String nom, Jeu score) {
        this.nom = nom;
        this.score = score;
    }

    public Joueur(String nom, Jeu score, Set set) {
        this.nom = nom;
        this.score = score;
        this.set = set;
    }

    public Joueur(String nom, Jeu score, Set set, int manche) {
        this.nom = nom;
        this.score = score;
        this.set = set;
        this.manche = manche;
    }

    public Joueur(String nom, Jeu score, Set set, int manche, int tiebreak) {
        this.nom = nom;
        this.score = score;
        this.set = set;
        this.manche = manche;
        this.tiebreak = tiebreak;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Jeu getScore() {
        return score;
    }

    public void setScore(Jeu score) {
        this.score = score;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public int getManche() {
        return manche;
    }

    public void setManche(int manche) {
        this.manche = manche;
    }
    
    public int getTiebreak() {
        return tiebreak;
    }

    public void setTiebreak(int tiebreak) {
        this.tiebreak = tiebreak;
    }
    
    
    
    @Override
    public String toString() {
        return "Joueur{" + "nom=" + nom + ", score=" + score + ", set=" + set + '}';
    }

}
