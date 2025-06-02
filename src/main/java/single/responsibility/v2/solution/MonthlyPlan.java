package single.responsibility.v2.solution;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class MonthlyPlan implements RecurringPlan {


    private static final int DAYS_IN_WEEK = 7;

    private Integer ordinal;
    private DayOfWeek dayOfWeek;

    @Override
    public boolean includes(LocalDate day) {
        if (!day.getDayOfWeek().equals(dayOfWeek)) {
            return false;
        }
        return (day.getDayOfMonth() / DAYS_IN_WEEK) + 1 == ordinal;
    }

}
