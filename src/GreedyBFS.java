import java.util.ArrayList;
import java.util.PriorityQueue;

import Utils.*;

public class GreedyBFS extends BaseClass {
    public ArrayList<String> gbfs_buffer = new ArrayList<String>();
    public WordValidator w = new WordValidator(this.start_word);

    public GreedyBFS(String start_word, String end_word) {
        super(start_word, end_word);
        this.gbfs_buffer.add(start_word);
    }

    public PriorityQueue<String> expandWord(String word) {
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
        return exp_word;
    }

    public void GreedyBFSRecursion() {
        String current_node = this.gbfs_buffer.get(this.gbfs_buffer.size() - 1);
        if (current_node.equalsIgnoreCase(this.end_word)) {
            return;
        }
        String next_node = expandWord(current_node).poll();
        this.gbfs_buffer.add(next_node);
        if (next_node.equalsIgnoreCase(this.end_word)) {
            return;
        }
        // System.out.println(this.gbfs_buffer);
        GreedyBFSRecursion();
    }

    public void mainGBFS() {
        GreedyBFS ucs = new GreedyBFS(this.start_word, this.end_word);
        final long startTime = System.currentTimeMillis();
        ucs.GreedyBFSRecursion();
        final long endTime = System.currentTimeMillis();
        ucs.printPath(ucs.gbfs_buffer);
        System.out.println("Took " + (endTime - startTime) + "ms to execute");
        System.out.println("Visited " + (ucs.gbfs_buffer.size() - 1) + " node(s)");
    }
}
