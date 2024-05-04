import Utils.*;
import java.util.*;

public class UCS extends BaseClass {
    public PriorityQueue<Node> simpul_hidup = new PriorityQueue<Node>(new UCSComparator());
    public Set<String> visited = new HashSet<String>();
    public WordValidator w = new WordValidator(this.start_word);

    public UCS(String start_word, String end_word) {
        super(start_word.toLowerCase(), end_word.toLowerCase());
        ArrayList<String> start_path = new ArrayList<String>();
        start_path.add(start_word);
        Node temp_start = new Node(start_word, start_path);
        this.simpul_hidup.add(temp_start);
    }

    public ArrayList<String> expandWord(String word) {
        ArrayList<String> exp_word = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                StringBuilder new_word = new StringBuilder(word);
                new_word.setCharAt(i, this.alphabet[j]);
                if (!new_word.toString().toLowerCase().equals(word)
                        && w.isWordValid(new_word.toString().toLowerCase())
                        && !this.visited.contains(new_word.toString())) {
                    exp_word.add(new_word.toString().toLowerCase());
                    this.visited.add(new_word.toString());
                }
            }
        }
        return exp_word;
    }

    public Node UCSPrioQueue() {
        while (!this.simpul_hidup.isEmpty()) {
            Node current_node = this.simpul_hidup.poll();
            String exp_word = current_node.word;
            ArrayList<String> available_words = this.expandWord(exp_word);
            for (int i = 0; i < available_words.size(); i++) {
                ArrayList<String> temp_path = new ArrayList<String>(current_node.current_path);
                temp_path.add(available_words.get(i));
                Node temp = new Node(available_words.get(i), temp_path);
                this.simpul_hidup.add(temp);
                if (available_words.get(i).equals(this.end_word.toLowerCase())) {
                    return temp;
                }
            }
        }
        // System.out.println(this.simpul_hidup);
        return this.simpul_hidup.poll(); // dummy return
    }

    public void mainUCS() {
        UCS ucs = new UCS(this.start_word, this.end_word);
        Node res = new Node(null, null);
        final long startTime = System.currentTimeMillis();
        res = (ucs.UCSPrioQueue());
        final long endTime = System.currentTimeMillis();
        ucs.printPath(res.current_path);
        System.out.println("Took : " + (endTime - startTime) + "ms");
        System.out.println("Visited " + ucs.visited.size() + " node(s)");
    }
}
