package com.renta_car.demorentacar.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Creacion de usuarios
    @Bean
    public UserDetailsService UserDetailService(PasswordEncoder encoder){

        //En memoria de arranque vamos a probar la configuracion de los usuarios
        UserDetails admin = User.withUsername("Administrador")
            .password(encoder.encode("1234")) // Codificamos la contraseña
            .roles("ADMIN", "USER")
            .build();

        UserDetails user = User.withUsername("usuario")
            .password(encoder.encode("1234"))
            .roles("USER")
            .build();
            
            
        return new InMemoryUserDetailsManager(admin,user);
    }

    //Configuracion para el control de acceso por HTTPSecurity

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
        http

            .csrf(csrf -> csrf.disable()) // Quitamos el CSRF para simplificar las pruebas
            .authorizeRequests(auth -> auth
                .requestMatchers("/saludar/**").permitAll()
                .requestMatchers("/cars/**").authenticated()
                .requestMatchers("/clients/**").authenticated()
                .requestMatchers("/reservations/**").authenticated()
                .anyRequest().denyAll() // cualquier otra ruta no esta permitida
            )
            .formLogin(withDefaults()); // <-- Use withDefaults() for form-based login

            return http.build();

    }

        // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
