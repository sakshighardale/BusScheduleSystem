import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Arrays;
public class Registration extends JFrame implements ActionListener
{
    JButton submit;
    int checkRadioButton; //0->passenger , 1->driver , 2->conductor
    JLabel label1,label2,label3, user;
    JTextField username, email;
    JPasswordField password;
    JRadioButton passenger,conductor,driver;
    ButtonGroup userGrp;
    Registration()
    {
        Font font=new Font("segoe print",Font.BOLD,14);
        setBounds(540,230,400,350);
        setTitle("Register");
        user=new JLabel("User Type :");
        user.setFont(font);
        user.setBounds(10,10,100,30);
        add(user);

        conductor=new JRadioButton("Conductor");
        conductor.setFont(font);
        conductor.setBounds(110,10,100,30);
        add(conductor);
        driver=new JRadioButton("Driver");
        driver.setFont(font);
        driver.setBounds(210,10,80,30);
        add(driver);
        passenger=new JRadioButton("Passenger");
        passenger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(new String(password.getPassword()));
            }
        });
        passenger.setFont(font);
        passenger.setBounds(287,10,100,30);
        add(passenger);

        userGrp=new ButtonGroup();
        userGrp.add(passenger);
        userGrp.add(driver);
        userGrp.add(conductor);

        label1=new JLabel("Username :");
        label1.setFont(font);
        label1.setBounds(10,50,100,30);
        add(label1);

        username=new JTextField();
        username.setFont(font);
        username.setBounds(120,50,200,30);
        add(username);

        label2=new JLabel("E-mail :");
        label2.setBounds(10,100,100,30);
        label2.setFont(font);
        add(label2);

        email=new JTextField();
        email.setFont(font);
        email.setBounds(120,100,200,30);
        add(email);

        label3=new JLabel("Password :");
        label3.setFont(font);
        label3.setBounds(10,150,100,30);
        add(label3);

        password=new JPasswordField();
        password.setFont(font);
        password.setEchoChar('*');
        password.setBounds(120,150,200,30);
        add(password);

        submit=new JButton("Register");
        submit.setFont(font);
        submit.setBounds(150,210,100,30);
        add(submit);
        //JFrame jfrme=new JFrame()
        //add()
        submit.addActionListener(this);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLayout(null);
        setTitle("Sign-up");
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==submit)
        {
            String userName=username.getText();
            String emailText=email.getText();
            String passwordText= new String(password.getPassword());
            submit.setBackground(Color.pink);
            if (!validateEmail(emailText)) {
                JOptionPane.showMessageDialog(null, "Invalid email format! Please enter a valid Gmail address.");
                return;
            }
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
                    PreparedStatement preparedStatement = connection.prepareStatement("Insert into passenger_bus values(passenger_id_seq.nextval,?,?,?)");
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, emailText);
                    preparedStatement.setString(3,passwordText);
                    int rowCount = preparedStatement.executeUpdate();
                    if (rowCount > 0) {
                        JOptionPane.showMessageDialog(null, "Registration of passenger is done successfully!!");
                        username.setText("");
                        email.setText("");
                        password.setText("");
                        userGrp.clearSelection();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Something went wrong!!!");
                    }
                }
                else if(driver.isSelected())
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("Insert into driver_bus values(driver_id_seq.nextval,?,?,?)");
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, emailText);
                    preparedStatement.setString(3,passwordText);
                    int rowCount = preparedStatement.executeUpdate();
                    if (rowCount > 0) {
                        JOptionPane.showMessageDialog(null, "Registration of driver is done successfully!!");
                        username.setText("");
                        email.setText("");
                        password.setText("");
                        userGrp.clearSelection();
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong!!!");
                    }
                }
                else if(conductor.isSelected())
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("Insert into conductor_bus values(conductor_id_seq.nextval,?,?,?)");
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, emailText);
                    preparedStatement.setString(3,passwordText);
                    int rowCount = preparedStatement.executeUpdate();
                    if (rowCount > 0) {
                        JOptionPane.showMessageDialog(null, "Registration of conductor is done successfully!!");
                        username.setText("");
                        email.setText("");
                        password.setText("");
                        userGrp.clearSelection();
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
    public boolean validateEmail(String email) {
        return email.toLowerCase().endsWith("@gmail.com");
    }
    public static void main(String[] args) {
        new Registration();
    }
}
