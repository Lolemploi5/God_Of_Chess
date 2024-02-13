import java.util.*;


public class StartMenu {
    public static void StartMenu(String[] args){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sélectionnez une action :");
            System.out.println("1. Action 1");
            System.out.println("2. Action 2");
            System.out.println("3. Quitter");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Vous avez choisi l'Action 1.");
                    // Ajoutez ici le code correspondant à l'Action 1
                    break;
                case 2:
                    System.out.println("Vous avez choisi l'Action 2.");
                    // Ajoutez ici le code correspondant à l'Action 2
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Choix non valide. Veuillez sélectionner une action valide.");
            }
        }
    }
}
