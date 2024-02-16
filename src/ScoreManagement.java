import java.util.ArrayList;
import java.util.List;

public class ScoreManagement {

    private static List<Score> scores = new ArrayList<>();//Liste pour stocker les scores des joueurs

    public static void mettreAJourScores(Joueur gagnant, Joueur perdant) {
        Score scoreGagnant = trouverScore(gagnant.getPseudo());//Recherche du score du gagnant
        Score scorePerdant = trouverScore(perdant.getPseudo());//Recherche du score du perdant

        if (scoreGagnant == null) {//Si le score du gagnant n'existe pas
            scoreGagnant = new Score(gagnant, 0);//Création d'un nouveau score
            scores.add(scoreGagnant);//Ajout du score à la liste
        }

        if (scorePerdant == null) {//Si le score du perdant n'existe pas
            scorePerdant = new Score(perdant, 0);//Création d'un nouveau score
            scores.add(scorePerdant);//Ajout du score à la liste
        }

        scoreGagnant.setScore(scoreGagnant.getScore() + 5);//Mise à jour du score du gagnant
        scorePerdant.setScore(scorePerdant.getScore() - 2);//Mise à jour du score du perdant
    }

    // Méthode pour trouver un score dans la liste
    private static Score trouverScore(String pseudo) {
        for (Score score : scores) {//Parcours de la liste des scores
            if (score.getJoueur().getPseudo().equals(pseudo)) {//Si le score du joueur est trouvé
                return score;//Retourne le score
            }
        }
        return null;
    }


    // Méthode pour afficher le tableau des scores
    public static void afficherTableauDesScores() {
        System.out.println(ConsoleColors.BLUE_BOLD + "Tableau des Scores :" + ConsoleColors.RESET);

        int count = 0;//Variable pour compter les 5 premiers scores
        for (Score score : scores) {//Parcours de la liste des scores
            if (count < 5) {//Si on a pas encore affiché les 5 premiers scores
                System.out.println(score.getJoueur().getPseudo() + ": " + score.getScore() + " points");//Affichage du score
                count++;//Incrémentation du compteur
            } else {
                break; // Sort de la boucle une fois les 5 premiers scores affichés
            }
        }
    }


}
