package ccc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/create_account").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()

                .antMatchers(HttpMethod.GET, "/api/event", "/api/eventCategory", "/api/eventCulture", "/api/location/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/user", "/api/event/unapproved").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET).hasAnyRole("USER","ADMIN")



                .antMatchers(HttpMethod.POST).hasAnyRole("USER","ADMIN")

                .antMatchers(HttpMethod.PUT).hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")


                .anyRequest().denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}