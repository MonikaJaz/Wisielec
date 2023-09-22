import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ActualGame extends JPanel implements ActionListener {
//dodalam kome
    MyFrame myFrame;
    JButton button;
    char[] hiddenWord;
    JPanel keyboard;
    JButton button1;
    File dictionary;
    ArrayList<String> words;
    JLabel guessLabel;
    char pressedLetter;
    char[] myAnswers;
    int lives;
    JLabel hangmanIcon;
    ImageIcon icon6;
    JButton button2;


    ActualGame(MyFrame myFrame) {
        this.myFrame = myFrame;
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        this.setOpaque(true);

        button = new JButton("GO BACK");
        button.addActionListener(this);
        button2 = new JButton("RESTART");
        button2.addActionListener(this);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(button, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        this.add(button2, gbc);

        icon6 = new ImageIcon("6.png");
        hangmanIcon = new JLabel(icon6);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 1; // Ustaw etykietę pod przyciskiem "GO BACK"
        gbc.gridx=0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(hangmanIcon, gbc);


        guessLabel = new JLabel();
        guessLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 2; // Ustaw etykietę pod przyciskiem "GO BACK"
        gbc.gridx=0;
        this.add(guessLabel, gbc);


        keyboard = new JPanel();
        keyboard.setLayout(new GridLayout(3, 10));
        char[] characters = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        for (char znak : characters) {
            button1 = new JButton(String.valueOf(znak));
            button1.addActionListener(this);
            keyboard.add(button1);

        }

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(keyboard, gbc);

        dictionary = new File("C:\\Users\\ADMIN\\Desktop\\projektyJava\\gui\\Hangman\\src\\engmix.txt");
        words = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(dictionary));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }

        } catch (IOException e) {
            System.out.println("File not found!");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        String randomWord = words.get(randomIndex);
        lives = 6;
        hiddenWord = randomWord.toCharArray();
        myAnswers = new char[hiddenWord.length];
        for (int i = 0; i < hiddenWord.length; i++) {
            myAnswers[i] = '?';

        }
        StringBuilder stringBuilder2 = new StringBuilder("Guess the word: ");
        for (int i = 0; i < myAnswers.length; i++) {
            stringBuilder2.append(myAnswers[i] + " ");
            guessLabel.setText(String.valueOf(stringBuilder2));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        pressedLetter = sourceButton.getText().toLowerCase().charAt(0);
        checkLetter();

        JButton sourceButton1 = (JButton) e.getSource();
        if (sourceButton1 == button2) {
            restartGame();
        }
        if(e.getSource()==button){
            myFrame.changePanel(myFrame.gra);
            myFrame.setVisible(true);
            }
        }


    private void restartGame() {

        lives = 6;
        guessLabel.setText("Guess the word: ");


        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        String randomWord = words.get(randomIndex);
        hiddenWord = randomWord.toCharArray();
        myAnswers = new char[hiddenWord.length];
        for (int i = 0; i < hiddenWord.length; i++) {
            myAnswers[i] = '?';
        }
        hangmanIcon.setIcon(icon6);
        displayLabel();
    }


    public void displayLabel() {

        StringBuilder stringBuilder = new StringBuilder("Guess the word: ");
        for (int i = 0; i < myAnswers.length; i++) {
            stringBuilder.append(myAnswers[i] + " ");
            guessLabel.setText(String.valueOf(stringBuilder));
        }

    }

    private void checkLetter() {
        boolean found = false;

        for (int i = 0; i < hiddenWord.length; i++) {
            if (pressedLetter == hiddenWord[i] && myAnswers[i] == '?') {
                myAnswers[i] = hiddenWord[i];
                found = true;
            }
            displayLabel();
        }

        if (!found) {
            System.out.println("Wrong letter!");
            lives--; // Odejmowanie życia tylko wtedy, gdy litera jest błędna
        }

        boolean done = true;
        for (int i = 0; i < myAnswers.length; i++) {
            if (myAnswers[i] == '?') {
                done = false;
                break;
            }
        }

        System.out.println("\nLives left: " + lives);
        drawHangman(lives);

        if (done) {
            guessLabel.setText("Congratulations, you won the game!");
        } else if (lives <= 0) {
            guessLabel.setText("Sorry, you lost. The word was: " + String.valueOf(hiddenWord));
        }


    }




    public void drawHangman(int l){
        if(l == 6) {
            hangmanIcon.setIcon(icon6);

        }
        else if(l == 5) {
            Icon icon5 = new ImageIcon("5.png");
            hangmanIcon.setIcon(icon5);
        }
        else if(l == 4) {
            Icon icon4 = new ImageIcon("4.png");
            hangmanIcon.setIcon(icon4);
        }
        else if(l == 3) {
            Icon icon3 = new ImageIcon("3.png");
            hangmanIcon.setIcon(icon3);
        }
        else if(l == 2) {
            Icon icon2 = new ImageIcon("2.png");
            hangmanIcon.setIcon(icon2);
        }
        else if(l == 1) {
            Icon icon1 = new ImageIcon("1.png");
            hangmanIcon.setIcon(icon1);
        }
        else{
            Icon icon0 = new ImageIcon("0.png");
            hangmanIcon.setIcon(icon0);
        }
    }

}

