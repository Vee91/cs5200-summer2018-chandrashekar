package edu.northeastern.cs5200.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.northeastern.cs5200.service.MyPersonDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TravelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyPersonDetailService myPersonDetailService;
	
	@Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(myPersonDetailService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/employee").hasRole("EMPLOYEE")
			.antMatchers("/book").authenticated()
			.antMatchers("/tickets").authenticated()
			.antMatchers("/cards").authenticated()
			.antMatchers("/profile").authenticated()
			.antMatchers("/api/admin/**").hasRole("ADMIN")
			.antMatchers("/api/crew/flights").hasRole("EMPLOYEE")
			.antMatchers("/api/profile").authenticated()
			.antMatchers("/api/update/profile").authenticated()
			.antMatchers("/api/card").authenticated()
			.antMatchers("/api/card/**").authenticated()
			.antMatchers("/api/itinerary").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin().loginPage("/login").successHandler(new RefererAuthenticationSuccessHandler()).permitAll()
			.and()
			.logout().logoutUrl("/logout") 
			.logoutSuccessUrl("/login").permitAll();
		
	}

}
