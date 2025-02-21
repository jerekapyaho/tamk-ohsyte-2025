package tamk.ohsyte;

import java.time.MonthDay;
import java.util.Comparator;
import java.util.Objects;

public class AnnualEvent extends Event implements Comparable<Event> {
    private MonthDay monthDay;

    public AnnualEvent(MonthDay monthDay, String description, Category category) {
        super(description, category);

        this.monthDay = monthDay;
    }

    public MonthDay getMonthDay() {
        return this.monthDay;
    }

    /**
     * Compares this event to another.
     *
     * @return negative, zero, or positive
     * @see Comparable
     */
    @Override
    public int compareTo(Event other) {
        // Attempt to cast - compareTo should throw ClassCastException
        // if the other instance is not the same type
        AnnualEvent otherEvent = (AnnualEvent) other;

        int result = Objects.compare(
                this.monthDay,
                otherEvent.monthDay,
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
