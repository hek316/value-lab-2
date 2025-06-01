package single.responsibility.v1.problem;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 제목, 주차, 요일, 시작시간, 소요시간
 * 월간 회의를 등록할 수 있는 클래스
 */
public class Schedule {
    private static final int DAYS_IN_WEEK = 7;

    private String title;
    @JsonFormat(pattern = "HH:mm") private LocalTime from;
    @JsonFormat(pattern = "MINUTES")private Duration duration;


    private Integer ordinal;
    private DayOfWeek dayofWeek;

    public Schedule(String title, LocalTime from,
                    Duration duration, Integer ordinal, DayOfWeek dayofWeek) {
        this.title = title;
        this.from = from;
        this.duration = duration;
        this.ordinal = ordinal;
        this.dayofWeek = dayofWeek;
    }

    // 일정이 있는지 확인하는 메소드 - 확인하고 싶은 값  인자로 받기
    public boolean includes(LocalDate day) {
        if(!day.getDayOfWeek().equals(dayofWeek)) return false;
        // 주차 계산
        return (day.getDayOfMonth() / DAYS_IN_WEEK) + 1 == ordinal;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(this);
    }
}
