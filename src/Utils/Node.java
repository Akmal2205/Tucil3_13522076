package Utils;

import java.util.*;

public class Node {
    public ArrayList<String> current_path;
    public String word;

    public Node(String word, ArrayList<String> path) {
        this.word = word;
        this.current_path = path;
    }
}
