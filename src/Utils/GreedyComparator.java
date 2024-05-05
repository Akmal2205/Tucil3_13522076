package Utils;

import java.util.Comparator;

public class GreedyComparator extends BaseClass implements Comparator<String> {
    public GreedyComparator(String start_word, String end_word) {
        super(start_word, end_word);
    }

    public int compare(String el1, String el2) {
        int value1 = this.costCounter(el1);
        int value2 = this.costCounter(el2);
        if (value1 > value2) {
            return -1;
        } else if (value1 < value2) {
            return 1;
        }
        return 0;
    }
}
