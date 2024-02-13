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

        System.out.println("le pseudo du joueur 1 ?");
        j1 = myObj.nextLine();  //Lecture du pseudo du joueur 1

        System.out.println("le pseudo du joueur 2 ?");
        j2 = myObj.nextLine();    //Lecture du pseudo du joueur 2

        MenuCommencement(); //Invocation d'une function pour commencer le jeu
        

    }
    public static void RegleMenu(){
        /* Fonction qui affiche les regles du jeu et propose de retourner au menu principal ou de reprendre la partie **/
        Scanner scanner = new Scanner(System.in);
            //Tout les choix disponibles
            System.out.println("Sélectionnez une action :");
            System.out.println("1. Retour au menu principal !");
            System.out.println("2. Reprendre la partie !");
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
                    System.out.println("Choix non valide. Veuillez sélectionner une action valide.");
            }

    }
    public static void MenuCommencement(){
        Scanner scanner = new Scanner(System.in);

            //Affichage de tous les choix possible
            System.out.println("Sélectionnez une action :");
            System.out.println("1. Regle du jeu !");
            System.out.println("2. Nouvelle Partie !");
            System.out.println("3. Charger une sauvegarde !");
            System.out.println("4. Quitter");

            int choix = scanner.nextInt(); //Lecture de notre choix

            switch (choix) {
                case 1: //Choix 1 affiche les regles et invoque la function regle menu
                    System.out.println("Les Regles du jeu :");

                    System.out.println("Pendant son tour un joueur peut déplacer son pion d’une case ");
                    System.out.println("(verticalement ou horizontalement), puis il détruit une case du plateau.");
                    System.out.println("Le dernier joueur pouvant encore se déplacer gagne.");
                    RegleMenu();

                    break;
                case 2: // choix 2 créer une nouvelle partie
                    System.out.println("Nouvelle partie ...");
                    Plateau monPlateau = new Plateau(10,11);
                    break;
                case 3: //Choix 3 charge une partie déjà sauvegarder
                    System.out.println("Chargement d'une sauvegarde");

                    break;
                case 4: //Choix 4 quitte le jeu
                    System.out.println("Au revoir !");
                    scanner.close();
                    System.exit(0);
                default: //Choix par defaut si vous mettez quelque chose de pas valide
                    System.out.println("Choix non valide. Veuillez sélectionner une action valide.");
            }

    }


}