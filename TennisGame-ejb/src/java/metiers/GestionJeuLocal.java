/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import javax.ejb.Local;
import pojo.Jeu;
import pojo.Joueur;

/**
 *
 * @author AlTo
 */
@Local
public interface GestionJeuLocal {
    public Jeu Avantager(String egAv);
    public Jeu Egaliser(String egAv);
}
