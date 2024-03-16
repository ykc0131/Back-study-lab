package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 하나만 생성해서 공용으로 쓰면 된다.
    // 스프링 컨테이너에 등록하고 이를 쓰면됨.
    // 생성자로 연결.


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // Autowire라고 하면, 스프링 컨테이너에서 MemberService를 가져옴
    // AutoWired라고 되어 있으면 멤버 서비스를 스피링 컨테이너에서 관리하도록 스프링에 넣어준다.
    // 현재 오류가 나는 이유 : Service 측에 Annotation을 걸어주지 않아서.

    // 생성자를 위와 같이 쓰면, 멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록되어 있는 멤버 서비스 객체를 가져다가 넣어준다. = Dependency Injection
    // - 의존관계 주입


}
