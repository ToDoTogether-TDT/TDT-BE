package TDT.backend.config;

import TDT.backend.common.auth.jwt.JwtAuthenticationFilter;
import TDT.backend.common.auth.jwt.JwtTokenProvider;
import TDT.backend.common.auth.jwt.MemberAuthenticationEntryPoint;
import TDT.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final LoginService loginService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().disable()
                .formLogin().disable().httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeRequests()
                .antMatchers("/users").permitAll()
                .antMatchers("/**").authenticated()
                .anyRequest().authenticated();
        return http.build();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider, loginService);
            builder.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);

            super.configure(builder);
        }
    }

}
