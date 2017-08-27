package metiers;

import javax.ejb.Stateless;
import pojo.Jeu;
import pojo.Joueur;

@Stateless
public class GestionJeu implements GestionJeuLocal {

    @Override
    public Jeu Avantager(String egAv) {
        Jeu j = new Jeu();
        egAv = "Avantage";
        j.setEgAv(egAv);
        return j;
    }

    @Override
    public Jeu Egaliser(String egAv) {
        Jeu j = new Jeu();
        egAv = "Egalité";
        j.setEgAv(egAv);
        return j;
    }

}
