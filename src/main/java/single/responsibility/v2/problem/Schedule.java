package single.responsibility.v2.problem;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * 제목, 주차, 요일, 시작시간, 소요시간
 * 월간 회의를 등록할 수 있는 클래스
 */
public class Schedule {
    private static final int DAYS_IN_WEEK = 7;

    private String title;
    private LocalTime from;
    private Duration duration;

    private Integer ordinal;
    private DayOfWeek dayOfWeek;

    private Set<DayOfWeek> dayOfWeeks;

    public Schedule(String title, LocalTime from, Duration duration, Integer ordinal, DayOfWeek dayOfWeek) {
        this.title = title;
        this.from = from;
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
        this.ordinal = ordinal;
    }

    public Schedule(String title, LocalTime from, Duration duration, Set<DayOfWeek> dayOfWeeks) {
        this.title = title;
        this.from = from;
        this.duration = duration;
        this.dayOfWeeks = dayOfWeeks;
    }

    public boolean includes(LocalDate day) {
        if (dayOfWeek != null) {
            return checkMonthly(day);
        }

        return checkWeekly(day);
    }

    private boolean checkMonthly(LocalDate day) {
        if (!day.getDayOfWeek().equals(dayOfWeek)) {
            return false;
        }

        return (day.getDayOfMonth() / DAYS_IN_WEEK) + 1 == ordinal;
    }

    private boolean checkWeekly(LocalDate day) {
        return dayOfWeeks.contains(day.getDayOfWeek());
    }
}
