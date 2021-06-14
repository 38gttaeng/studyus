# StudyUs, 언택트 시대의 비대면 스터디 모임 서비스
------------
## 팀원 
박은영(조장), 김동현, 박은영, 최인철, 김다빈, 이혜민

------------
## 수행기간
2021년 4월 22일 ~ 6월 15일 (약 한달)

------------


## Contetns

1. [개요](#개요)
2. [설계의 주안점](#설계의-주안점)
3. [사용기술 및 개발환경](#사용기술-및-개발환경)
4. [프로젝트 기능 구현](#프로젝트-기능-구현)
5. [주요기능](#주요기능)
6. [Document](#Document)

------------

## 개요
+ 코로나 사태가 계속 이어지면서 함께 만나서 할 수 있는 스터디는 어려운 일이 되었습니다. 하지만 이렇게 사람을 만날 수 없는 시기일수록 함께 공부하거나 자신의 창작에 대한 피드백을 받고 싶어하는 사람들의 욕구도 커져가고 있습니다. 이렇게 함부로 만날 수 없는 사람들의 함께 만나서 공부하고자 하는 스터디 니즈를 충족하고자 ‘스터디어스’를 기획하게 되었습니다.
------------

## 설계의 주안점
- 회원가입과 비밀번호 재설정에서 mailsender를 통해 인증번호, 임시 비밀번호 발송.
- Apache Commons FileUpload를 통한 파일업로드.
- WebSocket을 이용하여 채팅방 및 실시간 채팅 구현.
- Lombok을 이용하여 어노테이션 설정으로 간단하게 Domain의 메소드를 생성함으로써 작성 코드를 줄여줌.
- Chart.js를 사용해 실시간으로 변경되는 그래프를 구현.
- Fullcalendar 라이브러리를 이용한 휴가 현황 및 프로젝트 전체 일정, 모임 일정 캘린더 구현.
- 카카오 장소 검색 api를 이용해 관리자 스터디카페 등록시 장소를 지정해 인서트 할 수 있도록 구현.
- AJAX를 통해 프로젝트 상세 페이지를 한 페이지에 구현.
- 아임포트를 이용해 카카오페이, 네이버 페이, 신용카드 결제 등의 결제 구현.
- pdf.js를 통해 pdf 파일 미리보기와 뷰어 구현.
- 게시글 페이지와 과제 페이지에서 Quill을 사용한 글 작성 페이지 구현.
- 공지사항 페이지에서 SummerNote를 사용한 글 작성 페이지 구현.
- 도로명주소 API를 사용하여 스터디카페 등록 시 주소 검색 등록 구현.


------------
## 사용기술 및 개발환경
![image](https://user-images.githubusercontent.com/81937349/118357070-29345100-b5b3-11eb-9a8f-e692a8b2ba48.png)
Category | Detail
---- | ----
Laguage | HTML5, JS, CSS3, JQuery, JSP, Java
Library & API | Kakaomap, OpenWeather, Easy-pie-chart
IDE | VisualStudio, Eclpise
Server | Tomcat(v8.5)
Document | Google Drive, Figma, ERDCloud, diagrams.net
CI | Github
DataBase | AWS RDS, Oracle 

------------
## 프로젝트 기능 구현


- 공통
    - 프로젝트 주제 선정 및 기획 
    - 플로우차트, 유스케이스 다이어그램 설계
    - DB & 클래스, 시퀸스 다이어그램 설계
    - 메인 페이지 프론트/백 기초 구현
<br>

- 오재승
  - 조장(풀스택)
    - 관리자 페이지(회원 목록 조회, 회원 삭제, 회원 수정, 각 게시판 수정/삭제) 담당.
    - 문서 작업, 산책일기 달력 구현 및 카카오 MAP API 연결, 관리자 페이지 백 기능 구현.
<br>

- 홍찬희
  - 풀스택(프론트/백)
    - 산책 짝궁 페이지(1:1 채팅, 게시판_CRUD, 게시판 페이징 처리) 담당.
    - 유저간 채팅 기능, 관리자 페이지 프론트(CSS,JS) 및 산책 짝궁 게시판 기능 구현.
<br>  
   
- 이혜성
  - 풀스택(프론트/백)
    - 멍멍 이야기(태그별 게시판 페이징 처리, CRUD(이미지 포함), 댓글, 좋아요) 페이지 담당.
    - 메인페이지 백 보조, 커뮤니티(멍멍이야기) 검색(조회), 댓글, 좋아요 기능 구현.
<br>

- 신성진
  - 풀스택(프론트/백)
    - 퍼피런 이야기(공지사항_CRUD, 댓글, 페이징 처리) 페이지 담당.
    - 반려견 계산기 페이지, 공지사항(퍼피런 이야기) 페이지 상세 기능 구현.
 <br>  
   
- 김다빈
  - 풀스택(프론트/백)
    - 산책일기(지도 API, 목표 등록, 목표 설정, 게시판, 그래프, 달력) 페이지 담당.
    - 캘린더, 산책일기 게시판_CRUD, 공지사항 백 보조, 산책 기록 등록 및 달성 기능 구현.
<br>

- 이주연
  - 풀스택(프론트/백)
    - 마이페이지(회원가입, 로그인, 아이디찾기 회원 목록, 프로필 등록) 페이지 담당.
    - 메인 페이지 프론트(CSS,JS), 날씨, 그래프 API 연동, 마이페이지 페이지 기능 구현.
------------
## 주요 기능
1. 회원가입
![0001](https://user-images.githubusercontent.com/81956425/118357017-d8bcf380-b5b2-11eb-8bc1-57e70f51a482.jpg)

2. 마이페이지
![0002](https://user-images.githubusercontent.com/81956425/118356858-2553ff00-b5b2-11eb-9a61-109ae974c15f.jpg)
![0003](https://user-images.githubusercontent.com/81956425/118356816-048ba980-b5b2-11eb-9593-bca20d9adc97.jpg)
3. 산책기록
![0004](https://user-images.githubusercontent.com/81956425/118356817-05bcd680-b5b2-11eb-8fa5-c65e5d9044dc.jpg)
![0005](https://user-images.githubusercontent.com/81956425/118356818-05bcd680-b5b2-11eb-878e-b6403cf4f61c.jpg)
4. 산책일기
![0006](https://user-images.githubusercontent.com/81956425/118356819-06556d00-b5b2-11eb-9f19-cbf97207816d.jpg)
![0007](https://user-images.githubusercontent.com/81956425/118356820-06ee0380-b5b2-11eb-9286-94ffd09fb5e3.jpg)
5. 산책짝꿍
![0008](https://user-images.githubusercontent.com/81956425/118356821-06ee0380-b5b2-11eb-85b0-cca9938e157d.jpg)
6. 멍멍이야기(커뮤니티)
![0011](https://user-images.githubusercontent.com/81956425/118356935-7a901080-b5b2-11eb-9a33-623e809b9ac5.png)
![0012](https://user-images.githubusercontent.com/81956425/118356937-7bc13d80-b5b2-11eb-91ad-18db1451fedd.png)
7. 반려견 계산기
![0009](https://user-images.githubusercontent.com/81956425/118356822-07869a00-b5b2-11eb-9732-d82b5fdcfd87.jpg)
8. 관리자
![0010](https://user-images.githubusercontent.com/81956425/118356824-07869a00-b5b2-11eb-9566-4ab18402ea17.jpg)


------------
## Document
### 1. 유스케이스
![유스케이스](https://user-images.githubusercontent.com/81937349/118353075-c71e2080-b59f-11eb-85b5-9db49f2ff080.png)

### 2. ERD
![ERD_통합](https://user-images.githubusercontent.com/81937349/118353105-df8e3b00-b59f-11eb-9a3a-d53fd93c04d7.png)
