import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
public class Login extends JFrame implements ActionListener {
    Connection connection;
    private JLabel title;
    private JLabel email;
    private JTextField temail;
    private JLabel pass;
    private JPasswordField tpass;
    private JButton in,back;


    public Login (Connection connection ){
        this.connection=connection;
        System.out.println(connection);
        setTitle("Login");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel c = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\Dell\\Desktop\\BankSystem\\Photos\\BankBg.jpg");
                // Draw the image at the specified location
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);

            }};

        getContentPane().add(c);
        c.setLayout(null);
        //   TITTLE
        title = new JLabel("Login Form");
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.white);
        title.setSize(1000, 100);
        title.setLocation(250, 140);
        c.add(title);
        // NAME

        // E-mail
        email = new JLabel("E-mail");
        email.setFont(new Font("Arial", Font.PLAIN, 30));
        email.setSize(100, 30);
        email.setForeground(Color.ORANGE);
        email.setLocation(250, 250);
        c.add(email);
        //E-mail - INPUT
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 25));
        temail.setSize(300, 30);
        temail.setLocation(250, 300);
        c.add(temail);
//
//        //Password
        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.PLAIN, 30));
        pass.setSize(130, 30);
        pass.setForeground(Color.ORANGE);
        pass.setLocation(250, 350);
        c.add(pass);
        //Password-INPUT
        tpass = new JPasswordField(8);
        tpass.setFont(new Font("Arial", Font.PLAIN, 25));
        tpass.setSize(300, 30);
        tpass.setLocation(250, 400);
        c.add(tpass);

        //Login
        in = new  JButton("Login");
        in.setBorderPainted(false);
        in.setBorder(new LineBorder(Color.BLACK));
        in.setFont(new Font("Arial", Font.PLAIN, 25));
        in.setSize(150, 30);
        in.setLocation(300, 500);
        in.addActionListener(this);
        c.add(in);

        back = new JButton("<< Back");
        back.setBorderPainted(false);
        back.setBorder(new LineBorder(Color.BLACK));
        back.setFont(new Font("Arial", Font.PLAIN, 20));
        back.setSize(100, 50);
        back.setLocation(60, 60);
        back.addActionListener(this);
        c.add(back);

        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == in) {
                String acc_query = "SELECT * FROM account WHERE email = ?";
                String email = temail.getText();
                String password = new String(tpass.getPassword());
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(acc_query);
                    preparedStatement.setString(1, email);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()){
                        System.out.println("Account founded");
                        String login_query = "SELECT * FROM User WHERE email = ? AND password = ?";
                        try {
                            PreparedStatement preparedStatement1 = connection.prepareStatement(login_query);
                            preparedStatement1.setString(1, email);
                            preparedStatement1.setString(2, password);
                            ResultSet resultSet1 = preparedStatement1.executeQuery();
                            if (resultSet1.next()) {
                                System.out.println("Email founded");
                                UserPage up = new UserPage(connection, email);
                                up.setVisible(true);
                            } else {
                                System.out.println("Email not found");
                            }
                        } catch (SQLException k) {
                            k.printStackTrace();
                        }
                    } else {
                        System.out.println("Account not founded");
                        JOptionPane.showMessageDialog(null, "Create an Account ", "Alert", JOptionPane.INFORMATION_MESSAGE);
                        Account acc = new Account(connection);
                        acc.setVisible(true);

                    }
                } catch (SQLException k) {
                    k.printStackTrace();
                }
            } else if (e.getSource()==back){
                dispose();
            }
        }  }

