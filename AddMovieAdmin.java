import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class AddMovieAdmin extends JPanel implements ActionListener {

    private JButton start, stop;
    private BufferedImage pictureBackLeft;
    private JTextField textFieldName,textFieldAuthor,textFieldQuantity,textFieldPrice,textFieldPath;
    private JLabel titleLabel,quantityLabel,pathLabel,priceLabel,authorLabel,picLabelBackLeft;
    private AppFrame parentFrame;
    public String strNameMovie,strDirectorName,strQuantity,strPath,strPrice;


    public AddMovieAdmin(AppFrame parentFrame){


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





        textFieldName = new HintTextField("Enter Name Movie");
        textFieldName.setColumns(20);
        textFieldName.setBounds(90, 60, 210, 30);
        this.add(textFieldName);


        textFieldAuthor = new HintTextField("Enter Director Name");
        textFieldAuthor.setColumns(20);
        textFieldAuthor.setBounds(90, 130, 210, 30);
        this.add(textFieldAuthor);



        textFieldPrice = new HintTextField("Enter Price Ticket Cinema");
        textFieldPrice.setColumns(20);
        textFieldPrice.setBounds(90, 200, 210, 30);
        this.add(textFieldPrice);



        textFieldQuantity = new HintTextField("Enter Quantity Ticket Cinema");
        textFieldQuantity.setColumns(20);
        textFieldQuantity.setBounds(90, 270, 210, 30);
        this.add(textFieldQuantity);


        textFieldPath = new HintTextField("Enter Path Picture Movie");
        textFieldPath.setColumns(20);
        textFieldPath.setBounds(90, 340, 210, 30);
        this.add(textFieldPath);


        // Adjust the size and color of the stop button
        stop = new JButton("Add");
        stop.setPreferredSize(new Dimension(100, 75));
        stop.setBounds(90, 430, 210, 30);
        stop.setBackground(Color.decode("#6D7B8D"));
        stop.addActionListener(this);
        this.add(stop);


        titleLabel.setText("Name Movie");
        titleLabel.setBounds(90, 40, 150, 10);
        this.add(titleLabel);




        authorLabel.setText("Director Name");
        authorLabel.setBounds(90, 110, 150, 10);
        this.add(authorLabel);



        priceLabel.setText("Price Movie");
        priceLabel.setBounds(90, 180, 150, 10);
        this.add(priceLabel);


        quantityLabel.setText("Quantity Ticket");
        quantityLabel.setBounds(90, 250, 150, 10);
        this.add(quantityLabel);


        pathLabel.setText("Path Picture Movie");
        pathLabel.setBounds(90, 320, 150, 10);
        this.add(pathLabel);


        picLabelBackLeft = new JLabel();
        try {
            pictureBackLeft = ImageIO.read(new File("ImageApp/backLeft.jpg"));
            picLabelBackLeft = new JLabel(new ImageIcon(pictureBackLeft));
            picLabelBackLeft.setBounds(25, 25, 15, 15);
            this.add(picLabelBackLeft);

            picLabelBackLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    parentFrame.showStoreAdmin();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }




    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == stop) {
            if(textFieldName.getText().isEmpty()||
                    textFieldAuthor.getText().isEmpty()||
                    textFieldQuantity.getText().isEmpty()||
                    textFieldPrice.getText().isEmpty()||
                    textFieldPath.getText().isEmpty()){
                System.out.println("empty!");
            }else{
                strNameMovie = textFieldName.getText();
                strDirectorName = textFieldAuthor.getText();
                strPrice = textFieldPrice.getText();
                strQuantity = textFieldQuantity.getText();
                strPath = textFieldPath.getText();

                CSVManager.addNewBook(strNameMovie, strDirectorName, strPrice, strQuantity, strPath);


                parentFrame.showStoreAdmin();
            }

        }
    }



}
