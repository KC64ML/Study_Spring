package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    // VIP 라면
    void vip_o() {
        // given
        // 임의의 멤버 하나 생성
        Member member = new Member(1L, "memberVIP", Grade.VIP);


        // when
        // 10000원 일 때는 1000이 할인되어야 한다.
        int discount = discountPolicy.discount(member, 10000);

        // then
        // Assertions(org.assertj.core.api.Assertions)
        // 할인 금액이 1000원이 맞는지 검증
        assertThat(discount).isEqualTo(1000);
    }

    // 성공 테스트도 중요하지만 실패 테스트도 중요하다.
    @Test
    @DisplayName("VIP가 아니면 할인 적용되지 않아야 한다.")
    void vip_x(){
        // given
        Member member= new Member(2L,"memberBASIC", Grade.BASIC);


        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);

    }



}