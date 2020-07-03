package eni.maximot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Maximot1 {
    protected static Random random = new Random();
    protected static String sourceFile = "./dictionnaire.txt";

    /**
     *
     * @param args args[0]: Chemin vers le fichier source.
     */
    public static void main(String[] args) {
        if (args.length > 0) { sourceFile = args[0]; }
        Scanner console = new Scanner(System.in);
        char[] letters = getRandomWord();
        String word = initialize(console, letters);
        while (!isInFile(word) || !checkLetters(letters, word)) {
            if (word.equals("SHUFFLE")) { word = initialize(console, letters); }
            if (checkLetters(letters, word)) { System.out.println("Les lettres et la longueur du mot sont bonnes, mais celui-ci est invalide"); }
            else { System.out.println("Le mot " + word + " n'utilise pas exactement les mêmes lettres!"); }
            System.out.println("Essayez autre chose!");
            word = console.nextLine().toUpperCase();
        }
        console.close();
        System.out.println("Bravo, " + word + " est un mot valide!");
    }

    /**
     * Mélange les lettres et affiche sous forme de chaine de caractères les caractères d'un tableau de caractères entré en paramètre.
     * Obtient et retourne la saisie de l'utilisateur via le scanner entré en paramètre.
     *
     * @param scanner Scanner actif.
     * @param letters Tableau de caractères à mélanger et afficher.
     * @return Chaine de caractère correspondant à la saisie de l'utilisateur.
     */
    private static String initialize(Scanner scanner, char[] letters) {
        shuffle(letters);
        System.out.println("Les lettres ont été mélangées. Formez un mot à partir des lettres suivantes:");
        System.out.println("(Note: Vous pouvez taper SHUFFLE pour remélanger les lettres.)");
        display(letters);
        String word = scanner.nextLine().toUpperCase();
        if (word.equals("SHUFFLE")) { initialize(scanner, letters); }
        return word;
    }

    /**
     * Retourne une ligne aléatoire provenant d'un fichier source entré en paramètre.
     *
     * @param source Chemin vers le fichier source.
     * @return Tableau de caractères d'une ligne sélectionné aléatoirement au sein du fichier source.
     */
    public static char[] getRandomWord(String source) {
        Scanner dictionary = readInputFile(source);
        int randomLine = random.nextInt(getLinesCount(readInputFile(source)));
        for (int line = 1; line < randomLine; line++) {
            dictionary.nextLine();
        }
        char[] word = dictionary.nextLine().toUpperCase().toCharArray();
        dictionary.close();
        return word;
    }

    public static char[] getRandomWord() {
        return getRandomWord(sourceFile); // Fichier par défaut.
    }

    /**
     * Mélange les lettres d'un tableau de caractères entré en paramètre.
     *
     * @param word Tableau de caractères dont les lettres devront être mélangées.
     */
    public static void shuffle(char[] word) {
        for (int index = 0; index < word.length; index++) {
            int swapIndex = random.nextInt(word.length);
            swapLetter(word, index, swapIndex);
        }
    }

    /**
     * Échange la position de deux lettres au sein d'un tableau de caractères entré en paramètre.
     *
     * @param word Tableau de caractères dont les lettres doivent être échangées.
     * @param index Index de la première lettre à échanger au sein du tableau.
     * @param swapIndex Index de la seconde lettre à échanger au sein du tableau.
     */
    public static void swapLetter(char[] word, int index, int swapIndex) {
        char tempLetter = word[index];
        word[index] = word[swapIndex];
        word[swapIndex] = tempLetter;
    }

    /**
     * Affiche dans la console un tableau de caractères entré en paramètre sous forme de chaine de caractères.
     *
     * @param word Tableau de caractères à afficher.
     */
    public static void display(char[] word) {
        System.out.println(new String(word));
    }

    /**
     * Compare la longueur et les lettres entre un tableau de caractères et une chaine de caractères entrés en paramètres.
     * Retourne "true" si ces éléments sont les mêmes, ou "false" si ce n'est pas le cas.
     *
     * @param letters Tableau de caractères contenant les lettres du mot.
     * @param word Chaine de caractères dont les lettres sont à à vérifier.
     * @return Booléen faisant état de la similitude (ou non) entre les lettres et la longueur des paramètres.
     */
    public static boolean checkLetters(char[] letters, String word) {
        int length = letters.length;
        if (length != word.length()) {
            return false;
        }
        boolean[] indexes = new boolean[length];
        for (char character : letters) {
            for (int index = 0; index < length; index++) {
                // Si la lettre est déjà indexée et que ce n'est pas la dernière, on passe à la suivante suivante.
                if (indexes[index] && index != length - 1) {
                    continue;
                }
                // Si la lettre correspond au caractère du tableau, on l'indexe et on passe au caractère suivante:
                if (word.charAt(index) == character) {
                    indexes[index] = true;
                    break;
                }
                // Si aucune lettre ne correspond au caractère du tableau, on retourne "false":
                if (index == length - 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retourne "true" si une chaine de caractères entrée en paramètre correspond à une ligne du fichier.
     * Retourne "false" si ce n'est pas le cas.
     *
     * @param line Ligne à trouver dans le fichier.
     * @param source Chemin vers le fichier source.
     * @return Booléen attestant ou non de la présence de la ligne dans le fichier source.
     */
    public static boolean isInFile(String line, String source)  {
        Scanner dictionary = readInputFile(source);
        while (dictionary.hasNextLine()) {
            if (line.equals(dictionary.nextLine().toUpperCase())) {
                dictionary.close();
                return true;
            }
        }
        dictionary.close();
        return false;
    }

    public static boolean isInFile(String line) {
        return isInFile(line, sourceFile); // Fichier par défaut.
    }

    /**
     * Retourne le nombre de lignes non lues présentes au sein d'un scanner entré en paramètre.
     *
     * @param scanner Scanner actif.
     * @return Nombre de lignes non lues du scanner actif.
     */
    public static int getLinesCount(Scanner scanner) {
        int lines = 0;
        while (scanner.hasNextLine()) {
            lines++;
            scanner.nextLine();
        }
        scanner.close();
        return lines;
    }

    /**
     * Retourne, tout en gérant les erreurs, un objet de type Scanner permettant la lecture du fichier source entré en paramètre.
     *
     * @param source Chemin vers le fichier source.
     * @return Scanner permettant la lecture du fichier source.
     */
    public static Scanner readInputFile(String source) {
        FileInputStream file;
        try { file = new FileInputStream(source); }
        catch (IOException exception) {
            System.err.println("Une erreur est survenue lors de la lecture du fichier.");
            System.out.println("Entrez le chemin vers un fichier valide: ");
            // L'utilisateur peut entrer un chemin vers un autre fichier s'il y a une erreur:
            sourceFile = new Scanner(System.in).nextLine();
            return readInputFile(sourceFile);
        }
        return new Scanner(file);
    }

    public static Scanner readInputFile() {
        return readInputFile(sourceFile); // Fichier par défaut.
    }
}
