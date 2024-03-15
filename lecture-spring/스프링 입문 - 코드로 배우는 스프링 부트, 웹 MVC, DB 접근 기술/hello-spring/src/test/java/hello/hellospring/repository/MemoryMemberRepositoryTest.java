package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.*;

import hello.hellospring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore(); //테스트가 실행이 되고 끝날 때마다 한 번씩 저장소를 지워준다.
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        // Assertions - org.junit.jupiter
        Assertions.assertEquals(member, result);

        // Assertions - org.assertj.core
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    // 모든 테스트는 순서랑 상관없이 메소드별로 다 따로 동작하게 설계해야한다.
    // 테스트가 하나 끝나고 나면 데이터를 클리어 해줘야한다.

    // 테스트 코드를 작성 후 개발하는 것을 = 테스트 주도 개발 (TDD)
    // 테스트를 먼저 만들고 구현 클래스를 만들어서 돌려보는 것!
}
