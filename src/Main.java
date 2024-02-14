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

    public static void MenuCommencement(){
        Scanner scanner = new Scanner(System.in);

            //Affichage de tous les choix possible
            System.out.println();
            System.out.println(ConsoleColors.WHITE_BRIGHT + "Faites votre choix :" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD + "1. Règles du jeu !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD + "2. Nouvelle Partie !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "3. Charger une sauvegarde !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "4. Quitter" + ConsoleColors.RESET);
            System.out.printf(ConsoleColors.WHITE_BRIGHT + "Entrez le numéro de votre choix : " + ConsoleColors.RESET);


            int choix = scanner.nextInt(); //Lecture de notre choix

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
                    String j1, j2;
                    System.out.println();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Nouvelle partie ..." + ConsoleColors.RESET);
                    Scanner myObj = new Scanner(System.in);  //Créer un scan de l'objet

                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Entrez le pseudo du joueur 1 ?" + ConsoleColors.RESET);
                    j1 = myObj.nextLine();  //Lecture du pseudo du joueur 1

                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Entrez le pseudo du joueur 2 ?" + ConsoleColors.RESET);
                    j2 = myObj.nextLine();    //Lecture du pseudo du joueur 2

                    System.out.println();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "La partie commence !" + ConsoleColors.RESET);
                    Plateau monPlateau = new Plateau(10,11);
                    monPlateau.afficherPlateau();
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
                    System.out.println(ConsoleColors.RED_BRIGHT + "Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

    }


}