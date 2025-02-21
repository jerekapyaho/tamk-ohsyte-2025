package tamk.ohsyte;

import java.time.LocalDate;
import java.time.MonthDay;

public class EventFactory {
    public static Event makeEvent(String dateString, String description, String categoryString) {
        // Category handling is the same for all currently known subclasses.
        Category category = Category.parse(categoryString);

        if (dateString.startsWith("--")) {  // yearless date
            MonthDay monthDay = MonthDay.parse(dateString);
            //System.out.printf("Making AnnualEvent: %s, %s, %s%n", dateString, description, category);
            return new AnnualEvent(monthDay, description, category);
        } else {
            LocalDate date = LocalDate.parse(dateString);
            //System.out.printf("Making SingularEvent: %s, %s, %s%n", dateString, description, category);
            return new SingularEvent(date, description, category);
        }
    }
}
