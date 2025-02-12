package br.com.system.dothours.auth.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.system.dothours.config.JwtServiceGenerator;
import br.com.system.dothours.model.Usuario;

@Service
public class LoginService {

    @Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;


	public String logar(Login login) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getNome(),
						login.getSenha()
						)
				);
		Usuario user = repository.findByUsername(login.getNome()).get();
		String jwtToken = jwtService.generateToken(user);
		
		return jwtToken;
	}

}
