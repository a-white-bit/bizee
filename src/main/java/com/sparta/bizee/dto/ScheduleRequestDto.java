package com.sparta.bizee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
/*
 * -----------(학습용 메모)----------
 * JSON --> Object로 변환  : 역직렬화
 * Jackson 라이브러리가 해줌
 * 바이트코드(JSON 파일)로 객체를 생성해야 하는데,  리플렉션 기술을 사용함
 * 리플렉션으로 객체를 생성하려면 기본 생성자가 필수임
 * 결론: @NoArgsConstructor 꼭 필요함
 *
 * -------------(의문)--------------
 * 사용자 요청 JSON 형식에 의존할 수 밖에 없는가?
 * 아래 정의한 생성자 오버로딩 3개는 무쓸모인가? (현재는 적용되지 않는 것 같다. 기본생성자만 사용됨)
 *
 * 그러니까, 사용자의 JSON 형식이  내가 예상한 형식과 다른 형태로 요청이 된다면 요청을 거부할 수는 없는가?
 * 예시로  삭제 API는 id와 passKey가 필요한데, 이 둘은 잘 들어왔고, 추가로 title도 들어오면 성공적으로 기능을 진행함
 * --------------------------------
 */
public class ScheduleRequestDto {
    private int id;

    @NotBlank(message = "제목은 필수 입력사항입니다.")
    @Size(max = 200, message = "제목은 200자 이내로 작성해야합니다.")
    private String title;

    private String content;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String responsibility;

    @NotBlank(message = "암호는 필수 입력사항입니다.")
    private String passKey;


    // 등록 API 요청 리소스 전달
    public ScheduleRequestDto(String title, String content, String responsibility, String passKey) {
        this.title = title;
        this.content = content;
        this.responsibility = responsibility;
        this.passKey = passKey;
    }

    // 수정 API 요청 리소스 전달
    public ScheduleRequestDto(int id, String title, String content, String responsibility, String passKey) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.responsibility = responsibility;
        this.passKey = passKey;
    }

    // 삭제 API 요청 리소스 전달
    public ScheduleRequestDto(int id, String passKey) {
        this.id = id;
        this.passKey = passKey;
    }
}
