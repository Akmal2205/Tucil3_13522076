import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

import Utils.*;

public class GreedyBFS extends BaseClass {
    public ArrayList<String> gbfs_buffer = new ArrayList<String>();
    public WordValidator w = new WordValidator(this.start_word);

    public GreedyBFS(String start_word, String end_word) {
        super(start_word, end_word);
        this.gbfs_buffer.add(start_word);
    }

    public String expandWord(String word) {
        PriorityQueue<String> exp_word = new PriorityQueue<String>(new GreedyComparator(start_word, end_word));
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                StringBuilder new_word = new StringBuilder(word);
                new_word.setCharAt(i, this.alphabet[j]);
                if (!new_word.toString().toLowerCase().equals(word)
                        && w.isWordValid(new_word.toString().toLowerCase())) {
                    exp_word.add(new_word.toString().toLowerCase());
                }
            }
        }
        return exp_word.poll();
    }

    public void GreedyBFSRecursion() {
        String current_node = this.gbfs_buffer.get(this.gbfs_buffer.size() - 1);
        if (current_node.equalsIgnoreCase(this.end_word)) {
            return;
        }
        String next_node = expandWord(current_node);
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
        result[0] = gr.toPath(gr.gbfs_buffer);
        result[1] = "Took " + (endTime - startTime) + "ms to execute";
        result[2] = "Visited " + gr.gbfs_buffer.size() + " node(s)";
        gr.printPath(gr.gbfs_buffer);
        System.out.println("Took " + (endTime - startTime) + "ms to execute");
        System.out.println("Visited " + (gr.gbfs_buffer.size() - 1) + " node(s)");
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        return result;
    }
}
