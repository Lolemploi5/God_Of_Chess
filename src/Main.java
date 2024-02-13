import java.util.*;
public class Main {

    public static void main(String[] args) {
        /* Fonction principale qui permet de lancer le jeu **/
        System.out.println("Hello and welcome!");
        /* Declaration des variables **/
        String j1, j2;
        /* Declaration des variables pour la grille **/
        int longueur,Largeur;
        /* Declaration des variables pour les pions **/
        Scanner myObj = new Scanner(System.in);  //Créer un scan de l'objet

        System.out.println(ConsoleColors.WHITE_BRIGHT + "Entrez le pseudo du joueur 1 ?" + ConsoleColors.RESET);
        j1 = myObj.nextLine();  //Lecture du pseudo du joueur 1

        System.out.println("Entrez le pseudo du joueur 2 ?");
        j2 = myObj.nextLine();    //Lecture du pseudo du joueur 2

        MenuCommencement(); //Invocation d'une function pour commencer le jeu


    }
    public static void RegleMenu(){
        /* Fonction qui affiche les regles du jeu et propose de retourner au menu principal ou de reprendre la partie **/
        Scanner scanner = new Scanner(System.in);
            //Tout les choix disponibles
            System.out.println(" ");
            System.out.println(ConsoleColors.WHITE_BOLD + "Faites votre choix :" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "1. Retour au menu principal !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD + "2. Reprendre la partie !" + ConsoleColors.RESET);
            /* Lecture du choix **/
            int choix = scanner.nextInt();

            switch (choix) {
                case 1: //Choix 1 on retourne au menu de base
                    MenuCommencement();
                    break;
                case 2: //Choix 2 on retourne a notre games si on est en games et sinon ça nous retourne au menu si on est au menu
                    System.out.println("Au revoir !");
                    scanner.close();
                    System.exit(0);
                default: //Choix par defaut si on choisie quelque chose de non valide
                    System.out.println(ConsoleColors.RED_BRIGHT + "Choix non valide. Veuillez sélectionner une action valide." + ConsoleColors.RESET);
            }

    }
    public static void MenuCommencement(){
        Scanner scanner = new Scanner(System.in);

            //Affichage de tous les choix possible
            System.out.println(ConsoleColors.WHITE_BRIGHT + "Faites votre choix :" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD + "1. Règles du jeu !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD + "2. Nouvelle Partie !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "3. Charger une sauvegarde !" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "4. Quitter" + ConsoleColors.RESET);

            int choix = scanner.nextInt(); //Lecture de notre choix

            switch (choix) {
                case 1: //Choix 1 affiche les regles et invoque la function regle menu
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Les Règles du jeu :" + ConsoleColors.RESET);

                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Pendant son tour un joueur peut déplacer son pion d’une case " + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "(verticalement ou horizontalement), puis il détruit une case du plateau." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Le dernier joueur pouvant encore se déplacer gagne." + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Contraintes :" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.WHITE_BRIGHT + "Un joueur ne peut pas détruire une case occupée" + ConsoleColors.RESET);

                    RegleMenu();

                    break;
                case 2: // choix 2 créer une nouvelle partie
                    System.out.println(ConsoleColors.GREEN_BRIGHT + "Nouvelle partie ..." + ConsoleColors.RESET);
                    Plateau monPlateau = new Plateau(10,11);
                    break;
                case 3: //Choix 3 charge une partie déjà sauvegarder
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