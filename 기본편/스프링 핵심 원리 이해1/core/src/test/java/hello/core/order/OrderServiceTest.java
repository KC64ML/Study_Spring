package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    // @BeforeEach : 각 테스트 시작 전 무조건 실행이 되는 것
    @BeforeEach
    public void beforeEach(){
        // AppConfig를 만들고 멤버서비스, 오더서비스 할당해주기
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        // long을 사용해도 되지만, 이를 쓰면 null을 넣을 수 없다.
        // (이후에 DB 생성시 null을 사용할 수 있기 때문에 미리 고려).
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        // VIP인 경우 할인 금액인 1000원이 맞는지 검증
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}

