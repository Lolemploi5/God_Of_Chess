import java.util.Scanner;

public class Menus {
    public static void MenuCommencement(){
        Scanner scanner = new Scanner(System.in);//Création d'un scanner pour lire les entrées
        int choix;//Variable pour stocker le choix de l'utilisateur
        do {
            //Affichage de tous les choix possible
            System.out.println();
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Faites votre choix :" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "1. Règles du jeu !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "2. Nouvelle Partie !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "3. Charger une sauvegarde !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "4. Tableau des scores !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "5. Quitter" + ConsoleColors.RESET);
            System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT + "Entrez le numéro de votre choix : " + ConsoleColors.RESET);

            while (!scanner.hasNextInt()) { //Verification si le choix est un nombre
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous devez entrer un nombre (1-4)." + ConsoleColors.RESET);
                MenuCommencement();//Retour au menu de commencement
                scanner.next();//Lecture de notre choix
            }

            choix = scanner.nextInt(); //Lecture de notre choix

            switch (choix) {
                case 1: //Choix 1 affiche les regles et invoque la function regle menu
                    MenuAide.Menuaide();
                    MenuCommencement();//Retour au menu de commencement

                    break;
                case 2: // choix 2 créer une nouvelle partie

                    /* Declaration des variables **/
                    String j1Pseudo, j2Pseudo;
                    System.out.println();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Nouvelle partie ..." + ConsoleColors.RESET);
                    Scanner myObj = new Scanner(System.in);  //Créer un scan de l'objet

                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Entrez le pseudo du joueur 1 ?" + ConsoleColors.RESET);
                    j1Pseudo = myObj.nextLine();//Lecture du pseudo du joueur 1
                    Joueur joueur1 = new Joueur(5,5,j1Pseudo, 0); //Création du joueur 1


                    System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Entrez le pseudo du joueur 2 ?" + ConsoleColors.RESET);
                    j2Pseudo = myObj.nextLine();//Lecture du pseudo du joueur 2
                    Joueur joueur2 = new Joueur(5,4, j2Pseudo, 0); //Création du joueur 2



                    System.out.println();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "La partie commence !" + ConsoleColors.RESET);
                    Plateau monPlateau = new Plateau(11,10,joueur1,joueur2); //Création du plateau
                    Main.Jeu(monPlateau); //Lancement du jeu
                    break;
                case 3: //Choix 3 charge une partie déjà sauvegarder
                    System.out.println();
                    System.out.println(ConsoleColors.BLUE_BOLD + "Chargement d'une sauvegarde" + ConsoleColors.RESET);

                    break;
                case 4: //Choix 4 affiche le tableau des scores
                    Main.afficherTableauDesScores();
                    MenuCommencement(); // Redirigez ensuite l'utilisateur vers le menu de départ
                    break;

                case 5: //Choix 4 quitte le jeu
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Au revoir !" + ConsoleColors.RESET);
                    scanner.close(); //Fermeture du scanner
                    System.exit(0); //Fermeture du programme

                default: //Choix par defaut si vous mettez quelque chose de pas valide
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

        } while (choix != 3); //Tant que le choix n'est pas 3
    }

}
