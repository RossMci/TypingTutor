/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.k00241934.typingtutor;

import java.util.ArrayList;

/**
 *
 * @author ross1
 */
public class UserAccountRepository {

	public static ArrayList<UserAccount> getUsersAcconts() {
		return usersAcconts;
	}

	public static UserAccount GetUserAccountByCredentials(String username, String password) {
		for (UserAccount usersAccont : usersAcconts) {
			if (username.equals(usersAccont.getUsername()) && password.equals(usersAccont.getPassword())) {
				return usersAccont;
			}
		}
		return null;
	}
	//to do save function
	public static void save(){
	}
	public static ArrayList<UserAccount> usersAcconts = new ArrayList<>();
}
