import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage extends JPanel implements ActionListener {

    private JButton start, stop;
    private JTextField textField, textFieldPass;
    private JLabel accLabel, userLabel, passLabel, loginLabel;
    private AppFrame parentFrame;

    private String line = "";
    private String splitBy = ",";

    private static String userNameLogin;
    private JRadioButton checkLoginAdminRadio;


    public LoginPage(AppFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.decode("#BCC6CC"));
        this.setOpaque(true);
        this.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        textField = new HintTextField("Enter Username");
        textField.setColumns(20);
        textField.setBounds(90, 180, 210, 30);
        this.add(textField);

        textFieldPass = new HintTextField("Enter Password");
        textFieldPass.setColumns(20);
        textFieldPass.setBounds(90, 250, 210, 30);
        this.add(textFieldPass);


        start = new JButton("Sign In");
        start.setPreferredSize(new Dimension(100, 30));
        start.setBounds(90, 300, 210, 30);
        start.setBackground(Color.decode("#F67280"));
        start.addActionListener(this);
        this.add(start);

        stop = new JButton("Register");
        stop.setPreferredSize(new Dimension(100, 75));
        stop.setBounds(90, 370, 210, 30);
        stop.setBackground(Color.decode("#6D7B8D"));
        stop.addActionListener(this);
        this.add(stop);


        Font heading = new Font("Times Roman", Font.PLAIN, 28);
        loginLabel = new JLabel("Login");
        loginLabel.setFont(heading);
        loginLabel.setBounds(165, 80, 150, 40);
        this.add(loginLabel);

        userLabel = new JLabel("Username");
        userLabel.setBounds(90, 160, 150, 10);
        this.add(userLabel);

        passLabel = new JLabel("Password");
        passLabel.setBounds(90, 230, 150, 10);
        this.add(passLabel);

        accLabel = new JLabel("Don't have an account");
        accLabel.setBounds(90, 350, 150, 10);
        this.add(accLabel);

        checkLoginAdminRadio = new JRadioButton("  if you are Admin check it!");
        checkLoginAdminRadio.setBounds(90,430,210,30);
        checkLoginAdminRadio.setBackground(Color.ORANGE);
        this.add(checkLoginAdminRadio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            if (!textField.getText().isEmpty() && !textFieldPass.getText().isEmpty()) {
                checkLogin("DataBase/User.csv");
                checkLogin("DataBase/Admin.csv");
            }
        } else if (e.getSource() == stop) {
            parentFrame.showRegister();
        }
    }

    private void checkLogin(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                if (employee.length >= 4 && employee[1].equalsIgnoreCase(textField.getText()) && employee[3].equalsIgnoreCase(textFieldPass.getText())) {
                    userNameLogin = employee[1];
                    if (fileName.equals("DataBase/Admin.csv")) {
                        parentFrame.showStoreAdmin();
                    } else {
                        parentFrame.showStore();
                    }
                    return;
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
    }


    public static String getUserNameLogin() {
        return userNameLogin;
    }


}
