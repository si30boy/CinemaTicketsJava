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

public class DataSansCinemaAdmin extends JPanel {


    private BufferedImage pictureBackLeft;
    private JLabel picLabelBackLeft;
    private AppFrame parentFrame;
    JPanel contentPanel;
    private List<String[]> csvData;

    public DataSansCinemaAdmin(AppFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel = new CustomPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(new Dimension(820, 3000));
        contentPanel.setBackground(Color.WHITE);

        csvData = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader("DataBase/BuySansCinema.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3) {
                    csvData.add(values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int yOffset = 85;
        for (String[] record : csvData) {
            JLabel movieNameLabel = new JLabel("MovieName: " + record[0]);
            movieNameLabel.setBounds(20, yOffset, 200, 20);
            contentPanel.add(movieNameLabel);

            JLabel seatNumberLabel = new JLabel("SeatNumber: " + record[1]);
            seatNumberLabel.setBounds(220, yOffset, 200, 20);
            contentPanel.add(seatNumberLabel);

            JLabel sansTimeLabel = new JLabel("SansTime: " + record[2]);
            sansTimeLabel.setBounds(420, yOffset, 200, 20);
            contentPanel.add(sansTimeLabel);

            JLabel priceLabel = new JLabel("Price: " + record[3]);
            priceLabel.setBounds(620, yOffset, 200, 20);
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
                Line2D lin = new Line2D.Float(20, i, 800, i);
                g2.draw(lin);
            }
        }
    }


}
