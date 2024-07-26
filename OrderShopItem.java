import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class OrderShopItem extends JPanel implements ActionListener {

    private JButton stop;
    private BufferedImage pictureBackLeft;
    private JTextField textFieldAddress,textFieldPassword2,textFieldCard,textFieldCvv2,textFieldPhoneNumber,textFieldUsername;
    private JLabel titleLabel,quantityLabel,pathLabel,priceLabel,authorLabel,picLabelBackLeft,phoneLabel;
    private AppFrame parentFrame;
    public String strCard,strAddress,strCvv2,strPassword2,strPhoneNumber,strUsername;




    public OrderShopItem(AppFrame parentFrame){


        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel();
        titleLabel = new JLabel();
        pathLabel = new JLabel();
        priceLabel= new JLabel();
        quantityLabel = new JLabel();
        authorLabel =new JLabel();
        picLabelBackLeft = new JLabel();
        phoneLabel = new JLabel();



        //textField
        textFieldCard = new HintTextField("Enter Card Number");
        textFieldCard.setColumns(20);
        textFieldCard.setBounds(90, 60, 210, 30);
        this.add(textFieldCard);


        textFieldCvv2 = new HintTextField("Enter CVV2");
        textFieldCvv2.setColumns(20);
        textFieldCvv2.setBounds(90, 130, 80, 30);
        this.add(textFieldCvv2);

        textFieldPassword2 = new HintTextField("Enter P2");
        textFieldPassword2.setColumns(20);
        textFieldPassword2.setBounds(200, 130, 80, 30);
        this.add(textFieldPassword2);


        textFieldAddress = new HintTextField("Enter place Address");
        textFieldAddress.setColumns(20);
        textFieldAddress.setBounds(90, 200, 210, 30);
        this.add(textFieldAddress);



        textFieldPhoneNumber = new HintTextField("Enter Phone Number");
        textFieldPhoneNumber.setColumns(20);
        textFieldPhoneNumber.setBounds(90, 270, 210, 30);
        this.add(textFieldPhoneNumber);


        textFieldUsername = new HintTextField("Enter Username");
        textFieldUsername.setColumns(20);
        textFieldUsername.setBounds(90, 340, 210, 30);
        this.add(textFieldUsername);





        stop = new JButton("Pay");
        stop.setPreferredSize(new Dimension(100, 75));
        stop.setBounds(90, 430, 210, 30);
        stop.setBackground(Color.decode("#6D7B8D"));
        stop.addActionListener(this);
        this.add(stop);




        titleLabel.setText("Card Number");
        titleLabel.setBounds(90, 40, 150, 10);
        this.add(titleLabel);




        authorLabel.setText("CVV2");
        authorLabel.setBounds(90, 110, 80, 10);
        this.add(authorLabel);



        priceLabel.setText("Password2");
        priceLabel.setBounds(200, 110, 80, 10);
        this.add(priceLabel);


        quantityLabel.setText("Address");
        quantityLabel.setBounds(90, 180, 150, 10);
        this.add(quantityLabel);


        phoneLabel.setText("Phone Number");
        phoneLabel.setBounds(90, 250, 150, 10);
        this.add(phoneLabel);

        pathLabel.setText("Username");
        pathLabel.setBounds(90, 320, 150, 10);
        this.add(pathLabel);

        try {
            //  pictureShopping = ImageIO.read(new File("shopping.jpg"));
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




    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == stop) {
            if(textFieldPassword2.getText().isEmpty()||
                    textFieldCard.getText().isEmpty()||
                    textFieldCvv2.getText().isEmpty()||
                    textFieldAddress.getText().isEmpty()||
                    textFieldUsername.getText().isEmpty()||
                    textFieldPhoneNumber.getText().isEmpty()){
                System.out.println("empty!");
            }else{
                strCard = textFieldCard.getText();
                strAddress = textFieldAddress.getText();
                strCvv2 = textFieldCvv2.getText();
                strPassword2 = textFieldPassword2.getText();
                strPhoneNumber = textFieldPhoneNumber.getText();
                strUsername = textFieldUsername.getText();

                CSVManager.addNewBuy(strCard, strAddress , strCvv2, strPassword2, strPhoneNumber,strUsername);


                parentFrame.getResultBuyPage().refreshBooks();

                parentFrame.showResultBuyPage();
            }

        }
    }



}