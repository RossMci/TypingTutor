package model;

import java.awt.event.KeyEvent;

/**
 *
 * @author ross1
 */
public class UserAccount {
    private String username;
    private String password;
    private final int[] correctKeyScores= new int[KeyEvent.KEY_LAST+1];
    private final int[] incorrectKeyScores= new int[KeyEvent.KEY_LAST+1];

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the correctKeyScores
     */
    public int[] getCorrectKeyScores() {
        return correctKeyScores;
    }

    /**
     * @return the incorrectKeyScores
     */
    public int[] getIncorrectKeyScores() {
        return incorrectKeyScores;
    }
}
