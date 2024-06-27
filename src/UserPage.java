import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.PreparedStatement;
public class UserPage extends JFrame implements  ActionListener{
    Connection connection;
    private String full_name;
    private String email_id;
    private String account_no;
    private JButton back;
    private String account_type;
    private String gender;
    private String Phone;
    private String DOB;
    private String AccNum,AccType,date,pin,Balance;
    private JButton credit ,debit,exit ;
    public UserPage(Connection conection , String email){
        this.connection=conection;
        String req_query ="Select * From User where email =?";
        String req_query2="Select * From account where email =?";
        try{
            System.out.println("Database");
            PreparedStatement statement = connection.prepareStatement(req_query);
            PreparedStatement statement2 = connection.prepareStatement(req_query2);
            statement.setString(1,email);
            statement2.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            ResultSet resultSet2 =statement2.executeQuery();
            if(resultSet.next()&&resultSet2.next()){
            full_name= resultSet.getString("name");
            email_id =resultSet.getString("email");
            gender =resultSet.getString("Gen");
            Phone =resultSet.getString("MOB");
            DOB=resultSet.getString("DOB");
            AccNum=resultSet2.getString("AcNum");
            AccType=resultSet2.getString("AccType");
            date=resultSet2.getString("DOI");
            pin=resultSet2.getString("Pin");
            Balance =resultSet2.getString("Bal");
            userForm();
            System.out.println("FORM");}
            else{
                System.out.println("Something went wrong");
            }

        } catch (SQLException k){
            System.out.println("Error...");
            k.printStackTrace();
        }
    }
    public void userForm(){
        setBounds(300, 90, 900, 600);
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

        setTitle(full_name);
       JLabel Heading =new JLabel(" Money-Bank");
        Heading.setFont(new Font("Arial",Font.BOLD,70));
        Heading.setForeground(Color.white);
//        Heading.setHorizontalAlignment(SwingConstants.CENTER);
        Heading.setSize(300, 80);
        Heading.setBounds(150,150,1000,100);
        c.add(Heading);


        JLabel name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 25));
        name.setForeground(Color.RED);
        name.setSize(100, 25);
        name.setLocation(100, 300);
        c.add(name);
        JTextField tname = new JTextField(full_name);
        tname.setFont(new Font("Arial", Font.PLAIN, 25));
        tname.setEditable(false);
        tname.setSize(330, 30);
        tname.setLocation(200, 300);
        c.add(tname);

        JLabel email = new JLabel("E-mail");
        email.setFont(new Font("Arial", Font.PLAIN, 25));
        email.setSize(100, 25);
        email.setForeground(Color.RED);
        email.setLocation(100, 350);
        c.add(email);
        //E-mail - INPUT
        JTextField temail = new JTextField(email_id);
        temail.setFont(new Font("Arial", Font.PLAIN, 25));
        temail.setEditable(false);
        temail.setSize(330, 30);
        temail.setLocation(200, 350);
        c.add(temail);

        JLabel Gender = new JLabel("Gender");
        Gender.setForeground(Color.RED);
        Gender.setFont(new Font("Arial", Font.PLAIN, 25));
        Gender.setSize(100, 25);
        Gender.setLocation(100, 400);
        c.add(Gender);

        JTextField tgen=new JTextField(gender);
        tgen.setFont(new Font("Arial", Font.PLAIN, 25));
        tgen.setEditable(false);
        tgen.setSize(100, 25);
        tgen.setLocation(200, 400);
        c.add(tgen);

       JLabel dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.PLAIN, 25));
        dob.setForeground(Color.RED);
        dob.setSize(150, 25);
        dob.setLocation(100, 450);
        c.add(dob);

        JTextField DMY = new JTextField(DOB);
        DMY.setFont(new Font("Arial", Font.PLAIN, 25));
        DMY.setEditable(false);
        DMY.setSize(150, 25);
        DMY.setLocation(200, 450);
        c.add(DMY);

        JLabel mno = new JLabel("Mobile");
        mno.setFont(new Font("Arial", Font.PLAIN, 25));
        mno.setForeground(Color.RED);
        mno.setSize(100, 25);
        mno.setLocation(100, 500);
        c.add(mno);

        JTextField tmno = new JTextField(Phone);
        tmno.setFont(new Font("Arial", Font.PLAIN, 25));
        tmno.setEditable(false);
        tmno.setSize(150, 25);
        tmno.setLocation(200, 500);
        c.add(tmno);


        JLabel AcNum = new JLabel("AccNum");
        AcNum.setFont(new Font("Arial", Font.PLAIN, 25));
        AcNum.setForeground(Color.RED);
        AcNum.setSize(100, 25);
        AcNum.setLocation(600, 300);
        c.add(AcNum);

        JTextField acc= new JTextField(AccNum);
        acc.setFont(new Font("Arial", Font.PLAIN, 25));
        acc.setEditable(false);
        acc.setSize(150, 30);
        acc.setLocation(760, 300);
        c.add(acc);

        JLabel AcType = new JLabel("AccType");
        AcType.setFont(new Font("Arial", Font.PLAIN, 25));
        AcType.setForeground(Color.RED);
        AcType.setSize(100, 25);
        AcType.setLocation(600, 350);
        c.add(AcType);

        JTextField typea= new JTextField(AccType);
        typea.setFont(new Font("Arial", Font.PLAIN, 25));
        typea.setEditable(false);
        typea.setSize(150, 30);
        typea.setLocation(760, 350);
        c.add(typea);

        JLabel Bal=new JLabel("Balance");
        Bal.setFont(new Font("Arial",Font.PLAIN,25));
        Bal.setForeground(Color.RED);
        Bal.setSize(100, 25);
        Bal.setLocation(600, 400);
        c.add(Bal);

        JTextField bal= new JTextField(Balance);
        bal.setFont(new Font("Arial", Font.PLAIN, 25));
        bal.setEditable(false);
        bal.setSize(150, 30);
        bal.setLocation(760, 400);
        c.add(bal);

        JLabel DOI=new JLabel("Open-Date");
        DOI.setFont(new Font("Arial",Font.PLAIN,25));
        DOI.setForeground(Color.RED);
        DOI.setSize(150, 25);
        DOI.setLocation(600, 450);
        c.add(DOI);

        JTextField doi= new JTextField(date);
        doi.setFont(new Font("Arial", Font.PLAIN, 25));
        doi.setEditable(false);
        doi.setSize(150, 30);
        doi.setLocation(760, 450);
        c.add(doi);


        back = new JButton("<< Back");
        back.setBorderPainted(false);
        back.setBorder(new LineBorder(Color.BLACK));
        back.setFont(new Font("Arial", Font.PLAIN, 20));
        back.setSize(100, 50);
        back.setLocation(60, 60);
        back.addActionListener(this);
        c.add(back);

        credit = new JButton("Credit++");
        credit.setBorderPainted(false);
        credit.setBorder(new LineBorder(Color.BLACK));
        credit.setFont(new Font("Arial", Font.PLAIN, 20));
        credit.setSize(100, 50);
        credit.setLocation(1000, 300);
        credit.addActionListener(this);
        c.add(credit);

        debit = new JButton("Debit--");
        debit.setBorderPainted(false);
        debit.setBorder(new LineBorder(Color.BLACK));
        debit.setFont(new Font("Arial", Font.PLAIN, 20));
        debit.setSize(100, 50);
        debit.setLocation(1000, 400);
        debit.addActionListener(this);
        c.add(debit);

        exit = new JButton("Exit");
        exit.setBorderPainted(false);
        exit.setBorder(new LineBorder(Color.BLACK));
        exit.setFont(new Font("Arial", Font.PLAIN, 20));
        exit.setSize(100, 50);
        exit.setLocation(1000, 500);
        exit.addActionListener(this);
        c.add(exit);



    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==credit){
            Credit c=new Credit(email_id,AccNum,connection);
            c.setVisible(true);
        }


        else if(e.getSource()==debit){
            Debit d=new Debit(email_id,AccNum,connection);
            d.setVisible(true);
        }
        else if(e.getSource()==exit){
            System.exit(0);
        }

    }
}

