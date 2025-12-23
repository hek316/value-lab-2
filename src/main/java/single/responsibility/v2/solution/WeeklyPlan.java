package single.responsibility.v2.solution;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

// 함께 사용되는 로직과 필드를 함께 분리
// 데일리 스크럼 일정을 확인하는 로직이 변경될 때
public class WeeklyPlan implements RecurringPlan {

    private Set<DayOfWeek> dayOfWeeks;

    @Override
    public boolean includes(LocalDate day) {
        return dayOfWeeks.contains(day.getDayOfWeek());
    }
}
