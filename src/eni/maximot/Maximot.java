package eni.maximot;

public class Maximot {

    /**
     * @param args String[] | args[0]: Chemin vers le fichier source. (Optionnel)
     */
    public static void main(String[] args) {
        if (args.length > 0) { Dictionnary.sourceFile = args[0]; }
        Dictionnary.initialize();
        Drawing word = new Drawing();
        String guess = initialize(word);
        while (!word.getAnagrams().contains(guess)) {
            if (guess.equals("#SHUFFLE")) {
                word.setLetters();
                guess = initialize(word);
            }
            if (!word.getAnagrams().contains(guess)) {System.out.println("Le mot " + guess + " est incorrect!"); }
            System.out.println("Essayez autre chose!");
            guess = Tools.console.nextLine().toUpperCase();
        }
        System.out.println("Bravo, " + guess + " est un mot valide!");
        System.out.println("Voici une liste complète des possibilités:");
        for (String anagram : word.getAnagrams()) {
            System.out.println(anagram);
        }
    }

    /**
     * Affiche sous forme de chaine de caractères les lettres du mot tiré entré en paramètre.
     * Obtient et retourne la saisie de l'utilisateur.
     *
     * @param word Drawing | Tableau de caractères à mélanger et afficher.
     * @return String | Chaine de caractère correspondant à la saisie de l'utilisateur.
     */
    private static String initialize(Drawing word) {
        System.out.println("Les lettres ont été mélangées. Formez un mot à partir des lettres suivantes:");
        System.out.println(word);
        String guess = Tools.console.nextLine().toUpperCase();
        if (guess.equals("#SHUFFLE")) {
            word.setLetters();
            initialize(word);
        }
        return guess;
    }
}
