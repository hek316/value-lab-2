package single.responsibility.v2.solution;

import java.time.DayOfWeek;
import java.time.LocalDate;

// 함께 사용되는 로직과 필드를 함께 분리
// 월간 회의 일정을 확인하는 로직이 변경될 때
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
