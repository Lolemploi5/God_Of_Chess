import java.util.Random;

public class Plateau {

    public static int largeur;//Largeur du plateau
    public static int hauteur;//Hauteur du plateau
    public static Joueur joueur1;//Joueur 1
    public static Joueur joueur2;//Joueur 2

    public static Joueur joueurCourant;//Joueur entrain de jouer

    public static boolean[][] casesDestructibles;//Cases destructibles

    public Joueur getJoueur1() {//Récupérer le joueur 1
        return joueur1;//Retourne le joueur 1
    }

    public void setJoueur1(Joueur joueur1) {//Modifier le joueur 1
        this.joueur1 = joueur1;//Affecter le joueur 1
    }

    public Joueur getJoueur2() {//Récupérer le joueur 2
        return joueur2;//Retourne le joueur 2
    }

    public void setJoueur2(Joueur joueur2) {//Modifier le joueur 2
        this.joueur2 = joueur2;//Affecter le joueur 2
    }
    public String NomJoueur(){//Récupérer le nom du joueur courant
        return getJoueurCourant().getPseudo();//Retourne le nom du joueur courant
    }
    public Joueur getJoueurCourant() {//Récupérer le joueur courant
        return joueurCourant;//Retourne le joueur courant
    }

    public void setJoueurCourant(Joueur joueurCourant) {//Modifier le joueur courant
        this.joueurCourant = joueurCourant;//Affecter le joueur courant
    }

