import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

public class Main extends JFrame {
    private JPanel panel;
    private JButton btnToggleTimer;
    private JLabel displayTime;
    private JTextField inputTimeHour;
    private JButton btnStartTimer;
    private JLabel displayTotal;
    private final Timer timer;
    private Duration timeLeft = Duration.ZERO;

    public Main() {
        setTitle("Internet Cafe");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft.isZero() || timeLeft.isNegative()) {
                    timeLeft = Duration.ZERO;
                    timer.stop();
                    JOptionPane.showMessageDialog(Main.this, "Time's up!");
                }

                timeLeft = timeLeft.minusSeconds(1);

                updateDisplayTime();
            }
        });

        btnStartTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long hours = Long.parseLong(inputTimeHour.getText());
                timeLeft = Duration.ofHours(hours);
                timer.start();
            }
        });

        btnToggleTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!timeLeft.isZero() && !timeLeft.isNegative()) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            }
        });

        inputTimeHour.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDisplayTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDisplayTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDisplayTotal();
            }

            private void updateDisplayTotal() {
                try {
                    long hours = Long.parseLong(inputTimeHour.getText());
                    displayTotal.setText("Total: " + hours * 20 + " Baht");
                } catch (NumberFormatException e) {
                    displayTotal.setText("Invalid input. Please enter hours in integer number only.");
                }
            }
        });

        setVisible(true);
    }

    private void updateDisplayTime() {
        long hours = timeLeft.toHours();
        long minutes = timeLeft.toMinutesPart();
        long seconds = timeLeft.toSecondsPart();
        displayTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public static void main() {
        new Main();
    }
}