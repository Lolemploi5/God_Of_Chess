public class Score {
    private Joueur joueur;
    private int score;

    // Constructeur
    public Score(Joueur joueur, int score) {
        this.joueur = joueur;
        this.score = score;
    }

    // Méthodes d'accès
    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
