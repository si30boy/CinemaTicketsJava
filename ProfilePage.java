import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

public class ProfilePage extends JPanel implements ActionListener {
    private JButton stop;
    private BufferedImage pictureBackLeft;
    private JTextField textFieldEmail, textFieldPassword,textFieldUsername;
    private JLabel usernameLabel, passwordLabel, emailLabel,picLabelBackLeft;
    private AppFrame parentFrame;
    private String splitBy = ",";
    public String strName,strUser,strEmail,strPass;

    public ProfilePage(AppFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textFieldUsername = new HintTextField("Enter New Username");
        textFieldUsername.setColumns(20);
        textFieldUsername.setBounds(90, 90, 210, 30);
        this.add(textFieldUsername);


        textFieldPassword= new HintTextField("Enter New Password");
        textFieldPassword.setColumns(20);
        textFieldPassword.setBounds(90, 190, 210, 30);
        this.add(textFieldPassword);

        textFieldEmail = new HintTextField("Enter New Email");
        textFieldEmail.setColumns(20);
        textFieldEmail.setBounds(90, 290, 210, 30);
        this.add(textFieldEmail);

        stop = new JButton("Ok");
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

        loadUserData();
    }

    private void loadUserData() {
        String username = LoginPage.getUserNameLogin();
        try (BufferedReader br = new BufferedReader(new FileReader("DataBase/User.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] user = line.split(splitBy);

                if (user.length >= 4 && user[1].equalsIgnoreCase(username)) {

                    usernameLabel = new JLabel("Your Last Username : " + user[1]);
                    usernameLabel.setBounds(90, 50, 210, 20);
                    this.add(usernameLabel);

                    passwordLabel = new JLabel("Your Last Password : " + user[3]);
                    passwordLabel.setBounds(90, 150, 210, 20);
                    this.add(passwordLabel);

                    emailLabel = new JLabel("Your Last Email : " + user[2]);
                    emailLabel.setBounds(90, 250, 350, 20);
                    this.add(emailLabel);

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stop) {

            strName = "null";
            strUser = textFieldUsername.getText();
            strEmail = textFieldEmail.getText();
            strPass = textFieldPassword.getText();
            parentFrame.showStore();


            CSVManager.addNewUser(strName,strUser,strEmail,strPass);
        }
    }
}
