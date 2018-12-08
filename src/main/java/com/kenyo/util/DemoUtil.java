package com.kenyo.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("com.kenyo")
public class DemoUtil {
	
	private String email;
	
	private String password;
	
	private String hora_limite;
	
	private String tareaprogramada_hora;
	
	private boolean smtp_auth;
	private boolean smtp_enable;
	private boolean smtp_require;
	private String host;
	private int port;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHora_limite() {
		return hora_limite;
	}

	public void setHora_limite(String hora_limite) {
		this.hora_limite = hora_limite;
	}

	public String getTareaprogramada_hora() {
		return tareaprogramada_hora;
	}

	public void setTareaprogramada_hora(String tareaprogramada_hora) {
		this.tareaprogramada_hora = tareaprogramada_hora;
	}

	public boolean isSmtp_auth() {
		return smtp_auth;
	}

	public void setSmtp_auth(boolean smtp_auth) {
		this.smtp_auth = smtp_auth;
	}

	public boolean isSmtp_enable() {
		return smtp_enable;
	}

	public void setSmtp_enable(boolean smtp_enable) {
		this.smtp_enable = smtp_enable;
	}

	public boolean isSmtp_require() {
		return smtp_require;
	}

	public void setSmtp_require(boolean smtp_require) {
		this.smtp_require = smtp_require;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
	

}
