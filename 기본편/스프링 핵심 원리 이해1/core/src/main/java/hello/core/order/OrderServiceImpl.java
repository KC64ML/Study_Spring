package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // 멤버 레포지토에서 회원을 찾기 위함
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 고정 할인 정책도 필요
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 멤버를 찾기
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // OrderService 입장에서 할인에 대한 것 잘 모른다.
        // 할인에 대한 것은 discountPolicy 알아서 나오게
        // 이것이 바로 단일 체계 원칙! 잘 설계된 것이다.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
