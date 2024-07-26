import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;

public class ResultBuyPage extends JPanel implements ActionListener {

    private JButton stop;
    private BufferedImage pictureBackLeft;
    private JLabel rseultBuyLabel,sansTimeLabel,seatNumberLabel,movieNameLabel,picLabelBackLeft,priceLabel;
    private AppFrame parentFrame;
    private String line = "";
    private String splitBy = ",";
    private String movieName,seatNumber,sansTime,howmuchPay;
    JPanel contentPanel;


    public ResultBuyPage(AppFrame parentFrame){


        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel();
        rseultBuyLabel = new JLabel();
        seatNumberLabel= new JLabel();
        sansTimeLabel = new JLabel();
        movieNameLabel =new JLabel();
        picLabelBackLeft = new JLabel();
        priceLabel = new JLabel();



        stop = new JButton("OK");
        stop.setPreferredSize(new Dimension(100, 75));
        stop.setBounds(90, 430, 210, 30);
        stop.setBackground(Color.decode("#6D7B8D"));
        stop.addActionListener(this);
        this.add(stop);


        try {

            pictureBackLeft = ImageIO.read(new File("ImageApp/backLeft.jpg"));
            picLabelBackLeft = new JLabel(new ImageIcon(pictureBackLeft));
            picLabelBackLeft.setBounds(25, 25, 15, 15);
            this.add(picLabelBackLeft);

            picLabelBackLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentFrame.showStore();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void refreshBooks(){

        try (BufferedReader br = new BufferedReader(new FileReader("DataBase/BuySansCinema.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                movieName = employee[0];
                seatNumber = employee[1];
                sansTime = employee[2];
                howmuchPay = employee[3];

            }
        } catch (IOException x) {
            x.printStackTrace();
        }


        rseultBuyLabel.setText("Result Buy");
        rseultBuyLabel.setBounds(200, 40, 150, 20);
        this.add(rseultBuyLabel);




        movieNameLabel.setText("Movie Name : " + movieName  );
        movieNameLabel.setBounds(90, 110, 250, 10);
        this.add(movieNameLabel);



        seatNumberLabel.setText("Seat Number You Had : " + seatNumber);
        seatNumberLabel.setBounds(90, 180, 250, 10);
        this.add(seatNumberLabel);


        sansTimeLabel.setText("Sans Time You Had : "+ sansTime);
        sansTimeLabel.setBounds(90, 250, 250, 10);
        this.add(sansTimeLabel);


        priceLabel.setText("How Much You Pay For Ticket : " + howmuchPay);
        priceLabel.setBounds(90, 320, 250, 10);
        this.add(priceLabel);


    }



    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == stop) {
            parentFrame.showStore();

        }
    }



}