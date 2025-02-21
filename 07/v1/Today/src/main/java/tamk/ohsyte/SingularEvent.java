package tamk.ohsyte;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class SingularEvent extends Event implements Comparable<Event> {
    private LocalDate date;

    public SingularEvent(LocalDate date, String description, Category category) {
        // Call the superclass constructor to initialize
        super(description, category);

        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
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
