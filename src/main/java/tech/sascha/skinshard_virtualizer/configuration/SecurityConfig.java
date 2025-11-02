package tech.sascha.skinshard_virtualizer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

                                               .requestMatchers("/api/**").permitAll()
                                               .requestMatchers("/skins/**").permitAll()
                                               .requestMatchers("/images/**").permitAll()
                                               .anyRequest().authenticated()
                                      )
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
