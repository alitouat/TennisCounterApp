package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metiers.GestionJeuLocal;
import metiers.GestionJoueur;
import metiers.GestionJoueurLocal;
import metiers.GestionSetLocal;
import pojo.Jeu;
import pojo.Joueur;
import pojo.Set;

@WebServlet(name = "FrontControleur", urlPatterns = {"/FrontControleur"})
public class FrontControleur extends HttpServlet {

    @EJB
    private GestionSetLocal gestionSet;

    @EJB
    private GestionJeuLocal gestionJeu;

    @EJB
    private GestionJoueurLocal gestionJoueur;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // création d'objets Jeu et d'une collection d'objets Set les contenant
        List<Jeu> points = new ArrayList<>();
        Jeu sc0 = new Jeu(0);
        Jeu sc15 = new Jeu(15);
        Jeu sc30 = new Jeu(30);
        Jeu sc40 = new Jeu(40);
        points.add(sc0);
        points.add(sc15);
        points.add(sc30);
        points.add(sc40);

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

// Instanciation des objets Joueur et d'un points de début de jeu
        Joueur jo1 = new Joueur("toto");
        Joueur jo2 = new Joueur("titi");
        Joueur joTemp = new Joueur();

// récupération de la session et affichage de la page principale "home"
        HttpSession session = request.getSession();
        String page = "/WEB-INF/home.jsp";

// récupération d'un tableau des valeurs pour chaque input en provenance du formulaire la page home
        String joueur1 = request.getParameter("jo1");
        String joueur2 = request.getParameter("jo2");

// Si le parameterValue "Jeu" (qui récupère les valeurs du groupe de boutons du form) n'existe pas dans la session,
// cela signifie que c'est le premier affichage de la page, donc début du jeu.
// Donc création des attributs  scores à 0 (jeux et sets)
        if (request.getParameter("jo1") == null && request.getParameter("jo2") == null) {

            jo1.setScore(sc0);
            request.setAttribute("scJo1", jo1.getScore().getScore());

            jo2.setScore(sc0);
            request.setAttribute("scJo2", jo2.getScore().getScore());

            jo1.setTiebreak(0);
            request.setAttribute("tbJo1", jo1.getTiebreak());

            jo2.setTiebreak(0);
            request.setAttribute("tbJo2", jo2.getTiebreak());

            jo1.setSet(set0);
            request.setAttribute("setJo1", jo1.getSet().getSetScore());

            jo2.setSet(set0);
            request.setAttribute("setJo2", jo2.getSet().getSetScore());

            jo1.setManche(0);
            request.setAttribute("manchJo1", jo1.getManche());

            jo2.setManche(0);
            request.setAttribute("manchJo2", jo2.getManche());

            String egAv1 = "";
            request.setAttribute("egAv1", egAv1);
            String egAv2 = "";
            request.setAttribute("egAv2", egAv2);
        }

