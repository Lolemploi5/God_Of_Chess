class Joueur {

    private String pseudo;
    private int x;
    private int y;


    public Joueur(int x, int y, String pseudo ) {
        this.x = x;
        this.y = y;
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
}