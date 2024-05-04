package Utils;

import java.io.BufferedReader;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordValidator {
    public String dictionary_filename = "oracle_dictionary.txt";
    public Set<String> dictionary_map = new HashSet<String>();

    public WordValidator(String word) {
        File file = new File(
                "C:\\Users\\ASUS\\Documents\\Sem 4\\Stima\\Tucil3_13522076\\src\\Utils\\" + this.dictionary_filename);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st;
        // System.out.println("Initializing..");
        try {
            while ((st = br.readLine()) != null) {
                if (st.length() == word.length()) {
                    this.dictionary_map.add(st);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println("Done");
    }

    public Boolean isWordValid(String word) {
        return this.dictionary_map.contains(word.toLowerCase());
    }

    // public static void main(String[] args) {
    // WordValidator w = new WordValidator();
    // Scanner input_word = new Scanner(System.in);
    // System.out.print("Masukkan word : ");
    // String word = input_word.nextLine();
    // System.out.println(w.isWordValid(word.toLowerCase()));
    // }
}
