import javax.swing.*;
import java.awt.event.*;
import java.awt.*;  

public class RegisterPage extends JPanel implements ActionListener {
    
    private JButton stop;
    private JTextField textFieldUser,textFieldPass,textFieldEmail,textFieldName;
    private JLabel emailLabel,userLabel,passLabel,loginLabel,nameLabel;
    private AppFrame parentFrame;
    public String strName,strUser,strEmail,strPass;
    
  
    
    
    public RegisterPage(AppFrame parentFrame){


        this.parentFrame = parentFrame;
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       
        JLabel label = new JLabel();

        emailLabel = new JLabel();
        userLabel= new JLabel();
        passLabel = new JLabel();
        loginLabel =new JLabel();



        textFieldName = new HintTextField("Enter Fullname");
        textFieldName.setColumns(20);
        textFieldName.setBounds(90, 150, 210, 30);
        this.add(textFieldName);


        textFieldUser = new HintTextField("Enter Username");
        textFieldUser.setColumns(20);
        textFieldUser.setBounds(90, 220, 210, 30);
        this.add(textFieldUser);



        textFieldEmail = new HintTextField("Enter EmailAddress");
        textFieldEmail.setColumns(20);
        textFieldEmail.setBounds(90, 290, 210, 30);
        this.add(textFieldEmail);


        
        textFieldPass = new HintTextField("Enter Password");
        textFieldPass.setColumns(20);
        textFieldPass.setBounds(90, 360, 210, 30);
        this.add(textFieldPass);

        
       

        stop = new JButton("Sign Up");
        stop.setPreferredSize(new Dimension(100, 75));
        stop.setBounds(90, 430, 210, 30);
        stop.setBackground(Color.decode("#6D7B8D"));
        stop.addActionListener(this);
        this.add(stop);


        

        nameLabel = new JLabel();
        nameLabel.setText("FullName");
        nameLabel.setBounds(90, 130, 150, 10);
        this.add(nameLabel);
        
        
        

        emailLabel.setText("Email");
        emailLabel.setBounds(90, 270, 150, 10);
        this.add(emailLabel);



        passLabel.setText("Password");
        passLabel.setBounds(90, 340, 150, 10);
        this.add(passLabel);


        userLabel.setText("Username");
        userLabel.setBounds(90, 200, 150, 10);
        this.add(userLabel);
        

        loginLabel.setText("Sign Up");
        Font heading = new Font("Times Roman", Font.PLAIN, 28);
        loginLabel.setFont(heading);
        loginLabel.setBounds(145, 50, 150, 40);
        this.add(loginLabel);

        

    }
    
  

    
    public void actionPerformed(ActionEvent e) {
         

        if (e.getSource() == stop) {
            if(textFieldName.getText().isEmpty()||
                textFieldUser.getText().isEmpty()||
                    textFieldEmail.getText().isEmpty()||
                        textFieldPass.getText().isEmpty()){

            }else{
                strName = textFieldName.getText();
                strUser = textFieldUser.getText();
                strEmail = textFieldEmail.getText();
                strPass = textFieldPass.getText();
                parentFrame.showLogin();

                CSVManager.addNewUser(strName,strUser,strEmail,strPass);

            }
            
        }
    }   


     
}
