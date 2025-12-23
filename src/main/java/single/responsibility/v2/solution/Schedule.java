package single.responsibility.v2.solution;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 변경의 이유: 일정 기본 정보가 변경될 때
 * 제목,  시작시간, 소요시간
 * 회의를 등록할 수 있는 클래스
 */
public class Schedule {

    private String title;
    private LocalTime from;
    private Duration duration;
    private RecurringPlan plan;

    public Schedule(String title, LocalTime from, Duration duration,RecurringPlan plan) {
        this.title = title;
        this.from = from;
        this.duration = duration;
        this.plan  = plan;
    }

    public boolean includes(LocalDate day) {
        return plan.includes(day);
    }
}
