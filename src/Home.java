import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;

class HomeFrame extends JFrame implements ActionListener{

    Connection connection;
    private JLabel Heading;
    private JLabel SubHeading,SubHeading2,SubHeading3;
    private ImageIcon bg;
    private JButton in ,up,db;

    public HomeFrame (Connection c){
        this.connection =c;
        setTitle("Home Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 90, 1000, 1000);
//        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\Dell\\Desktop\\BankSystem\\Photos\\BankBg.jpg");
                // Draw the image at the specified location
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);

            }};
        getContentPane().add(panel);
        panel.setLayout(null);

        Heading =new JLabel("Welcome To MoneyBase");
        Heading.setFont(new Font("Arial",Font.BOLD,70));
        Heading.setForeground(Color.white);
//        Heading.setHorizontalAlignment(SwingConstants.CENTER);
        Heading.setSize(300, 100);
        Heading.setBounds(50,50,1000,100);
        panel.add(Heading);

        SubHeading =new JLabel("Fast and Simple ");
        SubHeading.setFont(new Font("Arial",Font.BOLD,65));
        SubHeading.setForeground(Color.ORANGE);
        SubHeading.setSize(300,100);
        SubHeading.setBounds(150,200,1000,100);
        panel.add(SubHeading);

        SubHeading2 =new JLabel(" Digital Payment ");
        SubHeading2.setFont(new Font("Arial",Font.BOLD,65));
        SubHeading2.setForeground(Color.ORANGE);
        SubHeading2.setSize(300,100);
        SubHeading2.setBounds(140,280,1000,100);
        panel.add(SubHeading2);
        SubHeading3 =new JLabel(" Solution.");
        SubHeading3.setFont(new Font("Arial",Font.BOLD,65));
        SubHeading3.setForeground(Color.ORANGE);
        SubHeading3.setSize(300,100);
        SubHeading3.setBounds(140,360,1000,100);
        panel.add(SubHeading3);


        in = new  JButton("Login");
        in.setFont(new Font("Arial", Font.PLAIN, 15));
        in.setSize(100, 30);
        in.setLocation(170, 500);
        in.addActionListener(this);
        panel.add(in);

        up = new  JButton("Signup");
        up.setFont(new Font("Arial", Font.PLAIN, 15));
        up.setSize(100, 30);
        up.setLocation(300, 500);
        up.addActionListener(this);
        panel.add(up);

        db = new  JButton("LOC");
        db.setFont(new Font("Arial", Font.PLAIN, 15));
        db.setSize(100, 30);
        db.setLocation(430, 500);
        db.addActionListener(this);
        panel.add(db);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==up) {
            SignUP signup = new SignUP(connection);
            signup.setVisible(true);
        }
        if(e.getSource()==db){
            DataBase d=new DataBase(connection);
            d.setVisible(true);
        }

        else{
            Login login =new Login(connection);
            login.setVisible(true);
        }
    }
}

public class Home {
    private static final String url = "jdbc:mysql://localhost:3306/bankdb";
    private static final String username = "root";
    private static final String password = "xxxxx";

        public static void main(String[] args) throws ClassNotFoundException, SQLException {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch (ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
            try{
                Connection connection = DriverManager.getConnection(url, username, password);
                System.out.println("connected");
                SwingUtilities.invokeLater(() -> {
                    HomeFrame form = new HomeFrame(connection);
                    form.setVisible(true);
                });
            }
            catch(SQLException e){
                System.out.println("error in ");
                e.printStackTrace();
            }
    }
}
