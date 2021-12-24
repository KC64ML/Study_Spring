package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration : 설정, 구성정보를 나타낸다.
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository();
    // 깨질지 안깨질지 고민하지말고, TestCase로 검사한다.

    // 예상되는 출력 순서
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 결과 (singleton은 어떻게든 single을 보장)
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    // 어디서든 Appconfig를 통해 멤버 서비스를 불러다 쓸 수 있다.
    // 생성자 주입
    // @Bean : 스프링 컨테이너에 등록하겠다.
    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
         // 구체적인 것을 여기서 선택하도록 한다.
         // 생성자 주입
        System.out.println("call AppConfig.orderService");
         return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
     }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
         return new RateDiscountPolicy();
    }
}
