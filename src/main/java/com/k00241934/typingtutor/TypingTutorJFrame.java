package com.k00241934.typingtutor;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author ross1
 */
public class TypingTutorJFrame extends javax.swing.JFrame implements KeyListener {

	//
	//	Private Fields
	//
	Color buttonOriginalColor = null;
	Color buttonPressedColor = Color.RED;
	UserAccount userAccount;
	JButton keyButtons[];
	public int numkeys = 0, currentindex = 0;
	String panagramsList[];
	String panagramOnDisplay;
	Stack<Turn> turnStack = new Stack<>();
	AppLauncherJFRame appLauncher;

	/**
	 * Creates new form TypingTutor
	 */
	// gets the jframes to be used in the program
	TypingTutorJFrame(AppLauncherJFRame appLauncher, UserAccount userAccount) {
		this.appLauncher = appLauncher;
		this.userAccount = userAccount;
		initComponents();
		intializecode();
		// sets the text area too the saved text 
		displayTextArea.setText(userAccount.getEnteredText());
// sets the current index too the correct one
		currentindex = userAccount.getEnteredText().length();
// adds the key event listener
		displayTextArea.addKeyListener(this);
		//updates the score 
		updateScoreBoard();

	}
// this method is too set all the values 
	private void intializecode() {
		keyButtons = new JButton[KeyEvent.KEY_LAST + 1];

		keyButtons[KeyEvent.VK_0] = ZeroButton;
		keyButtons[KeyEvent.VK_1] = oneButton;
		keyButtons[KeyEvent.VK_2] = TwoButton;
		keyButtons[KeyEvent.VK_3] = threeButton;
		keyButtons[KeyEvent.VK_4] = fourButton;
		keyButtons[KeyEvent.VK_5] = fiveButton;
		keyButtons[KeyEvent.VK_6] = sixButton;
		keyButtons[KeyEvent.VK_7] = sevenButton;
		keyButtons[KeyEvent.VK_8] = eightButton;
		keyButtons[KeyEvent.VK_9] = nineButton;
		keyButtons[KeyEvent.VK_DOWN] = downArrowButton;
		keyButtons[KeyEvent.VK_UP] = upArrowButton;
		keyButtons[KeyEvent.VK_LEFT] = leftArrowButton;
		keyButtons[KeyEvent.VK_RIGHT] = rightArrowButton;
		keyButtons[KeyEvent.VK_BACK_SPACE] = backspaceButton;
		keyButtons[KeyEvent.VK_CAPS_LOCK] = capsButton;
		keyButtons[KeyEvent.VK_TAB] = TabButton;
		keyButtons[KeyEvent.VK_SHIFT] = ShiftButton;
		keyButtons[KeyEvent.VK_SPACE] = spaceButton;
		keyButtons[KeyEvent.VK_PERIOD] = dotButton;
		keyButtons[KeyEvent.VK_ENTER] = EnterButton;
		keyButtons[KeyEvent.VK_CLOSE_BRACKET] = OpenBracketButton;
		keyButtons[KeyEvent.VK_OPEN_BRACKET] = closedBracketButton;
//        keyButtons[KeyEvent.VK_SLASH] = SlashButton;
		keyButtons[KeyEvent.VK_BACK_SLASH] = backSlashButton;
//        keyButtons[KeyEvent.VK_EQUALS] = equalsButton;
		keyButtons[KeyEvent.VK_COMMA] = comaButton;
		keyButtons[KeyEvent.VK_MINUS] = minusButton;
		keyButtons[KeyEvent.VK_SEMICOLON] = semicolonButton;
//        keyButtons[KeyEvent.VK_QUOTE] = quotationButton;
		keyButtons[KeyEvent.VK_Q] = QButton;
		keyButtons[KeyEvent.VK_W] = WButton;
		keyButtons[KeyEvent.VK_E] = EButton;
		keyButtons[KeyEvent.VK_R] = RButton;
		keyButtons[KeyEvent.VK_T] = TButton;
		keyButtons[KeyEvent.VK_Y] = YButton;
		keyButtons[KeyEvent.VK_U] = UButton;
		keyButtons[KeyEvent.VK_I] = IButton;
		keyButtons[KeyEvent.VK_O] = OButton;
		keyButtons[KeyEvent.VK_P] = PButton;
		keyButtons[KeyEvent.VK_A] = AButton;
		keyButtons[KeyEvent.VK_S] = SButton;
		keyButtons[KeyEvent.VK_D] = DButton;
		keyButtons[KeyEvent.VK_F] = FButton;
		keyButtons[KeyEvent.VK_G] = GButton;
		keyButtons[KeyEvent.VK_H] = HButton;
		keyButtons[KeyEvent.VK_J] = JButton;
		keyButtons[KeyEvent.VK_K] = KButton;
		keyButtons[KeyEvent.VK_L] = LButton;
		keyButtons[KeyEvent.VK_Z] = ZButton;
		keyButtons[KeyEvent.VK_X] = XButton;
		keyButtons[KeyEvent.VK_C] = CButton;
		keyButtons[KeyEvent.VK_V] = VButton;
		keyButtons[KeyEvent.VK_B] = BButton;
		keyButtons[KeyEvent.VK_N] = NButton;
		keyButtons[KeyEvent.VK_M] = MButton;

		panagramsList = new String[]{
			"The quick brown fox jumped over the lazy dog",
			"A paragraph should consist of six to seven sentences. No, it should be no longer than three sentences long. Actually, it should include a topic sentence, several supporting sentences, and possibly a concluding sentence."
		};

		panagramLabel.setText(panagramsList[0]);
		panagramOnDisplay = panagramsList[0];
//        backspaceButton.setEnabled(false);

	}
	// records the key the unicode character which represted by a key pressed
	@Override
	public void keyTyped(KeyEvent evt) {

		if (currentindex <= panagramsList[0].length()) {

			TypedPanagrams(evt);
		} else {
		}
	}
// method sets the text entered too the textarea
	private void TypedPanagrams(KeyEvent evt) {

		char character = evt.getKeyChar();
		int keyCode = KeyEvent.getExtendedKeyCodeForChar(character);
		//int keyCode = evt.getKeyCode();
// calls the word check method 
		wordsCheck(keyCode, character, panagramsList[0]);

		if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			// calls the correct method 
			correctDisplay();
		} else {
			displayTextArea.append(evt.getKeyChar() + "");
		}

	}
