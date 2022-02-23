package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    // 이걸 호출하고 나면 결과로 할인 금액을 리턴해준다.
    /*
     * @return 할인 대상 금액
     *  */
    int discount(Member member, int price);
}
