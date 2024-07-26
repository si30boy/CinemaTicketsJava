import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataMoviePageAdmin extends JPanel implements ActionListener {
    private JButton start, stop, seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12, seat13, seat14, seat15, finish;
    private JLabel moviePictureLabel;
    private JTextArea cinemaNameTextArea;
    private JLabel directorNameLabel, screenTimeLabel;
    private JLabel movieNameLabel;
    private BufferedImage pictureBackLeft;
    private JLabel picLabelBackLeft;
    private AppFrame parentFrame;
    private int chairSeatNumber = 0;
    private String line = "";
    private String splitBy = ",";
    public String movieNameOriginal,sansRealTime;
    public double priceTotal;

    public DataMoviePageAdmin(AppFrame parentFrame, BufferedImage moviePicture, String cinemaName, String directorName, String movieName , int Price) {
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(null);

        movieNameOriginal = movieName;
        priceTotal = Price;
        moviePictureLabel = new JLabel(new ImageIcon(moviePicture));
        moviePictureLabel.setBounds(275, 50, 100, 151);
        add(moviePictureLabel);

        String cinemaAddress;
        if (cinemaName.equals("Bahman")) {
            cinemaAddress = "Zanjan, Imam Street, before reaching Enqelab Square";
        } else {
            cinemaAddress = "Zanjan, Karmandan Town, Mahdavi Boulevard, Rosha Center - Karmandan Town Bus Station";
        }

        cinemaNameTextArea = new JTextArea("Cinema: " + cinemaName + "\n" + cinemaAddress);
        cinemaNameTextArea.setBounds(30, 110, 200, 100);
        cinemaNameTextArea.setLineWrap(true);
        cinemaNameTextArea.setWrapStyleWord(true);
        cinemaNameTextArea.setEditable(false);
        cinemaNameTextArea.setOpaque(false);
        add(cinemaNameTextArea);

        directorNameLabel = new JLabel("Director: " + directorName);
        directorNameLabel.setBounds(30, 80, 300, 30);
        add(directorNameLabel);

        movieNameLabel = new JLabel("Movie: " + movieName);
        movieNameLabel.setBounds(30, 50, 300, 30);
        add(movieNameLabel);

        seatTable();

        // Load and modify the CSV data
        List<String[]> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("DataBase/Movie.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] book = line.split(splitBy);
                csvData.add(book);

                if (book.length >= 7 && book[0].equals(movieName)) {
                    book[6] = String.valueOf(chairSeatNumber);
                    System.out.println(book[6]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the modified data back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DataBase/Movie.csv"))) {
            for (String[] book : csvData) {
                bw.write(String.join(",", book));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            pictureBackLeft = ImageIO.read(new File("ImageApp/backLeft.jpg"));
            picLabelBackLeft = new JLabel(new ImageIcon(pictureBackLeft));
            picLabelBackLeft.setBounds(25, 25, 15, 15);
            add(picLabelBackLeft);

            picLabelBackLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentFrame.showStoreAdmin();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        screenTimeLabel = new JLabel("Screen Time");
        Font heading = new Font("Times Roman", Font.PLAIN, 15);
        screenTimeLabel.setFont(heading);
        screenTimeLabel.setBounds(30, 170, 300, 30);
        add(screenTimeLabel);

        stop = new JButton("10:30 -  12");
        stop.setPreferredSize(new Dimension(100, 75));
        stop.setBounds(30, 200, 100, 30);
        stop.setBackground(Color.decode("#ADD8E6"));
        stop.addActionListener(this);
        this.add(stop);

        start = new JButton("9 - 10:30");
        start.setPreferredSize(new Dimension(100, 75));
        start.setBounds(150, 200, 100, 30);
        start.setBackground(Color.decode("#ADD8E6"));
        start.addActionListener(this);
        this.add(start);

        finish = new JButton("Enter");
        finish.setPreferredSize(new Dimension(208, 30));
        finish.setBounds(50, 420, 258, 30);
        finish.setBackground(Color.decode("#ADD8E6"));
        finish.addActionListener(this);
        this.add(finish);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String backGroundColor = "#FFC300";
        if (e.getSource() == start) {
//            start.setBackground(Color.decode("#800080"));
//            stop.setBackground(Color.CYAN);
            sansRealTime = "9 - 10:30";
        } else if (e.getSource() == stop) {
//            stop.setBackground(Color.decode("#800080"));
//            start.setBackground(Color.CYAN);
            sansRealTime = "10:30 -  12";
        } else if (e.getSource() == finish) {
            parentFrame.showStoreAdmin();
        } else if (e.getSource() == seat1) {
            seat1.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat2) {
            seat2.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat3) {
            seat3.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat4) {
            seat4.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat5) {
            seat5.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat6) {
            seat6.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat7) {
            seat7.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat8) {
            seat8.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat9) {
            seat9.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat10) {
            seat10.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat11) {
            seat11.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat12) {
            seat12.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat13) {
            seat13.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat14) {
            seat14.setBackground(Color.decode(backGroundColor));
        } else if (e.getSource() == seat15) {
            seat15.setBackground(Color.decode(backGroundColor));
        }
    }

    public void seatTable() {
        String color = "#FFC300";

        seat1 = new JButton("1");
        seat1.setPreferredSize(new Dimension(50, 50));
        seat1.setBounds(50, 250, 50, 50);
        seat1.setBackground(Color.decode(color));
        seat1.addActionListener(this);
        this.add(seat1);

        seat2 = new JButton("2");
        seat2.setPreferredSize(new Dimension(50, 50));
        seat2.setBounds(102, 250, 50, 50);
        seat2.setBackground(Color.decode(color));
        seat2.addActionListener(this);
        this.add(seat2);

        seat3 = new JButton("3");
        seat3.setPreferredSize(new Dimension(50, 50));
        seat3.setBounds(154, 250, 50, 50);
        seat3.setBackground(Color.decode(color));
        seat3.addActionListener(this);
        this.add(seat3);

        seat4 = new JButton("4");
        seat4.setPreferredSize(new Dimension(50, 50));
        seat4.setBounds(206, 250, 50, 50);
        seat4.setBackground(Color.BLACK);
        seat4.addActionListener(this);
        this.add(seat4);

        seat5 = new JButton("5");
        seat5.setPreferredSize(new Dimension(50, 50));
        seat5.setBounds(258, 250, 50, 50);
        seat5.setBackground(Color.decode(color));
        seat5.addActionListener(this);
        this.add(seat5);

        seat6 = new JButton("6");
        seat6.setPreferredSize(new Dimension(50, 50));
        seat6.setBounds(50, 302, 50, 50);
        seat6.setBackground(Color.decode(color));
        seat6.addActionListener(this);
        this.add(seat6);

        seat7 = new JButton("7");
        seat7.setPreferredSize(new Dimension(50, 50));
        seat7.setBounds(102, 302, 50, 50);
        seat7.setBackground(Color.BLACK);
        seat7.addActionListener(this);
        this.add(seat7);

        seat8 = new JButton("8");
        seat8.setPreferredSize(new Dimension(50, 50));
        seat8.setBounds(154, 302, 50, 50);
        seat8.setBackground(Color.decode(color));
        seat8.addActionListener(this);
        this.add(seat8);

        seat9 = new JButton("9");
        seat9.setPreferredSize(new Dimension(50, 50));
        seat9.setBounds(206, 302, 50, 50);
        seat9.setBackground(Color.decode(color));
        seat9.addActionListener(this);
        this.add(seat9);

        seat10 = new JButton("10");
        seat10.setPreferredSize(new Dimension(50, 50));
        seat10.setBounds(258, 302, 50, 50);
        seat10.setBackground(Color.decode(color));
        seat10.addActionListener(this);
        this.add(seat10);

        seat11 = new JButton("11");
        seat11.setPreferredSize(new Dimension(50, 50));
        seat11.setBounds(50, 354, 50, 50);
        seat11.setBackground(Color.decode(color));
        seat11.addActionListener(this);
        this.add(seat11);

        seat12 = new JButton("12");
        seat12.setPreferredSize(new Dimension(50, 50));
        seat12.setBounds(102, 354, 50, 50);
        seat12.setBackground(Color.decode(color));
        seat12.addActionListener(this);
        this.add(seat12);

        seat13 = new JButton("13");
        seat13.setPreferredSize(new Dimension(50, 50));
        seat13.setBounds(154, 354, 50, 50);
        seat13.setBackground(Color.decode(color));
        seat13.addActionListener(this);
        this.add(seat13);

        seat14 = new JButton("14");
        seat14.setPreferredSize(new Dimension(50, 50));
        seat14.setBounds(206, 354, 50, 50);
        seat14.setBackground(Color.BLACK);
        seat14.addActionListener(this);
        this.add(seat14);

        seat15 = new JButton("15");
        seat15.setPreferredSize(new Dimension(50, 50));
        seat15.setBounds(258, 354, 50, 50);
        seat15.setBackground(Color.BLACK);
        seat15.addActionListener(this);
        this.add(seat15);
    }
}
