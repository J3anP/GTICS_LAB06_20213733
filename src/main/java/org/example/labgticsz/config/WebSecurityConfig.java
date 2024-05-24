package org.example.labgticsz.config;

import jakarta.servlet.http.HttpSession;
import org.example.labgticsz.repository.RolRepository;
import org.example.labgticsz.repository.UserRepository;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.sql.DataSource;


@Configuration
public class WebSecurityConfig {

    final RolRepository rolRepository;
    final UserRepository userRepository;
    final DataSource dataSource;

    public WebSecurityConfig(RolRepository rolRepository, UserRepository userRepository, DataSource dataSource) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/submitLogin")
                .successHandler((request, response, authentication) -> {

                    DefaultSavedRequest defaultSavedRequest =
                            (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", userRepository.findByEmail(authentication.getName()));

                    if (defaultSavedRequest != null) {
                        String targetURl = defaultSavedRequest.getRequestURL();
                        new DefaultRedirectStrategy().sendRedirect(request, response, targetURl);
                    } else {
                        String rol = "";
                        for (GrantedAuthority role : authentication.getAuthorities()) {
                            rol = role.getAuthority();
                            break;
                        }
                        response.sendRedirect("/home/list");
                    }
                });

        http.authorizeHttpRequests()
                .requestMatchers("/home", "/home/list").hasAnyAuthority("CLIENTE", "GERENTE","ADMIN")
                .requestMatchers("/home/new","/home/edit").hasAnyAuthority("ADMIN")
                .requestMatchers("/home/delete").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll();

        http.logout()
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        return http.build();
    }
    @Bean
    public UserDetailsManager users(DataSource dataSource) {

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        String sqlAuth = "SELECT email, password FROM users WHERE email = ?";
        String sqlAuto = "SELECT u.email, r.name FROM users u INNER JOIN roles r ON u.idrol = r.id WHERE u.email = ?";

        users.setUsersByUsernameQuery(sqlAuth);
        users.setAuthoritiesByUsernameQuery(sqlAuto);
        return users;
    }
}
