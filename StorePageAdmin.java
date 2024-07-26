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
import java.util.HashMap;

public class StorePageAdmin extends JPanel  {

    private AppFrame parentFrame;
    BufferedImage pictureDataMovie, pictureAddBook, pictureLogOut,pictureCreditCard, picturePlusBook, pictureMinusBook,pictureDataBasketShop,pictureDataSansCinema;
    JLabel piclabelAddBook, picLabelLogOut,picLabelDataBasketShop,picLabelDataSansCinema,picLabelCreditCard;
    String line = "";
    String splitBy = ",";
    JPanel contentPanel;
    HashMap<Integer, JLabel> bookCountLabels;

    public StorePageAdmin(AppFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.bookCountLabels = new HashMap<>();
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(new BorderLayout());

        contentPanel = new CustomPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(new Dimension(400, 3000));
        contentPanel.setBackground(Color.WHITE);

        try {
            pictureCreditCard = ImageIO.read(new File("ImageApp/creditCard.jpg"));
            pictureDataSansCinema = ImageIO.read(new File("ImageApp/dataCinemaSans.jpg"));
            pictureDataBasketShop = ImageIO.read(new File("ImageApp/dataBasketShop.jpg"));
            pictureDataMovie = ImageIO.read(new File("ImageApp/dataMovie.jpg"));
            pictureAddBook = ImageIO.read(new File("ImageApp/plusAddBook.jpg"));
            picturePlusBook = ImageIO.read(new File("ImageApp/plusBook.jpg"));
            pictureMinusBook = ImageIO.read(new File("ImageApp/minusBook.jpg"));
            pictureLogOut = ImageIO.read(new File("ImageApp/logOut1.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




        picLabelDataBasketShop = new JLabel(new ImageIcon(pictureDataBasketShop));
        picLabelDataBasketShop.setBounds(320, 23, 22, 27);
        this.add(picLabelDataBasketShop);

        picLabelDataBasketShop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showDataBasketShopAdmin();
            }
        });

        picLabelDataSansCinema = new JLabel(new ImageIcon(pictureDataSansCinema));
        picLabelDataSansCinema.setBounds(290, 25, 23, 21);
        this.add(picLabelDataSansCinema);

        picLabelDataSansCinema.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showDataSansAdmin();
            }
        });

        picLabelCreditCard = new JLabel(new ImageIcon(pictureCreditCard));
        picLabelCreditCard.setBounds(260, 20, 25, 25);
        contentPanel.add(picLabelCreditCard);

        picLabelCreditCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showCreditCardDataAdmin();
            }
        });

        piclabelAddBook = new JLabel(new ImageIcon(pictureAddBook));
        piclabelAddBook.setBounds(350, 20, 25, 25);
        contentPanel.add(piclabelAddBook);

        piclabelAddBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showAddBookAdmin();
            }
        });

        picLabelLogOut = new JLabel(new ImageIcon(pictureLogOut));
        picLabelLogOut.setBounds(30, 20, 23, 26);
        contentPanel.add(picLabelLogOut);

        picLabelLogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showLogin();
            }
        });

        int yOffset = 20;

        try (BufferedReader br = new BufferedReader(new FileReader("DataBase/Movie.csv"))) {
            int bookIndex = 0;
            while ((line = br.readLine()) != null) {
                String[] book = line.split(splitBy); // use comma as separator
                try {
                    BufferedImage pictureBook = ImageIO.read(new File(book[5]));
                    buildNewBook(book, pictureBook, yOffset, bookIndex); // Build each book entry
                    yOffset += 200; // Increase offset for the next book
                    bookIndex++;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
    }


    public void buildNewBook(String[] book, BufferedImage pictureBook, int yOffset, int bookIndex) {
        JLabel picLabelBook = new JLabel(new ImageIcon(pictureBook));
        picLabelBook.setBounds(275, yOffset + 55, 100, 151);
        contentPanel.add(picLabelBook);


        JLabel picLabelDataMovie = new JLabel(new ImageIcon(pictureDataMovie));
        picLabelDataMovie.setBounds(240, yOffset + 95, 20, 21);
        contentPanel.add(picLabelDataMovie);

        picLabelDataMovie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showDataMovieAdmin(pictureBook, book[3], book[1], book[0], Integer.parseInt(book[2]));
            }
        });

        JLabel nameLabel = new JLabel(book[0]);
        Font head = new Font("Times Roman", Font.PLAIN, 20);
        nameLabel.setFont(head);
        nameLabel.setBounds(30, yOffset + 60, 150, 25);
        contentPanel.add(nameLabel);

        JLabel emailLabel = new JLabel(book[1]);
        emailLabel.setBounds(30, yOffset + 90, 150, 15);
        contentPanel.add(emailLabel);

        JLabel passLabel = new JLabel(book[2] + " T");
        Font head2 = new Font("Times Roman", Font.PLAIN, 17);
        passLabel.setForeground(Color.GREEN);
        passLabel.setFont(head2);
        passLabel.setBounds(30, yOffset + 175, 150, 20);
        contentPanel.add(passLabel);

        JLabel piclabelPlusBook = new JLabel(new ImageIcon(picturePlusBook));
        piclabelPlusBook.setBounds(230, yOffset + 163, 25, 25);
        contentPanel.add(piclabelPlusBook);

        JLabel piclabelMinusBook = new JLabel(new ImageIcon(pictureMinusBook));
        piclabelMinusBook.setBounds(170, yOffset + 165, 25, 25);
        contentPanel.add(piclabelMinusBook);

        JLabel labelNumberBook = new JLabel(book[4]);
        Font heading = new Font("Times Roman", Font.PLAIN, 20);
        labelNumberBook.setFont(heading);
        labelNumberBook.setBounds(200, yOffset + 155, 40, 40);
        contentPanel.add(labelNumberBook);


        bookCountLabels.put(bookIndex, labelNumberBook);


        piclabelPlusBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentCount = Integer.parseInt(book[4]);
                currentCount++;
                book[4] = String.valueOf(currentCount);
                labelNumberBook.setText(book[4]);
            }
        });


        piclabelMinusBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentCount = Integer.parseInt(book[4]);
                if (currentCount > 0) {
                    currentCount--;
                    book[4] = String.valueOf(currentCount);
                    labelNumberBook.setText(book[4]);
                }
            }
        });
    }



    class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;


            for (int i = 0; i < getHeight() / 200; i++) {
                int yOffset = i * 200;
                Line2D lin = new Line2D.Float(22, yOffset + 74, 375, yOffset + 74);
                Line2D lin2 = new Line2D.Float(375, yOffset + 74, 375, yOffset + 225);
                Line2D lin3 = new Line2D.Float(375, yOffset + 225, 22, yOffset + 225);
                Line2D lin4 = new Line2D.Float(22, yOffset + 225, 22, yOffset + 74);
                g2.draw(lin);
                g2.draw(lin2);
                g2.draw(lin3);
                g2.draw(lin4);
            }
        }
    }
}
