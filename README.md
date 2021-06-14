# StudyUs, 언택트 시대의 비대면 스터디 모임 서비스
------------
## 팀원 
박은영(조장), 김동현, 최인철, 김다빈, 이혜민

------------
## 수행기간
2021년 4월 22일 ~ 6월 15일 (약 한달반)

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
    - DB 모델링 & 클래스, 시퀸스 다이어그램 설계
    - 와이어프레임 제작
<br>

- 박은영(팀장)
  - 스터디 카페
    - 스터디 카페 목록 조회 (카카오맵 API)
    - 스터디 카페 디테일 조회
    - 스터디 카페 등록/ 수정/ 삭제 (도로명 주소)
    - 별점을 포함한 후기 작성
  - 메인 페이지
  - 관리자 페이지 (DataTables)
    - 회원 관리
    - 스터디 관리
    - 스터디 카페 관리
    - 결제 관리
<br>

- 김동현
  - 스터디
    - 스터디 생성/ 수정
    - 스터디 가입 신청 및 수락
    - 스터디 검색 (해시태그 검색)
    - 스터디 가입신청 관리 : 스터디 가입신청자 목록 조회, 스터디 가입 승인/거부
  - 출석
    - 스터디 출석 체크 Insert 로직
    - 출석률 산출 : 최근 30일 출석률 조회, 출석률 TOP5 그래프(chart.js)
  - 채팅
    - 웹소켓 실시간 채팅
    - 채팅 참여자 확인
  - 프로젝트 배포 : EC2 원격 Ubuntu 서버 프로젝트 배포
  - 도메인 : Route53 도메인 및 네임서버 연결
<br>  
   
- 최인철
  - 스터디 메인
    - 최신 공지사항
    - 출석 인원 조회, 출석 요일 조회
    - 스터디 프리미엄 적용 유무 표시
  - 공지사항
    - 공지사항 조회
    - 공지사항 작성/수정/삭제 (SummerNote)
    - 댓글 등록/수정/삭제 (Quill)
    - 메인 공지사항 등록/수정
    - 최신 공지글 알림
    - 조회수 표시
  - 스터디 회원 목록
    - 스터디 회원 목록 조회(닉네임, 이메일, 과제율, 출석률)
    - 스터디 회원 추방
  - 스터디 출석 확인
    - 전체 출석률 조회
    - 회원 출석 기록 관리(DataTables)
  - 프리미엄 결제 (아임포트)
<br>
   
- 김다빈
  - 스터디 카페
    - 룸 설정
    - 스터디 카페 예약
  - 일정(캘린더)
    - 전체 스터디 일정 조회 (FullCalendar)
    - 일정 정보 모달창
    - 일정 카테고리별 조회
  - 게시판
    - 게시글 작성/수정/삭제 (Quill)
    - 댓글 등록/수정/삭제 (Quill)
    - 게시글 검색(카테고리별 검색 가능)
    - 다중 파일 업로드/다운로드
    - 무한 스크롤로 게시글 목록 조회
  - 과제
    - 과제 그룹 조회, 추가/수정/삭제
    - 과제, 과제제출 조회, 추가/수정/삭제
    - 과제 진행상황 조회(chart.js)
  - 파일함
    - 이미지 파일 조회
    - 기타 파일 조회
    - 파일 다운로드
  - 스터디 게시물/댓글 관리 (DataTables)
  - 관리자 페이지 카페 예약 관리 : 예약 차트, 예약 확인(FullCalendar)
<br>

- 이혜민
  - 로그인 : 소셜 로그인(네이버)
  - 회원가입(이메일 인증)
  - 아이디 / 비밀번호 찾기(이메일 인증)
  - 마이페이지
    - 가입한 스터디 목록
    - 내 일정 (FullCalendar)
    - 작성한 스터디 카페 후기 목록
    - 내 정보 조회/수정
    - 내 결제 정보 조회
<br>
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
