package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // AppConfig를 하나 만들어서 이를 통해 멤버 서비스를 생성
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // new Member(1L, "memberA", Grade.VIP);

        // 스프링은 모든 것이 ApplicationContext 라는 것으로 실행된다.
        // AppConfig에 있는 환경설정 정보를 가지고 스프링 컨테이너에 넣고 관리함
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        // 이 상태에서 command+option+v를 누르면 아래의 내용과 같이 자동 생성이 된다.
        Member member = new Member(1L, "memberA", Grade.VIP);
        // 초기값 입력
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
