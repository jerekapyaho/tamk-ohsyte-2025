package tamk.ohsyte;

import java.util.*;
import java.util.function.Consumer;

import tamk.ohsyte.datamodel.Event;
import tamk.ohsyte.providers.EventProvider;
import tamk.ohsyte.filters.EventFilter;

/**
 * Manages and queries the events available from event providers.
 */
public class EventManager {
    private static final EventManager INSTANCE = new EventManager();

    //private final List<EventProvider> eventProviders;
    private final Map<String, EventProvider> eventProviders;

    private EventManager() {
        this.eventProviders = new HashMap<>();
    }

    /**
     * Gets the only instance of the event manager.
     *
     * @return the instance
     */
    public static EventManager getInstance() {
        return INSTANCE;
    }

    /**
     * Adds an event provider to the manager's list if it isn't
     * already there.

     * @param provider the event provider to add
     * @return <code>true</code> if the provider was added, <code>false</code> otherwise
     */
    public boolean addEventProvider(EventProvider provider) {
        final String identifier = provider.getIdentifier();
        if (!this.eventProviders.containsKey(identifier)) {
            this.eventProviders.put(identifier, provider);
            return true;
        }
        return false;
    }

    /**
     * Removes the specified event provider from the manager's list.
     *
     * @param providerId the identifier of the event provider to remove
     * @return <code>true</code> if the provider was removed, <code>false</code> if not
     */
    public boolean removeEventProvider(String providerId) {
        if (this.eventProviders.containsKey(providerId)) {
            this.eventProviders.remove(providerId);
            return true;
        }
        return false;
    }

    /*
     * Get all the events available from all registered event providers.
     *
     * @return the list of all events
     */
    private List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        for (String identifier : this.eventProviders.keySet()) {
            EventProvider provider = this.eventProviders.get(identifier);
            events.addAll(provider.getEvents());
        }

        return events;
    }

    /**
     * Gets the number of event providers for the manager.
     *
     * @return the number of event providers
     */
    public int getEventProviderCount() {
        return this.eventProviders.size();
    }

    /**
     * Gets the identifiers of all event providers of the manager.
     *
     * @return the set of provider identifiers
     */
    public Set<String> getEventProviderIdentifiers() {
        return this.eventProviders.keySet();
    }

    public List<Event> getEventsByProvider(String providerIdentifier, EventFilter filter) {
        List<Event> events = new ArrayList<>();

        EventProvider provider = this.eventProviders.get(providerIdentifier);
        if (provider != null) {
            events.addAll(provider.getEvents(filter));
        }

        return events;
    }

    /**
     * Gets the events that are accepted by the specified filter.
     *
     * @param filter the filter
     * @return list of events
     */
    public List<Event> getEvents(EventFilter filter) {
        return this.getAllEvents().stream()
                .filter(event -> filter.accepts(event))
                .toList();
    }
}
