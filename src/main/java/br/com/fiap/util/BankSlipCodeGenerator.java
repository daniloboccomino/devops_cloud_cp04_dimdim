package br.com.fiap.util;

import java.util.Random;

public class BankSlipCodeGenerator {
	
	public static final String generateCode() {
		Random random = new Random();
		
		String code = "";
		
		for (int i = 0; i <= 48; i++) {
			code = code + String.valueOf(random.nextInt(9));
		}
		
		return code;
	}
	
}
