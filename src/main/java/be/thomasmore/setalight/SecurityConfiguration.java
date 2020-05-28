package be.thomasmore.setalight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/register", "/logout").permitAll()
                .antMatchers("/denied").permitAll()
                .antMatchers("user/profilpage/**", "user/edit-profile/**").authenticated()
                .antMatchers("/user/addFriends/**").hasAuthority("USER")
                .antMatchers("/event/events/**").permitAll()
                .antMatchers("/event/event", "/event/events", "/event/edit-event/**").hasAnyAuthority("ADMIN", "PRODUCTIEHUIS")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/productiehuis/", "/productiehuis/registerProductiehuis").access("not (hasAnyAuthority('USER'))")
                .antMatchers("/productiehuis/page/**").hasAnyAuthority("PRODUCTIEHUIS", "ADMIN")
                .antMatchers("/productiehuis/profilepageProductiehuis/**").hasAnyAuthority("PRODUCTIEHUIS", "ADMIN")
                .anyRequest().permitAll().and().formLogin()
                .and().exceptionHandling().accessDeniedPage("/denied");
        http.csrf().ignoringAntMatchers("/h2-console/**").and().headers().frameOptions().sameOrigin();
//        http.formLogin().loginPage("/productiehuis/login").defaultSuccessUrl("/productiehuis/");
        http.formLogin().loginPage("/login");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                "select username, password, true from users where username = ?").authoritiesByUsernameQuery(
                "select username, role from users where username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

}
