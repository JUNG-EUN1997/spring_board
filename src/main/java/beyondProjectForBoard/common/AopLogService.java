package beyondProjectForBoard.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// @Aspect : Aop 코드임을 암시
@Aspect
@Component
@Slf4j
public class AopLogService {
    /*
     * Controller마다 작성하던 log를 작성하지 않도록 공통화 함
     * 컨트롤러 들어가기 전에 낚아채는 미들웨어.
     * 종류
     *    1. Around : 항상
     *    2. Before : Controller 입장 전
     *    3. After : Controller 입장 후
     * */
//    aop의 대상(공통화의 대상)이 되는 controller, service등의 위치를 명시
    @Pointcut("within(@org.springframework.stereotype.Controller *)") // 모든 컨트롤러를 대상으로 하겠다. 기준은 각 파일 상단의 어노테이션 기준
    public void controllerPointCut(){


    }


////    ⭐ 사용방법 1 ⭐ Around 통해 controller와 걸쳐있는 사용 메타
//    @Around("controllerPointCut()")
////    joinPoint : 사용자가 실행하려고 하는 코드를 의미, 위에서 정의한 controllerPointCut() 소스코드 그 자체를 의미한다
//    public Object controllerLogger(ProceedingJoinPoint joinPoint){
//        log.info("aop start");
//        log.info("Method명 : "+ joinPoint.getSignature().getName()); // 가려고자하는 곳
//
////        직접 HttpServletRequest객체를 꺼내는 법
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("HTTP 메서드 : "+request.getMethod());
//
//        Object result = null;
//
//        try {
////            사용자ㅏ 실행하고자 하는 코드 실행부로 돌아가라는  의미
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//        log.info("aop end");
//
//        return result;
//    }

//    ⭐ 사용방법 2 ⭐ Before, After 어노테이션 활용
//    @Before("controllerPointCut()")
//    public void beforeController(JoinPoint joinPoint){
//        log.info("@@@@@aop start");
//        log.info("Method명 : "+ joinPoint.getSignature().getName()); // 가려고자하는 곳
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("HTTP 메서드 : "+request.getMethod());
//    }
//
//
//    @After("controllerPointCut()")
//    public void afterController(JoinPoint joinPoint){
//        log.info("@@@@aop end");
//    }

//    실습! : 입력한 parameter json 형식으로 받기
//    @Around("controllerPointCut()")
//    public Object controllerLogger(ProceedingJoinPoint joinPoint){
//        log.info("aop start");
//        log.info("Method명 : "+ joinPoint.getSignature().getName());
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        log.info("HTTP 메서드 : "+request.getMethod());
//        Map<String,String[]> parameterMap = request.getParameterMap();
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode objectNode = objectMapper.valueToTree(parameterMap);
//        log.info("user inputs : "+ objectNode);
//
//        Object result = null;
//
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//        log.info("aop end");
//
//        return result;
//    }




}
