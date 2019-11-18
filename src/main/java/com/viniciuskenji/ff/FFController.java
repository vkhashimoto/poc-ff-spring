package com.viniciuskenji.ff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.finn.unleash.Unleash;

@RestController
@RequestMapping("/ff")
public class FFController {

	private final String LOGIN_TYPE_V2 = "login_v2";
	private final String LOGIN_TYPE_V3 = "login_v3";

	private Unleash unleash;

	@Autowired
	public FFController(Unleash unleash) {
		this.unleash = unleash;
	}

	@GetMapping
	public ResponseEntity<String> findFF(
			@RequestParam(name = "login_type", required = false, defaultValue = "login_v3") String loginType) {
		System.out.println("THIS IS THE VALUE OF 'flag'");
		System.out.println(loginType);

		// check if login_type is equals to LOGIN_TYPE_V2 and it is active
		if (loginType.equalsIgnoreCase(LOGIN_TYPE_V2) && unleash.isEnabled(LOGIN_TYPE_V2)) {
			System.out.println("Feature Flag '" + loginType + "' exists and is active");
		} else { // login_type is not equals to LOGIN_TYPE_V2 or the Feature Flag is inactive,
					// and set loginType to LOGIN_TYPE_V3
			System.out.println("Using '" + LOGIN_TYPE_V3 + "' as value of 'login_type'");
			loginType = LOGIN_TYPE_V3;
		}

		return ResponseEntity.status(200).body("You are logging in using the " + loginType + " system");
	}
}
