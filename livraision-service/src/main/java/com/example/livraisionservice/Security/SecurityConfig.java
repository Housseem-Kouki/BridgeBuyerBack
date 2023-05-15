package com.example.livraisionservice.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

 private UserDetailsServiceImp userDetailsServiceImp;
 private IUserService userService;

    public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp, IUserService userService) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.userService = userService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //qui sont les utilisateurs qui ont les droits d'acceder
        auth.userDetailsService(userDetailsServiceImp);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //Spécifier les droit d'acces

        http
                .authorizeRequests()
              //  .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
              //  .antMatchers("/login", "/refreshToken", "/activation").permitAll()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();


        //activation du filtre
        //http.addFilter(new JwtAuthentificationFilter(userService));
        //second Filtre
        http.addFilterBefore(new JwtAuthentificationFilter(userService), UsernamePasswordAuthenticationFilter.class);


        //toute les requetes n'essiste pas l'authfication
        // http.authorizeRequests().anyRequest().permitAll();
        /*
       http.authorizeRequests().antMatchers("/users", "/addUser").permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated();

         */
        //desactiver mecanisme frames for h2
        //http.headers().frameOptions().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
