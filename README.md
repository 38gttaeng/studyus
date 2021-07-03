# StudyUs, 언택트 시대의 비대면 스터디 모임 서비스
### http://studyus.co.kr/
------------
## 팀원 
박은영(조장), 김동현, 최인철, 김다빈, 이혜민

------------
## 수행기간
2021년 4월 26일 ~ 6월 15일 (약 한달 반)

------------

## Contents

1. [개요](#개요)
2. [설계의 주안점](#설계의-주안점)
3. [사용기술 및 개발환경](#사용기술-및-개발환경)
4. [프로젝트 기능 구현](#프로젝트-기능-구현)
5. [주요기능](#주요-기능)
6. [Document](#Document)

------------

## 개요
+ 코로나 사태가 계속 이어지면서 함께 만나서 할 수 있는 스터디는 어려운 일이 되었습니다. 하지만 이렇게 사람을 만날 수 없는 시기일수록 함께 공부하거나 자신의 창작에 대한 피드백을 받고 싶어하는 사람들의 욕구도 커져가고 있습니다. 이렇게 함부로 만날 수 없는 사람들의 함께 만나서 공부하고자 하는 스터디 니즈를 충족하고자 ‘스터디어스’를 기획하게 되었습니다.
<br>

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
<br>

------------
## 사용기술 및 개발환경
![개발환경](https://user-images.githubusercontent.com/82440040/121974226-eb953480-cdb9-11eb-822c-b52861c2a2ad.png)
Category | Detail
---- | ----
FrontEnd | HTML5, JS, CSS3, JQuery
BackEnd | Java(JDK 1.8), Servlet, Spring(5.2.5), Mybatis
OS | Windows OS
Library & API | MailSender, Apache Commons FileUpload, WebSocket, Lombok, Chart.js, Fullcalendar, Kakaomap, FontAwesome, Gson, Quill, SummerNote
IDE | Eclipse, VisualStudio
Server | Amazon EC2 Ubuntu 20.04 LTS, Amazon Route53, LetsEncrypt Tomcat 8.5
CI | Github
DataBase | Oracle(11g)

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
  - SSL : LetsEncrypt 인증키 설치
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
- 회원 관리
1. 회원가입
![git pdf_page_01](https://user-images.githubusercontent.com/74830395/121928863-8f132480-cd7b-11eb-82d6-861a74300ec1.png)
2. sns 로그인
![git pdf_page_02](https://user-images.githubusercontent.com/74830395/121928871-91757e80-cd7b-11eb-8eda-f24e7138d757.png)
3. 아이디/비밀번호 찾기
![git pdf_page_03](https://user-images.githubusercontent.com/74830395/121928926-9df9d700-cd7b-11eb-9e2b-e0a6b8d82c75.png)
4. 마이페이지
![git pdf_page_04](https://user-images.githubusercontent.com/74830395/121928932-9fc39a80-cd7b-11eb-97ac-98a36cb4c0dc.png)
![git pdf_page_05](https://user-images.githubusercontent.com/74830395/121928944-a4884e80-cd7b-11eb-8689-b5b8fa901f84.png)
<br>

- 스터디 카페
1. 스터디카페 조회 및 등록
![git pdf_page_06](https://user-images.githubusercontent.com/74830395/121927959-9ede3900-cd7a-11eb-8b35-94b95a1b99a4.png)
2. 스터디카페 룸 등록 및 리뷰
![git pdf_page_07](https://user-images.githubusercontent.com/74830395/121927970-a0a7fc80-cd7a-11eb-8e3d-ad01f845134a.png)
3. 스터디카페 예약 및 참여 신청
![git pdf_page_08](https://user-images.githubusercontent.com/74830395/121928083-c0d7bb80-cd7a-11eb-8741-badd192e1579.png)
<br>

- 스터디샵 결제
![git pdf_page_09](https://user-images.githubusercontent.com/74830395/121928256-ee246980-cd7a-11eb-86d9-9ef3be20c5fa.png)
<br>

- 스터디
1. 스터디 등록
![git pdf_page_10](https://user-images.githubusercontent.com/74830395/121928413-1613cd00-cd7b-11eb-9587-457a73e0db02.png)
2. 스터디 등록 및 가입 신청
![git pdf_page_11](https://user-images.githubusercontent.com/74830395/121928432-1b711780-cd7b-11eb-9e97-71e9a94cf17f.png)
3. 스터디 메인
![git pdf_page_12](https://user-images.githubusercontent.com/74830395/121928452-20ce6200-cd7b-11eb-8379-c5d0144440af.png)
<br>

- 공지사항
1. 공지사항 목록
![git pdf_page_13](https://user-images.githubusercontent.com/74830395/121928658-57a47800-cd7b-11eb-99c0-654c1a30f9a7.png)
2. 공지사항 등록 및 상세
![git pdf_page_14](https://user-images.githubusercontent.com/74830395/121928714-67bc5780-cd7b-11eb-8eb9-d2b2a0e4089e.png)
<br>

- 게시판
![git pdf_page_15](https://user-images.githubusercontent.com/74830395/121929006-b833b500-cd7b-11eb-9c83-f7ed9c381881.png)
<br>

- 과제
1. 그룹 조회 및 등록
![git pdf_page_16](https://user-images.githubusercontent.com/74830395/121929349-12347a80-cd7c-11eb-8aac-f84cc702e569.png)
2. 과제 조회
![git pdf_page_17](https://user-images.githubusercontent.com/74830395/121929360-16609800-cd7c-11eb-9617-50e7dec98995.png)
3. 과제/과제제출 등록
![git pdf_page_18](https://user-images.githubusercontent.com/74830395/121929363-182a5b80-cd7c-11eb-95cf-9812eb3287aa.png)
4. 과제 파일함
![git pdf_page_19](https://user-images.githubusercontent.com/74830395/121929371-1a8cb580-cd7c-11eb-8f30-33167b826a2f.png)
<br>

- 일정
![git pdf_page_20](https://user-images.githubusercontent.com/74830395/121929387-1d87a600-cd7c-11eb-89f9-8efa55bca924.png)
<br>

- 채팅
![git pdf_page_21](https://user-images.githubusercontent.com/74830395/121929397-1fea0000-cd7c-11eb-817d-d5cbd89b870d.png)
<br>

- 스터디 관리
1. 출석 확인
![git pdf_page_22](https://user-images.githubusercontent.com/74830395/121929664-64759b80-cd7c-11eb-82a3-2940bee31483.png)
2. 회원 목록
![git pdf_page_23](https://user-images.githubusercontent.com/74830395/121929675-663f5f00-cd7c-11eb-8bdf-c4cddff68991.png)
3. 게시물/댓글 관리
![git pdf_page_24](https://user-images.githubusercontent.com/74830395/121929682-68092280-cd7c-11eb-93d8-e56398960dc1.png)
<br>

- 관리자
1. 스터디 관리
![git pdf_page_25](https://user-images.githubusercontent.com/74830395/121929711-735c4e00-cd7c-11eb-95d7-58460864825b.png)
2. 스터디 카페 관리
![git pdf_page_26](https://user-images.githubusercontent.com/74830395/121929932-bd453400-cd7c-11eb-9916-58c89d8bd015.png)
3. 예약 관리
![git pdf_page_27](https://user-images.githubusercontent.com/74830395/121929940-bfa78e00-cd7c-11eb-828f-a3e3b2f9ac65.png)
![git pdf_page_28](https://user-images.githubusercontent.com/74830395/121929951-c209e800-cd7c-11eb-9b5e-e1d085c7fd8d.png)
4. 결제 관리
![git pdf_page_29](https://user-images.githubusercontent.com/74830395/121929981-cc2be680-cd7c-11eb-94a5-82b3c21157de.png)
<br>

------------
## Document
### ERD
![StudyUs erd](https://user-images.githubusercontent.com/74830395/121931190-3f822800-cd7e-11eb-8806-6697759343b7.png)