        if (request.getParameter("jo1") != null || request.getParameter("jo2") != null) {
            // on récupère tous les paramètres des joueurs, on les intègre dans les objets joueur Jo1 et Jo2
            int scJo1 = Integer.valueOf(request.getParameter("scJo1"));
            String egAv1 = String.valueOf(request.getParameter("egAvJo1"));
            int tbJo1 = Integer.valueOf(request.getParameter("tbJo1"));
            System.out.println("tJo1 = " + tbJo1);
            int setJ1 = Integer.valueOf(request.getParameter("setJo1"));
            int manchJo1 = Integer.valueOf(request.getParameter("manchJo1"));
            Jeu jeuJo1 = new Jeu(scJo1, egAv1);
            Set setJo1 = new Set(setJ1);
            jo1.setScore(jeuJo1);
            jo1.setSet(setJo1);
            jo1.setManche(manchJo1);
            jo1.setTiebreak(tbJo1);

            int scJo2 = Integer.valueOf(request.getParameter("scJo2"));
            String egAv2 = String.valueOf(request.getParameter("egAvJo2"));
            int tbJo2 = Integer.valueOf(request.getParameter("tbJo2"));
            int setJ2 = Integer.valueOf(request.getParameter("setJo2"));
            int manchJo2 = Integer.valueOf(request.getParameter("manchJo2"));
            Jeu jeuJo2 = new Jeu(scJo2, egAv2);
            Set setJo2 = new Set(setJ2);
            jo2.setScore(jeuJo2);
            jo2.setSet(setJo2);
            jo2.setManche(manchJo2);
            jo2.setTiebreak(tbJo2);

            String egAv = "";

            String avantage = "Avantage";
            String egalite = "EgalitÃ©";

            joTemp.setScore(jo1.getScore());

            if (request.getParameter("winner") == null) {
                Joueur Winner = new Joueur();
            }

            Joueur Winner1 = gestionJoueur.fixerScore(jo1);
            Joueur Winner2 = gestionJoueur.fixerScore(jo2);


//CAS OU LE JOUEUR 1 GAGNE LE POINT
            if (joueur1 != null) {
                System.out.println("entrée dans joueur 1 section");

                // Si le score est à 40/40
                if (scJo1 == sc40.getScore()) {
                    // Si jo1 est en Avantage (Gestion des set et match)
                    if (avantage.equalsIgnoreCase(egAv1)
                            || jo1.getTiebreak() >= 7 && jo1.getTiebreak() - jo2.getTiebreak() >= 2
                            || scJo2 != sc40.getScore()) {
                        System.out.println("entrée dans avantage jo1");

                        // sinon on incrémente le nb de sets et on remet les compteurs jeu à zéro
                        if (egAv1 != egalite && jo1.getManche() < 3
                                && setJ1 != 6) {
                            System.out.println("entrée dans set inférieur à 6");
                            Set setWinner = gestionSet.donnerPoint(joTemp, jo1);
                            jo1.setScore(sc0);
                            scJo1 = jo1.getScore().getScore();
                            jo2.setScore(sc0);
                            scJo2 = jo2.getScore().getScore();
                            setJo1 = setWinner;
                            egAv1 = "";
                        }

                        if (setJ1 == 6 && setJ2 == 6 && egAv1 != egAv && egAv2 != egAv) {
                            System.out.println("entrée dans tie-break");
                            Winner1 = gestionJoueur.gagnerTieBreak(jo1, jo2);
                            tbJo1 = Winner1.getTiebreak();
                            jo1.setScore(sc0);
                            scJo1 = jo1.getScore().getScore();
                            jo2.setScore(sc0);
                            scJo2 = jo2.getScore().getScore();
                            jo1.setTiebreak(tbJo1);
                            jo1.setScore(sc0);
                            jo2.setScore(sc0);
                            egAv1 = egAv;

                            System.out.println("jo1 tiebreak = " + jo1.getTiebreak());

                        }

                        // Si le nb de sets du gagnant est au moins 5 et est supérieur d'au moins 2 points au perdant
                        if (setJ1 >= 5 && setJ1 - setJ2 >= 1
                                || setJ1 == 6 && setJ2 == 6 && jo1.getTiebreak() >= 7
                                && jo1.getTiebreak() - jo2.getTiebreak() > 1) {

                            System.out.println("entrée dans set jo1 = 6");
                            // si le nombre de manches gagnées est égal à 2, 
                            if (jo1.getManche() == 2) {

                                System.out.println("entrée dans manche = 2");
                                // on incrémente et on déclare le match
                                jo1.setManche(3);
                                jo1.setScore(sc0);
                                jo2.setScore(sc0);
                                jo1.setSet(set0);
                                jo2.setSet(set0);
                                setJo1 = set0;
                                setJo2 = set0;
                                jo1.setTiebreak(0);
                                tbJo1 = jo1.getTiebreak();
                                jo2.setTiebreak(0);
                                tbJo2 = jo2.getTiebreak();
                                egAv1 = "";

                                page = "/WEB-INF/match.jsp";
                            }
                            if (jo1.getManche() == 1) {
                                System.out.println("entrée dans manche = 1");
                                // on incrémente et on remet les compteurs à zéro
                                jo1.setManche(2);
                                jo1.setScore(sc0);
                                jo2.setScore(sc0);
                                jo1.setSet(set0);
                                jo2.setSet(set0);
                                setJo1 = set0;
                                setJo2 = set0;
                                jo1.setTiebreak(0);
                                tbJo1 = jo1.getTiebreak();
                                jo2.setTiebreak(0);
                                tbJo2 = jo2.getTiebreak();
                                egAv1 = "";
                            }
                            if (jo1.getManche() == 0) {
                                System.out.println("entrée dans manche = 0");
                                // on incrémente et on remet les compteurs à zéro
                                jo1.setManche(1);
                                jo1.setScore(sc0);
                                scJo1 = jo1.getScore().getScore();
                                jo2.setScore(sc0);
                                scJo2 = jo2.getScore().getScore();
                                jo1.setSet(set0);
                                jo2.setSet(set0);
                                setJo1 = set0;
                                setJo2 = set0;
                                jo1.setTiebreak(0);
                                tbJo1 = jo1.getTiebreak();
                                jo2.setTiebreak(0);
                                tbJo2 = jo2.getTiebreak();
                                egAv1 = "";
                            }

                            Winner1 = gestionJoueur.fixerScore(jo1);
                            // todo : arraylist de joueurs winners pour conserver les scores

                        }

                        }
                    // si l'adversaire est en Avantage
                    if (avantage.equalsIgnoreCase(egAv2)) {
                        System.out.println("go égalité");
                        jeuJo1 = gestionJeu.Egaliser(egAv1);

                        egAv1 = jeuJo1.getEgAv();
                        egAv2 = jeuJo1.getEgAv();
                        
                        
                        
                        //si 40/40 ou 40A
                    } else if (egalite.equalsIgnoreCase(egAv1)
                            || scJo2 == sc40.getScore()) {
                        System.out.println("go avantage");
                        jeuJo1 = gestionJeu.Avantager(egAv1);
                        egAv1 = jeuJo1.getEgAv();
                        egAv2 = "";
                    }
                    

                } //si le score est à 40 et pas celui de l'adversaire
                else {
                    jo1 = gestionJoueur.gagnerPoint(jo1, joTemp);
                    scJo1 = jo1.getScore().getScore();
                    joTemp.setScore(jo1.getScore());
                    joTemp.setSet(jo1.getSet());
                }

                request.setAttribute("scJo1", scJo1);
                request.setAttribute("egAv1", egAv1);
                request.setAttribute("tbJo1", tbJo1);
                request.setAttribute("setJo1", setJo1.getSetScore());
                request.setAttribute("manchJo1", jo1.getManche());

                request.setAttribute("scJo2", scJo2);
                request.setAttribute("egAv2", egAv2);
                request.setAttribute("tbJo2", tbJo2);
                request.setAttribute("setJo2", setJo2.getSetScore());
                request.setAttribute("manchJo2", jo2.getManche());

                request.setAttribute("winner", Winner1);

                page = "/WEB-INF/home.jsp";

            }

//CAS OU LE JOUEUR 2 GAGNE LE POINT
            if (joueur2 != null) {

                System.out.println("entrée dans joueur 2 section");

                if (scJo2 == sc40.getScore()) {
                // Si jo2 est en Avantage (Gestion des set et match)
                if (avantage.equalsIgnoreCase(egAv2)
                        || jo2.getTiebreak() >= 7
                        && jo2.getTiebreak() - jo1.getTiebreak() >= 2
                        || scJo1 != sc40.getScore()) {
                    System.out.println("entrée dans avantage jo2");

                    // sinon on incrémente le nb de sets et on remet les compteurs jeu à zéro
                    if (egAv2 != egalite && jo2.getManche() < 3
                            && setJ2 != 6) {
                        System.out.println("entrée dans jo2 set inférieur à 6");
                        Set setWinner = gestionSet.donnerPoint(joTemp, jo2);
                        jo1.setScore(sc0);
                        scJo1 = jo1.getScore().getScore();
                        jo2.setScore(sc0);
                        scJo2 = jo2.getScore().getScore();
                        setJo2 = setWinner;
                        egAv2 = "";
                    }
                    
                    if (setJ1 == 6 && setJ2 == 6 && egAv1 != egAv && egAv2 != egAv) {
                    System.out.println("entrée dans tie-break 2");
                    Winner2 = gestionJoueur.gagnerTieBreak(jo2, jo1);
                    tbJo2 = Winner2.getTiebreak();
                    jo2.setTiebreak(tbJo2);
                    jo1.setScore(sc0);
                    scJo1 = jo1.getScore().getScore();
                    jo2.setScore(sc0);
                    scJo2 = jo2.getScore().getScore();
                    jo1.setScore(sc0);
                    jo2.setScore(sc0);
                    egAv2 = egAv;
                    System.out.println("jo2 tiebreak = " + jo2.getTiebreak());

                }

                    // Si le nb de sets du gagnant est au moins 6 et est supérieur d'au moins 2 points au perdant
                    if (setJ2 >= 5 && setJ2 - setJ1 >= 1
                            || setJ2 == 6 && setJ2 == 6 && jo2.getTiebreak() >= 7
                            && jo2.getTiebreak() - jo1.getTiebreak() > 1) {

                        System.out.println("entrée dans set jo2 = 6");
                        // si le nombre de manches gagnées est égal à 2, 
                        if (jo2.getManche() == 2) {

                            System.out.println("entrée dans manche 2 = 2");
                            // on incrémente et on déclare le match
                            jo2.setManche(3);
                            jo2.setScore(sc0);
                            jo1.setScore(sc0);
                            jo2.setSet(set0);
                            jo1.setSet(set0);
                            setJo2 = set0;
                            setJo1 = set0;
                            jo1.setTiebreak(0);
                            tbJo1 = jo1.getTiebreak();
                            jo2.setTiebreak(0);
                            tbJo2 = jo2.getTiebreak();
                            egAv2 = "";

                            page = "/WEB-INF/match.jsp";
                        }
                        if (jo2.getManche() == 1) {
                            System.out.println("entrée dans manche 2 = 1");
                            // on incrémente et on remet les compteurs à zéro
                            jo2.setManche(2);

                            jo2.setScore(sc0);
                            jo1.setScore(sc0);
                            jo2.setSet(set0);
                            jo1.setSet(set0);
                            setJo1 = set0;
                            setJo2 = set0;
                            jo1.setTiebreak(0);
                            tbJo1 = jo1.getTiebreak();
                            jo2.setTiebreak(0);
                            tbJo2 = jo2.getTiebreak();
                            egAv2 = "";
                        }
                        if (jo2.getManche() == 0) {
                            System.out.println("entrée dans manche 2 = 0");
                            // on incrémente et on remet les compteurs à zéro
                            jo2.setManche(1);
                            jo2.setScore(sc0);
                            scJo2 = jo2.getScore().getScore();
                            jo1.setScore(sc0);
                            scJo1 = jo1.getScore().getScore();
                            jo1.setSet(set0);
                            jo2.setSet(set0);
                            setJo1 = set0;
                            setJo2 = set0;
                            jo1.setTiebreak(0);
                            tbJo1 = jo1.getTiebreak();
                            jo2.setTiebreak(0);
                            tbJo2 = jo2.getTiebreak();
                            egAv2 = "";
                        }

                        Winner2 = gestionJoueur.fixerScore(jo2);

                    }

                } 

                 // si l'adversaire est en Avantage
                    if (avantage.equalsIgnoreCase(egAv1)) {
                        System.out.println("go égalité");
                        jeuJo2 = gestionJeu.Egaliser(egAv2);

                        egAv1 = jeuJo2.getEgAv();
                        egAv2 = jeuJo2.getEgAv();
                        
                        
                        
                        //si 40/40 ou 40A
                    } else if (egalite.equalsIgnoreCase(egAv2)
                            || scJo1 == sc40.getScore()) {
                        System.out.println("go avantage");
                        jeuJo2 = gestionJeu.Avantager(egAv2);
                        egAv2 = jeuJo2.getEgAv();
                        egAv1 = "";
                    }
            }


// si le score est à 40/40 et le nombre de sets est égal à 6 pour les deux joueurs, c'est un tie-break
                //si le score est à 40 et pas celui de l'adversaire
                else {
                    jo2 = gestionJoueur.gagnerPoint(jo2, joTemp);
                    scJo2 = jo2.getScore().getScore();
                    joTemp.setScore(jo2.getScore());
                    joTemp.setSet(jo2.getSet());
                }

                request.setAttribute("scJo1", scJo1);
                request.setAttribute("egAv1", egAv1);
                request.setAttribute("tbJo1", tbJo1);
                request.setAttribute("setJo1", setJo1.getSetScore());
                request.setAttribute("manchJo1", jo1.getManche());

                request.setAttribute("scJo2", scJo2);
                request.setAttribute("egAv2", egAv2);
                request.setAttribute("tbJo2", tbJo2);
                request.setAttribute("setJo2", setJo2.getSetScore());
                request.setAttribute("manchJo2", jo2.getManche());

                request.setAttribute("winner", Winner2);

                page = "/WEB-INF/home.jsp";
            }
        }
        

        page = response.encodeURL(page);

        getServletContext()
                .getRequestDispatcher(page).include(request, response);

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
