package com.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityApps {

	public Boolean checkToken(HttpServletRequest request) {
		Boolean isSession = true;
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("token") == null) {
			isSession = false;
		} else {
			String token = session.getAttribute("token").toString();
			
			String salt = "Xsis123Winners@@@XXxx";
			String clientToken = request.getHeader("Authorization");
			clientToken = salt.concat(clientToken);
			
			if(!clientToken.equals(token)) {
				isSession = false;
			}
		}
		
		return isSession;
	}
}