public class Plateau {

    private int largeur;
    private int hauteur;
    private Joueur joueur1;
    private Joueur joueur2;

    private Joueur joueurCourant;

    private boolean[][] casesDestructibles;

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }
    public String NomJoueur(){
        return getJoueurCourant().getPseudo();
    }
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public Plateau(int largeur, int hauteur , Joueur j1 , Joueur j2) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.joueurCourant = j1; // Initialiser le joueur courant au joueur 1 au début
        this.joueur1 = j1; // Le joueur démarre en haut à gauche
        this.joueur2 = j2; // Le joueur démarre en haut à gauche
        this.casesDestructibles = new boolean[largeur][hauteur];
        initialiserCasesDestructibles();
    }

    private void initialiserCasesDestructibles() {
        // Initialise toutes les cases comme destructibles au début
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                casesDestructibles[i][j] = true;
            }
        }
    }
    public void passerTour() {
        // Changer le joueur courant
        if (joueurCourant == joueur1) {
            joueurCourant = joueur2;
            setJoueurCourant(getJoueur2());
        } else {
            joueurCourant = joueur1;
            setJoueurCourant(getJoueur1());
        }

        System.out.println("\nC'est le tour de " + joueurCourant.getPseudo() + " !");
    }

    public void detruireCase(char lettre, int nombre) {

        // Vérifier si c'est le tour du joueur courant
        if (joueurCourant == joueur1 || joueurCourant == joueur2) {
            int x = lettre - 'A';
            int y = nombre - 1;

            // Vérifier si les coordonnées sont valides
            if (x >= 0 && x < largeur && y >= 0 && y < hauteur && casesDestructibles[x][y]) {
                casesDestructibles[y][x] = false;

                System.out.println(ConsoleColors.BLUE_BOLD + "\nCase (" + lettre + ", " + nombre + ") détruite !\n" + ConsoleColors.RESET);

            } else {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Impossible de détruire la case. Coordonnées invalides ou case déjà détruite.\n" + ConsoleColors.RESET);
            }

        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Ce n'est pas le tour de " + joueurCourant.getPseudo() + ". Impossible de détruire la case.\n" + ConsoleColors.RESET);
        }
    }


    public void afficherPlateau() {
        System.out.print("  ");
        for (int i = 1; i <= largeur; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();

        char lettre = 'A';

        for (int i = 0; i < hauteur; i++) {
            System.out.print(" " + lettre + " ");
            lettre++;

            for (int j = 0; j < largeur; j++) {
                if (joueur1.getX() == j && joueur1.getY() == i) {
                    System.out.print(ConsoleColors.BLUE_BRIGHT +"J1"+ ConsoleColors.RESET+" "); // Position du joueur 1
                }else if(joueur2.getX() == j && joueur2.getY() == i)
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

    public boolean deplacerJoueur(int deltaX, int deltaY) {
        boolean deplacementPossible = false;
        int newX = joueurCourant.getX() + deltaX;
        int newY = joueurCourant.getY() + deltaY;

        // Vérifier si la nouvelle position est valide
        if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur
                && casesDestructibles[newX][newY]
                && !((joueur1.getX() == newX && joueur1.getY() == newY) || (joueur2.getX() == newX && joueur2.getY() == newY))) {

            joueurCourant.setX(newX);
            joueurCourant.setY(newY);

            // Détruire la case après le déplacement
            casesDestructibles[newX][newY] = false;
            deplacementPossible = true;

        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Déplacement impossible. La nouvelle position est hors du plateau, la case est déjà détruite ou un joueur se trouve sur la case.\n" + ConsoleColors.RESET);
            deplacementPossible = false;
        }

    return deplacementPossible;



    }
}

