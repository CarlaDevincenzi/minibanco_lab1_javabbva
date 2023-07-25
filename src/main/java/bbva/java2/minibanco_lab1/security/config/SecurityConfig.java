package bbva.java2.minibanco_lab1.security.config;

import bbva.java2.minibanco_lab1.presentation.config.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST,"/minibanco/clientes/crear").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .antMatchers("/minibanco/clientes/auth/**").hasRole("CLIENTE")
                .antMatchers("/minibanco/clientes/**").hasRole("ADMIN")
                .antMatchers("/minibanco/cuentas/auth/**").hasRole("CLIENTE")
                .antMatchers("/minibanco/cuentas/**").hasRole("ADMIN")
                .antMatchers("/minibanco/transaccion/auth/**").hasRole("CLIENTE")
                .anyRequest()
                .authenticated()
                .and()

                //agregamos el filtro pero no al final de la cadena sino antes de otro filtro
                //en este caso va a antes del basic authentication filter
                //como estandar el primer filtro de autenticación de Spring es el UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                        .logoutUrl("/logout").permitAll();

        http.csrf().disable();

        http.formLogin().successHandler((req, res, auth)-> clearAuthenticationAttibutes(req));

        // si el usuario no está autenticado, solo enviar una respuesta de falla de autenticación

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // si el login falla, solo enviar una respuesta de falla de autenticación

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // si el logout es exitoso, solo enviar una respuesta exitosa

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttibutes(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if(sesion != null) {
            sesion.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

    //Anotación que nos va a permitir inyectar y utilizar este AuthenticationManager
    @Bean
    //Método para poder obtener nuestro AuthenticationManager y utilizarlo en nuestro AuthController
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
