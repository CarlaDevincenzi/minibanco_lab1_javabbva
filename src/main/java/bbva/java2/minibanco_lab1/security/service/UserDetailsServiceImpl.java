package bbva.java2.minibanco_lab1.security.service;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import bbva.java2.minibanco_lab1.presentation.response.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Cliente cliente = clienteRepository.buscaClientePorEmail(username);

        if(cliente != null) {
            if(cliente.getEmail().endsWith("@bbva.com")) {
                return User.builder()
                        .username(cliente.getEmail())
                        .password(cliente.getContrasenia())
                        .roles("ADMIN")
                        .build();
            } else {
                return User.builder()
                        .username(cliente.getEmail())
                        .password(cliente.getContrasenia())
                        .roles("CLIENTE")
                        .build();
            }
           /* Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
            SimpleGrantedAuthority role;

            if(cliente.getEmail().endsWith("@bbva.com")) {
                role = new SimpleGrantedAuthority("ADMIN");
            } else {
                role = new SimpleGrantedAuthority("CLIENTE");
            }
            authorities.add(role);
            return new User(cliente.getEmail(), cliente.getContrasenia(), authorities); */
        } else {
            throw new UsernameNotFoundException("El usuario \"" + username + "\" no se encuentra registrado.");
        }

    }
}
