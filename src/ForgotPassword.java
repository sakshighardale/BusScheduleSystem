import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForgotPassword extends JFrame implements ActionListener{
    JLabel label1,label2,user;
    JTextField username;
    JButton submit;
    JPasswordField password;
    JRadioButton passenger,conductor,driver;
    ButtonGroup userGrp;
    ForgotPassword()
    {
        Font font=new Font("segoe print",Font.BOLD,14);
        setBounds(540,230,400,350);
        setTitle("Register");
        user=new JLabel("User Type :");
        user.setFont(font);
        user.setBounds(10,20,100,30);
        add(user);
        conductor=new JRadioButton("Conductor");
        conductor.setFont(font);
        conductor.setBounds(110,20,100,30);
        add(conductor);
        driver=new JRadioButton("Driver");
        driver.setFont(font);
        driver.setBounds(210,20,80,30);
        add(driver);
        passenger=new JRadioButton("Passenger");
        passenger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(new String(password.getPassword()));
            }
        });
        passenger.setFont(font);
        passenger.setBounds(287,20,100,30);
        add(passenger);

        userGrp=new ButtonGroup();
        userGrp.add(passenger);
        userGrp.add(driver);
        userGrp.add(conductor);

        label1=new JLabel("Username :");
        label1.setFont(font);
        label1.setBounds(10,70,150,30);
        add(label1);

        username=new JTextField();
        username.setFont(font);
        username.setBounds(170,70,200,30);
        add(username);

        label2=new JLabel("New Password :");
        label2.setBounds(10,120,150,30);
        label2.setFont(font);
        add(label2);

        password=new JPasswordField();
        password.setFont(font);
        password.setEchoChar('*');
        password.setBounds(170,120,200,30);
        add(password);

        submit=new JButton("Update Password");
        submit.setFont(font);
        submit.setBounds(130,180,170,30);
        add(submit);
        //JFrame jfrme=new JFrame()
        //add()
        submit.addActionListener(this);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Forgot Password");
        setLayout(null);
        this.setVisible(true);
    }

//    public static void main(String[] args) {
//        new ForgotPassword();
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit)
        {
            String userName=username.getText();
            String passwordText= new String(password.getPassword());

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            catch (ClassNotFoundException m)
            {
                // System.out.println(m.showMessage());
            }
            try {

                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");
                if(passenger.isSelected()) {
                    PreparedStatement preparedStatement = connection.prepareStatement("update passenger_bus set password=? where username=?");
                    preparedStatement.setString(2, userName);
                    preparedStatement.setString(1,passwordText);
                    int rowCount = preparedStatement.executeUpdate();
                    if (rowCount > 0) {
                        submit.setBackground(Color.green);
                        JOptionPane.showMessageDialog(null, "Password is reset!!");
                        username.setText("");

                        password.setText("");
                        userGrp.clearSelection();
                        setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Something went wrong!!!");
                    }
                }
                else if(driver.isSelected())
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("update driver_bus set password=? where username=?");
                    preparedStatement.setString(2, userName);
                    preparedStatement.setString(1,passwordText);
                    int rowCount = preparedStatement.executeUpdate();
                    if (rowCount > 0) {
                        submit.setBackground(Color.green);
                        JOptionPane.showMessageDialog(null, "Password is reset!!");
                        username.setText("");
                        password.setText("");
                        userGrp.clearSelection();
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong!!!");
                    }
                }
                else if(conductor.isSelected())
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("update conductor_bus set password=? where username=?");
                    preparedStatement.setString(2, userName);
                    preparedStatement.setString(1,passwordText);
                    int rowCount = preparedStatement.executeUpdate();
                    if (rowCount > 0) {
                        submit.setBackground(Color.green);
                        JOptionPane.showMessageDialog(null, "Password is reset!!");
                        username.setText("");

                        password.setText("");
                        userGrp.clearSelection();
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong!!!");
                    }
                }
                connection.close();
            }
            catch (SQLException l)
            {
                // System.out.println(l.showMessage());
            }
        }
    }
}
