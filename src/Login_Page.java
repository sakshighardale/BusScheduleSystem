import oracle.jdbc.logging.annotations.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login_Page extends JFrame implements ActionListener
{
    JPanel verification,moveBus;
    JLabel login_welcome,userName, password,signup, busImage ,scheduleImage, appName,devss;
    JRadioButton passenger,conductor,driver;
    JTextField userNameText;
    JPasswordField passwordText;

    JButton submit, resetPassword, devs;
    ButtonGroup group;

ResultSet resultSet;
    JButton createAcc;
    ImageIcon bus;

    //for user specification
    int id_for_nextPageMove;
    int Conductor_OR_Driver;



    Login_Page()
    {

        setBounds(130,100,1260,590); //main frame
        this.setResizable(false);


        moveBus=new JPanel();
        moveBus.setLayout(null);
        bus=new ImageIcon("C:\\Users\\Sakshi Ghardale\\IdeaProjects\\Bus Schedule System\\src\\Image_bus_schedule_system.jpg");

        busImage=new JLabel();
        busImage.setBounds(18,130,510,290);
        busImage.setIcon(bus);
        moveBus.add(busImage);
//        scheduleImage=new JLabel();
//        scheduleImage.setBounds(300,190,240,190);
//        scheduleImage.setIcon(new ImageIcon("C:\\Users\\Sakshi Ghardale\\IdeaProjects\\Bus Schedule System\\src\\schedule.png"));
//        moveBus.add(scheduleImage);

        moveBus.setBackground(new Color(0, 0, 0));
        moveBus.setBounds(14,10,550,530); //left panel
        //moveBus.add(new MyPanel<Image>());
        add(moveBus);

        appName=new JLabel("  Bus Schedule System");
        appName.setBounds(5,30,550,70);
        appName.setOpaque(true);
        appName.setBackground(new Color(45, 28, 28));
        appName.setForeground(Color.red);
        appName.setFont(new Font("Georgia",Font.CENTER_BASELINE,45));
        moveBus.add(appName);

//        devs=new JLabel("Devs :Rutwika Ganer ,Sakshi Ghardale, Shreya Gunjkar");
//        devs.setBounds(80,450,360,100);
//        moveBus.add(devs);

        devs=new JButton("@Created_by@");
        devs.setBounds(140,450,200,30);
        devs.setBackground(new Color(229, 38, 59));
        devs.setForeground(Color.white);
        devs.addActionListener(this);
        moveBus.add(devs);

        verification=new JPanel();
        add(verification);
        verification.setBackground(new Color(37, 164, 9, 232));
        verification.setBorder(BorderFactory.createLineBorder(Color.black,20,false));
        verification.setLayout(null);
        verification.setBounds(590,20,640,480);

        Font font_heading=new Font("pluto",Font.BOLD,45);
        Font font=new Font("pluto",Font.BOLD,20);
        setTitle("Login Page");



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        login_welcome=new JLabel("      LOGIN");
        login_welcome.setForeground(new Color(143, 7, 7));
       // login_welcome.setBackground(new Color(0, 0, 0));
        login_welcome.setBounds(170,20,300,40);
        login_welcome.setOpaque(true);
        login_welcome.setBackground(new Color(39, 215, 55));
        login_welcome.setFont(font_heading);
        verification.add(login_welcome);

        //user specification
        passenger=new JRadioButton("Passenger");
//        passenger.setFont();
        passenger.setBounds(50,90,150,40);
        passenger.setFont(font);

        passenger.setBackground(Color.lightGray);
        verification.add(passenger);
        conductor=new JRadioButton("Conductor");
        conductor.setFont(font);

        conductor.setBackground(Color.lightGray);
        conductor.setBounds(220,90,150,40);
        verification.add(conductor);
        driver=new JRadioButton("Driver");
        driver.setFont(font);

        driver.setBackground(Color.lightGray);
        driver.setBounds(390,90,150,40);
        verification.add(driver);
        group=new ButtonGroup();
        group.add(passenger);
        group.add(conductor);
        group.add(driver);

        //username
        userName=new JLabel("Enter username :");
        userName.setBounds(50,150,200,40);
        userName.setFont(font);
        userName.setBackground(Color.lightGray);
        userName.setOpaque(true);
        verification.add(userName);
        userNameText=new JTextField();
        userNameText.setBounds(270,150,270,40);
        userNameText.setFont(font);
        verification.add(userNameText);

        //password
        password=new JLabel("Enter password :");
        password.setBounds(50,230,200,40);
        password.setFont(font);
        password.setBackground(Color.lightGray);
        password.setOpaque(true);
        verification.add(password);
        passwordText=new JPasswordField();
        passwordText.setBounds(270,230,270,40);
        passwordText.setFont(font);
        verification.add(passwordText);

        //login btn
        submit=new JButton("Login");
        submit.setFont(font);
        submit.setBounds(30,310,250,40);
        submit.addActionListener(this);
        verification.add(submit);

        resetPassword=new JButton("Forgot Password?");
        resetPassword.setFont(font);
        resetPassword.setBounds(300,310,250,40);
        resetPassword.addActionListener(this);
        verification.add(resetPassword);


        //signup btn and all
        signup=new JLabel("Create new account --->");
        signup.setForeground(Color.blue);
        signup.setBounds(90,400,300,40);
        signup.setFont(new Font("Arial",Font.PLAIN,20));
        verification.add(signup);
        createAcc = new JButton("SignUp");
        createAcc.setFont(font);
        createAcc.setBounds(350, 400, 200, 40);
        verification.add(createAcc);
        createAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration registration = new Registration();
            }
        });
        setVisible(true);
    }
    public static void main(String[] args) {

        new Login_Page();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit)
        {
            String userName=userNameText.getText();
            String passwordTextText= new String(passwordText.getPassword());
            submit.setBackground(Color.yellow);
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            catch (ClassNotFoundException m)
            {
                // System.out.println(m.showMessage());
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");
                //System.out.println("connection done");
                if(passenger.isSelected()) {
                    PreparedStatement preparedStatement = connection.prepareStatement("Select * from passenger_bus where username=? and password=?");
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, passwordTextText);
                    System.out.println("statement instance done");
                    resultSet = preparedStatement.executeQuery();
                    //System.out.println("Done"+resultSet.getInt("id"));

                } else if(driver.isSelected()) {
                    PreparedStatement preparedStatement = connection.prepareStatement("Select * from driver_bus where username=? and password=?");
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, passwordTextText);
                    //System.out.println("statement instance done");
                    resultSet = preparedStatement.executeQuery();
                    //System.out.println("Retrieval done");
                }
                else if(conductor.isSelected()) {
                    PreparedStatement preparedStatement = connection.prepareStatement("Select * from conductor_bus where username=? and password=?");
                    preparedStatement.setString(1, userName);
                    preparedStatement.setString(2, passwordTextText);
                    //System.out.println("statement instance done");
                    resultSet = preparedStatement.executeQuery();
                    //System.out.println("Retrieval done");
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong!!!");
                }
                    if(resultSet.next())
                    {
                        JOptionPane.showMessageDialog(null,"Successfully Logged in!!");
                        id_for_nextPageMove=resultSet.getInt(1);
                        System.out.println("id:"+id_for_nextPageMove);
                        System.out.println("Retrieval done");
                        if(passenger.isSelected())
                        {
                            new Passenger_Interface(id_for_nextPageMove);
                        }
                        else if(conductor.isSelected() || driver.isSelected())
                        {
                           if(conductor.isSelected())
                           {
                               Conductor_OR_Driver=22; //conductor
                           }
                           else
                           {
                               Conductor_OR_Driver=33; //driver
                           }
                            new Conductor_Driver_Interface(id_for_nextPageMove,Conductor_OR_Driver);
                        }
                        this.setVisible(false);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Wrong username or password!!");
                        submit.setBackground(Color.red);
                        submit.setForeground(Color.white);
                    }

                connection.close();
                }
            catch (SQLException h)
            {

            }
        }

        if(e.getSource()==resetPassword)
        {
            new ForgotPassword();
        }
        if(e.getSource()==devs)
        {
            new Devs();
        }
    }
}
