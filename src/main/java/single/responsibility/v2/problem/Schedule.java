package single.responsibility.v2.problem;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 *
 * 새로운 요구사항 추가 - 데일리 스크럼
 * 제목, 주차, 요일, 시작시간, 소요시간
 * 월간 회의를 등록할 수 있는 클래스
 *
 * 서로 배타적으로 초기화 되는 인스턴스가 있으면 서로 다른 책임이 존재
 * -> 인스턴스 변수와 메서드 사이의 관계를 살펴본다. -> 일부 인스턴스 변수가 일부 메서드에서만 사용되는지 살펴본다.
 */
public class Schedule {
    private static final int DAYS_IN_WEEK = 7;

    private String title;
    private LocalTime from;
    private Duration duration;

    // 월간 회의에서만 쓰임
    private Integer ordinal;
    private DayOfWeek dayOfWeek;

    // 주간회의에서만 쓰임
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
        // 반복 일정 종류에 따라 초기화 되는 인스턴스 변수가 달라짐
        // 단일 책임 원칙 분리 기준 - 서로 배타적으로 초기화 되는 인스턴스가 있으면 서로 다른 책임이 존재한다는 징후
        // 서로 다른 시점에 초기화 되는 인스턴스 변수들을 기준으로 책임을 분리
        if (dayOfWeek != null) {
            // 월간 회의
            return checkMonthly(day);
        }
        // 주간 회의
        return checkWeekly(day);
    }

    // 월간 회의에서만 쓰임
    private boolean checkMonthly(LocalDate day) {
        if (!day.getDayOfWeek().equals(dayOfWeek)) {
            return false;
        }

        return (day.getDayOfMonth() / DAYS_IN_WEEK) + 1 == ordinal;
    }

    // 주간회의에서만 쓰임
    private boolean checkWeekly(LocalDate day) {
        return dayOfWeeks.contains(day.getDayOfWeek());
    }
}
