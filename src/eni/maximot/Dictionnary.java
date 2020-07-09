package eni.maximot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class Dictionnary {
    static String sourceFile = "./dictionnaire.txt";
    static List<String> words = new ArrayList<>();

    /**
     * Initialise ou ajoute la liste de mots du fichier source au dictionnaire.
     *
     * @param source | String | Chemin vers le fichier source.
     */
    public static void initialize(String source) {
        Scanner dictionary = readInputFile(source);
        setWords(dictionary);
    }

    public static void initialize() {
        initialize(sourceFile); // Fichier par défaut.
    }


    /**
     * Retourne une ligne aléatoire provenant du dictionnaire de mots.
     *
     * @return String | Chaine de caractères sélectionnée aléatoirement au sein du fichier source.
     */
    public static String getRandomWord() {
        return words.get(Tools.random.nextInt(words.size()));
    }

    /**
     * Retourne "true" si une chaine de caractères entrée en paramètre correspond à un mot du dictionnaire.
     * Retourne "false" si ce n'est pas le cas.
     *
     * @param guess String | Mot à trouver dans le fichier.
     * @return Booléen | Valeur "true" ou "false" attestant ou non de la présence du mot dans le fichier source.
     */
    public static boolean isInFile(String guess)  {
        return words.contains(guess);
    }

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

    /**
     * Assigne l'ensemble des mots présent dans le fichier à la variable "words".
     *
     * @param scanner Scanner | Scanner actif.
     */
    public static void setWords(Scanner scanner) {
        Set<String> lines = new HashSet<>(words); // Un set est utilisé pour éviter les doublons.
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine().toUpperCase());
        }
        scanner.close();
        words = new ArrayList<>(lines);
    }

    /**
     * Met à jour l'ensemble les mots du dictionnaire en y ajoutant les mots du fichier entré en source.
     *
     * @return List | Liste des mots.
     */
    public static List<String> getWords(String source) {
        initialize(source);
        return words;
    }

    public static List<String> getWords() {
        return words;
    }
}
