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

        MenuCommencement(); //Invocation d'une fonction pour commencer le jeu


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
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous devez entrer une lettre (A-J) et un nombre (1-9) séparés par un deux-points (:)." + ConsoleColors.RESET);
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
            System.out.println(ConsoleColors.YELLOW_BOLD + "/help pour le menu!" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "/haut , /bas , /droite , /gauche" + ConsoleColors.RESET);
            System.out.printf(ConsoleColors.WHITE_BRIGHT + "Entrez la commande de votre choix : " + ConsoleColors.RESET);

            while (!scanner.hasNextLine()) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous devez entrer un texte." + ConsoleColors.RESET);
                scanner.next();
            }

            choix = scanner.nextLine().toLowerCase(); // Lecture de notre choix (converti en minuscules pour simplifier la comparaison)

            switch (choix) {
                case "/help": //Choix 1 affiche les regles et invoque la function regle menu
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Les Règles du jeu :" + ConsoleColors.RESET);

                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Pendant son tour un joueur peut déplacer son pion d’une case " + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "(verticalement ou horizontalement), puis il détruit une case du plateau." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Le dernier joueur pouvant encore se déplacer" + ConsoleColors.GREEN_BOLD_BRIGHT + " gagne" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Contraintes :" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas détruire une case occupée." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas occuper une case détruite ou une case deja occupée." + ConsoleColors.RESET);
                    System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT + "- Un joueur bloqué pendant un tour est déclaré " + ConsoleColors.RED_BOLD_BRIGHT+ "perdant" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
                    System.out.println();
                    Jeu(LePlateau);

                    break;
                case "/haut": //Choix 3 charge une partie déjà sauvegarder
                    System.out.println();
                    System.out.println(ConsoleColors.BLUE_BOLD + "Vous montez d'une case" + ConsoleColors.RESET);
                    LePlateau.deplacerJoueur(0,-1);
                    MenuDetruire(LePlateau);

                    Jeu(LePlateau);

                    break;
                case "/bas": //Choix 4 quitte le jeu
                    System.out.println();
                    System.out.println(ConsoleColors.BLUE_BOLD + "Vous descendez d'une case" + ConsoleColors.RESET);
                    LePlateau.deplacerJoueur(0,1);

                    MenuDetruire(LePlateau);
                    Jeu(LePlateau);

                    break;

                case "/droite": //Choix 4 Revenir a la game
                    System.out.println();
                    System.out.println(ConsoleColors.BLUE_BOLD + "Vous vous deplacez d'une case vers la droite" + ConsoleColors.RESET);
                    LePlateau.deplacerJoueur(1,0);

                    MenuDetruire(LePlateau);
                    Jeu(LePlateau);

                    break;
                case "/gauche": //Choix 5 Revenir a la game
                    System.out.println();
                    System.out.println(ConsoleColors.BLUE_BOLD + "Vous vous deplacez d'une case vers la gauche" + ConsoleColors.RESET);
                    LePlateau.deplacerJoueur(-1,0);

                    MenuDetruire(LePlateau);
                    Jeu(LePlateau);

                default: //Choix par defaut si vous mettez quelque chose de pas valide
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

        } while (choix.length() != 5);
    }
    public static void MenuJeu(Joueur MyJoueur){
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            //Affichage de tous les choix possible
            System.out.println();
            System.out.println(ConsoleColors.WHITE_BRIGHT + "Faites votre choix : " + MyJoueur.getPseudo() + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD + "1. Règles du jeu !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "2. Sauvegarder la partie !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "3. Quitter la partie" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BRIGHT + "4. Revenir a la partie" + ConsoleColors.RESET);
            System.out.printf(ConsoleColors.WHITE_BRIGHT + "Entrez le numéro de votre choix : " + ConsoleColors.RESET);

            while (!scanner.hasNextInt()) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous devez entrer un nombre." + ConsoleColors.RESET);
                scanner.next();
            }

            choix = scanner.nextInt(); //Lecture de notre choix

            switch (choix) {
                case 1: //Choix 1 affiche les regles et invoque la function regle menu
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Les Règles du jeu :" + ConsoleColors.RESET);

                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Pendant son tour un joueur peut déplacer son pion d’une case " + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "(verticalement ou horizontalement), puis il détruit une case du plateau." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Le dernier joueur pouvant encore se déplacer" + ConsoleColors.GREEN_BOLD_BRIGHT + " gagne" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Contraintes :" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas détruire une case occupée." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas occuper une case détruite ou une case deja occupée." + ConsoleColors.RESET);
                    System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT + "- Un joueur bloqué pendant un tour est déclaré " + ConsoleColors.RED_BOLD_BRIGHT+ "perdant" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
                    System.out.println();
                    MenuJeu(MyJoueur);

                    break;
                case 2: //Choix 3 charge une partie déjà sauvegarder
                    System.out.println();
                    System.out.println(ConsoleColors.BLUE_BOLD + "Creation d'une sauvegarde" + ConsoleColors.RESET);

                    break;
                case 3: //Choix 4 quitte le jeu
                    System.out.println(ConsoleColors.RED_BOLD + "Au revoir !" + ConsoleColors.RESET);
                    scanner.close();
                    System.exit(0);
                    break;

                case 4: //Choix 4 Revenir a la game
                    System.out.println();
                    MenuJeu(MyJoueur);


                default: //Choix par defaut si vous mettez quelque chose de pas valide
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

        } while (choix != 4);
    }

    public static void MenuCommencement(){
        Scanner scanner = new Scanner(System.in);
            int choix;
        do {
            //Affichage de tous les choix possible
            System.out.println();
            System.out.println(ConsoleColors.WHITE_BRIGHT + "Faites votre choix :" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD + "1. Règles du jeu !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD + "2. Nouvelle Partie !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "3. Charger une sauvegarde !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "4. Quitter" + ConsoleColors.RESET);
            System.out.printf(ConsoleColors.WHITE_BRIGHT + "Entrez le numéro de votre choix : " + ConsoleColors.RESET);

            while (!scanner.hasNextInt()) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous devez entrer un nombre (1-4)." + ConsoleColors.RESET);
                MenuCommencement();
                scanner.next();
            }

            choix = scanner.nextInt(); //Lecture de notre choix

            switch (choix) {
                case 1: //Choix 1 affiche les regles et invoque la function regle menu
                    System.out.println();
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Les Règles du jeu :" + ConsoleColors.RESET);

                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Pendant son tour un joueur peut déplacer son pion d’une case " + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "(verticalement ou horizontalement), puis il détruit une case du plateau." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Le dernier joueur pouvant encore se déplacer" + ConsoleColors.GREEN_BOLD_BRIGHT + " gagne" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Contraintes :" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas détruire une case occupée." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas occuper une case détruite ou une case deja occupée." + ConsoleColors.RESET);
                    System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT + "- Un joueur bloqué pendant un tour est déclaré " + ConsoleColors.RED_BOLD_BRIGHT+ "perdant" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
                    System.out.println();
                    MenuCommencement();

                    break;
                case 2: // choix 2 créer une nouvelle partie

                    /* Declaration des variables **/
                    String j1Pseudo, j2Pseudo;
                    System.out.println();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Nouvelle partie ..." + ConsoleColors.RESET);
                    Scanner myObj = new Scanner(System.in);  //Créer un scan de l'objet

                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Entrez le pseudo du joueur 1 ?" + ConsoleColors.RESET);
                    j1Pseudo = myObj.nextLine();  //Lecture du pseudo du joueur 1
                    Joueur joueur1 = new Joueur(5,5,j1Pseudo);


                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Entrez le pseudo du joueur 2 ?" + ConsoleColors.RESET);
                    j2Pseudo = myObj.nextLine();    //Lecture du pseudo du joueur 2

                    Joueur joueur2 = new Joueur(5,4, j2Pseudo);

                    System.out.println();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "La partie commence !" + ConsoleColors.RESET);
                    Plateau monPlateau = new Plateau(11,10,joueur1,joueur2);
                    Jeu(monPlateau);
                    break;
                case 3: //Choix 3 charge une partie déjà sauvegarder
                    System.out.println();
                    System.out.println(ConsoleColors.BLUE_BOLD + "Chargement d'une sauvegarde" + ConsoleColors.RESET);

                    break;
                case 4: //Choix 4 quitte le jeu
                    System.out.println(ConsoleColors.RED_BOLD + "Au revoir !" + ConsoleColors.RESET);
                    scanner.close();
                    System.exit(0);

                default: //Choix par defaut si vous mettez quelque chose de pas valide
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

    }


        while (choix != 3);
    }
}