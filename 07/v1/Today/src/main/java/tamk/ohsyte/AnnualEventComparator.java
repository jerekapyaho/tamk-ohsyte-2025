package tamk.ohsyte;

import java.util.Comparator;

public class AnnualEventComparator implements Comparator<AnnualEvent> {
    public int compare(AnnualEvent a, AnnualEvent b) {
        return a.getDescription().compareTo(b.getDescription());
    }
}
