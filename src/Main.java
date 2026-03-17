import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
    private JPanel panel;
    private JButton btnToggleTimer;
    private JLabel labelTimeDisplay;
    private JTextField inputTimeHour;
    private JButton btnStartTimer;

    private final Timer timer;

    private Duration timeLeft;

    public Main() {
        setTitle("Internet Cafe");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft = timeLeft.minusSeconds(1);

                if (timeLeft.isZero() ||  timeLeft.isNegative()) {
                    timeLeft = Duration.ZERO;
                    timer.stop();
                }

                displayTime();
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
                if (timer.isRunning()) {
                    timer.stop();
                } else {
                    timer.start();
                }
            }
        });

        setVisible(true);
    }

    private void displayTime() {
        long hours = timeLeft.toHours();
        long minutes = timeLeft.toMinutesPart();
        long seconds = timeLeft.toSecondsPart();
        labelTimeDisplay.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public static void main() {
        new Main();
    }
}