import java.time.LocalDate;

public class EventTest {
    public static void main(String[] args) {
        Category category = new Category("test", "test");

        Event[] events = new Event[args.length];
        for (int i = 0; i < args.length; i++) {
            LocalDate today = LocalDate.now();
            Event event = new Event(args[i], today, category);
            events[i] = event;
        }

        for (Event event : events) {
            System.out.println(event);
        }
    }
}