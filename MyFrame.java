import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    Gra gra;
    private ActualGame actualGamePanel;

    // Konstruktor MainFrame i inicjalizacja paneli
    public MyFrame() {
        gra = new Gra(this);
        actualGamePanel = new ActualGame(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 700);
        this.getContentPane().setBackground(Color.BLACK);


        // Dodaj domyślny panel (np. MainMenuPanel) do głównego okna
        getContentPane().add(gra);
        this.setVisible(true);
    }

    // Metoda do zmiany panelu na inny
    public void changePanel(JPanel newPanel) {
        getContentPane().removeAll();
        getContentPane().add(newPanel);
        revalidate();
        repaint();
    }

    // Inne metody i kod...
}
