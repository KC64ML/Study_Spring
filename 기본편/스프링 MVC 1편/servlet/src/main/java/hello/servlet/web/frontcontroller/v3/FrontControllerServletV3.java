package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;

import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v3/members/new-form
@WebServlet(name = "frontControllerServletv3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerv3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerv3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());  // "/front ~ "가 호출되면 new MemberFormControllerv3 실행
        controllerv3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());  // "/front ~ "가 호출되면 new MemberSaveControllerv3 실행
        controllerv3Map.put("/front-controller/v3/members", new MemberListControllerV3());  // "/front ~ "가 호출되면 new MemberListControllerv3 실행
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // /front-controller/v3/members -> new MemberListControllerv3()가 반환된다.
        String requestURI = request.getRequestURI();   // frontControllerServletv3 주소

        ControllerV3 controller= controllerv3Map.get(requestURI);
        // controllerv3Map 3개 중 선택된 것이 반환된다.
        // 없을 시 null을 반환한다.

        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paraMap = createParamMap(request);

        ModelView mv = controller.process(paraMap);

        // new-form
        String viewName = mv.getViewName();// 논리 이름 new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paraMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName->paraMap.put(paraName, request.getParameter(paraName)));
        return paraMap;
    }
}
