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

    public String toPath(ArrayList<String> path) {
        String res = "";
        for (int i = 0; i < path.size() - 1; i++) {
            if (!path.get(i).equals(" ")) {
                res += path.get(i).toString() + "->";
            }
        }
        res += path.get(path.size() - 1).toString();
        return res;
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
        Node no_res = null;
        return no_res; // dummy return
    }

    public String[] mainUCS() {
        UCS ucs = new UCS(this.start_word, this.end_word);
        Node res = new Node(null, null);
        String[] result = { "No Path", "Took 0ms to execute", "Visited 0 node(s)" };
        final long startTime = System.currentTimeMillis();
        res = (ucs.UCSPrioQueue());
        final long endTime = System.currentTimeMillis();
        if (res != null) {
            ucs.printPath(res.current_path);
            result[0] = ucs.toPath(res.current_path);
        }
        result[1] = "Took " + (endTime - startTime) + "ms to execute";
        result[2] = "Visited " + ucs.visited.size() + " node(s)";
        System.out.println("Took " + (endTime - startTime) + "ms to execute");
        System.out.println("Visited " + ucs.visited.size() + " node(s)");
        return result;
    }
}
