package tech.inkware.springenvers.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBcrypt {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.err.println(encoder.encode("admin123"));

	}

}
