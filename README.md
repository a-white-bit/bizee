# 🥣Bizee : 일정 관리 앱 서버

## 개요

> ### _**Bizee**_ 는 일정(Schedule)을 관리하고 공유할 수 있는 *웹 애플리케이션 서버*입니다.
> 
> 'Bizee'는 'Busy'의 발음을 가져와 만들어졌습니다.
> 

> ### 프로젝트 설명
> 웹에서 일정을 등록하고, 조회, 수정, 삭제를 할 수 있습니다.
>
> 현재는 http 요청에 대한 API 기능만 동작하며, 테스트 시에는 [postman](https://web.postman.co/)같은 도구를 사용합니다.
> 
> 현재 프로젝트에 데이터베이스는 사용되지 않습니다. (서버 종료시 일정 기록이 사라집니다.)
> 
> 본 프로젝트는 내일배움캠프 과제로 사용되었습니다.
> 
## 요구사항

> ### 기능 요구사항
> 0. 공통 조건
>   - 일정 작성, 수정, 조회 시 반환 받은 일정 정보에 **비밀번호**는 제외
>   - 일정 수정, 삭제 시 선택한 일정의 **비밀번호**와 요청할 때 함께 보낸 비밀번호가 일치하는 경우에만 가능
> 
> 
> 1. 일정 등록
>   - **할일 제목, 할일 내용, 담당자, 비밀번호, 작성일**을 저장할 수 있습니다.
>       - 저장된 일정 정보를 반환 받아 확인할 수 있습니다.
>
> 2. 조회 
>   - 선택한 일정의 정보를 조회할 수 있습니다.
> 
> 3. 일정 목록 조회 (전체 조회)
>   - 등록된 일정 전체를 조회할 수 있습니다.
>   - 조회된 일정 목록은 **작성일** 기준 내림차순으로 정렬되어있습니다.
> 
> 4. 수정
>   - 선택한 일정의 **할일 제목, 할일 내용, 담당자**를 수정할 수 있습니다.
>     - 서버에 일정 수정을 요청할 때 **비밀번호**를 함께 전달합니다.
>   - 수정된 일정의 정보를 반환받아 확인할 수 있습니다.
> 
> 5. 삭제
>   - 선택한 일정을 삭제할 수 있습니다.
>     - 서버에 일정 삭제를 요청할 때 **비밀번호**를 함께 전달합니다.
> 

> ### 프로그래밍 요구사항
>
> 1. Spring Boot를 사용하여 CRUD 기능이 포함된 REST API를 설계합니다.
> 2. 수정, 삭제 API 요청(reqeust) 방식은 (body) 방법을 사용합니다.
> 3. Entity를 그대로 반환하지 않습니다. (DTO를 사용)


<br>

## Use Case Diagram

<details>
<summary>다이어그램</summary>
<img src="src/main/resources/images/usecase.png" alt="Usecase">
</details>

<br>

## API


|    기능     | Method |      URL       |                                       request                                        |                                                   response                                                   |
|:---------:|:------:|:--------------:|:------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------:|
|   일정 등록   |  POST  |   /schedule    | { 'title':제목, 'content':내용, 'responsibility':담당자, 'passkey':암호, 'creationData':작성일 } |              { 'id':등록번호, 'title':제목, 'content':내용, 'responsibility':담당자, 'creationData':작성일 }               |
|   일정 조회   |  GET   | /schedule/{id} |                                            id: 등록번호                                  |              { 'id':등록번호, 'title':제목, 'content':내용, 'responsibility':담당자, 'creationData':작성일 }               |
| 일정 전체 조회  |  GET   |   /schedule    |                                                                                      | { 'scheduleList' : [{ 'id':등록번호, 'title':제목, 'content':내용, 'responsibility':담당자, 'creationData':작성일 }, ..] } |
|   일정 수정   |  PUT   |   /schedule    |      { 'id':등록번호, title':제목, 'content':내용, 'responsibility':담당자, 'passkey':암호 }      |              { 'id':등록번호, 'title':제목, 'content':내용, 'responsibility':담당자, 'creationData':작성일 }               |
|   일정 삭제   | DELETE |   /schedule    |                             { 'id':등록번호, 'passkey':암호 }                              |                                                { 'id':등록번호 }                                                 |



<br>

## ERD

<details>
<summary>자세히</summary>
</details>

<br>

## 구현해야 할 기능
<details>
<summary>자세히</summary>

1. 

</details>

<br>

## 프로젝트 진행 계획

1. 어떤 프로젝트인지와 구현해야 할 기능을 대략적으로 작성
    - 과제에 진행에 필요한 내용들을 [README.md](./README.md)에서 모두 볼 수 있도록 작성
2. 요구사항에 맞는 [Use Case Diagram](#Use-Case-Diagram), [API 명세서](#API), [ERD 작성](#ERD)
2. [구현해야 할 기능](#구현해야-할-기능)에 대한 세부적인 추가 기능 및 예외 처리 목록 작성
    - 구현 방법과 예외 처리에 대한 간략한 설명 및 계획 추가
3. [구현해야 할 기능](#구현해야-할-기능) 순서대로 Class 생성 및 코드 작성
    - 구현해야 할 기능에 따른 Class 설정


<br>