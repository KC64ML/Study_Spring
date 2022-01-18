package hello.springmvc.basic.response;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");

        return mav;
    }

    // @ResponseBody를 사용후 String 반환시 문자가 그냥 반환된다.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello2");

        return "response/hello";
        // ""이면서 string일시 view의 논리적 이름으로 반환된다.
    }


    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello3");

    }
}



