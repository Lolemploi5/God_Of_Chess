import java.io.Serializable;

public class Joueur implements Serializable {

    private String pseudo;
    private int x;
    private int y;
    private int points;


    public Joueur(int x, int y, String pseudo, int points ) {
        this.x = x;
        this.y = y;
        this.pseudo = pseudo;
    }
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public static boolean deplacerJoueur(int deltaX, int deltaY) {//Déplacer le joueur
        boolean deplacementPossible = false;//Initialiser le déplacement possible à faux
        int newX = Plateau.joueurCourant.getX() + deltaX;//Nouvelle position en x
        int newY = Plateau.joueurCourant.getY() + deltaY;//Nouvelle position en y

        // Vérifier si la nouvelle position est valide
        if (newX >= 0 && newX < Plateau.largeur && newY >= 0 && newY < Plateau.hauteur
                && Plateau.casesDestructibles[newX][newY]
                && !((Plateau.joueur1.getX() == newX && Plateau.joueur1.getY() == newY) || (Plateau.joueur2.getX() == newX && Plateau.joueur2.getY() == newY))) {

            Plateau.joueurCourant.setX(newX);//Affecter la nouvelle position en x
            Plateau.joueurCourant.setY(newY);//Affecter la nouvelle position en y

            deplacementPossible = true;//Le déplacement est possible

        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nErreur : Déplacement impossible. La nouvelle position est hors du plateau, la case est déjà détruite ou un joueur se trouve sur la case.\n" + ConsoleColors.RESET);
            deplacementPossible = false;//Le déplacement est impossible
        }

        return deplacementPossible;//Retourner si le déplacement est possible


    }

    // Méthode auxiliaire pour vérifier si un joueur est bloqué
    public static boolean estBloque(Joueur joueur) {
        int x = joueur.getX();
        int y = joueur.getY();

        // Vérifier si le joueur peut se déplacer dans n'importe quelle direction
        return !(peutSeDeplacer(x + 1, y) || peutSeDeplacer(x - 1, y) || peutSeDeplacer(x, y + 1) || peutSeDeplacer(x, y - 1));
    }

    // Méthode auxiliaire pour vérifier si une case est accessible
    public static boolean peutSeDeplacer(int x, int y) {
        return (x >= 0 && x < Plateau.largeur && y >= 0 && y < Plateau.hauteur && Plateau.casesDestructibles[x][y]
                && !((Plateau.joueur1.getX() == x && Plateau.joueur1.getY() == y) || (Plateau.joueur2.getX() == x && Plateau.joueur2.getY() == y)));
    }

}