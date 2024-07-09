package com.sparta.bizee.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * -----------(학습용 메모)----------
 * [JSON --> 객체]로 변환  : 역직렬화
 * Jackson 라이브러리가 해줌
 * 바이트코드(JSON 파일)로 객체를 생성해야 하는데,  리플렉션 기술을 사용함
 * 리플렉션으로 객체를 생성하려면 기본 생성자가 필수임
 * 결론: 역직렬화는 @Getter, @NoArgsConstructor 필요함
 * --------------------------------
 */
@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    @NotBlank(message = "제목은 필수 입력사항입니다.")
    @Size(max = 200, message = "제목은 200자 이내로 작성해야합니다.")
    private String title;

    private String content;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String responsibility;

    @NotBlank(message = "암호는 필수 입력사항입니다.")
    private String passKey;
}
