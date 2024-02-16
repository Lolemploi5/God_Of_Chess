import java.util.*;
/*
 * Classe principale du jeu God of Chess.
 * Cette classe contient la fonction principale main() qui lance le jeu.
 * Elle gère également les scores des joueurs et les fonctionnalités du jeu.
 */
public class Main {
    private static List<Score> scores = new ArrayList<>();//Liste pour stocker les scores des joueurs

    public static void main(String[] args) {
        /* Fonction principale qui permet de lancer le jeu **/
        Art.GOC();//Petit art ascii pour le fun
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Bienvenue dans le jeu de la destruction !" + ConsoleColors.RESET);
        /* Declaration des variables pour la grille **/
        int longueur,Largeur;
        /* Declaration des variables pour les pions **/

        Menus.MenuCommencement(); //Invocation d'une fonction pour commencer le jeu


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

    /* Fonction pour le jeu */
    public static void MenuDetruire(Plateau LePlateau){
        Scanner scanner = new Scanner(System.in);//Création d'un scanner pour lire les entrées
        String input;//Variable pour stocker le choix de l'utilisateur
        char lettre;//Variable pour stocker la lettre de la case
        int nombre;//Variable pour stocker le nombre de la case

        while (true) {//Boucle pour vérifier si les coordonnées sont valides
            System.out.printf(ConsoleColors.WHITE_BRIGHT + "\nEntrez la lettre et le nombre de la case (ex. C:9) : " + ConsoleColors.RESET);
            input = scanner.nextLine().toUpperCase();//Lecture de notre choix

            if ((input.matches("[A-J]:([1-9]|10|11)"))) {//Vérification si les coordonnées sont valides
                lettre = input.charAt(0);//Récupération de la lettre
                nombre = Integer.parseInt(input.substring(2));//Récupération du nombre
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Vous devez entrer une lettre (A-J) et un nombre (1-11) séparés par deux-points (:)." + ConsoleColors.RESET);
            }
        }

        // Détruire la case spécifiée
        LePlateau.detruireCase(lettre, nombre, LePlateau);
    }
    /* Fonction pour le jeu */
    public static void Jeu(Plateau LePlateau){
        Scanner scanner = new Scanner(System.in);//Création d'un scanner pour lire les entrées
        String choix;//Variable pour stocker le choix de l'utilisateur

        LePlateau.afficherPlateau();//Affichage du plateau

        do {
            //Affichage de tous les choix possible
            System.out.println();
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "Faites votre choix : " + ConsoleColors.WHITE_BOLD_BRIGHT + LePlateau.NomJoueur() + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD + "/aide " + ConsoleColors.WHITE_BRIGHT + "pour le menu!" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "/h , /b , /d , /g" + ConsoleColors.RESET);
            System.out.printf(ConsoleColors.WHITE_BRIGHT + "Entrez la commande de votre choix : " + ConsoleColors.RESET);

            while (!scanner.hasNextLine()) {//Verification si le choix est un nombre
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous devez entrer un texte." + ConsoleColors.RESET);
                scanner.next();//Lecture de notre choix
            }

            choix = scanner.nextLine().toLowerCase(); // Lecture de notre choix (converti en minuscules pour simplifier la comparaison)

            switch (choix) {
                case "/aide": //Choix 1 affiche les regles et invoque la function regle menu
                    MenuAide.Menuaide();
                    Jeu(LePlateau);//Retour au menu de commencement

                    break;
                case "/h":

                    System.out.println();
                    if (LePlateau.deplacerJoueur(0,-1)){//Vérification si le joueur peut monter d'une case
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous montez d'une case" + ConsoleColors.RESET);
                        MenuDetruire(LePlateau);//Invocation de la fonction pour détruire une case
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas monter d'une case" + ConsoleColors.RESET);
                    }
                    Jeu(LePlateau);

                    break;
                case "/b":
                    if(LePlateau.deplacerJoueur(0,1)){//Vérification si le joueur peut descendre d'une case
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous descendez d'une case" + ConsoleColors.RESET);
                        MenuDetruire(LePlateau);//Invocation de la fonction pour détruire une case
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas descendre d'une case" + ConsoleColors.RESET);
                    }

                    Jeu(LePlateau);

                    break;

                case "/d":
                    if(LePlateau.deplacerJoueur(1,0)){//Vérification si le joueur peut se déplacer d'une case vers la droite
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous vous deplacez d'une case vers la droite" + ConsoleColors.RESET);
                        MenuDetruire(LePlateau);//Invocation de la fonction pour détruire une case
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas vous deplacer d'une case vers la droite" + ConsoleColors.RESET);
                    }
                    Jeu(LePlateau);//Retour au menu de commencement

                    break;
                case "/q":
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous avez quitté la game" + ConsoleColors.RESET);
                        Menus.MenuCommencement();

                    break;
                case "/g":
                    if(LePlateau.deplacerJoueur(-1,0)){//Vérification si le joueur peut se déplacer d'une case vers la gauche
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous vous deplacez d'une case vers la gauche" + ConsoleColors.RESET);
                        MenuDetruire(LePlateau);//Invocation de la fonction pour détruire une case
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas vous deplacer d'une case vers la gauche" + ConsoleColors.RESET);
                    }

                    Jeu(LePlateau);//Retour au menu de commencement

                default: //Choix par defaut si vous mettez quelque chose de pas valide
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

        } while (choix.length() != 5);//Tant que le choix n'est pas 5
    }
}