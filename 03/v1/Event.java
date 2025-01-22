import java.time.LocalDate;

public class Event {
    private String description;
    private LocalDate date;
    private Category category;

    public Event(String description, LocalDate date, Category category) {
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Category getCategory() {
        return this.category;
    }
}
