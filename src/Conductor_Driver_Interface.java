import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Conductor_Driver_Interface extends JFrame implements ActionListener {
    //for tabbedPane
    JPanel home, home1, search, history, loginPanel, panelSearch;
    JFrame profileFrame;
    JPanel profilePanel;
    JTextArea profileInfo;

    JButton Wages;

    //panelSearch labels
    JLabel searchWelcome;
    //panel for main page
    JPanel main;
    JLabel welcome, pmpmlBus, aboutApp;
    JButton login, create;
    JTabbedPane tabbedPane;
    //ImageIcon pmpmlBusIcon;


    //for menubar
    JMenuBar bar;

    JButton searchBtn, showProfile, logout;
    //for profile showing
    int passengerID;
    String passengerName, passengerPassword, passengerEmail;
    ResultSet resultSet;
    int driverOrConductor;
    JTextArea main_Text;

    Conductor_Driver_Interface(int id, int user) {

        setResizable(false);
        passengerID = id;
        driverOrConductor = user;
        setBounds(390, 3, 770, 820);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        if(user==22)
        {
            setTitle("Conductor -Bus Schedule System");
        }
        else
        {
            setTitle("Driver -Bus Schedule System");
        }


//        aboutApp = new JLabel("About us!!!");
//        aboutApp.setFont(new Font("segoe print", Font.BOLD, 40));
//        aboutApp.setBounds(860, 20, 400, 40);
//        aboutApp.setForeground(new Color(190, 40, 40));
//        add(aboutApp);
//        //tabbed pane
//        tabbedPane = new JTabbedPane();
//        tabbedPane.setBounds(760, 70, 400, 600);
//       // add(tabbedPane);
//        home = new JPanel();
//
//
//        //home JPanel in tabbedPane
//        home.setBackground(new Color(255, 200, 250));
//        welcome = new JLabel("Welcome");
//        welcome.setForeground(new Color(187, 25, 25));
//        welcome.setBounds(6, 10, 370, 100);
//        welcome.setFont(new Font("segoe print", Font.CENTER_BASELINE, 80));
//        home.add(welcome);
//        home.setLayout(null);
//        tabbedPane.add("Home", home);
//
//        //login JPanel for tabbedPane
//        loginPanel = new JPanel();
//        loginPanel.setBackground(new Color(255, 80, 90));
//        tabbedPane.add("Create Account", loginPanel);
//
//
//        //search JPanel for tabbedPane
//        search = new JPanel();
//        search.setBackground(new Color(90, 230, 70));
//        tabbedPane.add("Search", search);
//
//
//        //history JPanel for tabbedPane
//        history = new JPanel();
//        history.setBackground(new Color(180, 90, 250));
//        tabbedPane.add("History", history);


        main = new JPanel();
        main.setBounds(30, 30, 700, 720);
        main.setBackground(new Color(90,150,150));
        main.setLayout(null);
        this.add(main);

        main_Text=new JTextArea();
        main_Text.setBounds(25,80,645,600);
        if(user==22)
            main_Text.setText("\n\n\n!!Conductor Interface!!");
        else
            main_Text.setText("\n\n\n!!Driver Interface!!");
        main_Text.setFont(new Font("georgia",Font.ROMAN_BASELINE,65));
        main_Text.setEnabled(false);
        main_Text.setBackground(new Color(90, 130, 130));
        main.add(main_Text);

//adding menuBar to panel main
        bar = new JMenuBar();
        bar.setBounds(0, 0, 700, 50);
        main.add(bar);

        showProfile = new JButton("Profile");
        showProfile.addActionListener(this);
        showProfile.setFont(new Font("segoe print", Font.BOLD, 30));
        bar.add(showProfile);

        Wages=new JButton("Wages");
        Wages.addActionListener(this);
        Wages.setFont(new Font("segoe print",Font.BOLD,30));
        bar.add(Wages);

        logout=new JButton("Logout");
        logout.addActionListener(this);
        logout.setFont(new Font("segoe print",Font.BOLD,30));
        bar.add(logout);



//        searchBtn = new JButton("Search");
//        searchBtn.setBounds(0, 0, 200, 30);
//        searchBtn.addActionListener(this);
//        searchBtn.setFont(new Font("segoe print", Font.BOLD, 30));
//        bar.add(searchBtn);
//
//
//        //panel for search
//        panelSearch = new JPanel();
//        panelSearch.setBounds(20, 70, 600, 600);
//        panelSearch.setBackground(new Color(40, 170, 205));
//        panelSearch.setLayout(null);
//
//
//        //welcome label
//        searchWelcome = new JLabel("Search for schedules of buses :");
//        searchWelcome.setForeground(Color.GREEN);
//        searchWelcome.setBounds(10, 10, 500, 30);
//        searchWelcome.setFont(new Font("segoe print", Font.BOLD, 30));
//        panelSearch.add(searchWelcome);
//        main.add(panelSearch);
//        panelSearch.setVisible(false);

        //profile visible
        profileFrame = new JFrame();
        profileFrame.setBounds(390, 30, 350, 230);
        profileFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        profileFrame.setResizable(false);
        profilePanel = new JPanel();
        profilePanel.setBounds(180, 10, 350, 230);
        profilePanel.setBackground(Color.PINK);
        profileFrame.add(profilePanel);
        profilePanel.setLayout(null);

        profileInfo = new JTextArea();
        profileInfo.setBounds(5, 5, 325, 180);
        profileInfo.setLineWrap(true);
        profileInfo.setFont(new Font("pluto", Font.BOLD, 20));
        profileInfo.setForeground(Color.BLACK);
        profileInfo.setBackground(new Color(40, 0, 0));
        profileInfo.setEnabled(false);
        profilePanel.add(profileInfo);

//        loginBtn = new JButton("login");
//        loginBtn.setBounds(600, 0, 200, 30);
//        loginBtn.setFont(new Font("segoe print", Font.BOLD, 30));
//        loginBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////not done yet
//            }
//        });
//        bar.add(loginBtn);


        setVisible(true);
    }

    public static void main(String[] args) {
        //new Conductor_Driver_Interface(1,22);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchBtn) {
            panelSearch.setVisible(true);
        }
        if (e.getSource() == showProfile) {
            showPassengerProfile(passengerID);
        }
        if (e.getSource()==logout)
        {
            int m=JOptionPane.showConfirmDialog(null,"Do you want to logout??");
            if(m==0) {
                new Login_Page();
                this.setVisible(false);
            }
            else if(m==1)
            {
                JOptionPane.showMessageDialog(null,"Nice choice!! Have a good experience!!");
            }

        }
        if(e.getSource()==Wages)
        {
            new Conductor_Driver_WagesCalculation(passengerID,driverOrConductor);
        }
    }

    public void showPassengerProfile(int id) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException m) {
            // System.out.println(m.showMessage());
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");

            if (driverOrConductor == 22) //22->conductor
            {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from conductor_bus where id=?");
                preparedStatement.setInt(1, passengerID);
                resultSet = preparedStatement.executeQuery();
            } else if (driverOrConductor == 33) //33->driver
            {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from driver_bus where id=?");
                preparedStatement.setInt(1, passengerID);
                resultSet = preparedStatement.executeQuery();
            }

            if (resultSet.next()) {
                passengerName = resultSet.getString(2);
                passengerEmail = resultSet.getString(3);
                passengerPassword = resultSet.getString(4);
                System.out.println(passengerID + passengerPassword + passengerEmail);
            }

            connection.close();

        } catch (SQLException u) {

        }
        if (driverOrConductor == 22) {
            profileInfo.setText("        :::  Profile :::\n  Conductor ID : " + passengerID + "\n  Username : " + passengerName + "\n  Email : " + passengerEmail + "\n  Password : " + passengerPassword);
            profileFrame.setVisible(true);
        } else {
            profileInfo.setText("        :::  Profile :::\n  Driver ID : " + passengerID + "\n  Username : " + passengerName + "\n  Email : " + passengerEmail + "\n  Password : " + passengerPassword);
            profileFrame.setVisible(true);
        }
    }
}