import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import  java.util.Random;

public class Account extends JFrame implements ActionListener {

    String full_name;
    Double Balance=0.00;
    JButton sub,back;
    JLabel email;
    JTextField temail;
    JLabel securitypin;
    JPasswordField tpin;
    JComboBox Account;
    Connection connection;
    private String Types[]={"Saving","Current"};


    public Account(Connection conection) {
        this.connection=conection;
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

            }
        };

        getContentPane().add(c);
        c.setLayout(null);

       JLabel title = new JLabel("Account  Form ");
        title.setFont(new Font("Arial", Font.BOLD, 70));
        title.setForeground(Color.white);
        title.setSize(1000, 100);
        title.setLocation(200, 200);
        c.add(title);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 30));
        email.setForeground(Color.ORANGE);
        email.setSize(100, 30);
        email.setLocation(200, 350);
        c.add(email);
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 25));
        temail.setSize(300, 30);
        temail.setLocation(300, 350);
        c.add(temail);

        securitypin =new JLabel("Pin");
        securitypin.setFont(new Font("Arial", Font.PLAIN, 30));
        securitypin.setForeground(Color.ORANGE);
        securitypin.setSize(200, 30);
        securitypin.setLocation(200, 400);
        c.add(securitypin);

        tpin = new JPasswordField();
        tpin.setFont(new Font("Arial", Font.PLAIN, 30));
        tpin.setSize(300, 30);
        tpin.setLocation(300, 400);
        c.add(tpin);

        JLabel acc_type =new JLabel("Acc_Type");
        acc_type.setFont(new Font("Arial", Font.PLAIN, 30));
        acc_type.setForeground(Color.ORANGE);
        acc_type.setSize(200, 30);
        acc_type.setLocation(200, 450);
        c.add(acc_type);

         Account =new JComboBox(Types);
        Account.setFont(new Font("Arial", Font.PLAIN, 25));
        Account.setSize(180, 30);
        Account.setLocation(400, 450);
        c.add(Account);

        sub = new  JButton("SUBMIT");
        sub.setBorderPainted(false);
        sub.setBorder(new LineBorder(Color.BLACK));
        sub.setFont(new Font("Arial", Font.PLAIN, 25));
        sub.setSize(150, 50);
        sub.setLocation(300, 550);
        sub.addActionListener(this);
        c.add(sub);
        back = new JButton("<< Back");
        back.setBorderPainted(false);
        back.setBorder(new LineBorder(Color.BLACK));
        back.setFont(new Font("Arial", Font.PLAIN, 20));
        back.setSize(100, 50);
        back.setLocation(60, 60);
        back.addActionListener(this);
        c.add(back);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==sub){
            String email= temail.getText();
            String pin = new String(tpin.getPassword());
            if(pin.length()==4) {
                String acc_type =(String)Account.getSelectedItem();
                String Acc_Num=new String(String.valueOf( randomAcc()));
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String Date =currentDate.format(formatter);
                String Bal = new String(String.valueOf(Balance));
                String register_query = "INSERT INTO account( AcNum,email,Pin,Bal,AccType,DOI) VALUES(?,?,?,?,?,?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(register_query);
                    preparedStatement.setString(1, Acc_Num);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, pin);
                    preparedStatement.setString(4, Bal);
                    preparedStatement.setString(5, acc_type);
                    preparedStatement.setString(6, Date);
                    int affectedRows = preparedStatement.executeUpdate();
                    System.out.println("Successfully created a new account");
                    JOptionPane.showMessageDialog(null, "Your Account is created", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    UserPage up = new UserPage(connection, email);
                    up.setVisible(true);
                } catch (SQLException k) {
                    System.out.println("erroracc");
                    k.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pin length should be four ", "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if (e.getSource()==back){
            dispose();
        }
        }
        public int randomAcc(){
             Random acc= new Random();
        return acc.nextInt(9000)+10000;
}
}
