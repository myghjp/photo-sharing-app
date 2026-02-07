package portfolio.PhotoSharingApp.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/create-account").permitAll()
				.requestMatchers("/media/**").permitAll()
				.anyRequest().authenticated()
			);

		http.formLogin(login -> login
				.loginProcessingUrl("/postlogin")
				.loginPage("/login")
				.failureUrl("/login")
				.usernameParameter("emailAddress")
				.passwordParameter("password")
				.defaultSuccessUrl("/select-group", true)
				.permitAll()
			).logout(logout -> logout
				.logoutUrl("/postlogout")
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.permitAll()
			);

		return http.build();
	}
}