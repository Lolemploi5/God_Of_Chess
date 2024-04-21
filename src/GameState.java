import java.io.*;
import java.util.List;

public class GameState implements Serializable {
    private List<Score> scores;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurCourant;
    private boolean[][] casesDestructibles;

    public GameState(List<Score> scores, Joueur joueur1, Joueur joueur2, Joueur joueurCourant, boolean[][] casesDestructibles) {
        this.scores = scores;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurCourant = joueurCourant;
        this.casesDestructibles = casesDestructibles;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public boolean[][] getCasesDestructibles() {
        return casesDestructibles;
    }


    public List<Score> getScores() {
        return scores;
    }




    public void sauvegarderEtat(String nomFichier, String directoryPath) {
        try {
            // Créer le répertoire s'il n'existe pas
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Créer le fichier dans le répertoire spécifié
            FileOutputStream fileOut = new FileOutputStream(directoryPath + File.separator + nomFichier + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException f) {
            System.out.println("Erreur: Le fichier " + nomFichier + " ne peut pas être créé ou ouvert.");
            f.printStackTrace();
        } catch (IOException i) {
            System.out.println("Erreur: Une erreur d'entrée/sortie s'est produite lors de l'écriture dans le fichier " + nomFichier + ".");
            i.printStackTrace();
        }
    }

    public static GameState chargerEtat(String nomFichier, String directoryPath) {
        GameState gameState = null;

        try {
            File file = new File(directoryPath + File.separator + nomFichier + ".ser");
            if (!file.exists() || !file.isFile()) {
                System.out.println("Erreur: Le fichier " + nomFichier + " n'existe pas ou n'est pas un fichier.");
                return null;
            }

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            GameState e = (GameState) in.readObject();
            in.close();
            fileIn.close();
            return e;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Erreur: La classe GameState n'a pas été trouvée.");
            c.printStackTrace();
            return null;
        }
    }
}