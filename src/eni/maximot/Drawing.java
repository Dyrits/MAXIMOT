package eni.maximot;

import java.util.*;

public class Drawing {
    private String word;
    private List<Character> letters;
    private List<String> anagrams;

    /**
     * Constructeur.
     */
    public Drawing() {
        setWord();
        setLetters();
        setAnagrams();
    }

    /**
     * Compare la longueur et les lettres entre le mot tiré et une chaine de caractères entrés en paramètres.
     * Retourne "true" si ces éléments sont les mêmes, ou "false" si ce n'est pas le cas.
     *
     * @param guess String | Chaine de caractères dont les lettres sont à à vérifier.
     * @return Booléen | Valeur "true" ou "false" faisant état de la similitude (ou non) entre les lettres et la longueur du mot et celles du mot tiré.
     */
    public boolean checkLetters(String guess) {
        if (this.getLength() != guess.length()) { return false; }
        List <Character> letters = new ArrayList<>(this.letters);
        for (char letter : guess.toCharArray()) {
            letters.remove(Character.valueOf(letter));
        }
        return letters.size() == 0;
    }

    /**
     * @return String | Affiche les lettres du mot mélangé.
     */
    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        for (char letter : this.letters) {
           toString.append(letter);
        }
        return toString.toString();
    }


    // GETTERS & SETTERS

    public void setWord() {
        this.word = Dictionnary.getRandomWord();
    }

    public String getWord() {
        return this.word;
    }

    /**
     *  Assigne les lettres du mot à une ArrayList et les mélange.
     */
    public void setLetters() {
        this.letters = new ArrayList<>();
        for (char letter : this.word.toCharArray()) {
            this.letters.add(letter);
        }
        Collections.shuffle(this.letters);
    }

    public int getLength() {
        return this.word.length();
    }

    /**
     * Assigne l'ensemble des anagrammes du mot à une ArrayList.
     */
    public void setAnagrams() {
        this.anagrams = new ArrayList<>();
        for (String word : Dictionnary.getWords()) {
            if (checkLetters(word)) {
                anagrams.add(word);
            }
        }
    }

    public List<String> getAnagrams() {
        return this.anagrams;
    }


}
