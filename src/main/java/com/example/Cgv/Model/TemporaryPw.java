package com.example.Cgv.Model;

import java.util.Random;

public class TemporaryPw {
	Random random = new Random();
	
	public String randomNumber() {
		String temporaryPw = "";
		for(int i = 0 ; i<6 ; i++) {
			int randomNumber = random.nextInt(9);
			temporaryPw += randomNumber;
		}
		return temporaryPw;
	}
}
