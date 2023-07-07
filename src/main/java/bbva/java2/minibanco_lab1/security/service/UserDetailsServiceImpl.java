package bbva.java2.minibanco_lab1.security.service;

import bbva.java2.minibanco_lab1.application.repository.IClienteRepository;
import bbva.java2.minibanco_lab1.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Cliente> clienteOp = clienteRepository.buscaClientePorEmail(username);

        if(clienteOp.isPresent()) {
            Cliente cliente = clienteOp.get();
            return User.builder()
                       .username(cliente.getEmail())
                       .password(cliente.getContrasenia())
                       .roles(cliente.getEmail().endsWith("@bbva.com") ?
                               "ADMIN" : "CLIENTE")
                       .build();
        } else {
            throw new UsernameNotFoundException("El usuario \"" + username + "\" no se encuentra registrado.");
        }
    }
}
