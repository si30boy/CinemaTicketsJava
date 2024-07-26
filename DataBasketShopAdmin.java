import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DataBasketShopAdmin extends JPanel {

    private BufferedImage pictureBackLeft;
    private JLabel picLabelBackLeft;
    private AppFrame parentFrame;
    JPanel contentPanel;
    private List<String[]> csvData;

    public DataBasketShopAdmin(AppFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(new BorderLayout()); // Use BorderLayout for the JScrollPane
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel = new CustomPanel(); // This panel will contain all your components and custom painting
        contentPanel.setLayout(null); // Use null layout for absolute positioning
        contentPanel.setPreferredSize(new Dimension(500, 3000)); // Set preferred size larger than the panel to allow scrolling
        contentPanel.setBackground(Color.WHITE);

        csvData = new ArrayList<>();

        // Read CSV data
        try (BufferedReader br = new BufferedReader(new FileReader("DataBase/movieInBasketShop.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) { // Ensure the line has at least 3 columns
                    csvData.add(values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int yOffset = 85;
        for (String[] record : csvData) {

            JLabel userLabel = new JLabel("Username: " + record[0]);
            userLabel.setBounds(20, yOffset, 150, 20);
            contentPanel.add(userLabel);

            JLabel nameLabel = new JLabel("MovieName: " + record[1]);
            nameLabel.setBounds(180, yOffset, 200, 20);
            contentPanel.add(nameLabel);

            JLabel priceLabel = new JLabel("Price: " + record[2]);
            priceLabel.setBounds(380, yOffset, 80, 20);
            contentPanel.add(priceLabel);

            yOffset += 70;
        }

        try {
            pictureBackLeft = ImageIO.read(new File("ImageApp/backLeft.jpg"));

            picLabelBackLeft = new JLabel(new ImageIcon(pictureBackLeft));
            picLabelBackLeft.setBounds(25, 25, 15, 15);
            contentPanel.add(picLabelBackLeft);

            picLabelBackLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentFrame.showStoreAdmin();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            for (int i = 70; i < getHeight(); i += 70) {
                Line2D lin = new Line2D.Float(20, i, 480, i);
                g2.draw(lin);
            }
        }
    }

}
