
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    Connection connection;

    public DataBase(Connection connection) {
        this.connection=connection;
        setTitle("Database Table ");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane);

        // Connect to the database and fetch data
        connectAndPopulateTable();

        setVisible(true);
    }

    private void connectAndPopulateTable() {
        try {
            // Connect to your database
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");

            // Create a statement
            Statement stmt = connection.createStatement();

            // Execute a query
            ResultSet rs = stmt.executeQuery("SELECT * from account");

            // Get metadata about the result set (column names)
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rs.getMetaData().getColumnName(i));
            }

            // Populate the table with data
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            // Close the resources
//            rs.close();
//            stmt.close();
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
