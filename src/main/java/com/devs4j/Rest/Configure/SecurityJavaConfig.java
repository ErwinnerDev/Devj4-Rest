package com.devs4j.Rest.Configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity // anotación propia de SpringSecurity para hacer configuraciones en endpoint (controller)
@EnableGlobalMethodSecurity( // anotacion de spring pata hacer configuraciones globlales (cualquier capa)
		//securedEnabled = true //@Secured("ROLE_USER") propio de Spring
		jsr250Enabled = true //de JEE
		,prePostEnabled = true // Para usar las anotaciones @PreAuthorize y @PostAuthorize, se pue usar con securedEnabled o jsr250Enabled
		) 
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{// se debe de estender de esta clase
	
	@Autowired
	private PasswordEncoderSpringSegurity passwordEncoder;

	//esta calse se sobre escribe. se crearon lso roles
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password(passwordEncoder.encoder().encode("password")).roles("ADMIN")
		.and()
		.withUser("userDev").password(passwordEncoder.encoder().encode("contra")).roles("USER")
		.and()
		.withUser("invitadoA").password(passwordEncoder.encoder().encode("123")).roles("INVITADO");
	}
	* Se comenta esta este metodo por que ya se gahace la implemetacion por StrinmgSecurity en la clase Devs4jUserDetailsService
	*/


	//configurar lso roles a las ruras URL
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/userjpa/**").hasRole("ADMIN")
			.antMatchers("/perfil/**").permitAll()//permitor cualquier rol solo que se sí pueda autentificarse
			.antMatchers("/rol/**").permitAll()
			.and()
			.httpBasic();
	}
	
	
	
	

}
