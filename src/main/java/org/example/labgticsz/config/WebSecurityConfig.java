package org.example.labgticsz.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {


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
