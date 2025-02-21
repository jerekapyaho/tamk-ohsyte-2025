package tamk.ohsyte;

import java.util.Comparator;

public class SingularEventComparator implements Comparator<SingularEvent> {
    public int compare(SingularEvent a, SingularEvent b) {
        return a.getYear() - b.getYear();
    }
}
