package com.dxhh.elearning.configs;

import com.dxhh.elearning.jwt.JwtAuthenticationEntryPoint;
import com.dxhh.elearning.jwt.JwtRequestFilter;
import com.dxhh.elearning.services.UserService;
import com.dxhh.elearning.utils.Routing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/course-comments/**").authenticated()
                        .requestMatchers(
                                Routing.USER_NOTES + "/**",
                                Routing.LECTURES + "/**",
                                Routing.FAVORITES + "/**",
                                Routing.LAST_LECTURE + "/**",
                                Routing.PROGRESS + "/**",
                                Routing.REGISTRATION + "/**",
                                Routing.SELF + "/**",
                                Routing.VNPAY + "/**",
                                Routing.PAYPAL + "/**"
                        )
                        .authenticated()
//                        .requestMatchers("/api/lecture-comments/**").authenticated()
//                        .requestMatchers("/api/lecturer-registration/**").authenticated()
                        .requestMatchers(Routing.STATS + "/**").hasRole("ADMIN")
                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                        .anyRequest().permitAll())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

//    @Bean
//    public ClientRegistration googleClientRegistration() {
//        return ClientRegistration.withRegistrationId("google")
//                .clientId("281158067602-ojdjfk6tuna36d0lqjhsni8q2ic1l6ec.apps.googleusercontent.com")
//                .clientSecret("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")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("http://localhost:8080/login/oauth2/code/{registrationId}")
//                .scope("openid", "profile", "email")
//                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
//                .tokenUri("https://accounts.google.com/o/oauth2/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .clientName("Google")
//                .build();
//    }
}