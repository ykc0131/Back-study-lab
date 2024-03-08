# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

# 1. 프로젝트 환경설정

---
## 라이브러리 살펴보기
> Gradle은 의존관계가 있는 라이브러리를 함께 다운로드 한다.

#### spring-boot-starter-web
- spring-boot-starter-tomcat : 톰캣(웹서버)
- spring-webmvc : 스프링 웹 MVC

#### spring-boot-starter
- dependency
- auto configuration
- logging
- spring-core

#### spring-boot-starter-test
- Junit이 핵심
---
## tymeleaf 템플릿엔진 동작 확인
1. 웹 브라우저에서 localhost:8080/hello를 던진다.
2. Spring boot는 Tomcat이라는 웹서버(WAS)를 내장하고 있고 GetMapping() 값과 매칭되는 것을 찾는다. 
3. GetMapping에 해당되는 매서드를 실행하고, Model을 통해 값을 전달한다. 
4. Model의 data값에 value를 넣어준다. 
5. 매서드의 return의 이름에 대해서 resources/templates/{return명}.html 을 찾아서 랜더링을 해준다.
    ex) return "hello" -> templates/hello.html 을 의미한다. 

<br>

#### 따라서, 컨트롤러에서 리턴 값으로 문자를 반환하면 ViewResolver가 화면을 찾아서 처리한다. 
- Spring boot 템플릿엔진 기본 viewName 매핑
- resources:templates/ + {ViewName} + .html

---
# 2. 스프링 웹 개발 기초
- 정적 컨텐츠 
  - 파일을 그대로 웹 브라우저에 올림
- MVC와 템플릿 엔진
  - MVC : Model-View-Controller
  - 동적
- API
  - Json 데이터 구조 포맷으로 클라이언트한테 데이터를 전달

---
## 정적 컨텐츠
> /static 에서 정적 컨텐츠를 제공하고 컨텐츠는 그대로를 반환한다. 
1. 웹 브라우저에서 localhost:8080/hello-static.html를 던진다.
2. Spring boot는 Tomcat이라는 웹서버(WAS)이 요청을 받는다. 이를 Spring에 넘긴다. 
3. 컨트롤러 쪽에 hello-static.html이 있는지 찾는다 = 컨트롤러가 먼저 우선순위를 가진다. 
4. 컨트롤러에 없으면 resources: static/hello-static.html을 찾는다. 
5. static/hello-static.html 그대로를 반환해준다. 
