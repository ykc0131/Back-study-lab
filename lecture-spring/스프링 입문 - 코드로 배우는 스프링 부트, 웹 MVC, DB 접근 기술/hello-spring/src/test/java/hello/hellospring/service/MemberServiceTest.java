package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MemberService;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // 같은 메모리 멤버 리포지토리가 사용
        // (멤버 서비스 입장에서) 외부에서 메모리 멤버 리포지토리를 넣어준다. = Dependency Injection (DI)
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 주어진 상황.
        Member member = new Member();
        member.setName("hello");

        //when : 수행 작업.
        Long saveId = memberService.join(member);

        //then : 결과 제시.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        // 방법 1 - try-catch문으로 오류 잡는 방법
//        try{
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다");
//        } catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // 방법 2 - assertThrows
        memberService.join(member1);
        // IllegalStateException 예외 터짐.
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // NullPointerException

        //then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}