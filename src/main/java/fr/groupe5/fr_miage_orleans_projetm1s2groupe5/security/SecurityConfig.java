package fr.groupe5.fr_miage_orleans_projetm1s2groupe5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v*/trans/user").permitAll()
                .antMatchers(HttpMethod.GET, "api/v*/trans/users").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"api/v*/trans/user/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"api/v*/trans/user/{id}").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"api/v*/trans/user/{id}").hasRole("USER")
                .antMatchers(HttpMethod.POST,"api/v*/trans/abonnement/{username}").hasRole("USER")
                .antMatchers(HttpMethod.GET,"api/v*/trans/abonnements/{username}").hasRole("USER")
                .antMatchers(HttpMethod.POST,"api/v*/trans/titreTransport/{username}").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"api/v*/trans/titreTransport/validation/{username}").hasRole("USER")
                .antMatchers(HttpMethod.GET,"api/v*/trans/titreTransports/{username}").hasRole("USER")
                .anyRequest().hasRole("USER")
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   // public SecretKey getSecretKey(){
        //return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //}
}
