package Utils;

import java.util.ArrayList;

public class BaseClass {
    public String end_word;
    public String start_word;
    public char[] alphabet = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    public BaseClass(String start_word, String end_word) {
        this.start_word = start_word;
        this.end_word = end_word;
    }

    public int costCounter(String word) {
        int counter = 0;
        for (int i = 0; i < end_word.length(); i++) {
            if (end_word.toLowerCase().charAt(i) == word.toLowerCase().charAt(i)) {
                counter++;
            }
        }
        return counter;
    }

    public int depthCounter(String word) {
        int counter = 0;
        for (int i = 0; i < end_word.length(); i++) {
            if (start_word.toLowerCase().charAt(i) == word.toLowerCase().charAt(i)) {
                counter++;
            }
        }
        return counter;
    }

    public void printPath(ArrayList<String> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            if (!path.get(i).equals(" ")) {
                System.out.print(path.get(i) + "->");
            }
        }
        System.out.println(path.get(path.size() - 1));
    }

    // public static void main(String[] args) {
    // WordValidator w = new WordValidator();
    // final long startTime = System.currentTimeMillis();
    // System.out.println(w.isWordValid("shay"));
    // final long endTime = System.currentTimeMillis();
    // System.out.println("Took : " + (endTime - startTime) + "ms");
    // }
}
