package beyondProjectForBoard.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//    사용방법 : 1) slf4어노테이션 또는 2) Logger 직접선언

@RestController
@Slf4j
// ⭐ 방법 1 ⭐ slf4어노테이션 통한 로거 선엉 방법
public class LogController {
//    ⭐ 방법 2 ⭐로거직접선언방법 사용
//    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log/test1")
    public String logTest1(){
//        기존 로그 방식 : System.out.println();
//        문제점 : 성능이 좋지 않음, 로그분류작업 불가
        System.out.println("println 로그.");
//        logger.info("info 로그");
//        logger.error("error 로그");
//        logger.debug("debug 로그");
//        logger.trace("trace 로그");
//        logger.warn("warn 로그");
        log.info("info 로그"); // 어노테이션을 통한 방법
        return "ok";
    }
}
