package com.k00241934.typingtutor;



import java.awt.event.KeyEvent;

/**
 *
 * @author ross1
 */
public class UserAccount {

    private String username;
    private String password;
    private int correctKeyScoreTotal;
    private int incorrectKeyScoreTotal;
    private final int[] correctKeyScores = new int[KeyEvent.KEY_LAST + 1];
    private final int[] incorrectKeyScores = new int[KeyEvent.KEY_LAST + 1];

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

    public void resetScores() {
        correctKeyScoreTotal = 0;
        incorrectKeyScoreTotal = 0;
        
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
	

	public String getIncorrectCharacters() {
		String output="";
		for (int index = 0; index < incorrectKeyScores.length; index++) {
			if(incorrectKeyScores[index]>0)
			{
				String incorrectCharacter = KeyEvent.getKeyText(index);
				output+=incorrectCharacter+" ";
			}
			
		}
        return output;
    }
	
	
    /**
     * @return the correctKeyScoreTotal
     */
    public int getCorrectKeyScoreTotal() {
        return correctKeyScoreTotal;
    }

    /**
     * @return the incorrectKeyScoreTotal
     */
    public int getIncorrectKeyScoreTotal() {
        return incorrectKeyScoreTotal;
    }

    public void incrementCorrectKeyScoreTotal() {
        correctKeyScoreTotal++;
    }

    public void decrementCorrectKeyScoreTotal() {
        if (correctKeyScoreTotal > 0) {
            correctKeyScoreTotal--;
        }
    }
    
        public void decrementIncorrectKeyScoreTotal() {
        if (incorrectKeyScoreTotal > 0) {
              incorrectKeyScoreTotal--;
        }
    }

    /**
     * @return the incorrectKeyScoreTotal
     */
    public void incrementIncorrectKeyScoreTotal() {
        incorrectKeyScoreTotal++;
    }
}
