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
 * 올바른 클래스의 크기 : 단일 책임 원칙 - 클래스는 단 한 가지 변경 이유만을 가져야한다. (높은 응집도)
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

    // 일정 확인 방식이 변경될 때 수정
    // 일정이 있는지 확인하는 메소드 - 확인하고 싶은 값  인자로 받기
    public boolean includes(LocalDate day) {
        if(!day.getDayOfWeek().equals(dayofWeek)) return false;
        // 주차 계산
        return (day.getDayOfMonth() / DAYS_IN_WEEK) + 1 == ordinal;
    }


    // 출력 포맷이 변경될 때 수정
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writeValueAsString(this);
    }

    // -> 서로 상관없는 이유로 변경되는 코드가 함께 뭉처있기 때문에 클래스의 응집도가 낮음, json 포맷을 변경하고 싶으면 같은 클래스의 포함된 include 메서드도 영향 받을 수 있고, include 를 의존하는 클라이언트에게도
    // 영향이 갈 수 있음: 결합도는 높음
}
