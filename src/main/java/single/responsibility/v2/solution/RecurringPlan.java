package single.responsibility.v2.solution;

import java.time.LocalDate;

// 일정을 확인하는 오퍼레이션이 변경될 때
public interface RecurringPlan {
    boolean includes(LocalDate day);
}
