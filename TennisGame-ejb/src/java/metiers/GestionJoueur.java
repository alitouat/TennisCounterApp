package metiers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import pojo.Jeu;
import pojo.Joueur;

@Stateless
public class GestionJoueur implements GestionJoueurLocal {

    public Joueur gagnerPoint(Joueur j, Joueur jTemp) {
        List<Jeu> points = new ArrayList<>();
        Jeu sc0 = new Jeu(0);
        Jeu sc15 = new Jeu(15);
        Jeu sc30 = new Jeu(30);
        Jeu sc40 = new Jeu(40);
        points.add(sc0);
        points.add(sc15);
        points.add(sc30);
        points.add(sc40);

        jTemp.setScore(j.getScore());

        for (Jeu je : points) {

            System.out.println("---------------------------------");
            System.out.println("je à l'entrée dans la boucle " + je.getScore());
            System.out.println("J1Temp à l'entrée dans la boucle " + jTemp.getScore().getScore());

            // à chaque passage de boucle, si le points du joueur 1 est inférieur au points récupéré dans le tableau
            // et si le score du joueur Temp est égal au score du joueur 1
            if (je.getScore() > jTemp.getScore().getScore() && jTemp.getScore().getScore() == j.getScore().getScore()) {
                j.setScore(je);
                System.out.println("jo1 dans la boucle = " + j.getScore().getScore());

            }
        }

        return j;

    }

    public Joueur fixerScore(Joueur j) {
        return j;
    }

    public Joueur gagnerTieBreak(Joueur jWinner, Joueur jLooser) {
        System.out.println("entrée dans gestion tie-break");
        System.out.println("jwinner tieBreak = "+jWinner.getTiebreak());
        if (jWinner.getTiebreak() < 7 || jWinner.getTiebreak() >= 7 && jWinner.getTiebreak() - jLooser.getTiebreak() < 2) {
            int tb = jWinner.getTiebreak();
            tb++;
            jWinner.setTiebreak(tb);
        }

        return jWinner;
    }

}
