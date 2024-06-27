import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Debit extends JFrame implements ActionListener {
    private JTextField am,tpin;
    private JButton in,cancel;
    Connection connect;
    private String email,acc;


    public  Debit(String email, String acc, Connection connection){
        this.email=email;
        this.acc=acc;
        this.connect=connection;
        setBounds(300, 90, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel c = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\Dell\\Desktop\\BankSystem\\Photos\\back.jpg");
                // Draw the image at the specified location
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);

            }
        };
        getContentPane().add(c);
        c.setLayout(null);
        setResizable(false);

        JLabel Heading =new JLabel("Debit");
        Heading.setFont(new Font("Arial",Font.BOLD,30));
        Heading.setForeground(Color.white);
//        Heading.setHorizontalAlignment(SwingConstants.CENTER);
        Heading.setBounds(50,50,1000,50);
        c.add(Heading);


        JLabel Amount = new JLabel("Amount");
        Amount.setFont(new Font("Arial", Font.PLAIN, 20));
        Amount.setSize(100, 20);
        Amount.setForeground(Color.ORANGE);
        Amount.setLocation(60, 100);
        c.add(Amount);
        //E-mail - INPUT
        am = new JTextField();
        am.setFont(new Font("Arial", Font.PLAIN, 20));
        am.setSize(300, 20);
        am.setLocation(60, 130);
        c.add(am);


        JLabel securitypin =new JLabel("Pin");
        securitypin.setFont(new Font("Arial", Font.PLAIN, 20));
        securitypin.setForeground(Color.ORANGE);
        securitypin.setSize(200, 20);
        securitypin.setLocation(60, 160);
        c.add(securitypin);

        tpin = new JPasswordField();
        tpin.setFont(new Font("Arial", Font.PLAIN, 20));
        tpin.setSize(300, 20);
        tpin.setLocation(60, 190);
        c.add(tpin);

        in = new  JButton("Debit");
        in.setBorderPainted(false);
        in.setBorder(new LineBorder(Color.BLACK));
        in.setFont(new Font("Arial", Font.PLAIN, 25));
        in.setSize(150, 30);
        in.setLocation(100, 250);
        in.addActionListener(this);
        c.add(in);

        cancel = new JButton("Cancel");
        cancel.setBorderPainted(false);
        cancel.setBorder(new LineBorder(Color.BLACK));
        cancel.setFont(new Font("Arial", Font.PLAIN, 25));
        cancel.setSize(150, 30);
        cancel.setLocation(300, 250);
        cancel.addActionListener(this);
        c.add(cancel);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==in){
            String acc_query = "SELECT * FROM account WHERE email = ? && AcNum=? && Pin=? ";
            BigDecimal Amount=new BigDecimal(am.getText());
            String pin=tpin.getText();
            try {
                PreparedStatement preparedStatement = connect.prepareStatement(acc_query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, acc);
                preparedStatement.setString(3,pin );
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Account founded");
                    BigDecimal balance=new BigDecimal(resultSet.getString("Bal"));
                    System.out.println(balance);
                    int comp =balance.compareTo(Amount);
                    if(comp<0) JOptionPane.showMessageDialog(null, "Insufficient Balance", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    else{
                    String sum = String.valueOf(balance.subtract(Amount));
                    String up_query="Update account SET Bal=? where email=? && AcNum=? && Pin=?";
                    try{
                        PreparedStatement preparedStatement2 = connect.prepareStatement(up_query);
                        preparedStatement2.setString(1, sum);
                        preparedStatement2.setString(2, email);
                        preparedStatement2.setString(3, acc);
                        preparedStatement2.setString(4, pin);
                        int affectedRows = preparedStatement2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Your Account has been Debited successfully", "Alert", JOptionPane.INFORMATION_MESSAGE);
                        UserPage u =new UserPage(connect,email);
                        u.setVisible(true);
                    }catch (SQLException k) {
                        k.printStackTrace();
                    }
                    }
            } else{
                    System.out.println(email);
                    System.out.println(acc);
                    System.out.println(pin);
                    JOptionPane.showMessageDialog(null, "Wrong Details", "Alert", JOptionPane.INFORMATION_MESSAGE);
                }}
                catch (SQLException k){
                k.printStackTrace();
            }
    } else if(e.getSource()==cancel)dispose();
    }}
