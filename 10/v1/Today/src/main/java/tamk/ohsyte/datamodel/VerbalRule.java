package tamk.ohsyte.datamodel;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

public class VerbalRule implements Rule {
    public enum Ordinal {
        FIRST(1),
        SECOND(2),
        THIRD(3),
        FOURTH(4),
        FIFTH(5),
        LAST(6);

        private final int value;

        Ordinal(int value) {
            this.value = value;
        }

        public int value() { return this.value; }
    };

    private Ordinal ordinal;
    private DayOfWeek day;
    private Month month;

    public VerbalRule(Ordinal ordinal, DayOfWeek day, Month month) {
        this.ordinal = ordinal;
        this.day = day;
        this.month = month;
    }

    public static VerbalRule parse(String ruleString) {
        // Parse a rule of the following format:
        // first|second|third|fourth|fifth|last <weekday> in <month>
        //   weekday: Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday
        //   month: January|February|March| ... |November|December
        String[] parts = ruleString.split(" ");
        System.out.println(Arrays.toString(parts));
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid rule format");
        }

        List<String> ordinals = List.of("first", "second", "third", "fourth", "fifth", "last");
        System.out.println(Arrays.toString(ordinals.toArray()));
        if (!ordinals.contains(parts[0])) {
            throw new IllegalArgumentException("Unrecognized ordinal: " + parts[0]);
        }
        Ordinal ordinal = Ordinal.valueOf(parts[0].toUpperCase());

        List<String> weekdays = List.of("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");
        System.out.println(Arrays.toString(weekdays.toArray()));
        if (!weekdays.contains(parts[1].toLowerCase())) {
            throw new IllegalArgumentException("Unknown weekday: " + parts[1]);
        }
        DayOfWeek day = DayOfWeek.valueOf(parts[1].toUpperCase());

        List<String> months = List.of("january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december");
        if (!months.contains(parts[3])) {
            throw new IllegalArgumentException("Unknown month: " + parts[3]);
        }
        Month month = Month.valueOf(parts[3].toUpperCase());

        return new VerbalRule(ordinal, day, month);
    }

    @Override
    public MonthDay getMonthDay(int year) {
        LocalDate d = LocalDate.of(year, this.month, 1);

        // FIXME: "last" will not give correct results yet!

        LocalDate d2 = d.with(
                TemporalAdjusters.dayOfWeekInMonth(this.ordinal.value(), this.day));
        return MonthDay.of(d2.getMonth(), d2.getDayOfMonth());
    }

    @Override
    public String toString() {
        return String.format("%s %s in %s", this.ordinal, this.day, this.month);
    }
}
