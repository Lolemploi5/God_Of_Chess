import java.util.Scanner;

public class Jeu{
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
                    if (JoueurManagement.deplacerJoueur(0,-1)){//Vérification si le joueur peut monter d'une case
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous montez d'une case" + ConsoleColors.RESET);
                        Menus.MenuDetruire(LePlateau);//Invocation de la fonction pour détruire une case
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas monter d'une case" + ConsoleColors.RESET);
                    }
                    Jeu(LePlateau);

                    break;
                case "/b":
                    if(JoueurManagement.deplacerJoueur(0,1)){//Vérification si le joueur peut descendre d'une case
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous descendez d'une case" + ConsoleColors.RESET);
                        Menus.MenuDetruire(LePlateau);//Invocation de la fonction pour détruire une case
                    }
                    else{
                        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Erreur : Vous ne pouvez pas descendre d'une case" + ConsoleColors.RESET);
                    }

                    Jeu(LePlateau);

                    break;

                case "/d":
                    if(JoueurManagement.deplacerJoueur(1,0)){//Vérification si le joueur peut se déplacer d'une case vers la droite
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous vous deplacez d'une case vers la droite" + ConsoleColors.RESET);
                        Menus.MenuDetruire(LePlateau);//Invocation de la fonction pour détruire une case
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
                    if(JoueurManagement.deplacerJoueur(-1,0)){//Vérification si le joueur peut se déplacer d'une case vers la gauche
                        System.out.println();
                        System.out.println(ConsoleColors.BLUE_BOLD + "Vous vous deplacez d'une case vers la gauche" + ConsoleColors.RESET);
                        Menus.MenuDetruire(LePlateau);//Invocation de la fonction pour détruire une case
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
