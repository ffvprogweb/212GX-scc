package com.fatec.scc.security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fatec.scc.servico.UserDetailsServiceI;
@Configuration
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {
	Logger logger = LogManager.getLogger(ConfiguracaoDeSeguranca.class);
	@Autowired
	private UserDetailsServiceI userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			logger.info(">>>>>> metodo configura http security executado");
			http.csrf().disable().authorizeRequests()
			//.antMatchers("HttpMethod.GET", "/").permitAll()
			.antMatchers("HttpMethod.GET", "/sig/cliente").hasRole("ADMIN") //form cadastro
			.antMatchers("HttpMethod.POST", "/sig/clientes").hasRole("ADMIN") //save
			.antMatchers("HttpMethod.GET", "/sig/clientes").hasAnyRole("ADMIN","USER") //form consulta
			
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutUrl("/login?logout").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and().authorizeRequests()
			.antMatchers("/h2-console/**").hasRole("ADMIN")
			.anyRequest().authenticated();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info(">>>>>> gerenciador de autenticacao = ");
		auth.userDetailsService(userDetailsService).passwordEncoder(pc());
	}
	@Bean
	public BCryptPasswordEncoder pc() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/h2-console/**");
	}
}
