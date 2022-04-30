package com.example.patient2.sec;

import com.example.patient2.sec.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired //injection des dépendences
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//spring security ne va pas générer un mot de passe car on a créer notre propre configuration

        /*

        String encodedPWD=passwordEncoder.encode("1234");
        System.out.println(encodedPWD);
        auth.inMemoryAuthentication().withUser("user1").password(encodedPWD).roles("USER");//premier utilisateur//hadik{noop}(no password encoder) veut dire qu'on dit à spring security que c'est pas la peine d'encoder le mot de passe
       // auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");//premier utilisateur//hadik{noop}(no password encoder) veut dire qu'on dit à spring security que c'est pas la peine d'encoder le mot de passe
        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1111")).roles("USER");//deuxième utilisateur
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("2345")).roles("USER","ADMIN");//troisième utilisateur
*/

        /*auth.jdbcAuthentication()//jdbc authentication
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?") //quand l'utilisateur va inserer sont username et mot de passe cette requete sera appelé dik ? ya3ni le username saisi
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);*/


        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();//demande à spring security d'utilisé un formulaire d'authentification par défaut
        http.authorizeRequests().antMatchers("/").permitAll(); /*hna ya3ni fach kadir f URL / ya3ni ca nécessite pas une authentification*/
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");/* hna wakha dakhel f URL /delete*  bach dir une suppression wla /edit/*** bach dir modification wla /save/*** bach t enregistrer  mat9derch 7it ghir ADMIN li 3andou l7e9*/
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/webjars/***").permitAll();
        http.authorizeRequests().anyRequest().authenticated(); //ya3ni toute les ressources nécessite une authentification
        http.exceptionHandling().accessDeniedPage("/403");

    }


}
