package Utils;

import java.util.Comparator;

public class AStarComparator extends BaseClass implements Comparator<Node> {
    public AStarComparator(String start_word, String end_word) {
        super(start_word, end_word);
    }

    public int compare(Node el1, Node el2) {
        int value1 = el1.current_path.size() + this.costCounter(el1.word);
        int value2 = el2.current_path.size() + this.costCounter(el2.word);
        if (value1 > value2) {
            return 1;
        } else if (value1 < value2) {
            return -1;
        }
        return 0;
    }
}
