package com.sparta.bizee.Repository;

import com.sparta.bizee.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class ScheduleRepository {

    // Schedule 인스턴스 저장소
    private final Map<Integer, Schedule> scheduleList;

    public ScheduleRepository() {
        this.scheduleList = new HashMap<>();
    }

    public Schedule save(Schedule schedule) {
        // schedule Map의 Max ID 확인
        int maxId = !scheduleList.isEmpty() ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setId(maxId);

        // Map에 저장
        scheduleList.put(maxId, schedule);
        return schedule;
    }

    public Schedule findById(int id) {
        // Schedule 리스트에서 해당 일정이 존재하는지 확인
        if (scheduleList.containsKey(id)) {
            // 해당 일정 가져오기
            return scheduleList.get(id);
        } else {
            throw new IllegalArgumentException("해당 일정이 삭제되었거나 찾을 수 없습니다.");
        }
    }

    public List<Schedule> findAll() {
        // 비어있는 일정 리스트인지 확인
        if (scheduleList.isEmpty()) {
            throw new IllegalArgumentException("아무 일정도 없습니다.");
        }

        // Map -> List 변환
        List<Schedule> mapToList = new ArrayList<>(scheduleList.values());

        // 날짜 내림차순 정렬
        Collections.sort(mapToList);
        return mapToList;
    }


    /*
     * Memo---------------------
     * Schedule Entity -> setter 없애기 시도? (불변객체로 변경)
     * DB를 적용하면 트랜잭션, 영속성 때문에 상관 없을지도...
     */
    public Schedule update(Schedule schedule) {
        // id 일치 확인 + get
        Schedule original = findById(schedule.getId());

        // 암호 확인
        if (!Objects.equals(schedule.getPassKey(), original.getPassKey())) {
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
        }

        // 수정 작업
        if (schedule.getTitle() != null) {
            original.setTitle(schedule.getTitle());
        }
        if (schedule.getContent() != null) {
            original.setContent(schedule.getContent());
        }
        if (schedule.getResponsibility() != null) {
            original.setResponsibility(schedule.getResponsibility());
        }

        return original;
    }

    public int delete(Schedule schedule) {
        // id 일치 확인 + get
        Schedule original = findById(schedule.getId());

        // 암호 확인
        if (!Objects.equals(schedule.getPassKey(), original.getPassKey())) {
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
        }

        int id = original.getId();

        // 삭제
        scheduleList.remove(id);
        return id;
    }
}
