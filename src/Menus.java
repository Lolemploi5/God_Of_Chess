import java.util.Scanner;

public class Menus {
    public static void MenuCommencement(){
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            //Affichage de tous les choix possible
            System.out.println();
            System.out.println(ConsoleColors.WHITE_BRIGHT + "Faites votre choix :" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD + "1. Règles du jeu !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "2. Nouvelle Partie !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "3. Charger une sauvegarde !" + ConsoleColors.RESET);
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

                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Entrez le pseudo du joueur 1 ?" + ConsoleColors.RESET);
                    j1Pseudo = myObj.nextLine();  //Lecture du pseudo du joueur 1
                    Joueur joueur1 = new Joueur(5,5,j1Pseudo);


                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Entrez le pseudo du joueur 2 ?" + ConsoleColors.RESET);
                    j2Pseudo = myObj.nextLine();    //Lecture du pseudo du joueur 2

                    Joueur joueur2 = new Joueur(5,4, j2Pseudo);

                    System.out.println();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "La partie commence !" + ConsoleColors.RESET);
                    Plateau monPlateau = new Plateau(11,10,joueur1,joueur2);
                    Main.Jeu(monPlateau);
                    break;
                case 3: //Choix 3 charge une partie déjà sauvegarder
                    System.out.println();
                    System.out.println(ConsoleColors.BLUE_BOLD + "Chargement d'une sauvegarde" + ConsoleColors.RESET);

                    break;
                case 4: //Choix 4 quitte le jeu
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Au revoir !" + ConsoleColors.RESET);
                    scanner.close();
                    System.exit(0);

                default: //Choix par defaut si vous mettez quelque chose de pas valide
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

        } while (choix != 3);
    }

}
