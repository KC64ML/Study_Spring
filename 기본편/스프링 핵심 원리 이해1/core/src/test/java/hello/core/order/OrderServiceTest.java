package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

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

