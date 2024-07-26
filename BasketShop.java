import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class BasketShop extends JPanel implements ActionListener {

    private JButton stop, start;
    private JLabel emailLabel, nameLabel, priceLabel, numberBookOrder, totalLabel, totalLabelCoupons;
    private AppFrame parentFrame;
    public JTextField textFieldBonus;
    public String strCodeCoupons;
    BufferedImage pictureGarbage, pictureBackLeft;
    JLabel picLabelBackLeft, picLabelGarbage;

    JLabel picLabelCoupons;
    String line = "";
    String splitBy = ",";
    JPanel contentPanel;
    List<Integer> yOffsets;
    List<String[]> bookList;
    double totalValue = 0;
    double totalValuePay = 0;

    public BasketShop(AppFrame parentFrame) {
        picLabelCoupons = new JLabel();

        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(new BorderLayout());

        contentPanel = new CustomPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(new Dimension(400, 3000));
        contentPanel.setBackground(Color.WHITE);

        yOffsets = new ArrayList<>();
        bookList = new ArrayList<>();

        nameLabel = new JLabel();
        emailLabel = new JLabel();
        priceLabel = new JLabel();

        loadBooks();

        try {
            pictureBackLeft = ImageIO.read(new File("ImageApp/backLeft.jpg"));
            picLabelBackLeft = new JLabel(new ImageIcon(pictureBackLeft));
            picLabelBackLeft.setBounds(25, 25, 30, 30);
            picLabelBackLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentFrame.showStore();
                }
            });
            contentPanel.add(picLabelBackLeft);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        totalLabel = new JLabel("Total Value: " + totalValue + " T");
        totalLabel.setBounds(90, 150, 210, 30);
        contentPanel.add(totalLabel);

        textFieldBonus = new HintTextField("Enter Coupons");
        textFieldBonus.setColumns(20);
        textFieldBonus.setBounds(90, 200, 210, 30);
        contentPanel.add(textFieldBonus);

        picLabelCoupons.setText("If you have a discount code");
        picLabelCoupons.setBounds(90, 180, 210, 15);
        contentPanel.add(picLabelCoupons);

        totalLabelCoupons = new JLabel();
        totalLabelCoupons.setBounds(90, 250, 210, 30);
        contentPanel.add(totalLabelCoupons);

        start = new JButton("Check Code Bonus");
        start.setPreferredSize(new Dimension(100, 75));
        start.setBounds(90, 320, 210, 30);
        start.setBackground(Color.decode("#6D7B8D"));
        start.addActionListener(this);
        contentPanel.add(start);

        stop = new JButton("Submit");
        stop.setPreferredSize(new Dimension(100, 75));
        stop.setBounds(90, 360, 210, 30);
        stop.setBackground(Color.decode("#6D7B8D"));
        stop.addActionListener(this);
        contentPanel.add(stop);

        contentPanel.setPreferredSize(new Dimension(400, 1000));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void loadBooks() {
        int yOffset = 20;
        try (BufferedReader br = new BufferedReader(new FileReader("DataBase/MovieInBasketShop.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] book = line.split(splitBy);
                bookList.add(book);
                try {
                    nameLabel.setText(book[1]);
                    yOffset += 50;
                    double value = Double.parseDouble(book[2]);
                    priceLabel.setText(book[2] + "T");
                    buildNewBook(book, yOffset);
                    totalValue += value;
                } catch (NumberFormatException ex) {
                    System.err.println("Error parsing value: " + book[2]);
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshBooks(int number) {
        contentPanel.removeAll();
        yOffsets.clear();
        totalValue = 0;

        int num = number;
        if(num == 1){
            bookList.clear();
            loadBooks();
            num = 0;
        }

        int yOffset = 20;

        try {
            pictureBackLeft = ImageIO.read(new File("ImageApp/backLeft.jpg"));
            picLabelBackLeft = new JLabel(new ImageIcon(pictureBackLeft));
            picLabelBackLeft.setBounds(25, 25, 30, 30);
            picLabelBackLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentFrame.showStore();
                }
            });
            contentPanel.add(picLabelBackLeft);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String[] book : bookList) {
            try {
                nameLabel.setText(book[1]);
                yOffset += 50;
                double value = Double.parseDouble(book[2]);
                priceLabel.setText(book[2] + "T");
                buildNewBook(book, yOffset);
                totalValue += value;
            } catch (NumberFormatException ex) {
                System.err.println("Error parsing value: " + book[2]);
                ex.printStackTrace();
            }
        }

        totalLabel = new JLabel("Total Value: " + totalValue + " T");
        totalLabel.setBounds(90, yOffset + 150, 210, 30);
        contentPanel.add(totalLabel);

        textFieldBonus.setBounds(90, yOffset + 200, 210, 30);
        contentPanel.add(textFieldBonus);

        picLabelCoupons.setBounds(90, yOffset + 180, 210, 15);
        contentPanel.add(picLabelCoupons);

        totalLabelCoupons.setBounds(90, yOffset + 250, 300, 30);
        contentPanel.add(totalLabelCoupons);

        start.setBounds(90, yOffset + 320, 210, 30);
        contentPanel.add(start);

        stop.setBounds(90, yOffset + 360, 210, 30);
        contentPanel.add(stop);

        contentPanel.setPreferredSize(new Dimension(400, yOffset + 1000));
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void buildNewBook(String[] book, int yOffset) {
        //loadBooks();
        yOffsets.add(yOffset);

        JLabel nameLabel = new JLabel(book[1]);
        Font head = new Font("Times Roman", Font.PLAIN, 19);
        nameLabel.setFont(head);
        nameLabel.setBounds(30, yOffset + 80, 150, 25);
        contentPanel.add(nameLabel);

        JLabel priceLabel = new JLabel(book[2] + " T");
        Font head2 = new Font("Times Roman", Font.PLAIN, 17);
        priceLabel.setForeground(Color.GREEN);
        priceLabel.setFont(head2);
        priceLabel.setBounds(300, yOffset + 80, 150, 20);
        contentPanel.add(priceLabel);

        try {
            pictureGarbage = ImageIO.read(new File("ImageApp/garbage.jpg"));
            picLabelGarbage = new JLabel(new ImageIcon(pictureGarbage));
            picLabelGarbage.setBounds(250, yOffset + 80, 21, 25);

            picLabelGarbage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    contentPanel.remove(nameLabel);
                    contentPanel.remove(priceLabel);
                    contentPanel.remove(picLabelGarbage);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                    bookList.remove(book);
                    refreshBooks(0);
                }
            });
            contentPanel.add(picLabelGarbage);
            contentPanel.revalidate();
            contentPanel.repaint();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stop) {
            parentFrame.showOrderShopItem();
        } else if (e.getSource() == start) {
            strCodeCoupons = textFieldBonus.getText();
            if ("aliyasami".equals(strCodeCoupons)) {
                totalValuePay = totalValue - totalValue * 0.3;
                totalLabelCoupons.setText("Total With Coupons: " + totalValuePay + " T");
                contentPanel.add(totalLabelCoupons);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        }
    }

    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            for (int yOffset : yOffsets) {
                Line2D lin = new Line2D.Float(22, yOffset + 74, 375, yOffset + 74);
                Line2D lin2 = new Line2D.Float(22, yOffset + 125, 375, yOffset + 125);
                g2.draw(lin);
                g2.draw(lin2);
            }
        }
    }
}
