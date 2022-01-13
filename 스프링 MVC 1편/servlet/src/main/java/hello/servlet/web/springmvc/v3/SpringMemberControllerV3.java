package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * v3
 * Model 도입
 * ViewName 직접 반환
 * `@RequestParam 사용
 * `@RequestMapping -> @GetMapping, @PostMapping
 */

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // GET인 경우에만 호출 (좀 더 편리하게 작성할 수 있다.)
//    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    // 데이터 변경시 POST
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {

        Member member = new Member(username, age);
        memberRepository.save(member);


        model.addAttribute("member", member);
        return "save-result";
    }

    // /springmvc/v3/members 호출 됨
    // 더할 것이 없으면 비우기
    // 조회이므로 GET
//    @RequestMapping(method =RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";

    }
}
