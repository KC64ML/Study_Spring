package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    // 멤버 레포지토에서 회원을 찾기 위함
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 멤버를 찾기
        Member member = memberRepository.findById(memberId);

        // OrderService 입장에서 할인에 대한 건 잘 모른다. 할인에 대한 건 discountPolicy 알아서 해라!
        // 이것이 바로 단일 체계 원칙! 잘 설계된 것이다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
