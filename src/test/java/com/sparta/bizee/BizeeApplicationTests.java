package com.sparta.bizee;

import com.sparta.bizee.entity.Comment;
import com.sparta.bizee.entity.Schedule;
import com.sparta.bizee.repository.CommentRepository;
import com.sparta.bizee.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
class BizeeApplicationTests {

	/*@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	CommentRepository commentRepository;

	@Rollback(false)
	@DisplayName("Entity 관계 설정 테스트")
	@Test
	void test1() {
		Schedule schedule = new Schedule();
		schedule.setTitle("일정제목");
		schedule.setContent("일정내용");
		schedule.setResponsibility("abc@email.com");
		schedule.setPassKey("123");

		scheduleRepository.save(schedule);
	}

	@Rollback(false)
	@DisplayName("Entity 관계 설정 테스트")
	@Test
	void test2() {

		Schedule schedule = scheduleRepository.findById(1L).orElseThrow();
		Comment comment = new Comment();
		comment.setContent("댓글내용");
		comment.setUserId(1L);
		comment.setSchedule(schedule);

		commentRepository.save(comment);
	}*/
}