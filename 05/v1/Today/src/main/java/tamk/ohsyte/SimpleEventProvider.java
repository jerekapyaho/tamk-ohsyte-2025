package tamk.ohsyte;

import java.time.Month;
import java.time.MonthDay;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class SimpleEventProvider implements EventProvider {
    private List<Event> events;

    public SimpleEventProvider() {
        final String[] rows = new String[]{
                "2001-03-24,OS X 10.0 released,apple/macos",
                "2001-09-25,OS X 10.1 released,apple/macos",
                "2002-08-23,OS X 10.2 Jaguar released,apple/macos",
                "2003-10-24,OS X 10.3 Panther released,apple/macos",
            "2024-09-16,macOS 15 Sequoia released,apple/macos",
            "2015-09-30,OS X 10.11 El Capitan released,apple/macos",
            "2019-10-07,macOS 10.15 Catalina released,apple/macos",
            "2017-09-25,macOS 10.13 High Sierra released,apple/macos",
            "2020-11-12,macOS 11 Big Sur released,apple/macos",
            "2007-10-26,Mac OS X 10.5 Leopard released,apple/macos",
            "2011-07-20,Mac OS X 10.7 Lion released,apple/macos",
            "2013-10-22,OS X 10.9 Mavericks released,apple/macos",
            "2018-09-24,macOS 10.14 Mojave released,apple/macos",
            "2021-10-25,macOS 12 Monterey released,apple/macos",
            "2012-07-25,OS X 10.8 Mountain Lion released,apple/macos",
            "2016-09-20,macOS 10.12 Sierra released,apple/macos",
            "2009-08-28,Mac OS X 10.6 Snow Leopard released,apple/macos",
            "2023-09-26,macOS 14 Sonoma released,apple/macos",
            "2005-04-29,Mac OS X 10.4 Tiger released,apple/macos",
            "2022-10-24,macOS 13 Ventura released,apple/macos",
            "2014-10-16,OS X 10.10 Yosemite released,apple/macos",
            "2023-09-19,Java SE 21 released,oracle/java",
            "2023-03-21,Java SE 20 released,oracle/java",
            "2022-09-20,Java SE 19 released,oracle/java",
            "2022-03-22,Java SE 18 released,oracle/java",
            "2021-09-14,Java SE 17 released,oracle/java",
            "2021-03-16,Java SE 16 released,oracle/java",
            "2020-09-16,Java SE 15 released,oracle/java",
            "2020-03-17,Java SE 14 released,oracle/java",
            "2019-09-17,Java SE 13 released,oracle/java",
            "2019-03-19,Java SE 12 released,oracle/java",
            "2018-09-25,Java SE 11 released,oracle/java",
            "2018-03-20,Java SE 10 released,oracle/java",
            "2017-09-21,Java SE 9 released,oracle/java",
            "2014-03-18,Java SE 8 released,oracle/java",
            "2011-07-28,Java SE 7 released,oracle/java",
            "2006-12-11,Java SE 6 released,oracle/java",
            "2004-09-30,J2SE 5.0 released,oracle/java",
            "2002-02-13,J2SE 1.4 released,oracle/java",
            "2000-05-08,J2SE 1.3 released,oracle/java",
            "1998-12-04,J2SE 1.2 released,oracle/java",
            "1997-02-18,JDK 1.1 released,oracle/java",
            "1996-01-23,JDK 1.0 released,oracle/java",
            "2024-02-08,Rust 1.76.0 released,programming/rust",
            "2023-12-28,Rust 1.75.0 released,programming/rust",
            "2023-11-16,Rust 1.74.0 released,programming/rust",
            "2023-10-05,Rust 1.73.0 released,programming/rust",
            "2023-08-24,Rust 1.72.0 released,programming/rust",
            "2023-07-13,Rust 1.71.0 released,programming/rust",
            "2023-06-01,Rust 1.70.0 released,programming/rust",
            "2023-04-20,Rust 1.69.0 released,programming/rust",
            "2023-03-09,Rust 1.68.0 released,programming/rust",
            "2023-01-26,Rust 1.67.0 released,programming/rust",
        };

        this.events = new ArrayList<Event>();

        for (String row : rows) {
            this.events.add(makeEvent(row));
        }
    }

    private Event makeEvent(String row) {
        String[] parts = row.split(",");
        LocalDate date = LocalDate.parse(parts[0]);
        String description = parts[1];
        String categoryString = parts[2];
        String[] categoryParts = categoryString.split("/");
        String primary = categoryParts[0];
        String secondary = null;
        if (categoryParts.length == 2) {
            secondary = categoryParts[1];
        }
        Category category = new Category(primary, secondary);
        return new Event(date, description, category);
    }

    public List<Event> getEvents() {
        return new ArrayList<Event>(this.events);
    }

    public List<Event> getEventsOfCategory(Category category) {
        List<Event> result = new ArrayList<Event>();
        for (Event event : this.events) {
            if (event.getCategory().equals(category)) {
                result.add(event);
            }
        }
        return result;
    }

    public List<Event> getEventsOfDate(MonthDay monthDay) {
        List<Event> result = new ArrayList<Event>();

        for (Event event : this.events) {
            final Month eventMonth = event.getDate().getMonth();
            final int eventDay = event.getDate().getDayOfMonth();
            if (monthDay.getMonth() == eventMonth && monthDay.getDayOfMonth() == eventDay) {
                result.add(event);
            }
        }

        return result;
    }
}
