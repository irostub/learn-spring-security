package com.irostub.learnspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/info", "/account/**").permitAll() //ant pattern
                .mvcMatchers("/admin").hasRole("ADMIN") //admin 권한을 가지고 있어야만 접근 가능
                .mvcMatchers("/user").hasRole("USER") //admin 권한을 가지고 있어야만 접근 가능
                .anyRequest().authenticated(); //위 사항을 제외한 모든 요청에 대해 인증이 되어야 접근

        http.formLogin(); //spring security 가 기본 제공하는 login form
        http.httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    /* 더 이상 인메모리인증을 사용하지 않음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("iro").password("{noop}123").roles("USER").and()
                .withUser("admin").password("{noop}!@#").roles("ADMIN");
        //{noop} 에 대해, password 는 spring security 5.x 버전부터 등장했으며 password encoder 가 다양한 인코딩 방식 중 {} prefix 에
        //있는 암호화 방식을 따라 암호화 한다. 그리고 입력값이 들어와서 password 를 비교할때도 같은 인코딩을 통해 암호화를 하여 비교한다.
    }
     */
}
