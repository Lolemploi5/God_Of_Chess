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
            casesDestructibles[x][y] = false;
            System.out.println("Case (" + lettre + ", " + nombre + ") détruite !");
        } else {
            System.out.println("Impossible de détruire la case. Coordonnées invalides ou case déjà détruite.");
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
                    System.out.print("██ "); // Case destructible
                } else {
                    System.out.print("██ "); // Case déjà détruite
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
            System.out.println("Déplacement impossible. La nouvelle position est hors du plateau ou la case est déjà détruite.");
        }
    }

    public static void main(String[] args) {
        Plateau plateau = new Plateau(10, 10);

        // Afficher le plateau initial
        plateau.afficherPlateau();

        // Déplacer le joueur vers la droite (B) et vers le bas (2)
        plateau.deplacerJoueur(1, 1);
        plateau.afficherPlateau();

        // Demander à l'utilisateur de détruire une case spécifique
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez la lettre de la case (A à J) : ");
        char lettre = scanner.next().toUpperCase().charAt(0);
        System.out.println("Entrez le nombre de la case (1 à 10) : ");
        int nombre = scanner.nextInt();

        // Détruire la case spécifiée
        plateau.detruireCase(lettre, nombre);
        plateau.afficherPlateau();


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
