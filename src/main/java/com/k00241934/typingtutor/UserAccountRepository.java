/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.k00241934.typingtutor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;

/**
 *
 * @author ross1
 */
public class UserAccountRepository {

	public static ArrayList<UserAccount> getUsersAcconts() {
		return usersAcconts;
	}

	/**
	 * Gets a UserAccount that matches with a username and password
	 *
	 * @param username
	 * @param password
	 * @return if correct username and password, return the matching user
	 * account, else returns null
	 */
	public static UserAccount GetUserAccountByCredentials(String username, String password) {
		for (UserAccount usersAccont : usersAcconts) {
			if (username.equals(usersAccont.getUsername()) && password.equals(usersAccont.getPassword())) {
				return usersAccont;
			}
		}
		return null;
	}

	/**
	 * Checks a user account exists for a username
	 *
	 * @param username
	 * @return true if username is taken, return false if not
	 */
	public static boolean CheckUserAccountWithUserNameExists(String username) {
		var userAccount = GetUserAccountByUserName(username);
		if (userAccount == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets a UserAccount that matches with a username
	 *
	 * @param username
	 * @return if correct username, return the matching user account, else
	 * returns null
	 */
	public static UserAccount GetUserAccountByUserName(String username) {
		for (UserAccount usersAccont : usersAcconts) {
			if (username.equals(usersAccont.getUsername())) {
				return usersAccont;
			}
		}
		return null;
	}

	public static void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filePath);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {

			Object object = ois.readObject();
			usersAcconts = (ArrayList<UserAccount>) object;
		} catch (OptionalDataException e) {
			if (!e.eof) {
				throw e;
			}
		} catch (Exception e) {
			usersAcconts = new ArrayList<>();
			System.out.println(e);
		} finally {
			ois.close();
		}
	}

//to do save function
	public static void save() throws FileNotFoundException, IOException {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {

			fout = new FileOutputStream(filePath);

			oos = new ObjectOutputStream(fout);
			oos.writeObject(usersAcconts);
		} catch (Exception e) {
		} finally {
			fout.close();
			oos.close();
		}
	}
	private static String filePath = "assest.files/UserScores.bin";
	public static ArrayList<UserAccount> usersAcconts = new ArrayList<>();
}
