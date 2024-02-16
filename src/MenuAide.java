public class MenuAide {
    public static void Menuaide (){
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Les Règles du jeu :" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BRIGHT + "Pendant son tour un joueur peut déplacer son pion d’une case " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BRIGHT + "(verticalement ou horizontalement), puis il détruit une case du plateau." + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Le dernier joueur pouvant encore se déplacer" + ConsoleColors.GREEN_BOLD_BRIGHT + " gagne" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
        System.out.println();

        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Contraintes :" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas détruire une case occupée." + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BRIGHT + "- Un joueur ne peut pas occuper une case détruite ou une case deja occupée." + ConsoleColors.RESET);
        System.out.printf(ConsoleColors.WHITE_BOLD_BRIGHT + "- Un joueur bloqué pendant un tour est déclaré " + ConsoleColors.RED_BOLD_BRIGHT+ "perdant" + ConsoleColors.WHITE_BOLD_BRIGHT + "!" + ConsoleColors.RESET);
        System.out.println(" \n");

        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Les Commandes dans le jeu :" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "/s " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour sauvegarder." + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "/q " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour quitter le jeu." + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "/h " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour monter d'une case." + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "/b " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour baisser d'une case." + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "/d " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour allez a droite ." + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "/g " + ConsoleColors.RESET + ConsoleColors.WHITE_BRIGHT+ "pour allez a gauche." + ConsoleColors.RESET);
        System.out.println();
    }
}
