// Java program to implement
// a Simple Registration Form
// using Java Swing


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

class SignUP
        extends JFrame
        implements ActionListener  {

    // Components of the Form
//    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel mno;
    private JTextField tmno;
    private JLabel email;
    private JTextField temail;
    private JLabel terms;
    private JLabel pass;
    private JPasswordField tpass;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JButton back;
    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
    private String years[]
            = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019" };

    Connection connection;

    // constructor, to initialize the components
    // with default values.
    public SignUP (Connection connection ){
        this.connection=connection;
        System.out.println(connection);
        setTitle("Sign-Up");
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
        title = new JLabel("SignUP Form");
        title.setFont(new Font("Arial", Font.BOLD, 70));
        title.setForeground(Color.white);
        title.setSize(1000, 100);
        title.setLocation(250, 100);
        c.add(title);
        // NAME
        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 30));
        name.setForeground(Color.ORANGE);
        name.setSize(100, 30);
        name.setLocation(250, 230);
        c.add(name);
        //NAME-INPUT
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 25));
        tname.setSize(300, 30);
        tname.setLocation(370, 230);
        c.add(tname);
        // E-mail
        email = new JLabel("E-mail");
        email.setFont(new Font("Arial", Font.PLAIN, 30));
        email.setSize(100, 30);
        email.setForeground(Color.ORANGE);
        email.setLocation(250, 280);
        c.add(email);
        //E-mail - INPUT
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 25));
        temail.setSize(300, 30);
        temail.setLocation(370, 280);
        c.add(temail);
        gender = new JLabel("Gender");
        gender.setForeground(Color.ORANGE);
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 20);
        gender.setLocation(250, 330);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSelected(true);
        male.setSize(80, 20);
        male.setLocation(370, 330);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setSize(100, 20);
        female.setLocation(450, 330);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setForeground(Color.ORANGE);
        dob.setSize(100, 20);
        dob.setLocation(250, 380);
        c.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 20));
        date.setSize(50, 20);
        date.setLocation(370, 380);
        c.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(420, 380);
        c.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(480, 380);
        c.add(year);

        mno = new JLabel("Mobile");
        mno.setFont(new Font("Arial", Font.PLAIN, 20));
        mno.setForeground(Color.ORANGE);
        mno.setSize(100, 20);
        mno.setLocation(250, 430);
        c.add(mno);

        tmno = new JTextField(10);
        tmno.setFont(new Font("Arial", Font.PLAIN, 15));
        tmno.setSize(150, 20);
        tmno.setLocation(370, 430);
        c.add(tmno);
//
//        //Password
        pass = new JLabel("Password");
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setSize(130, 20);
        pass.setForeground(Color.ORANGE);
        pass.setLocation(250, 480);
        c.add(pass);
        //Password-INPUT
        tpass = new JPasswordField(8);
        tpass.setFont(new Font("Arial", Font.PLAIN, 20));
        tpass.setSize(230, 20);
        tpass.setLocation(370, 480);
        c.add(tpass);

        //Accept-terms
        term = new JCheckBox();
        term.setFont(new Font("Arial", Font.PLAIN, 25));
        term.setSize(20, 25);
        term.setLocation(250, 550);
        c.add(term);
        terms = new JLabel("Accept terms and conditions.");
        terms.setFont(new Font("Arial", Font.PLAIN, 25));
        terms.setForeground(Color.ORANGE);
        terms.setSize(500, 25);
        terms.setLocation(280, 550);
        c.add(terms);

//        //SUBMIT-BUTTON
        sub = new  JButton("SUBMIT");
        sub.setBorderPainted(false);
        sub.setBorder(new LineBorder(Color.BLACK));
        sub.setFont(new Font("Arial", Font.PLAIN, 25));
        sub.setSize(150, 50);
        sub.setLocation(250, 600);
        sub.addActionListener(this);
        c.add(sub);
//        //RESET-BUTTON
        reset = new JButton("Reset");
        reset.setBorderPainted(false);
        reset.setBorder(new LineBorder(Color.BLACK));
        reset.setFont(new Font("Arial", Font.PLAIN, 25));
        reset.setSize(150, 50);
        reset.setLocation(400, 600);
        reset.addActionListener(this);
        c.add(reset);
        back = new JButton("<< Back");
        back.setBorderPainted(false);
        back.setBorder(new LineBorder(Color.BLACK));
        back.setFont(new Font("Arial", Font.PLAIN, 20));
        back.setSize(100, 50);
        back.setLocation(60, 60);
        back.addActionListener(this);
        c.add(back);

    }
    public boolean user_exist(String email){

        String query = "SELECT * FROM user WHERE email = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String name = tname.getText();
            String password = new String(tpass.getPassword());
            String email = temail.getText();
            String mob=tmno.getText();
            String DOB = (String)date.getSelectedItem()+(String)month.getSelectedItem()+(String)year.getSelectedItem();
            String gen;
            if(mob.length()!=10) JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Alert", JOptionPane.INFORMATION_MESSAGE);
            if(password.length()>8) JOptionPane.showMessageDialog(null, "Password length should be less than 9", "Alert", JOptionPane.INFORMATION_MESSAGE);
            if (term.isSelected()&& mob.length()==10 && password.length()<=8) {
                if(male.isSelected()){
                     gen="Male";
                }
                else{
                    gen="Female";
                }
                if (user_exist(email)) {
                    JOptionPane.showMessageDialog(null, "Email Already Existed ", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Existed");
                    return;
                }
                String register_query = "INSERT INTO User(name, email, password,DOB,MOB,Gen) VALUES(?, ?, ?,?,?,?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(register_query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, password);
                    preparedStatement.setString(4, DOB);
                    preparedStatement.setString(5, mob);
                    preparedStatement.setString(6, gen);

                    int affectedRows = preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successfully Registered ", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Sucesfully entered");
                    Account acc=new Account(connection);
                    acc.setVisible(true);

                } catch (SQLException k) {
                    k.printStackTrace();
                }
            } else if(!term.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please check the terms and conditions ", "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource()==back) {
            dispose();

        } else if (e.getSource() == reset) {
                String def = "";
                tname.setText(def);
                tpass.setText(def);
                term.setSelected(false);
            }

    }
}

