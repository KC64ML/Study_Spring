package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v2/members/new-form
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());  // "/front ~ "가 호출되면 new MemberFormControllerV2 실행
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());  // "/front ~ "가 호출되면 new MemberSaveControllerV2 실행
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());  // "/front ~ "가 호출되면 new MemberListControllerV2 실행
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        // /front-controller/v2/members -> new MemberListControllerv2()가 반환된다.
        String requestURI = request.getRequestURI();   // frontControllerServletv2 주소

        ControllerV2 controller= controllerV2Map.get(requestURI);
        // controllerv2Map 3개 중 선택된 것이 반환된다.
        // 없을 시 null을 반환한다.

        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // new MyView("/WEB-INF/views/new-form.jsp");
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
