package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    // @BeforeEach : 각 테스트 시작 전 무조건 실행이 되는 것
    @BeforeEach
    public void beforeEach(){
        // AppConfig를 만들고 멤버서비스 할당해주기
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        // given
        Member member = new Member(1L, "memberA",Grade.VIP);


        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);


        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