// is used too get rid of text in the textarea
	private void correctDisplay() {
		String text = displayTextArea.getText();
		int lenght = text.length();
		if (lenght != 0) {
			displayTextArea.setText(text.substring(0, lenght - 1));
		}
	}

	// method to check if the keys typed match the panagram
	public void wordsCheck(int userLetterKeycode, char userLetter, String panagram) {
		//ends program if the keystyped is greater then pangram length
		if (currentindex < panagram.length()) {

		     // checks if back space wasnt typed
			if (userLetterKeycode != KeyEvent.VK_BACK_SPACE) {
				final char expectedLetterInPanagram = panagramOnDisplay.charAt(currentindex);
				final int expectedLetterInPanagramKeyCode = KeyEvent.getExtendedKeyCodeForChar(expectedLetterInPanagram);
                  // Turn stack 
				var newTurn = new Turn();
				// gets the excepted keycode for panagram
				newTurn.keycode = expectedLetterInPanagramKeyCode;
                  //checks if user letter = the excepted letter
				if (userLetter == expectedLetterInPanagram) {
					// increments the correct key score total
					userAccount.incrementCorrectKeyScoreTotal();
					// then puts the correct number  excepted count up
					userAccount.getCorrectKeyScores()[expectedLetterInPanagramKeyCode]++;
					
					userAccount.setEnteredText(displayTextArea.getText());
				   
					newTurn.guessCorrect = true;
				} else {

					userAccount.incrementIncorrectKeyScoreTotal();
					userAccount.getIncorrectKeyScores()[expectedLetterInPanagramKeyCode]++;
					userAccount.setEnteredText(displayTextArea.getText());
					newTurn.guessCorrect = false;
				}

				// increments the current index
				currentindex++;
                // trackes keys pressed
				numkeys++;
				// moves in too the next turn
				turnStack.push(newTurn);
             // if backspace is press it undos the last turn on the stack and updates the score
			} else if (userLetterKeycode == KeyEvent.VK_BACK_SPACE) {
				if (currentindex > 0) {
					undoLastTurn();
				}
				numkeys++;
			}
			updateScoreBoard();
		} else {
			// shows the finshed mthod and rests the lession and returns too applauncher
			JOptionPane.showMessageDialog(this, "Finished click ok to return to the app lanuncher");
			this.resetKeyColors();
			this.finishLesson();
		}
	}

	// used too undo last turn
	private void undoLastTurn() {
		Turn previousTurn = turnStack.pop();

		if (previousTurn.guessCorrect) {
			userAccount.decrementCorrectKeyScoreTotal();
			userAccount.getCorrectKeyScores()[previousTurn.keycode]--;
		} else {
			userAccount.decrementIncorrectKeyScoreTotal();
			userAccount.getIncorrectKeyScores()[previousTurn.keycode]--;
		}
		currentindex--;
		userAccount.setEnteredText(displayTextArea.getText());
	}

	private void updateScoreBoard() {
		numofkeysincorrectLabel.setText(String.valueOf(userAccount.getIncorrectKeyScoreTotal()));
		correctLabel.setText(String.valueOf(userAccount.getCorrectKeyScoreTotal()));

		int accuracy = (int) (userAccount.getCorrectKeyScoreTotal() / (double) panagramOnDisplay.length() * 100);
		accuracynumLabel.setText(accuracy + "%");

		difficultnumLabel.setText(userAccount.getIncorrectCharacters());
	}

