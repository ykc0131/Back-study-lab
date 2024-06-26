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
3. 컨트롤러 쪽에 hello-static이 있는지 찾는다 = 컨트롤러가 먼저 우선순위를 가진다. 
4. 컨트롤러에 없으면 resources: static/hello-static.html을 찾는다. 
5. static/hello-static.html 그대로를 반환해준다. 

---
## MVC와 템플릿 엔진
#### Model - View - Controller
1. 웹 브라우저에서 localhost:8080/hello-mvc.html를 던진다.
2. Spring boot는 Tomcat이라는 웹서버(WAS)이 요청을 받는다. 이를 Spring에 넘긴다.
3. 컨트롤러 쪽에 hello-mvc가 있는지 찾는다 = 컨트롤러가 먼저 우선순위를 가진다.
4. 컨트롤러를 찾으면 해당 매서드를 수행하고 return "hello-template"를 하면 VewResolver에게 넘겨준다. 
5. ViewResolver가 뷰를 찾아주고 Thymeleaf 템플릿 엔진을 연결 시켜준다. 
6. 템플릿 엔진이 랜더링을 해서 변환한 HTML을 웹브라우저에 반환을 한다.

---
## API
이전 템플릿 엔진은 View라는 탬플릿이 있는 상황에서 조작하는 방식!
@ResponseBody를 사용하면 요청 데이터를 그대로 내려줌

#### JSON : Key-Value로 이루어진 구조

### @ResponseBody 사용 원리
1. 웹 브라우저에서 localhost:8080/hello-api.html를 던진다.
2. Spring boot는 Tomcat이라는 웹서버(WAS)이 요청을 받는다. 이를 Spring에 넘긴다.
3. 컨트롤러 쪽에 hello-api가 있는지 찾는다.
4. template은 ViewResolver한테 던져준다. 하지만! 컨트롤러에 @ResponseBody가 붙어있으면 HTTP의 응답에 데이터 그대로 넣을 거라고 인식한다.
    - 반환값이 객체일 경우, 기본 default는 json 방식으로 데이터를 만들어서 HTTP 응답에 반환하겠다는게 기본 정책.
5. @ResponseBody가 붙어있으면 HttpMessageConverter가 동작한다. 
    - 문자일 경우 = StringConverter 동작
    - 객체일 경우 = JsonConverter 동작

#### 추가내용
+)getter, setter 방식을 프로퍼티(Property) 접근 방식이라고 한다. 

---

---
# 3. 회원 관리 예제 - 백엔드 개발
### 1) Controller 
- 웹 MVC의 컨트롤러 역할
### 2) Service
- 비즈니스 도메인 객체를 가지고 핵심 비즈니스 로직이 동작하도록 구현한 객체
- ex. 회원은 중복 가입이 안된다. 
### 3) Repository
- 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
### 4) Domain 
- 비즈니스 도메인 객체 
- ex. 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

<br>

#### 추가내용
+)Optional은 값이 null이 나올 가능성을 대비하여 사용한다. 

---
## 테스트 케이스 작성
개발한 기능을 실행해서 테스트할 때, Java의 main 메소드를 통해서 실행하거나, 
웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다. 

#### JUnit
테스트 프레임워크

#### Assertions 
org.junit.jupiter 제공

---

## Service
Repository와 Domain을 활용해서 실제 비즈니스 로직을 작성

---
# 4. 스프링 빈과 의존관계
Spring 컨테이너에서 Spring Bean이 관리된다.


### 스프링 빈을 등록하는 2가지 방법
1. 컴포넌트 스캔과 자동 의존관계 설정
2. 자바 코드로 직접 스프링 빈 등록하기
---
### 1. 컴포넌트 스캔과 자동 의존관계 설정
- @Component Annotation
  - Spring이 Component와 관련된 Annotation이 있으면, 하나씩 스프링 객체로 생성을 해서 스프링 컨테이너에 등록을 한다. 
- @Autowired는 연관관계를 설정해준다. 
- ex) MemberController - MemberService - MemberRepository

<br>

#### Q) 아무 곳에 @Component가 있어도 되나?
#### A) 기본적으로는 안된다.
~SpringApplication.java에서 실행할 때, 해당 패키지 내에 있는 매서드해당 spring이 다 뒤져서 spring bean으로 등록한다. 
해당 프로젝트로 따지자면, HelloSpringApplication.java에서 실행 시작 후, 패키지 hello.hellospring 이후로 spring이 @Component Annotation이 붙은 매서드를 spring bean으로 등록해준다. 
- 즉,해당 패키지 하위에 존재하지 않으면 컴포넌트 스캔 대상이 되지 않는다.
- @SpringBootApplication을 까보면, @ComponentScan()이라는 Annotation이 있다. (ComponentScan 위치를 바꿀 수 있긴 하다.)


<br>

스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다. (유일하게 하나만 등록해서 공유한다.)
- 메모리 절약하고 좋다.
- 따라서, 같은 스프링 빈으로 모두 같은 인스턴스이다. 
- 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다. 

---
### 2. 자바 코드로 직접 스프링 빈 등록하기
@Configuration Annotation을 통해 빈 등록해주기

```java
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```
---
### 각각 장단점
Dependency Injection (DI)에는 1) 필드 주입, 2) setter 주입, 3) 생성자 주입 이렇게 3가지 방법이 있다. 
1. 필드 주입

    생성자를 빼고 필드에다가 Auto-wired하는 방법
    ```java
   @Controller 
   public class MemberController{
        @Autowired private MemberService memberService;
   }
   ```
   -> But, 필드 주입은 추천하지 않는다. 
    필드 주입은 이후에 수정 혹은 바꿀 수 있는 방법이 없다.


2. 생성자 주입

    생성자를 통해서 들어오는 방법
    ```java
   @Service
   public class MemberService{
        private final MemberRepository memberRepository;
        
        @Autowired
        public MemberService(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
   }
    ```
   

3. Setter 주입

    생성은 생성대로 되고 Setter는 나중에 호출이 되는 방법
    ```java
   @Controller
   public class MemberController{
        private final MemberService memberService;
   
       @Autowired
       public void setMemberService(MemberService memberService){
            this.memberService = memberService;
        }
   }
   ```
   - 단점 : 멤버 컨트롤을 호출했을 때 setter가 public으로 열려있어야 한다. 사실, memberService가 한번 설정되면 바꿔치기 할 이유가 없다. 퍼블릭하게 노출되면 잘못 바꿀 가능성도 있기 때문에 문제가 된다.

<br>

결과적으로 제일 많이 사용하는 게, 생성자 injection 방식이다.
조립 시점에 생성자로 한 번만 조립해놓고 끝을 낸다. 
- 의존관계가 실행 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장!

