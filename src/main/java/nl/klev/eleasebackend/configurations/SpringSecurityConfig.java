package nl.klev.eleasebackend.configurations;


import nl.klev.eleasebackend.filter.JwtRequestFilter;
import nl.klev.eleasebackend.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeRequests()
//                users
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/all/{string}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/{username}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("ADMIN", "USER")

//                authority-user
                .antMatchers(HttpMethod.GET, "/users/{username}/authorities").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users/{username}/authorities").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/{username}/authorities/{authority}").hasRole("ADMIN")

//                accounts
                .antMatchers(HttpMethod.POST, "/accounts").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/accounts/{accountId}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/accounts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/accounts").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/accounts/{accountId}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/accounts/**").hasAnyRole("ADMIN", "USER")

//                assigning entities
                .antMatchers(HttpMethod.PUT, "/accounts/{accountId}/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/accounts/{accountId}/membership").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/accounts/{accountId}/vehicle").hasRole("ADMIN")

//                memberships
                .antMatchers(HttpMethod.POST, "/memberships").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/memberships/{membershipId}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/memberships").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/memberships/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/memberships/**").hasRole("ADMIN")

//                vehicles
                .antMatchers(HttpMethod.POST, "/vehicles").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/vehicles").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/vehicles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")

//                garages
                .antMatchers(HttpMethod.POST, "/garages").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/garages").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/garages/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/garages/**").hasRole("ADMIN")

//                files
                .antMatchers("/files/**").hasAnyRole("ADMIN", "USER")


//                authentication
                .antMatchers("/authenticate").permitAll()

                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}