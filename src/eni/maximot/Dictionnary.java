package eni.maximot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Dictionnary {
    static String sourceFile = "./dictionnaire.txt";

    /**
     * Retourne une ligne aléatoire provenant d'un fichier source entré en paramètre.
     *
     * @param source String | Chemin vers le fichier source.
     * @return String | Chaine de caractères sélectionnée aléatoirement au sein du fichier source.
     */
    public static String getRandomWord(String source) {
        int randomLine = Tools.random.nextInt(getLinesCount(readInputFile(source)));
        // Le fichier source a été mis à jour (en cas d'invalidité) par le précédent appel à "readInputFile()".
        Scanner dictionary = readInputFile();
        for (int line = 1; line < randomLine; line++) {
            dictionary.nextLine();
        }
        String word = dictionary.nextLine().toUpperCase();
        dictionary.close();
        return word;
    }

    public static String getRandomWord() {
        return getRandomWord(sourceFile); // Fichier par défaut.
    }

    /**
     * Retourne le nombre de lignes non lues présentes au sein d'un scanner entré en paramètre.
     *
     * @param scanner Scanner | Scanner actif.
     * @return int | Nombre de lignes non lues du scanner actif.
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
     * Retourne "true" si une chaine de caractères entrée en paramètre correspond à une ligne du fichier.
     * Retourne "false" si ce n'est pas le cas.
     *
     * @param guess String | Mot à trouver dans le fichier.
     * @param source String | Chemin vers le fichier source.
     * @return Booléen | Valeur "true" ou "false" attestant ou non de la présence du mot dans le fichier source.
     */
    public static boolean isInFile(String guess, String source)  {
        Scanner dictionary = readInputFile(source);
        while (dictionary.hasNextLine()) {
            if (guess.equals(dictionary.nextLine().toUpperCase())) {
                dictionary.close();
                return true;
            }
        }
        dictionary.close();
        return false;
    }

    public static boolean isInFile(String guess) { return isInFile(guess, sourceFile); }

    /**
     * Retourne, tout en gérant les erreurs, un objet de type Scanner permettant la lecture du fichier source entré en paramètre.
     * Le fichier source est mis à jour manuellement si celui-ci est invalide.
     *
     * @param source String | Chemin vers le fichier source.
     * @return Scanner | Scanner permettant la lecture du fichier source.
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
