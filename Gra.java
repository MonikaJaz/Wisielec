import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gra extends JPanel implements ActionListener {
    private MyFrame myFrame;

    ImageIcon icon;
    JLabel label;
    JLabel header;
    JButton button;
    ActualGame actualGamePanel;

    Gra(MyFrame myFrame) {
        this.myFrame = myFrame;

        // Tworzenie JPanel i ustawienie layoutu na GridBagLayout
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setOpaque(true);

        header = new JLabel("THE HANGMAN GAME");
        button = new JButton("START");
        button.setPreferredSize(new Dimension(100,50));
        button.addActionListener(this);
        icon = new ImageIcon("hangman.png");
        label = new JLabel(icon);

        // Utworzenie obiektu GridBagConstraints, aby wyśrodkować komponenty
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // marginesy
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Komponenty zajmują dwa rzędy
        this.add(header, gbc);

        gbc.gridy = 1;
        this.add(label, gbc);

        gbc.gridy = 2;
        this.add(button, gbc);
       // this.add(this, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
                myFrame.changePanel(new ActualGame(myFrame)); // Zmień panel na ActualGame
                myFrame.setVisible(true);
            }


        }
    }

