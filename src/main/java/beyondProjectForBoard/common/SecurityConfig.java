package beyondProjectForBoard.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // spring security 설정을 customizing 하기 위함
@EnableGlobalMethodSecurity(prePostEnabled = true) // pre:사전, post:사후
public class SecurityConfig {

    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
//                csrf공격에 대한 설정은 하지 않겠다 라는 의미
//                      csrf라는 보안공격이 있는데, 해당 공격에 대해 보안설정을 하지 않겠다라는 의미. MVC는 하면 안되지만 respfulAPI는 ㄱㅊ
                /*
                * csrf란
                * - 내가 우리은행(A)에 로그인 해놓기
                * - 다른 사이트 왔다갔다 하다가 이상한 사이트(B) 클릭!
                * - 그 사이트에서 우리은행에 요청으로 [A가 B에게 1000만원 송금 API 정송] 을 보냄
                * - 이걸 보통 악용할 수 있는 방법은 쿠키나 세션을 통한 로그인
                * - 이 공격 막는 방법은 각 페이지마다 csrf 키값을 설정하는 것
                * */
                .csrf().disable()

                .build();
    }
}
