import java.time.LocalDate;

public class Event {
    private LocalDate date;
    private String description;
    private Category category;

    public Event(LocalDate date, String description, Category category) {
        this.date = date;
        this.description = description;
        this.category = category;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return String.format(
            "%s: %s (%s)",
            this.date, this.description, this.category);
    }
}
