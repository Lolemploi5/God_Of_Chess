public class Plateau {

    private int largeur;//Largeur du plateau
    private int hauteur;//Hauteur du plateau
    private Joueur joueur1;//Joueur 1
    private Joueur joueur2;//Joueur 2

    private Joueur joueurCourant;//Joueur entrain de jouer

    private boolean[][] casesDestructibles;//Cases destructibles

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
        this.joueurCourant = j1; // Initialiser le joueur courant au joueur 1 au début
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
    public void passerTour() {//Passer le tour
        // Changer le joueur courant
        if (joueurCourant == joueur1) {//Si le joueur courant est le joueur 1
            joueurCourant = joueur2;//Le joueur courant devient le joueur 2
            setJoueurCourant(getJoueur2());
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "\nC'est le tour de " + ConsoleColors.BLUE_BOLD_BRIGHT + joueurCourant.getPseudo() + ConsoleColors.PURPLE_BOLD_BRIGHT + " !" + ConsoleColors.RESET);
            System.out.println();
        } else {//Sinon le joueur courant est le joueur 2
            joueurCourant = joueur1;//Affecter le joueur courant au joueur 1
            setJoueurCourant(getJoueur1());
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "\nC'est le tour de " + ConsoleColors.RED_BOLD_BRIGHT + joueurCourant.getPseudo() + ConsoleColors.PURPLE_BOLD_BRIGHT + " !" + ConsoleColors.RESET);
            System.out.println();

        }

    }

    public void detruireCase(char lettre, int nombre, Plateau LePlateau) {//Détruire une case
        // Vérifier si c'est le tour du joueur courant
        if (joueurCourant == joueur1 || joueurCourant == joueur2) {
            int y = lettre - 'A';
            int x = nombre - 1;
    
            // Vérifier si les coordonnées sont valides
            if ((x >= 0) && (x < largeur) && (y >= 0) && (y < hauteur) && (casesDestructibles[x][y])) {
    
                // Vérifier si la case contient un joueur
                if ((joueur1.getX() == x && joueur1.getY() == y) || (joueur2.getX() == x && joueur2.getY() == y)) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Impossible de détruire la case. Un joueur occupe cette case.\n" + ConsoleColors.RESET);
                    Main.MenuDetruire(LePlateau);

                } else if (!casesDestructibles[x][y]) {
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Impossible de détruire la case. La case est déjà détruite.\n" + ConsoleColors.RESET);
                    Main.MenuDetruire(LePlateau);

                } else {
                    casesDestructibles[x][y] = false;
    
                    System.out.println(ConsoleColors.BLUE_BOLD + "\nCase (" + lettre + ", " + nombre + ") détruite !\n" + ConsoleColors.RESET);
                    passerTour();
                }
    
            } else {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Impossible de détruire la case. Coordonnées invalides.\n" + ConsoleColors.RESET);
                Main.MenuDetruire(LePlateau);

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

    public boolean deplacerJoueur(int deltaX, int deltaY) {//Déplacer le joueur
        boolean deplacementPossible = false;//Initialiser le déplacement possible à faux
        int newX = joueurCourant.getX() + deltaX;//Nouvelle position en x
        int newY = joueurCourant.getY() + deltaY;//Nouvelle position en y

        // Vérifier si la nouvelle position est valide
        if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur
                && casesDestructibles[newX][newY]
                && !((joueur1.getX() == newX && joueur1.getY() == newY) || (joueur2.getX() == newX && joueur2.getY() == newY))) {

            joueurCourant.setX(newX);//Affecter la nouvelle position en x
            joueurCourant.setY(newY);//Affecter la nouvelle position en y

            deplacementPossible = true;//Le déplacement est possible

        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Déplacement impossible. La nouvelle position est hors du plateau, la case est déjà détruite ou un joueur se trouve sur la case.\n" + ConsoleColors.RESET);
            deplacementPossible = false;//Le déplacement est impossible
        }

    return deplacementPossible;//Retourner si le déplacement est possible



    }
}

