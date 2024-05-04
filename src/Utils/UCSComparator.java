package Utils;

import java.util.*;

public class UCSComparator implements Comparator<Node> {
    public int compare(Node el1, Node el2) {
        if (el1.current_path.size() > el2.current_path.size()) {
            return 1;
        } else if (el1.current_path.size() < el2.current_path.size()) {
            return -1;
        }
        return 0;
    }
}
