package single.responsibility.v2.solution;

import java.time.LocalDate;

public interface RecurringPlan {
    boolean includes(LocalDate day);
}
