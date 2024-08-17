package com.scm.Smart_Contact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.Smart_Contact.services.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig 
{



//private InMemoryUserDetailsManager inMemoeyUserDetailsManager;
// @Bean
// public UserDetailsService userDetailsService()
// {

//    UserDetails user=  User
//    .withDefaultPasswordEncoder()
//    .username("admin")
//    .password("admin123")
//   // .roles("ADMIN","USER")
//    .build();
// System.out.println(user.getPassword());

//     var inMemoeyUserDetailsManager= new InMemoryUserDetailsManager();
//     return inMemoeyUserDetailsManager;

// }
@Autowired
private SecurityCustomUserDetailService userDetailService;


@Bean
public DaoAuthenticationProvider authenticationProvider()
{
    

    DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
{

   httpSecurity.authorizeHttpRequests(authorize->{

   // authorize.requestMatchers("/home","/register","/services").permitAll();
   authorize.requestMatchers("/user/**").authenticated();
   authorize.anyRequest().permitAll();
   });

   httpSecurity.formLogin(Customizer.withDefaults());



    return httpSecurity.build();
}
@Bean   
public PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}


}


