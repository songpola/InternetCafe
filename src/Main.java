import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPanel panel;
    private JButton btnStartTimer;
    private JLabel labelTimeDisplay;

    private final Timer timer;

    private int seconds;

    public Main() {
        setTitle("Internet Cafe");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                displayTime();
            }
        });

        btnStartTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                    btnStartTimer.setText("Start");
                } else {
                    timer.start();
                    btnStartTimer.setText("Stop");
                }
            }
        });

        setVisible(true);
    }

    private void displayTime() {
        int minutes = seconds / 60;
        labelTimeDisplay.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public static void main() {
        new Main();
    }
}