package tamk.ohsyte;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents an event that has occurred once in history.
 */
public class SingularEvent extends Event implements Comparable<Event> {
    private LocalDate date;

    /**
     * Constructs a singular event with date, description, and category.
     *
     * @param date the date of the event
     * @param description the description of the event
     * @param category the category of the event
     */
    public SingularEvent(LocalDate date, String description, Category category) {
        // Call the superclass constructor to initialize
        super(description, category);

        this.date = date;
    }

    /**
     * Gets the date of the event.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Convenience method to return just the year of the date.
     *
     * @return the year
     */
    public int getYear() {
        return this.date.getYear();
    }

    /**
     * Tests for equality with another event.
     *
     * @return true if events are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        // Identical references?
        if (o == this) return true;

        // Correct type and non-null?
        if (!(o instanceof SingularEvent)) return false;

        // Cast to our type:
        SingularEvent that = (SingularEvent) o;

        if (Objects.equals(this.getDate(), that.getDate()) &&
                Objects.equals(this.getDescription(), that.getDescription()) &&
                Objects.equals(this.getCategory(), that.getCategory())) {
            return true;
        }

        return false;
    }

    /**
     * Returns a hash code for this event.
     *
     * @return hash code computed based on the fields
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getDate(), this.getDescription(), this.getCategory());
    }

    /**
     * Compares this event to another.
     *
     * @return negative, zero, or positive
     * @see Comparable
     */
    @Override
    public int compareTo(Event other) {
        SingularEvent otherEvent = (SingularEvent) other;

        int result = Objects.compare(
                this.date,
                otherEvent.date,
                Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }

        result = Objects.compare(
                this.getDescription(),
                otherEvent.getDescription(),
                Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }

        return Objects.compare(
                this.getCategory(),
                otherEvent.getCategory(),
                Comparator.naturalOrder());
    }
}
