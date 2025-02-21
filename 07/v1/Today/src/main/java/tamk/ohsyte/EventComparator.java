package tamk.ohsyte;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    public int compare(Event a, Event b) {
        return a.compareTo(b);
    }
}