    public Plateau(int largeur, int hauteur , Joueur j1 , Joueur j2) {//Constructeur
        this.largeur = largeur;//Affecter la largeur
        this.hauteur = hauteur;//Affecter la hauteur
        Random rand = new Random();
        
        // Générer un nombre aléatoire soit 0 ou 1
        int randomNum = rand.nextInt(2);

        // Si le nombre aléatoire est 0, le joueur 1 commence, sinon le joueur 2 commence
        this.joueurCourant = (randomNum == 0) ? j1 : j2;

        this.joueur1 = j1; // Le joueur démarre en haut à gauche
        this.joueur2 = j2; // Le joueur démarre en haut à gauche
        this.casesDestructibles = new boolean[largeur][hauteur];
        initialiserCasesDestructibles();//Initialiser les cases destructibles
    }
    /**
     * Initialiser toutes les cases comme destructibles au début
     */
    private void initialiserCasesDestructibles() {
        // Initialise toutes les cases comme destructibles au début
        for (int i = 0; i < largeur; i++) {//Parcourir les colonnes
            for (int j = 0; j < hauteur; j++) {//Parcourir les lignes
                casesDestructibles[i][j] = true;//Initialiser les cases destructibles
            }
        }
    }
    /**
     * Vérifier si le joueur courant est bloqué
     * retourne true si le joueur courant est bloqué, false sinon
     */
    public void passerTour() {
        // Changer le joueur courant
        if (joueurCourant == joueur1) {
            joueurCourant = joueur2;
            setJoueurCourant(getJoueur2());
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "\nC'est le tour de " + ConsoleColors.RED_BOLD_BRIGHT + joueurCourant.getPseudo() + ConsoleColors.PURPLE_BOLD_BRIGHT + " !" + ConsoleColors.RESET);
            System.out.println();
        } else {
            joueurCourant = joueur1;
            setJoueurCourant(getJoueur1());
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "\nC'est le tour de " + ConsoleColors.BLUE_BOLD_BRIGHT + joueurCourant.getPseudo() + ConsoleColors.PURPLE_BOLD_BRIGHT + " !" + ConsoleColors.RESET);
            System.out.println();
        }

        winLoose(); // Vérifier si un joueur est bloqué après chaque tour
    }



    public void winLoose() {
        boolean joueur1Bloque = Joueur.estBloque(joueur1);
        boolean joueur2Bloque = Joueur.estBloque(joueur2);
        // Vérifier si les deux joueurs sont bloqués
        if (joueur1Bloque && joueur2Bloque) {
            Art.JeuPerdu();
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "Les deux joueurs sont bloqués. La partie est " + ConsoleColors.RED_BOLD_BRIGHT + "nulle" + ConsoleColors.PURPLE_BOLD_BRIGHT + "." + ConsoleColors.RESET);
            Menus.MenuCommencement(this);

        } else if (joueur1Bloque) {
            Art.JeuPerdu();
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "Le joueur " + ConsoleColors.BLUE_BOLD_BRIGHT + joueur1.getPseudo() + ConsoleColors.PURPLE_BOLD_BRIGHT + " est bloqué et ne peut donc plus se déplacer. " + "Le joueur " + ConsoleColors.RED_BOLD_BRIGHT +  joueur2.getPseudo() + ConsoleColors.GREEN_BOLD_BRIGHT + " gagne " + ConsoleColors.PURPLE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
            Main.mettreAJourScores(joueur2,joueur1);
            Menus.MenuCommencement(this);

        } else if (joueur2Bloque) {
            Art.JeuPerdu();
            Main.mettreAJourScores(joueur1,joueur2);

            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "Le joueur " + ConsoleColors.RED_BOLD_BRIGHT + joueur2.getPseudo() + ConsoleColors.PURPLE_BOLD_BRIGHT + " est bloqué et ne peut donc plus se déplacer. " + "Le joueur " + ConsoleColors.BLUE_BOLD_BRIGHT + joueur1.getPseudo() + ConsoleColors.GREEN_BOLD_BRIGHT + " gagne " + ConsoleColors.PURPLE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);

            Menus.MenuCommencement(this);

        }
    }




    public void detruireCase(char lettre, int nombre, Plateau LePlateau) {//Détruire une case
        // Vérifier si c'est le tour du joueur courant
        if (joueurCourant == joueur1 || joueurCourant == joueur2) {
            int x = nombre - 1;  // Ajuster les coordonnées pour représenter les colonnes
            int y = lettre - 'A';  // Ajuster les coordonnées pour représenter les lignes
    
            // Vérifier si les coordonnées sont valides
            if ((x >= 0) && (x < largeur) && (y >= 0) && (y < hauteur)) {
    
                // Vérifier si la case contient un joueur
                if ((joueur1.getX() == x && joueur1.getY() == y) || (joueur2.getX() == x && joueur2.getY() == y)) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Impossible de détruire la case. Un joueur occupe cette case." + ConsoleColors.RESET);
                    Menus.MenuDetruire(LePlateau);

                } else if (!casesDestructibles[x][y]) {//Vérifier si la case est déjà détruite
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Impossible de détruire la case. La case est déjà détruite." + ConsoleColors.RESET);
                    Menus.MenuDetruire(LePlateau);

                } else {
                    casesDestructibles[x][y] = false;
    
                    System.out.println(ConsoleColors.BLUE_BOLD + "\nCase (" + lettre + ", " + nombre + ") détruite !" + ConsoleColors.RESET);
                    passerTour();

                }
    
            } else {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Impossible de détruire la case. Coordonnées invalides.\n" + ConsoleColors.RESET);
                Menus.MenuDetruire(LePlateau);

            }
    
        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Ce n'est pas le tour de " + joueurCourant.getPseudo() + ". Impossible de détruire la case.\n" + ConsoleColors.RESET);
        }
    }
    

    public void afficherPlateau() {//Afficher le plateau
        System.out.print("  ");
        for (int i = 1; i <= largeur; i++) {//Parcourir les colonnes
            System.out.print(" " + i + " ");
        }
        System.out.println();

        char lettre = 'A';//Initialiser la lettre A

        for (int i = 0; i < hauteur; i++) {//Parcourir les lignes
            System.out.print(" " + lettre + " ");
            lettre++;//Incrémenter la lettre

            for (int j = 0; j < largeur; j++) {//Parcourir les colonnes
                if (joueur1.getX() == j && joueur1.getY() == i) {//Si la position du joueur 1 est égale à la position actuelle
                    System.out.print(ConsoleColors.BLUE_BRIGHT +"J1"+ ConsoleColors.RESET+" "); // Position du joueur 1
                }else if(joueur2.getX() == j && joueur2.getY() == i)//Si la position du joueur 2 est égale à la position actuelle
                {
                    System.out.print(ConsoleColors.RED_BRIGHT +"J2"+ConsoleColors.RESET+ " "); // Position du joueur 2
                }else if (casesDestructibles[j][i]) {
                    System.out.print(ConsoleColors.WHITE_BRIGHT + "██" + ConsoleColors.RESET + " "); // Case destructible
                } else {
                    System.out.print(ConsoleColors.BLACK_BRIGHT + "██" + ConsoleColors.RESET + " "); // Case déjà détruite
                }
            }
            System.out.println();
        }
    }

}

