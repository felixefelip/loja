package com.spring.loja.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.spring.loja.model.Usuario;
import com.spring.loja.service.serviceImpl.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UsuarioServiceImpl usuarioDetailsSevice;

	@Bean
	public Usuario usuarioLogado() {
		Usuario usuarioLogado = new Usuario();

		return usuarioLogado;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/images/**").permitAll()
				.antMatchers("/cadastro", "login").not().authenticated().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("michelli").password(passwordEncoder().encode("123")).roles("ADMIN");

		auth.userDetailsService(usuarioDetailsSevice).passwordEncoder(new BCryptPasswordEncoder());

	}

	// Para quando o Bootstrap usado for baixado, não quando é usado pelo link
	// online
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/bootstrap/**");
		// web.ignoring().antMatchers("/bootstrap/**", "/style/**");
	}

}
