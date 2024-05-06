import java.util.*;

import Utils.*;

public class GreedyBFS extends BaseClass {
    public ArrayList<String> gbfs_buffer = new ArrayList<String>();
    public WordValidator w = new WordValidator(this.start_word);
    public Boolean local_optima = false;
    public Boolean no_path = false;

    public GreedyBFS(String start_word, String end_word) {
        super(start_word, end_word);
        this.gbfs_buffer.add(start_word);
    }

    public Boolean containsDupe() {
        Set<String> dupe_checker = new HashSet<>();
        for (int i = 0; i < this.gbfs_buffer.size(); i++) {
            Boolean dupe = dupe_checker.add(this.gbfs_buffer.get(i));
            if (!dupe) {
                return true;
            }
        }
        return false;
    }

    public String expandWord(String word) {
        ArrayList<String> exp_word = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                StringBuilder new_word = new StringBuilder(word);
                if (word.toLowerCase().charAt(i) != this.alphabet[j]) {
                    new_word.setCharAt(i, this.alphabet[j]);
                }
                if (!new_word.toString().toLowerCase().equals(word)
                        && w.isWordValid(new_word.toString().toLowerCase())) {
                    if (new_word.toString().equalsIgnoreCase(this.end_word)) {
                        return new_word.toString();
                    }
                    exp_word.add(new_word.toString().toLowerCase());
                }
            }
        }
        Collections.sort(exp_word, new GreedyComparator(this.start_word, this.end_word));
        if (exp_word.size() != 0) {
            return exp_word.get(0);
        }
        return null;
    }

    public void GreedyBFSRecursion() {
        String current_node = this.gbfs_buffer.get(this.gbfs_buffer.size() - 1);
        String next_node = expandWord(current_node);
        System.out.println(this.gbfs_buffer);
        if (this.containsDupe()) {
            this.local_optima = true;
            return;
        }
        if (next_node == null) {
            this.no_path = true;
            return;
        }
        System.out.println(current_node);
        this.gbfs_buffer.add(next_node);
        if (next_node.equalsIgnoreCase(this.end_word)) {
            return;
        }
        GreedyBFSRecursion();
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

    public String[] mainGBFS() {
        GreedyBFS gr = new GreedyBFS(this.start_word, this.end_word);
        String[] result = { "No Path", "Took 0ms to execute", "Visited 0 node(s)" };
        final long startTime = System.currentTimeMillis();
        gr.GreedyBFSRecursion();
        final long endTime = System.currentTimeMillis();
        System.out.println(gr.gbfs_buffer.size());
        if (!gr.local_optima && !gr.no_path) {
            System.out.println(gr.gbfs_buffer.size());
            result[0] = gr.toPath(gr.gbfs_buffer);
            // gr.printPath(gr.gbfs_buffer);
        }
        result[1] = "Took " + (endTime - startTime) + "ms to execute";
        result[2] = "Visited " + gr.gbfs_buffer.size() + " node(s)";
        if (gr.local_optima) {
            result[0] = "Local Optima";
        }
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        return result;
    }
}
