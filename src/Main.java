import java.util.*;
public class Main {

    public static void main(String[] args) {
        /* Fonction principale qui permet de lancer le jeu **/
        //Petit art ascii pour le fun
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n" +
                " .---.  .----. .----.     .----. .----.    .---. .-. .-..----. .----. .----.\n" +
                "/   __}/  {}  \\| {}  \\   /  {}  \\| {_     /  ___}| {_} || {_  { {__  { {__  \n" +
                "\\  {_ }\\      /|     /   \\      /| |      \\     }| { } || {__ .-._} }.-._} }\n" +
                " `---'  `----' `----'     `----' `-'       `---' `-' `-'`----'`----' `----' \n" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Bienvenue dans le jeu de la destruction !" + ConsoleColors.RESET);
        /* Declaration des variables pour la grille **/
        int longueur,Largeur;
        /* Declaration des variables pour les pions **/

        Menus.MenuCommencement(); //Invocation d'une fonction pour commencer le jeu


    }

    public static void MenuDetruire(Plateau LePlateau){
        Scanner scanner = new Scanner(System.in);
        String input;
        char lettre;
        int nombre;

        while (true) {
            System.out.printf(ConsoleColors.WHITE_BRIGHT + "\nEntrez la lettre et le nombre de la case (ex. C:9) : " + ConsoleColors.RESET);
            input = scanner.nextLine().toUpperCase();

            if (input.matches("[A-J]:[1-9]")) {
                lettre = input.charAt(0);
                nombre = Integer.parseInt(input.substring(2));
                break;
            } else {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous devez entrer une lettre (A-J) et un nombre (1-11) séparés par un deux-points (:)." + ConsoleColors.RESET);
            }
        }

        // Détruire la case spécifiée
        LePlateau.detruireCase(lettre, nombre);
    }

    public static void Jeu(Plateau LePlateau){
        Scanner scanner = new Scanner(System.in);
        String choix;

        LePlateau.afficherPlateau();

        do {
            //Affichage de tous les choix possible
            System.out.println();
            System.out.println(ConsoleColors.WHITE_BRIGHT + "Faites votre choix : " + LePlateau.NomJoueur() + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD + "/aide pour le menu!" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "/haut , /bas , /droite , /gauche" + ConsoleColors.RESET);
            System.out.printf(ConsoleColors.WHITE_BRIGHT + "Entrez la commande de votre choix : " + ConsoleColors.RESET);

            while (!scanner.hasNextLine()) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous devez entrer un texte." + ConsoleColors.RESET);
                scanner.next();
            }

            choix = scanner.nextLine().toLowerCase(); // Lecture de notre choix (converti en minuscules pour simplifier la comparaison)

            switch (choix) {
                case "/aide": //Choix 1 affiche les regles et invoque la function regle menu
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Les Règles du jeu :" + ConsoleColors.RESET);

                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Pendant son tour un joueur peut déplacer son pion d’une case " + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "(verticalement ou horizontalement), puis il détruit une case du plateau." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Le dernier joueur pouvant encore se déplacer" + ConsoleColors.GREEN_BOLD_BRIGHT + " gagne" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Contraintes :" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas détruire une case occupée." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas occuper une case détruite ou une case deja occupée." + ConsoleColors.RESET);
                    System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT + "- Un joueur bloqué pendant un tour est déclaré " + ConsoleColors.RED_BOLD_BRIGHT+ "perdant" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
                    System.out.println(" \n");
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Les Commandes dans le jeu :" + ConsoleColors.RESET);

                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "/s " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour sauvegarder." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "/q " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour quitter le jeu." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "/h " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour monter d'une case." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "/b " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour baisser d'une case." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "/d " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour allez a droite ." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "/g " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour allez a gauche." + ConsoleColors.RESET);
                    System.out.println();

                    Jeu(LePlateau);

                    break;
                case "/haut":

                    System.out.println();
                    if (LePlateau.deplacerJoueur(0,-1)){
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous montez d'une case" + ConsoleColors.RESET);
                        MenuDetruire(LePlateau);
                        LePlateau.passerTour();
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas monter d'une case" + ConsoleColors.RESET);
                    }
                    Jeu(LePlateau);

                    break;
                case "/bas":
                    if(LePlateau.deplacerJoueur(0,1)){
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous descendez d'une case" + ConsoleColors.RESET);
                        MenuDetruire(LePlateau);
                        LePlateau.passerTour();
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas descendre d'une case" + ConsoleColors.RESET);
                    }

                    Jeu(LePlateau);

                    break;

                case "/droite":
                    if(LePlateau.deplacerJoueur(1,0)){
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous vous deplacez d'une case vers la droite" + ConsoleColors.RESET);
                        MenuDetruire(LePlateau);
                        LePlateau.passerTour();
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas vous deplacer d'une case vers la droite" + ConsoleColors.RESET);
                    }
                    Jeu(LePlateau);

                    break;
                case "/gauche":
                    if(LePlateau.deplacerJoueur(-1,0)){
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous vous deplacez d'une case vers la gauche" + ConsoleColors.RESET);
                        MenuDetruire(LePlateau);
                        LePlateau.passerTour();
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas vous deplacer d'une case vers la gauche" + ConsoleColors.RESET);
                    }

                    Jeu(LePlateau);

                default: //Choix par defaut si vous mettez quelque chose de pas valide
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

        } while (choix.length() != 5);
    }


}