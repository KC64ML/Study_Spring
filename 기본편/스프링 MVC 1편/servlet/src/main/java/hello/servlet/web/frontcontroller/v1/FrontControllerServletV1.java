package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());  // "/front ~ "가 호출되면 new MemberFormControllerV1 실행
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());  // "/front ~ "가 호출되면 new MemberSaveControllerV1 실행
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());  // "/front ~ "가 호출되면 new MemberListControllerV1 실행
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // /front-controller/v1/members -> new MemberListControllerV1()가 반환된다.
        String requestURI = request.getRequestURI();   // frontControllerServletV1 주소

        ControllerV1 controller= controllerV1Map.get(requestURI);
        // controllerV1Map 3개 중 선택된 것이 반환된다.
        // 없을 시 null을 반환한다.

        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);
    }
}
