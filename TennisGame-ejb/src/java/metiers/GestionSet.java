/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import pojo.Joueur;
import pojo.Set;

/**
 *
 * @author AlTo
 */
@Stateless
public class GestionSet implements GestionSetLocal {

    public Set donnerPoint(Joueur jTemp, Joueur J) {

        // Création d'ojets Set, et d'une collection d'objets Set les contenant
        Set set0 = new Set(0);
        Set set1 = new Set(1);
        Set set2 = new Set(2);
        Set set3 = new Set(3);
        Set set4 = new Set(4);
        Set set5 = new Set(5);
        Set set6 = new Set(6);
        Set set7 = new Set(7);
        List<Set> setList = new ArrayList<>();
        setList.add(set0);
        setList.add(set1);
        setList.add(set2);
        setList.add(set3);
        setList.add(set4);
        setList.add(set5);
        setList.add(set6);
        setList.add(set7);

        jTemp.setSet(J.getSet());
        for (Set st : setList) {
            
            if (st.getSetScore() > jTemp.getSet().getSetScore() && jTemp.getSet().getSetScore() == J.getSet().getSetScore()) {
                System.out.println("joTemp setscore = "+jTemp.getSet().getSetScore());
                System.out.println("jo1 setscore = "+J.getSet().getSetScore());
                System.out.println("set boucle = "+st.getSetScore());
                J.setSet(st);
                
            }
        }
        return J.getSet();
    }
}
