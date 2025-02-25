package br.com.system.dothours.config;

import io.jsonwebtoken.SignatureAlgorithm;


/**
 * Configurações relacionadas ao JWT (JSON Web Token) utilizadas na aplicação.
 * 
 * A classe {JwtConfig} contém constantes que são utilizadas para a geração e validação de tokens JWT,
 * incluindo a chave secreta para assinatura do token, o algoritmo de assinatura e o tempo de expiração do token.
 * Essas configurações são essenciais para garantir a criação e validação de tokens de maneira segura.
 * 
 * As configurações são:
 * 
 *   <strong>SECRET_KEY</strong>: A chave secreta usada para assinar os tokens. Deve ser mantida em segurança.
 *   <strong>ALGORITMO_ASSINATURA</strong>: O algoritmo de assinatura utilizado para assinar o token (HS256, no caso).
 *   <strong>HORAS_EXPIRACAO_TOKEN</strong>: O tempo de expiração do token em horas. Este valor define quanto tempo o token será válido após sua criação.
 * 
 * 
 * @see JwtServiceGenerator
 */
public class JwtConfig {

    
	 /** 
     * Chave secreta usada para a assinatura dos tokens JWT. 
     * Deve ser mantida em segurança e nunca ser exposta publicamente.
     */
	public static final String SECRET_KEY = "  z8R1K3sD9mW4X2pL7vN0Q5jY6tV8bF2cP9aG1dM3xT7oZ4wJ6kC5Y7nR0X8L2vB";
	

	 /** 
     * O algoritmo de assinatura utilizado para assinar os tokens JWT.
     * Neste caso, está sendo utilizado o algoritmo HS256.
     */
	public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;


	 /** 
     * O tempo de expiração do token, em horas.
     * Este valor define quanto tempo o token será válido após sua criação.
     */
	public static final int HORAS_EXPIRACAO_TOKEN = 5;

}
