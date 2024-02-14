import java.util.*;

public class Plateau {

    private int largeur;
    private int hauteur;
    private Joueur joueur;
    private boolean[][] casesDestructibles;

    public Plateau(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.joueur = new Joueur(0, 0); // Le joueur démarre en haut à gauche
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
    public void detruireCase(char lettre, int nombre) {
        int x = lettre - 'A';
        int y = nombre - 1;

        // Vérifier si les coordonnées sont valides
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur && casesDestructibles[x][y]) {
            casesDestructibles[y][x] = false;

            System.out.println("\n Case (" + lettre + ", " + nombre + ") détruite ! \n");
        } else {
            System.out.println("\n Impossible de détruire la case. Coordonnées invalides ou case déjà détruite.\n");
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
                if (joueur.getX() == j && joueur.getY() == i) {
                    System.out.print("J1 "); // Position du joueur
                } else if (casesDestructibles[j][i]) {
                    System.out.print(ConsoleColors.WHITE_BRIGHT + "██ "); // Case destructible
                } else {
                    System.out.print(ConsoleColors.BLACK_BRIGHT + "██ "); // Case déjà détruite
                }
            }
            System.out.println();
        }
    }

    public void deplacerJoueur(int deltaX, int deltaY) {
        int newX = joueur.getX() + deltaX;
        int newY = joueur.getY() + deltaY;

        // Vérifier si la nouvelle position est valide
        if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur && casesDestructibles[newX][newY]) {
            joueur.setX(newX);
            joueur.setY(newY);

            // Détruire la case après le déplacement
            casesDestructibles[newX][newY] = false;
        } else {
            System.out.println("\n Déplacement impossible. La nouvelle position est hors du plateau ou la case est déjà détruite ou un Joueur se trouve sur la case.\n");
        }
    }

    public static void main(String[] args) {
        Plateau plateau = new Plateau(10, 10);

        // Afficher le plateau initial
        plateau.afficherPlateau();
        System.out.println();

        // Déplacer le joueur vers la droite (B) et vers le bas (2)
        plateau.deplacerJoueur(1, 1);
        plateau.afficherPlateau();
        System.out.println();

        // Demander à l'utilisateur de détruire une case spécifique
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\n Entrez la lettre et le nombre de la case (ex. C:9) : ");
        String input = scanner.nextLine().toUpperCase();

        // Extraire la lettre et le nombre de l'entrée utilisateur
        char lettre = input.charAt(0);
        int nombre = Integer.parseInt(input.substring(2));

        // Détruire la case spécifiée
        plateau.detruireCase(lettre, nombre);
        plateau.afficherPlateau();
        System.out.println();


    }
}

class Joueur {
    private int x;
    private int y;

    public Joueur(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}