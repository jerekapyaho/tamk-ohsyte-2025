package tamk.ohsyte.providers;

import tamk.ohsyte.datamodel.Category;
import tamk.ohsyte.datamodel.Event;
import tamk.ohsyte.filters.EventFilter;

import java.util.List;
import java.time.MonthDay;

public interface EventProvider {
    List<Event> getEvents();
    List<Event> getEvents(EventFilter filter);
    List<Event> getEventsOfCategory(Category category);
    List<Event> getEventsOfDate(MonthDay monthDay);
    String getIdentifier();
    boolean isAddSupported();
}
