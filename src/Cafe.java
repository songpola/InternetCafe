import javax.swing.*;
import java.awt.*;

public class Cafe extends JPanel {

    public Cafe() {
        // 0 rows (dynamic), 8 columns, with 10px gaps
        setLayout(new GridLayout(0, 8, 10, 10));

        // 2. Loop to create your PC buttons
        int numberOfPCs = 10; // Example amount
        for (int i = 1; i <= numberOfPCs; i++) {
            JToggleButton pcButton = new JToggleButton("PC " + i);

            // Set default visual style (e.g., green for available)
            pcButton.setBackground(Color.GREEN);
            pcButton.setOpaque(true);
            pcButton.setBorderPainted(false); // Often looks cleaner for flat UI blocks

            // Add click behavior
            pcButton.addActionListener(e -> {
                if (pcButton.isSelected()) {
                    pcButton.setBackground(Color.RED); // Selected/Occupied
                } else {
                    pcButton.setBackground(Color.GREEN); // Deselected/Available
                }
            });

            // Add to the grid
            add(pcButton);
        }

        // 3. Put the grid inside a scroll pane
        // JScrollPane scrollPane = new JScrollPane(pcGridPanel);
        // Add scrollPane to your main frame...
    }
}
