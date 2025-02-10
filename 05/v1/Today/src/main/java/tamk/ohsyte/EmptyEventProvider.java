package tamk.ohsyte;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

public class EmptyEventProvider implements EventProvider {
    public List<Event> getEvents() {
        return new ArrayList<>();
    }

    public List<Event> getEventsOfCategory(Category category) {
        return new ArrayList<>();
    }

    public List<Event> getEventsOfDate(MonthDay monthDay) {
        return new ArrayList<>();
    }
}