// records when key is pressed down
	@Override
	public void keyPressed(KeyEvent event) {
		//colorKeyButton(event, buttonPressedColor);
	}

	private void colorKeyButton(KeyEvent event, Color color) {
//        char character = event.getKeyChar();
		int keyIndex = event.getKeyCode();//KeyEvent.getExtendedKeyCodeForChar(character);
		JButton selectedButton = keyButtons[keyIndex];
		selectedButton.setBackground(color);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//colorKeyButton(e, null);
	}

	//https://stackoverflow.com/questions/13668131/java-swing-virtual-keyboard-on-jframe/13668842
	//
	//  Event Handlers
	//
	private void finishLesson() {
		userAccount.resetScores();
		userAccount.setEnteredText("");

		this.appLauncher.setVisible(true);
		this.dispose();
	}

	private void resetLesson() {
		userAccount.resetScores();
		turnStack.clear();
		userAccount.setEnteredText("");
		displayTextArea.setText("");
		currentindex = 0;
		updateScoreBoard();
	}

	private void displayTextAreaKeyPressed_(KeyEvent evt) {
		colorKeyButton(evt, buttonPressedColor);
	}

	private void displayTextAreaKeyReleased_(KeyEvent evt) {
		colorKeyButton(evt, buttonOriginalColor);
	}

	private void resetKeyColors() {
		for (JButton button : this.keyButtons) {
			if (button != null) {

				button.setBackground(buttonOriginalColor);
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        keyBoardPanel = new javax.swing.JPanel();
        TildeButton = new javax.swing.JButton();
        oneButton = new javax.swing.JButton();
        TwoButton = new javax.swing.JButton();
        threeButton = new javax.swing.JButton();
        fourButton = new javax.swing.JButton();
        fiveButton = new javax.swing.JButton();
        sixButton = new javax.swing.JButton();
        sevenButton = new javax.swing.JButton();
        eightButton = new javax.swing.JButton();
        nineButton = new javax.swing.JButton();
        ZeroButton = new javax.swing.JButton();
        plusButton = new javax.swing.JButton();
        minusButton = new javax.swing.JButton();
        backspaceButton = new javax.swing.JButton();
        QButton = new javax.swing.JButton();
        WButton = new javax.swing.JButton();
        EButton = new javax.swing.JButton();
        RButton = new javax.swing.JButton();
        TButton = new javax.swing.JButton();
        YButton = new javax.swing.JButton();
        OButton = new javax.swing.JButton();
        IButton = new javax.swing.JButton();
        TabButton = new javax.swing.JButton();
        PButton = new javax.swing.JButton();
        OpenBracketButton = new javax.swing.JButton();
        closedBracketButton = new javax.swing.JButton();
        backSlashButton = new javax.swing.JButton();
        AButton = new javax.swing.JButton();
        SButton = new javax.swing.JButton();
        DButton = new javax.swing.JButton();
        GButton = new javax.swing.JButton();
        FButton = new javax.swing.JButton();
        KButton = new javax.swing.JButton();
        HButton = new javax.swing.JButton();
        JButton = new javax.swing.JButton();
        LButton = new javax.swing.JButton();
        semicolonButton = new javax.swing.JButton();
        hashButton = new javax.swing.JButton();
        EnterButton = new javax.swing.JButton();
        ZButton = new javax.swing.JButton();
        XButton = new javax.swing.JButton();
        upArrowButton = new javax.swing.JButton();
        CButton = new javax.swing.JButton();
        VButton = new javax.swing.JButton();
        BButton = new javax.swing.JButton();
        NButton = new javax.swing.JButton();
        MButton = new javax.swing.JButton();
        comaButton = new javax.swing.JButton();
        dotButton = new javax.swing.JButton();
        QuestionMarkButton = new javax.swing.JButton();
        leftArrowButton = new javax.swing.JButton();
        spaceButton = new javax.swing.JButton();
        rightArrowButton = new javax.swing.JButton();
        downArrowButton = new javax.swing.JButton();
        ShiftButton = new javax.swing.JButton();
        capsButton = new javax.swing.JButton();
        UButton = new javax.swing.JButton();
        textPanel = new javax.swing.JPanel();
        noteLabel = new javax.swing.JLabel();
        howTouseLabel = new javax.swing.JLabel();
        currentpanagramLabel = new javax.swing.JLabel();
        panagramLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Instruction1Label = new javax.swing.JLabel();
        Instruction2Label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        displayScrollPane = new javax.swing.JScrollPane();
        displayTextArea = new javax.swing.JTextArea();
        TrackingPanel = new javax.swing.JPanel();
        keyscorrecrLabel = new javax.swing.JLabel();
        incorrectlabel = new javax.swing.JLabel();
        accuracyLabel = new javax.swing.JLabel();
        accuracynumLabel = new javax.swing.JLabel();
        difficultkeysLabel = new javax.swing.JLabel();
        difficultnumLabel = new javax.swing.JLabel();
        numofkeysincorrectLabel = new javax.swing.JLabel();
        correctLabel = new javax.swing.JLabel();
        resetLessonButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Typing Tutor");
        setFocusCycleRoot(false);
        setSize(new java.awt.Dimension(300, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        keyBoardPanel.setFocusable(false);

        TildeButton.setText("~");
        TildeButton.setFocusable(false);
        TildeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TildeButtonActionPerformed(evt);
            }
        });

        oneButton.setText("1");
        oneButton.setFocusable(false);
        oneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneButtonActionPerformed(evt);
            }
        });

        TwoButton.setText("2");
        TwoButton.setFocusable(false);
        TwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TwoButtonActionPerformed(evt);
            }
        });

        threeButton.setText("3");
        threeButton.setFocusable(false);

        fourButton.setText("4");
        fourButton.setFocusable(false);

        fiveButton.setText("5");
        fiveButton.setFocusable(false);

        sixButton.setText("6");
        sixButton.setFocusable(false);

        sevenButton.setText("7");
        sevenButton.setFocusable(false);

        eightButton.setText("8");
        eightButton.setFocusable(false);

        nineButton.setText("9");
        nineButton.setFocusable(false);

        ZeroButton.setText("0");
        ZeroButton.setFocusable(false);

        plusButton.setText("+");
        plusButton.setFocusable(false);

        minusButton.setText("-");
        minusButton.setFocusable(false);

        backspaceButton.setText("Backspace");
        backspaceButton.setFocusable(false);

        QButton.setText("Q");
        QButton.setFocusable(false);

        WButton.setText("W");
        WButton.setFocusable(false);

        EButton.setText("E");
        EButton.setFocusable(false);

        RButton.setText("R");
        RButton.setFocusable(false);

        TButton.setText("T");
        TButton.setFocusable(false);

        YButton.setText("Y");
        YButton.setFocusable(false);

        OButton.setText("O");
        OButton.setFocusable(false);

        IButton.setText("I");
        IButton.setFocusable(false);

        TabButton.setText("Tabs");
        TabButton.setFocusable(false);

        PButton.setText("P");
        PButton.setFocusable(false);

        OpenBracketButton.setText("[");
        OpenBracketButton.setFocusable(false);

        closedBracketButton.setText("]");
        closedBracketButton.setFocusable(false);

        backSlashButton.setText("\\");
            backSlashButton.setFocusable(false);

            AButton.setText("A");
            AButton.setFocusable(false);

            SButton.setText("S");
            SButton.setFocusable(false);

            DButton.setText("D");
            DButton.setFocusable(false);

            GButton.setText("G");
            GButton.setFocusable(false);

            FButton.setText("F");
            FButton.setFocusable(false);

            KButton.setText("K");
            KButton.setFocusable(false);

            HButton.setText("H");
            HButton.setFocusable(false);

            JButton.setText("J");
            JButton.setFocusable(false);

            LButton.setText("L");
            LButton.setFocusable(false);

            semicolonButton.setText(";");
            semicolonButton.setFocusable(false);

            hashButton.setText("#");
            hashButton.setFocusable(false);

            EnterButton.setText("Enter");
            EnterButton.setFocusable(false);

            ZButton.setText("Z");
            ZButton.setFocusable(false);

            XButton.setText("X");
            XButton.setFocusable(false);

            upArrowButton.setText("^");
            upArrowButton.setFocusable(false);

            CButton.setText("C");
            CButton.setFocusable(false);

            VButton.setText("V");
            VButton.setFocusable(false);

            BButton.setText("B");
            BButton.setFocusable(false);

            NButton.setText("N");
            NButton.setFocusable(false);

            MButton.setText("M");
            MButton.setFocusable(false);

            comaButton.setText(",");
            comaButton.setFocusable(false);

            dotButton.setText(".");
            dotButton.setFocusable(false);

            QuestionMarkButton.setText("?");
            QuestionMarkButton.setFocusable(false);

            leftArrowButton.setText("<");
            leftArrowButton.setFocusable(false);

            spaceButton.setFocusable(false);

            rightArrowButton.setText(">");
            rightArrowButton.setFocusable(false);

            downArrowButton.setText("↓");
            downArrowButton.setFocusable(false);

            ShiftButton.setText("Shift");
            ShiftButton.setFocusable(false);

            capsButton.setText("Caps");
            capsButton.setFocusable(false);

            UButton.setText("U");
            UButton.setFocusable(false);

            javax.swing.GroupLayout keyBoardPanelLayout = new javax.swing.GroupLayout(keyBoardPanel);
            keyBoardPanel.setLayout(keyBoardPanelLayout);
            keyBoardPanelLayout.setHorizontalGroup(
                keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(keyBoardPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(keyBoardPanelLayout.createSequentialGroup()
                            .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, keyBoardPanelLayout.createSequentialGroup()
                                    .addGap(228, 228, 228)
                                    .addComponent(spaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(69, 69, 69)
                                    .addComponent(leftArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(downArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rightArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, keyBoardPanelLayout.createSequentialGroup()
                                        .addComponent(capsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(AButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(GButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(HButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(KButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(semicolonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(hashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(EnterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(backspaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, keyBoardPanelLayout.createSequentialGroup()
                                            .addComponent(TabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(QButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(WButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(EButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(RButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(YButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(UButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(IButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(OButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(PButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(OpenBracketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(closedBracketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(backSlashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(0, 10, Short.MAX_VALUE))
                        .addGroup(keyBoardPanelLayout.createSequentialGroup()
                            .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(keyBoardPanelLayout.createSequentialGroup()
                                    .addComponent(TildeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(oneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TwoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(threeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fiveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sixButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sevenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(nineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(ZeroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(7, 7, 7)
                                    .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(keyBoardPanelLayout.createSequentialGroup()
                                    .addComponent(ShiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ZButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(XButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(CButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(VButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(NButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(MButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(QuestionMarkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(87, 87, 87)
                                    .addComponent(upArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
            keyBoardPanelLayout.setVerticalGroup(
                keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(keyBoardPanelLayout.createSequentialGroup()
                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(oneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TildeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(threeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TwoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fiveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sixButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sevenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ZeroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backspaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(6, 6, 6)
                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(QButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(WButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(YButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(UButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(IButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(OButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(OpenBracketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(closedBracketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(backSlashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(capsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(GButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(HButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(KButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(semicolonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EnterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ShiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ZButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(XButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(VButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(MButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(QuestionMarkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(upArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leftArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(downArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rightArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(spaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(21, Short.MAX_VALUE))
            );

            textPanel.setBackground(new java.awt.Color(255, 255, 255));
            textPanel.setFocusable(false);

            noteLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            noteLabel.setText("Note: click the buttons will not write any text and any action and you cant go back  ");
            noteLabel.setFocusable(false);

            howTouseLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            howTouseLabel.setText("Type some Text using the keyboard . The key board will highlight the keys you hit and the text will be displayed");
            howTouseLabel.setFocusable(false);

            currentpanagramLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            currentpanagramLabel.setText("current panagram:");
            currentpanagramLabel.setFocusable(false);

            panagramLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            panagramLabel.setFocusable(false);

            jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("Instructions ");

            Instruction1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Instruction1Label.setText("close the program to return to menu");

            Instruction2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Instruction2Label.setText("Press rest too retake lesson");

            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText("When exitinng press the save button");

            javax.swing.GroupLayout textPanelLayout = new javax.swing.GroupLayout(textPanel);
            textPanel.setLayout(textPanelLayout);
            textPanelLayout.setHorizontalGroup(
                textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(textPanelLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(textPanelLayout.createSequentialGroup()
                            .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(howTouseLabel)
                                .addComponent(noteLabel))
                            .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(textPanelLayout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(textPanelLayout.createSequentialGroup()
                                    .addGap(122, 122, 122)
                                    .addComponent(Instruction1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textPanelLayout.createSequentialGroup()
                            .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(textPanelLayout.createSequentialGroup()
                                    .addComponent(currentpanagramLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Instruction2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(textPanelLayout.createSequentialGroup()
                                    .addComponent(panagramLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(129, 129, 129)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(40, 40, 40)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            textPanelLayout.setVerticalGroup(
                textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(textPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(howTouseLabel)
                        .addComponent(jLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Instruction1Label))
                    .addGap(14, 14, 14)
                    .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(textPanelLayout.createSequentialGroup()
                            .addComponent(currentpanagramLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panagramLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(textPanelLayout.createSequentialGroup()
                            .addComponent(Instruction2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            displayScrollPane.setFocusable(false);

            displayTextArea.setEditable(false);
            displayTextArea.setColumns(20);
            displayTextArea.setRows(5);
            displayTextArea.setFocusCycleRoot(true);
            displayTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    displayTextAreaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    displayTextAreaKeyReleased(evt);
                }
            });
            displayScrollPane.setViewportView(displayTextArea);

            TrackingPanel.setBackground(new java.awt.Color(255, 255, 204));
            TrackingPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            TrackingPanel.setFocusable(false);

            keyscorrecrLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            keyscorrecrLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            keyscorrecrLabel.setText("     Correct keys Typed");
            keyscorrecrLabel.setFocusable(false);

            incorrectlabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            incorrectlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            incorrectlabel.setText(" Typed key incorrect");
            incorrectlabel.setFocusable(false);

            accuracyLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            accuracyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            accuracyLabel.setText("Accuracy");
            accuracyLabel.setFocusable(false);

            accuracynumLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            accuracynumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            accuracynumLabel.setText("100%");
            accuracynumLabel.setFocusable(false);

            difficultkeysLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            difficultkeysLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            difficultkeysLabel.setText("Difficult  Keys");
            difficultkeysLabel.setFocusable(false);

            difficultnumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            difficultnumLabel.setFocusable(false);

            numofkeysincorrectLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            numofkeysincorrectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            numofkeysincorrectLabel.setFocusable(false);

            correctLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            correctLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            correctLabel.setFocusable(false);

            resetLessonButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            resetLessonButton.setText("Reset");
            resetLessonButton.setFocusable(false);
            resetLessonButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    resetLessonButtonActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout TrackingPanelLayout = new javax.swing.GroupLayout(TrackingPanel);
            TrackingPanel.setLayout(TrackingPanelLayout);
            TrackingPanelLayout.setHorizontalGroup(
                TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TrackingPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(TrackingPanelLayout.createSequentialGroup()
                            .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(difficultkeysLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(difficultnumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(TrackingPanelLayout.createSequentialGroup()
                                    .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(correctLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(keyscorrecrLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                    .addGap(0, 2, Short.MAX_VALUE))
                                .addComponent(resetLessonButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                        .addGroup(TrackingPanelLayout.createSequentialGroup()
                            .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(numofkeysincorrectLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(incorrectlabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(TrackingPanelLayout.createSequentialGroup()
                            .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(accuracyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(accuracynumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 0, Short.MAX_VALUE))))
            );

            TrackingPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {accuracyLabel, accuracynumLabel, correctLabel, incorrectlabel, keyscorrecrLabel, numofkeysincorrectLabel});

            TrackingPanelLayout.setVerticalGroup(
                TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TrackingPanelLayout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(keyscorrecrLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(correctLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(incorrectlabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(numofkeysincorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(114, 114, 114)
                    .addComponent(accuracyLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(accuracynumLabel)
                    .addGap(106, 106, 106)
                    .addComponent(difficultkeysLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(difficultnumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(133, 133, 133)
                    .addComponent(resetLessonButton)
                    .addGap(28, 28, 28))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(21, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(keyBoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(displayScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(TrackingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TrackingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(textPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(displayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(keyBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void TildeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TildeButtonActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_TildeButtonActionPerformed

    private void TwoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TwoButtonActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_TwoButtonActionPerformed

    private void oneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneButtonActionPerformed

    }//GEN-LAST:event_oneButtonActionPerformed

    private void resetLessonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetLessonButtonActionPerformed
		resetLesson();
    }//GEN-LAST:event_resetLessonButtonActionPerformed

    private void displayTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_displayTextAreaKeyPressed
		displayTextAreaKeyPressed_(evt);
    }//GEN-LAST:event_displayTextAreaKeyPressed

    private void displayTextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_displayTextAreaKeyReleased
		displayTextAreaKeyReleased_(evt);
    }//GEN-LAST:event_displayTextAreaKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		this.appLauncher.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

	//a method to delcare values
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AButton;
    private javax.swing.JButton BButton;
    private javax.swing.JButton CButton;
    private javax.swing.JButton DButton;
    private javax.swing.JButton EButton;
    private javax.swing.JButton EnterButton;
    private javax.swing.JButton FButton;
    private javax.swing.JButton GButton;
    private javax.swing.JButton HButton;
    private javax.swing.JButton IButton;
    private javax.swing.JLabel Instruction1Label;
    private javax.swing.JLabel Instruction2Label;
    private javax.swing.JButton JButton;
    private javax.swing.JButton KButton;
    private javax.swing.JButton LButton;
    private javax.swing.JButton MButton;
    private javax.swing.JButton NButton;
    private javax.swing.JButton OButton;
    private javax.swing.JButton OpenBracketButton;
    private javax.swing.JButton PButton;
    private javax.swing.JButton QButton;
    private javax.swing.JButton QuestionMarkButton;
    private javax.swing.JButton RButton;
    private javax.swing.JButton SButton;
    private javax.swing.JButton ShiftButton;
    private javax.swing.JButton TButton;
    private javax.swing.JButton TabButton;
    private javax.swing.JButton TildeButton;
    private javax.swing.JPanel TrackingPanel;
    private javax.swing.JButton TwoButton;
    private javax.swing.JButton UButton;
    private javax.swing.JButton VButton;
    private javax.swing.JButton WButton;
    private javax.swing.JButton XButton;
    private javax.swing.JButton YButton;
    private javax.swing.JButton ZButton;
    private javax.swing.JButton ZeroButton;
    private javax.swing.JLabel accuracyLabel;
    private javax.swing.JLabel accuracynumLabel;
    private javax.swing.JButton backSlashButton;
    private javax.swing.JButton backspaceButton;
    private javax.swing.JButton capsButton;
    private javax.swing.JButton closedBracketButton;
    private javax.swing.JButton comaButton;
    private javax.swing.JLabel correctLabel;
    private javax.swing.JLabel currentpanagramLabel;
    private javax.swing.JLabel difficultkeysLabel;
    private javax.swing.JLabel difficultnumLabel;
    private javax.swing.JScrollPane displayScrollPane;
    private javax.swing.JTextArea displayTextArea;
    private javax.swing.JButton dotButton;
    private javax.swing.JButton downArrowButton;
    private javax.swing.JButton eightButton;
    private javax.swing.JButton fiveButton;
    private javax.swing.JButton fourButton;
    private javax.swing.JButton hashButton;
    private javax.swing.JLabel howTouseLabel;
    private javax.swing.JLabel incorrectlabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel keyBoardPanel;
    private javax.swing.JLabel keyscorrecrLabel;
    private javax.swing.JButton leftArrowButton;
    private javax.swing.JButton minusButton;
    private javax.swing.JButton nineButton;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel numofkeysincorrectLabel;
    private javax.swing.JButton oneButton;
    private javax.swing.JLabel panagramLabel;
    private javax.swing.JButton plusButton;
    private javax.swing.JButton resetLessonButton;
    private javax.swing.JButton rightArrowButton;
    private javax.swing.JButton semicolonButton;
    private javax.swing.JButton sevenButton;
    private javax.swing.JButton sixButton;
    private javax.swing.JButton spaceButton;
    private javax.swing.JPanel textPanel;
    private javax.swing.JButton threeButton;
    private javax.swing.JButton upArrowButton;
    // End of variables declaration//GEN-END:variables

}
