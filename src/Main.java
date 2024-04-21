import java.io.*;
import java.util.*;
/*
 * Classe principale du jeu God of Chess.
 * Cette classe contient la fonction principale main() qui lance le jeu.
 * Elle gère également les scores des joueurs et les fonctionnalités du jeu.
 */
public class Main {
    private static List<Score> scores = new ArrayList<>();//Liste pour stocker les scores des joueurs

    public static void main(String[] args) {
        scores = chargerScores("SaveScore", "Sauvegarde");
        if (scores == null) {
            scores = new ArrayList<>();
            System.out.println("Erreur lors du chargement des scores. Une nouvelle liste de scores a été créée.");
        } else {
            System.out.println("Scores chargés avec succès.");
        }
        /* Fonction principale qui permet de lancer le jeu **/
        Art.GOC();//Petit art ascii pour le fun
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Bienvenue dans le jeu de la destruction !" + ConsoleColors.RESET);
        /* Declaration des variables pour la grille **/
        int longueur,Largeur;
        /* Declaration des variables pour les pions **/
        Joueur joueur1 = new Joueur(5,5,"Joueur1", 0); //Création du joueur 1
        Joueur joueur2 = new Joueur(5,4, "Joueur2", 0); //Création du joueur 2
        Plateau LePlateau = new Plateau(11,10,joueur1,joueur2); //Création du plateau

        Menus.MenuCommencement(LePlateau); //Invocation d'une fonction pour commencer le jeu




    }
    public static void sauvegarderScores(List<Score> scores, String nomFichier, String directoryPath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(directoryPath + File.separator + nomFichier + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(scores);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public static List<Score> getScores() {
        return scores;
    }
    // Ajouter cette liste de scores dans votre classe Main

    // Méthode pour mettre à jour les scores
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
        Main.sauvegarderScores(getScores(), "SaveScore", "Sauvegarde");//Sauvegarde des scores
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
        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "\nTableau des Scores :" + ConsoleColors.RESET);

        int count = 0;//Variable pour compter les 5 premiers scores
        for (Score score : scores) {//Parcours de la liste des scores
            if (count < 5) {//Si on a pas encore affiché les 5 premiers scores
                System.out.println(ConsoleColors.BLUE_BOLD + score.getJoueur().getPseudo() + ConsoleColors.WHITE_BOLD_BRIGHT + ": " + score.getScore() + " points" + ConsoleColors.RESET);//Affichage du score
                count++;//Incrémentation du compteur
            } else {
                break; // Sort de la boucle une fois les 5 premiers scores affichés
            }
        }
    }

    public static List<Score> chargerScores(String nomFichier, String directoryPath) {
        List<Score> scores = null;
        try {
            File file = new File(directoryPath + File.separator + nomFichier + ".ser");
            if (!file.exists() || !file.isFile()) {
                System.out.println("Erreur: Le fichier " + nomFichier + " n'existe pas ou n'est pas un fichier.");
                return null;
            }

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            scores = (List<Score>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return scores;
    }

    /* Fonction pour le jeu */


}