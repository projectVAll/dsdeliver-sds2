package com.devsuperior.dsdeliver.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	//Metodo para liberar os cors
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {//tem que fazer esse teste para poder utilizar o h2
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();//desabilitando a protecao contra csrf, tipo de ataque baseado em session . como a aplicacao é Rest,
											//e o mesmo não guarda nada na session, não tem como sofrer esse tipo de ataque
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//Falando que o police não armazena estado
		http.authorizeRequests().anyRequest().permitAll();//liberando o acesso a todas requisições
	}

//Cors -Recurso que os navegadores tem quando uma aplicacao qu está em um dominio tenta acessar uma aplicacao que esta em outro dominio
	//por padrão é bloqueado por motivo de segurança
//macete para permitir que o cors permita/libere o acesso do backend em outro contexto/outros servidor	
	//backend vai estar hospedado no heroku front end vai estar em outro lugar
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}