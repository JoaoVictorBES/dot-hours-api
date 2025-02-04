package br.com.system.dothours.auth.Login;

public interface LoginRepository {

    Object findByUsername(String username);

}
