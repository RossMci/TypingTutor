package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import model.UserAccount;

/**
 *
 * @author ross1
 */
public class TypingTutor extends javax.swing.JFrame implements KeyListener {
// gloable values

    UserAccount userAccount = new UserAccount();
    JButton keyButtons[];
    String panagramsList[];
    public static int numkeys = 0, correct = 0, incorrect = 0, currentindex = 0;
    double accuracy;
    String lastIncorrectLetter;
    String keyText;
    String textArea;
    String panagrams;
    String subpanagram;

    /**
     * Creates new form TypingTutor
     */
    public TypingTutor() {
        initComponents();
        intializecode();
        displayTextArea.addKeyListener(this);

    }

    public void intializecode() {
        keyButtons = new JButton[KeyEvent.KEY_LAST + 1];
        keyButtons[KeyEvent.VK_0] = ZeroButton;
        
        keyButtons[KeyEvent.VK_A] = AButton;

        keyButtons = new JButton[]{
            TildeButton, oneButton, TwoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton,
            nineButton, ZeroButton, minusButton, plusButton, backspaceButton, TabButton, QButton, WButton,
            EButton, RButton, TButton, YButton, UButton, IButton, OButton, PButton, OpenBracketButton,
            closedBracketButton, backSlashButton, capsButton, AButton, SButton, DButton, FButton, GButton, HButton,
            JButton, KButton, LButton, semicolonButton, hashButton, EnterButton, ShiftButton, ZButton, XButton, CButton,
            VButton, BButton, NButton, MButton, comaButton, dotButton, QuestionMarkButton, upArrowButton, downArrowButton,
            leftArrowButton, rightArrowButton, backspaceButton
        };

        panagramsList = new String[]{"The quick brown fox jumped over the lazy dog", "hi", "A paragraph should consist of six to seven sentences. No, it should be no longer than three sentences long. Actually, it should include a topic sentence, several supporting sentences, and possibly a concluding sentence."};

        lastIncorrectLetter = difficultnumLabel.getText();

        panagramLabel.setText(panagramsList[0]);
        panagrams = panagramsList[0];
        keyText = displayTextArea.getText();
        textArea = displayTextArea.getText();
        subpanagram = panagramsList[0].substring(0, currentindex);
        backspaceButton.setEnabled(false);

    }
    // records the key the unicode character which represted by a key pressed 

    @Override
    public void keyTyped(KeyEvent e) {
        if (currentindex < panagramsList[0].length()) {

//        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
//            String keyText = displayTextArea.getText();
//            int keycode = keyText.length();
//            if (keycode != 0) {
//                keyText = keyText.substring(0, keycode - 1);
//
//            }
//
//            displayTextArea.setText(keyText);
//        } else {
            displayTextArea.setText(displayTextArea.getText() + e.getKeyChar());

//        }
            wordsCheck(e.getKeyCode(),e.getKeyChar(), panagramsList[0]);
        } else {
        }
    }

    // method to check if the keys typed match the panagram
    public void wordsCheck(int keycode, char letter, String panagram) {
//        if(panagramsList[0].charAt(currentindex)==textArea.equals(subpanagram)){
//            
//        }
        if (currentindex <= panagram.length()) {

            //to check if the key typed mathches the panagram at the current index
            if (letter != KeyEvent.VK_BACK_SPACE) {
                if (letter == panagrams.charAt(currentindex)) {
                    correct++;
                    correctLabel.setText(String.valueOf(correct));
                    userAccount.getCorrectKeyScores()[keycode]++;
                } else {
                    incorrect++;
                    numofkeysincorrectLabel.setText(String.valueOf(incorrect));

                    lastIncorrectLetter = lastIncorrectLetter + ' ' + letter;
                    if (difficultnumLabel.getText().contains(lastIncorrectLetter)) {
                        difficultnumLabel.setText(lastIncorrectLetter);
                    }
                };
                // increments the current index
                currentindex++;

                numkeys++;
                accuracy = correct / panagrams.length() * 100;
                System.out.println(accuracy);
                System.out.println(panagrams.length());

            }
//              else if ( e.getKeyChar()==KeyEvent.VK_BACK_SPACE) {
//                //to check if the key typed mathches the panagram at the current index
//                if (e.getKeyChar() ==panagramsList[0].charAt(currentindex)) {
//                    correct--;
//                    correctLabel.setText(String.valueOf(correct));
//                } else if(incorrect>0) {
//                    incorrect--;
//                    numofkeysincorrectLabel.setText(String.valueOf(incorrect));
//
//                }
//                // increments the current index
//                currentindex--;
//            }
        }

    }

