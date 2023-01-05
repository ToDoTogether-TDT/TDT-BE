package TDT.backend.config;

import TDT.backend.common.auth.jwt.JwtAuthenticationFilter;
import TDT.backend.common.auth.jwt.JwtTokenProvider;
import TDT.backend.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class AppConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final LoginService loginService;

    public AppConfig(JwtTokenProvider jwtTokenProvider, LoginService loginService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginService = loginService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable().cors().disable()
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
