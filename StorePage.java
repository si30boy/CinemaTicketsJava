import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.*;
import java.awt.geom.Line2D;

public class StorePage extends JPanel {

    private AppFrame parentFrame;
    BufferedImage pictureShopping, pictureColoredStar, pictureEmptyStar, pictureProfileCinema, pictureLogOut, pictureDataMovie;
    JLabel picLabelLogOut, piclabelProfileCinema;
    JPanel contentPanel;

    public StorePage(AppFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(new BorderLayout());

        contentPanel = new CustomPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(new Dimension(400, 3000));
        contentPanel.setBackground(Color.WHITE);

        try {
            pictureDataMovie = ImageIO.read(new File("ImageApp/dataMovie.jpg"));
            pictureShopping = ImageIO.read(new File("ImageApp/shopping.jpg"));
            pictureProfileCinema = ImageIO.read(new File("ImageApp/profileCinema.jpg"));
            pictureLogOut = ImageIO.read(new File("ImageApp/logOut1.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        piclabelProfileCinema = new JLabel(new ImageIcon(pictureProfileCinema));
        piclabelProfileCinema.setBounds(345, 20, 24, 24);
        contentPanel.add(piclabelProfileCinema);

        piclabelProfileCinema.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showProfile();
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] book = line.split(",");
                if (book.length < 6) {
                    continue;
                }
                try {
                    BufferedImage pictureBook = ImageIO.read(new File(book[5]));
                    buildNewBook(book, pictureBook, yOffset);
                    yOffset += 200;
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

    public void buildNewBook(String[] book, BufferedImage pictureBook, int yOffset) {
        JLabel picLabelBook = new JLabel(new ImageIcon(pictureBook));
        picLabelBook.setBounds(275, yOffset + 55, 100, 151);
        contentPanel.add(picLabelBook);

        JLabel picLabelShopping = new JLabel(new ImageIcon(pictureShopping));
        picLabelShopping.setBounds(240, yOffset + 65, 20, 17);
        contentPanel.add(picLabelShopping);

        JLabel picLabelDataMovie = new JLabel(new ImageIcon(pictureDataMovie));
        picLabelDataMovie.setBounds(240, yOffset + 95, 20, 21);
        contentPanel.add(picLabelDataMovie);

        picLabelShopping.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginPage loginPage = new LoginPage(parentFrame);
                CSVManager.purchaseBook(loginPage.getUserNameLogin(), book[0], book[2]);

                parentFrame.getBasketShop().refreshBooks(1);
                parentFrame.showBasketShop();
            }
        });

        picLabelDataMovie.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.showDataMovie(pictureBook, book[3], book[1], book[0], Integer.parseInt(book[2]));
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

        JLabel cinemaNameLabel = new JLabel(book[3] + " cinema");
        Font head3 = new Font("Times Roman", Font.PLAIN, 10);
        cinemaNameLabel.setForeground(Color.BLUE);
        cinemaNameLabel.setFont(head3);
        cinemaNameLabel.setBounds(30, yOffset + 110, 150, 25);
        contentPanel.add(cinemaNameLabel);

        JLabel passLabel = new JLabel(book[2] + " T");
        Font head2 = new Font("Times Roman", Font.PLAIN, 17);
        passLabel.setForeground(Color.GREEN);
        passLabel.setFont(head2);
        passLabel.setBounds(30, yOffset + 175, 150, 20);
        contentPanel.add(passLabel);

        try {
            pictureColoredStar = ImageIO.read(new File("ImageApp/starColored.jpg"));
            pictureEmptyStar = ImageIO.read(new File("ImageApp/starEmpty3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fiveStar(yOffset + 180);
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

    public void fiveStar(int yOffset) {
        int mazrabDistance = 160;

        JLabel picLabel = new JLabel(new ImageIcon(pictureEmptyStar));
        picLabel.setBounds(mazrabDistance, yOffset, 15, 14);
        contentPanel.add(picLabel);

        picLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                picLabel.setIcon(new ImageIcon(pictureColoredStar));
            }
        });
        mazrabDistance += 19;

        JLabel picLabel1 = new JLabel(new ImageIcon(pictureEmptyStar));
        picLabel1.setBounds(mazrabDistance, yOffset, 15, 14);
        contentPanel.add(picLabel1);

        picLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                picLabel1.setIcon(new ImageIcon(pictureColoredStar));
            }
        });
        mazrabDistance += 19;

        JLabel picLabel2 = new JLabel(new ImageIcon(pictureEmptyStar));
        picLabel2.setBounds(mazrabDistance, yOffset, 15, 14);
        contentPanel.add(picLabel2);

        picLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                picLabel2.setIcon(new ImageIcon(pictureColoredStar));
            }
        });
        mazrabDistance += 19;

        JLabel picLabel3 = new JLabel(new ImageIcon(pictureEmptyStar));
        picLabel3.setBounds(mazrabDistance, yOffset, 15, 14);
        contentPanel.add(picLabel3);

        picLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                picLabel3.setIcon(new ImageIcon(pictureColoredStar));
            }
        });
        mazrabDistance += 19;

        JLabel picLabel4 = new JLabel(new ImageIcon(pictureEmptyStar));
        picLabel4.setBounds(mazrabDistance, yOffset, 15, 14);
        contentPanel.add(picLabel4);

        picLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                picLabel4.setIcon(new ImageIcon(pictureColoredStar));
            }
        });
    }
}
