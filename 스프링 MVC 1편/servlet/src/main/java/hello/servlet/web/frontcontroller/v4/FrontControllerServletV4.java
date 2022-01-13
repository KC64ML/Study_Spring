package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v4/members/new-form
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerv4Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerv4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());  // "/front ~ "가 호출되면 new MemberFormControllerV4 실행
        controllerv4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());  // "/front ~ "가 호출되면 new MemberSaveControllerV4 실행
        controllerv4Map.put("/front-controller/v4/members", new MemberListControllerV4());  // "/front ~ "가 호출되면 new MemberListControllerV4 실행
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // /front-controller/v4/members -> new MemberListControllerv4()가 반환된다.
        String requestURI = request.getRequestURI();   // frontControllerServletv4 주소

        ControllerV4 controller= controllerv4Map.get(requestURI);
        // controllerv4Map 3개 중 선택된 것이 반환된다.
        // 없을 시 null을 반환한다.

        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paraMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();  // 추가

        String viewName = controller.process(paraMap, model);


        MyView view = viewResolver(viewName);

        view.render(model,request, response);

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
