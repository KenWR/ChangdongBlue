# ChangDong_Blue

## 01/25 할일


# 변경사항

### 01/14

1. 유저 로그인 시도 횟수 제한

### 01/13

1. 전자결제 시스템 구현
2. 브랜치 병합

### 01/11

1. sangha, sihyun-attendance 브랜치 병합

2. Security 유저 업데이트

3. 병합된 브랜치와 함께 리팩토링

- 데이터타입 Object -> 해당하는 타입
- 컨트롤러단 서비스로직 -> 서비스단으로
- JPQL지우고 Repository 수정 등

### 01/10

- 대부분 브랜치 통합 +  챗봇 통합

### 01/06

-  application-mariadb.yml 에서 작성한 file 부분을 [application-file](https://github.com/nowon-kdt/ChangDong_Blue/blob/7f28ce4e90a5150745b3f5395235789083aaa23a/src/main/resources/application-file.yml) 로 만들고 [application](https://github.com/nowon-kdt/ChangDong_Blue/blob/7f28ce4e90a5150745b3f5395235789083aaa23a/src/main/resources/application.yml)안 profile에 추가함
- phone 중복허용 [TeacherEntity](https://github.com/nowon-kdt/ChangDong_Blue/blob/7f28ce4e90a5150745b3f5395235789083aaa23a/src/main/java/cdb/domain/entity/TeacherEntity.java)
  [EmployeeEntity](https://github.com/nowon-kdt/ChangDong_Blue/blob/7f28ce4e90a5150745b3f5395235789083aaa23a/src/main/java/cdb/domain/entity/EmployeeEntity.java)

---

# 역할분담

- 양xx님 : 보드컨트롤러 , 시큐리티 필터, DB ,강의컨트롤러, 학생출석, 사이드메뉴,탑메뉴,인덱스등 전역 페이지 디자인 및 인덱스 자바스크립트, 전자결재 시스템 구현

- 차xx님 : 학생컨트롤러 ,강의컨트롤러, 학생출석,직원평가 헤더등 페이지 디자인, 전자결재 시스템 구현

- 본인 : 챗봇, DB, 챗봇에 코모란구현, 챗봇 자바스크립트, 챗봇, 학생출석 기능 및 페이지 구현

- 황xx님 : 강사컨트롤러, 파일업로드, 서버 및 CICD, 파일업로드 페이지등 디자인, 전자결재 시스템 구현

- 윤xx님 : 공지사항 페이지, 로그인페이지, 등록페이지등 디자인

- 윤xx : DB, 시큐리티, 리팩토링, 서버 및 CICD, 기타유틸 및 기능개선, 버전관리

---

현재 엔티티 상황
![참고 엔티티](https://user-images.githubusercontent.com/111475563/215385278-99f4e86a-c8de-4337-b1e4-911bbcd6cb4e.png)


---

학생 출석,퇴실 => 9시30분 , 17시40분
강사+직원 출근,퇴근 => 9시, 16시

학생 출결 => 교사가 해당 과목 페이지에서 체크하는 방식으로
출석:9시 ~ 18시 : AttendanceEntity
지각: 9시 이후 : AttendanceEntity
조퇴: 수업참여시간이 50% 이상일시 :AttendanceEntity
결석: 50%미만일시 : AttendanceEntity
!주간, 야간도 비율기준으로 측정

수료기준: 총 수업일 80% 참여, 지각조퇴 3개당 -1일, 결석 -1일 : AttendanceEntity, CourseEntity

강사,직원 => 출근및 퇴근은 본인들이 체크하고 시스템에서 시간에 따라 분류 : AttendanceEntity

부서 : 인사,
인사 => 해당 직원에 대한 기록 조회(근태관리) 및 코멘트 작성 : EmployeeEntity

학생상담 => 학생 기록 조회및 코멘트 작성 (상담일지등) : EmployeeEntity
취업지원 => 외부 취업관련 일정 등록 : EmployeeEntity
교육운영 => 수업 필요물품(책,컴퓨터 등) 발주, 총 수업일정및 강의실 등록 관리, 수업과정에 학생등록 : EmployeeEntity

강사 => 위에 언급한 학생들 출석체크 + 필요물품을 운영팀에 요청할수 있는 페이지 구성 + 학생개별 면담일지 : TeacherEntity

수업 : 한 선생님당 하나의 과정만 담당 , 동시에 여러수업 진행 :CourseEntity

인사 서비스 : 회원등록 , 직원조회 : EmployeeEntity
