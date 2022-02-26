package com.snhu.sslserver;

import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class SslServerController {
	public String createHash() throws NoSuchAlgorithmException {
		String name = "Kareem Elkwae" ;
		
		MessageDigest md = MessageDigest.getInstance("SHA-256"); //initializing message digest for SHA-256
		byte[] sha = md.digest(name.getBytes(StandardCharsets.UTF_8)); //Creating byte array to digest the name/input into bytes
		
		BigInteger hex = new BigInteger(1, sha); 
		StringBuilder checksum = new StringBuilder(hex.toString(16)); //creating checksum from hex
		
		while (checksum.length() < 32) {  //filling out checksum 
			checksum.insert(0,  '0');
		}
		
		String hash = checksum.toString(); //converting checksum to string
		
		return "Data: " + name + "    SHA-256 Hash: " + hash;
	}
	
	@RequestMapping("/hash") //creating page mapping so we can see if it works
	public String printChecksum() throws NoSuchAlgorithmException{
		return createHash();
	}
}


