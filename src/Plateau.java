
    public class Plateau {

        public Plateau (int longueurPlateau, int largeurPlateau) {

            int i, j;

            for (i = 0; i < longueurPlateau; i++) {
                for (j = 0; j < largeurPlateau; j++) {
                    System.out.print("██ ");  // Case blanche
                }
                System.out.println();  // Nouvelle ligne pour la prochaine rangée
            }

        }
    }