    Color original = null;
    Color pressedColor = Color.RED;
// records when key is pressed down

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // adds the jbuttons from this method
        int keyIndex = keyEvent.getKeyCode();
        //
        //JButton selectedButton = keyButtons[keyIndex];
        //selectedButton.setBackground(pressedColor);

        oldCodeToBeRemoved(keyIndex);

    }
// resets the buttons after a button is released 

    private void oldCodeToBeRemoved(int keyIndex) {
        // colors the jbuttons
        for (int i = 0; i < keyButtons.length; i++) {
            if (keyButtons[i].getText().equalsIgnoreCase(KeyEvent.getKeyText(keyIndex))) {
                keyButtons[i].setBackground(Color.red);

            }

        }

        switch (keyIndex) {

            case 38:
                upArrowButton.setBackground(Color.red);
                break;

            case 40:
                downArrowButton.setBackground(Color.red);
                break;
            case 37:
                leftArrowButton.setBackground(Color.red);
                break;
            case 39:
                rightArrowButton.setBackground(Color.red);
                break;
            case 20:
                capsButton.setBackground(Color.red);
                break;
            case 128:
                TildeButton.setBackground(Color.red);
                break;
            case 91:
                OpenBracketButton.setBackground(Color.red);
                break;

            case 93:
                closedBracketButton.setBackground(Color.red);
                break;
            case 92:
                backSlashButton.setBackground(Color.red);
                break;

            case 47:
                QuestionMarkButton.setBackground(Color.red);
                break;

            case 520:
                hashButton.setBackground(Color.red);
                break;

            case 59:
                semicolonButton.setBackground(Color.red);
                break;

            case 32:
                spaceButton.setBackground(Color.red);
                break;
            case 44:
                comaButton.setBackground(Color.red);
                break;
            case 46:
                dotButton.setBackground(Color.red);
                break;
            case 45:
                minusButton.setBackground(Color.red);
                break;
            case 61:
                plusButton.setBackground(Color.red);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        for (int i = 0; i < keyButtons.length; i++) {
            if (keyButtons[i].getText().equals(KeyEvent.getKeyText(keycode))) {
                keyButtons[i].setBackground(null);

            }

        }

        switch (keycode) {

            case 38:
                upArrowButton.setBackground(null);
                break;

            case 40:
                downArrowButton.setBackground(null);
                break;
            case 37:
                leftArrowButton.setBackground(null);
                break;
            case 39:
                rightArrowButton.setBackground(null);
                break;
            case 20:
                capsButton.setBackground(null);
                break;
            case 128:
                TildeButton.setBackground(null);
                break;
            case 91:
                OpenBracketButton.setBackground(null);
                break;

            case 93:
                closedBracketButton.setBackground(null);
                break;
            case 92:
                backSlashButton.setBackground(null);
                break;

            case 47:
                QuestionMarkButton.setBackground(null);
                break;

            case 520:
                hashButton.setBackground(null);
                break;

            case 59:
                semicolonButton.setBackground(null);
                break;

            case 32:
                spaceButton.setBackground(null);

                break;
            case 44:
                comaButton.setBackground(null);
                break;
            case 46:
                dotButton.setBackground(null);
                break;
            case 45:
                minusButton.setBackground(null);
                break;
            case 61:
                plusButton.setBackground(null);
                break;
        }

    }

    //https://stackoverflow.com/questions/13668131/java-swing-virtual-keyboard-on-jframe/13668842
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
        displayPanel = new javax.swing.JPanel();
        displayScrollPane = new javax.swing.JScrollPane();
        displayTextArea = new javax.swing.JTextArea();
        textPanel = new javax.swing.JPanel();
        noteLabel = new javax.swing.JLabel();
        howTouseLabel = new javax.swing.JLabel();
        currentpanagramLabel = new javax.swing.JLabel();
        panagramLabel = new javax.swing.JLabel();
        TrackingPanel = new javax.swing.JPanel();
        keyscorrecrLabel = new javax.swing.JLabel();
        incorrectlabel = new javax.swing.JLabel();
        accuracyLabel = new javax.swing.JLabel();
        accuracynumLabel = new javax.swing.JLabel();
        difficultkeysLabel = new javax.swing.JLabel();
        difficultnumLabel = new javax.swing.JLabel();
        keysnumcorrectLabel = new javax.swing.JLabel();
        numofkeysincorrectLabel = new javax.swing.JLabel();
        correctLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Typeing Tutor");
        setSize(new java.awt.Dimension(300, 300));

        TildeButton.setText("~");
        TildeButton.setFocusable(false);
        TildeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TildeButtonActionPerformed(evt);
            }
        });

        oneButton.setText("1");
        oneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneButtonActionPerformed(evt);
            }
        });

        TwoButton.setText("2");
        TwoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TwoButtonActionPerformed(evt);
            }
        });

        threeButton.setText("3");

        fourButton.setText("4");

        fiveButton.setText("5");

        sixButton.setText("6");

        sevenButton.setText("7");

        eightButton.setText("8");

        nineButton.setText("9");

        ZeroButton.setText("0");

        plusButton.setText("+");

        minusButton.setText("-");

        backspaceButton.setText("Backspace");

        QButton.setText("Q");

        WButton.setText("W");

        EButton.setText("E");

        RButton.setText("R");

        TButton.setText("T");

        YButton.setText("Y");

        OButton.setText("O");

        IButton.setText("I");

        TabButton.setText("Tabs");

        PButton.setText("P");

        OpenBracketButton.setText("[");

        closedBracketButton.setText("]");

        backSlashButton.setText("\\");

            AButton.setText("A");

            SButton.setText("S");

            DButton.setText("D");

            GButton.setText("G");

            FButton.setText("F");

            KButton.setText("K");

            HButton.setText("H");

            JButton.setText("J");

            LButton.setText("L");

            semicolonButton.setText(";");

            hashButton.setText("#");

            EnterButton.setText("Enter");

            ZButton.setText("Z");

            XButton.setText("X");

            upArrowButton.setText("^");

            CButton.setText("C");

            VButton.setText("V");

            BButton.setText("B");

            NButton.setText("N");

            MButton.setText("M");

            comaButton.setText(",");

            dotButton.setText(".");

            QuestionMarkButton.setText("?");

            leftArrowButton.setText("<");

            rightArrowButton.setText(">");

            downArrowButton.setText("↓");

            ShiftButton.setText("Shift");

            capsButton.setText("Caps");

            UButton.setText("U");

            javax.swing.GroupLayout keyBoardPanelLayout = new javax.swing.GroupLayout(keyBoardPanel);
            keyBoardPanel.setLayout(keyBoardPanelLayout);
            keyBoardPanelLayout.setHorizontalGroup(
                keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(keyBoardPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(keyBoardPanelLayout.createSequentialGroup()
                            .addComponent(TildeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(oneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TwoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(threeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fiveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sixButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sevenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(eightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(ZeroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)
                            .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backspaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(keyBoardPanelLayout.createSequentialGroup()
                            .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(keyBoardPanelLayout.createSequentialGroup()
                                        .addComponent(TabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(QButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(WButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(EButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(RButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(YButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(UButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(IButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(OButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(OpenBracketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(closedBracketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(backSlashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5))
                                    .addGroup(keyBoardPanelLayout.createSequentialGroup()
                                        .addComponent(capsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(SButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(GButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(HButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(KButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(semicolonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(hashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(EnterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(keyBoardPanelLayout.createSequentialGroup()
                                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, keyBoardPanelLayout.createSequentialGroup()
                                            .addComponent(ShiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(ZButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(XButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(CButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(VButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(BButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(NButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(MButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(comaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(QuestionMarkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(upArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, keyBoardPanelLayout.createSequentialGroup()
                                            .addGap(238, 238, 238)
                                            .addComponent(spaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(217, 217, 217)
                                            .addComponent(leftArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(downArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rightArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))))
            );
            keyBoardPanelLayout.setVerticalGroup(
                keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(keyBoardPanelLayout.createSequentialGroup()
                    .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(keyBoardPanelLayout.createSequentialGroup()
                            .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(oneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TildeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(threeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TwoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fourButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fiveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sixButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sevenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ZeroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(backspaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(QButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(WButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(RButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(YButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(IButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(OButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(OpenBracketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(closedBracketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(backSlashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(EnterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(capsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(AButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(DButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(GButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(HButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(JButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(KButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(semicolonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(hashButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                            .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ShiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ZButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(XButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(VButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dotButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(QuestionMarkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(upArrowButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(keyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(leftArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(downArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rightArrowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 2, Short.MAX_VALUE))
                        .addGroup(keyBoardPanelLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(spaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(16, Short.MAX_VALUE))
            );

            displayTextArea.setEditable(false);
            displayTextArea.setColumns(20);
            displayTextArea.setRows(5);
            displayScrollPane.setViewportView(displayTextArea);

            javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
            displayPanel.setLayout(displayPanelLayout);
            displayPanelLayout.setHorizontalGroup(
                displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayPanelLayout.createSequentialGroup()
                    .addComponent(displayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            displayPanelLayout.setVerticalGroup(
                displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayPanelLayout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(displayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            textPanel.setBackground(new java.awt.Color(255, 255, 255));

            noteLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            noteLabel.setText("Note: click the buttons will not write any text and any action and you cant go back  ");

            howTouseLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            howTouseLabel.setText("Type some Text using the keyboard . The key board will highlight the keys you hit and the text will be displayed");

            currentpanagramLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            currentpanagramLabel.setText("current panagram:");

            panagramLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

            javax.swing.GroupLayout textPanelLayout = new javax.swing.GroupLayout(textPanel);
            textPanel.setLayout(textPanelLayout);
            textPanelLayout.setHorizontalGroup(
                textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(textPanelLayout.createSequentialGroup()
                    .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textPanelLayout.createSequentialGroup()
                            .addContainerGap(41, Short.MAX_VALUE)
                            .addComponent(howTouseLabel))
                        .addGroup(textPanelLayout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(noteLabel)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(textPanelLayout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addGroup(textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panagramLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(currentpanagramLabel))))
                    .addGap(496, 496, 496))
            );
            textPanelLayout.setVerticalGroup(
                textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(textPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(howTouseLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(currentpanagramLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panagramLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            TrackingPanel.setBackground(new java.awt.Color(255, 255, 204));
            TrackingPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            keyscorrecrLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            keyscorrecrLabel.setText("     Correct keys Typed");

            incorrectlabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            incorrectlabel.setText(" Typed key incorrect");

            accuracyLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            accuracyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            accuracyLabel.setText("Accuracy");

            accuracynumLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            accuracynumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            accuracynumLabel.setText("100%");

            difficultkeysLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            difficultkeysLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            difficultkeysLabel.setText("difficult  keys");

            difficultnumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            keysnumcorrectLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
            keysnumcorrectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            numofkeysincorrectLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
            numofkeysincorrectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            correctLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            correctLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            jButton1.setText("Reset");
            jButton1.setFocusable(false);

            javax.swing.GroupLayout TrackingPanelLayout = new javax.swing.GroupLayout(TrackingPanel);
            TrackingPanel.setLayout(TrackingPanelLayout);
            TrackingPanelLayout.setHorizontalGroup(
                TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TrackingPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrackingPanelLayout.createSequentialGroup()
                            .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(keyscorrecrLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(correctLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(keysnumcorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(276, 276, 276))
                        .addGroup(TrackingPanelLayout.createSequentialGroup()
                            .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(difficultnumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(incorrectlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(accuracyLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                        .addComponent(accuracynumLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(TrackingPanelLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(numofkeysincorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(TrackingPanelLayout.createSequentialGroup()
                    .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(difficultkeysLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(TrackingPanelLayout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addComponent(jButton1)))
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            TrackingPanelLayout.setVerticalGroup(
                TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TrackingPanelLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(keyscorrecrLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(TrackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(keysnumcorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrackingPanelLayout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(correctLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(39, 39, 39)
                    .addComponent(incorrectlabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(numofkeysincorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(48, 48, 48)
                    .addComponent(accuracyLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(accuracynumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(difficultkeysLabel)
                    .addGap(18, 18, 18)
                    .addComponent(difficultnumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addComponent(jButton1)
                    .addContainerGap(571, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(textPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TrackingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(keyBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1427, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(textPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(keyBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(TrackingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TypingTutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TypingTutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TypingTutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TypingTutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TypingTutor typing = new TypingTutor();
                typing.setVisible(true);
                typing.intializecode();

            }
        });
    }

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
    private javax.swing.JPanel displayPanel;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel keyBoardPanel;
    private javax.swing.JLabel keyscorrecrLabel;
    private javax.swing.JLabel keysnumcorrectLabel;
    private javax.swing.JButton leftArrowButton;
    private javax.swing.JButton minusButton;
    private javax.swing.JButton nineButton;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JLabel numofkeysincorrectLabel;
    private javax.swing.JButton oneButton;
    private javax.swing.JLabel panagramLabel;
    private javax.swing.JButton plusButton;
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
