import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Passenger_Interface extends JFrame implements ActionListener {
    JPanel home, home1, search, history, loginPanel, panelSearch;
    JFrame profileFrame;
    JPanel profilePanel;
    JTextArea profileInfo, searched_schedule;
    JScrollPane sp;

    //panelSearch labels
    JLabel searchWelcome, source, destintion;
    //panel for main page
    JPanel main;
    JLabel welcome, pmpmlBus, aboutApp;
    //JButton login, create;
    JTabbedPane tabbedPane;
    //ImageIcon pmpmlBusIcon;

JTextArea welcome_Text_Info;
    //history
    JPanel mainHistory,history1, delete_history;
    JTextArea history_disp;
    JLabel labelHistory;
    JButton delete;

    //for search

    JComboBox jComboBox_source, jComboBox_destination;
    String [] source_values={"Pune Station","Swargate","Katraj","Deccan Gymkhana","Bhekrainagar","Pimpri Gaon","Alandi","Nigdi","Bhosari Gavhane Wasti"};
    String [] destination_values={"Shivajinagar","Pune Station","Pulgate","Katraj","Pimpri Gaon","Bopkhel","Dange Chowk","Gahunje Gaon","Shewalewadi","Vadgaon Maval","Akurdi Railway Station"};

    //for menubar
    JMenuBar bar;


    JTextArea main_Text;
    JButton searchBtn, showProfile, logout,searchSchedule, showHistory;

    //for profile showing
    int passengerID;
    String passengerName,passengerPassword,passengerEmail;
    ResultSet resultSet;
    ResultSet resultSet2;
    String bus_id_for_retrival;
    JButton backHome;
    Passenger_Interface(int id) {

        passengerID=id;
        setResizable(false);
        setBounds(170, 3, 1200, 820);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Passenger -Bus Schedule System");
        aboutApp = new JLabel("About us!!!");
        aboutApp.setFont(new Font("segoe print", Font.BOLD, 40));
        aboutApp.setBounds(860, 20, 400, 40);
        aboutApp.setForeground(new Color(190, 40, 40));
        add(aboutApp);
        //tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(760, 70, 400, 600);
        add(tabbedPane);
        home = new JPanel();


        //home JPanel in tabbedPane
        home.setBackground(new Color(0, 0, 0));
        welcome = new JLabel("Welcome");
        welcome.setForeground(new Color(199, 13, 13));
        welcome.setBounds(20, 10, 370, 100);
        welcome.setFont(new Font("georgia", Font.CENTER_BASELINE, 70));
        home.add(welcome);
        home.setLayout(null);
        tabbedPane.add("Home", home);

        welcome_Text_Info=new JTextArea();
        welcome_Text_Info.setText("      :::   Functions provided   :::\n\n\n  1)View your 'Profile Information.'\n  2)Search for 'Bus Schedules'\n    a)Select 'Source' and 'Destination' locations.\n    b)Click 'Find' button to search.\n  3)View History of already viewed bus schedules.\n  4)Logout.");
        welcome_Text_Info.setBounds(7,100,380,400);
        welcome_Text_Info.setFont(new Font("helvetica",Font.PLAIN,25));
        welcome_Text_Info.setEnabled(false);
        welcome_Text_Info.setBackground(new Color(24, 122, 21, 255));
        welcome_Text_Info.setDisabledTextColor(Color.BLACK);
        welcome_Text_Info.setLineWrap(true);
        welcome_Text_Info.setWrapStyleWord(true);
        home.add(welcome_Text_Info);


//        //login JPanel for tabbedPane
//        loginPanel = new JPanel();
//        loginPanel.setBackground(new Color(255, 80, 90));
//        tabbedPane.add("Create Account", loginPanel);


//        //search JPanel for tabbedPane
//        search = new JPanel();
//        search.setBackground(new Color(219, 29, 70));
//        tabbedPane.add("Search", search);
//

        //history JPanel for tabbedPane


        history=new JPanel();

        history.setLayout(null);
        history_disp=new JTextArea();
        history.add(history_disp);
//label
        labelHistory=new JLabel(" History ");
        labelHistory.setBounds(95,10,200,40);
        labelHistory.setFont(new Font("georgia",Font.BOLD,40));
        labelHistory.setForeground(new Color(0, 0, 0));
        history.add(labelHistory);
        history.setBackground(new Color(187, 25, 25));
        //TEXTAREA
        history_disp.setBounds(10,65,370,300);
        history_disp.setEnabled(false);
        history_disp.setBackground(Color.black);
        history_disp.setText("");
        history_disp.setLineWrap(true);
        history_disp.setFont(new Font("pluto",Font.PLAIN,20));
        history_disp.setBorder(BorderFactory.createBevelBorder(12));

        history.add(history_disp);
        delete_history=new JPanel() ;
        delete_history.setLayout(null);
        delete_history.setBounds(10,380,200,85);
        delete_history.setBackground(new Color(0, 0, 0));
        history.add(delete_history);
        showHistory=new JButton("Show History");
        showHistory.setBounds(9,5,180,35);
        showHistory.setFont(new Font("pluto",Font.PLAIN,18));
        showHistory.addActionListener(this);
        delete_history.add(showHistory);
        delete=new JButton("Delete All");
        delete.setBounds(9,44,180,35);

        delete.setFont(new Font("pluto",Font.PLAIN,18));

        delete.addActionListener(this);
        delete_history.add(delete);

        tabbedPane.add("History", history);



        main = new JPanel();
        main.setBounds(30, 30, 700, 720);
        main.setBackground(Color.lightGray);
        main.setLayout(null);
        this.add(main);


        main_Text=new JTextArea();
        main_Text.setBounds(25,80,645,600);
        main_Text.setText("\n\n\n!!Passenger Interface!!");
        main_Text.setFont(new Font("georgia",Font.ROMAN_BASELINE,65));
        main_Text.setEnabled(false);
        main_Text.setBackground(new Color(231, 99, 17));
        main.add(main_Text);

        //adding menuBar to panel main
        bar = new JMenuBar();
        bar.setBounds(0, 0, 700, 50);
        //bar.setBackground(new Color(213, 68, 113, 232));
        main.add(bar);

        showProfile=new JButton("Profile");
        showProfile.addActionListener(this);
        showProfile.setFont(new Font("segoe print",Font.BOLD,30));
        bar.add(showProfile);


        searchBtn = new JButton("Search");
        searchBtn.setBounds(0, 0, 200, 30);
        searchBtn.addActionListener(this);
        searchBtn.setFont(new Font("segoe print", Font.BOLD, 30));
        bar.add(searchBtn);

        logout=new JButton("Logout");
        logout.addActionListener(this);
        logout.setFont(new Font("segoe print",Font.BOLD,30));
        bar.add(logout);

        //panel for search
        panelSearch = new JPanel();
        panelSearch.setBounds(20, 70, 660, 660);
        panelSearch.setBackground(new Color(40,170,205));
        panelSearch.setLayout(null);
//for searching the schedule


// back button to leave search panel and go to home back
        backHome=new JButton("<---");
        backHome.setToolTipText("Back To Home");
        backHome.addActionListener(this);
        backHome.setBounds(550,10,60,30);
        backHome.setBackground(new Color(229, 38, 59));
        backHome.setFont(new Font("pluto",Font.BOLD,17));
        panelSearch.add(backHome);


        source=new JLabel("Source :");
        source.setBounds(10,50,100,30);
        source.setFont(new Font("pluto",Font.BOLD,25));
        panelSearch.add(source);
        jComboBox_source=new JComboBox(source_values);
        jComboBox_source.setBounds(120,50,190,30);
        jComboBox_source.setFont(new Font("pluto",Font.PLAIN,18));
        panelSearch.add(jComboBox_source);

        destintion=new JLabel("Destination :");
        destintion.setBounds(320,50,150,30);
        destintion.setFont(new Font("pluto",Font.BOLD,25));
        panelSearch.add(destintion);
        jComboBox_destination=new JComboBox(destination_values);
        jComboBox_source.setSelectedIndex(0);
        jComboBox_destination.setSelectedIndex(0);
        jComboBox_destination.setBounds(470,50,190,30);
        jComboBox_destination.setFont(new Font("pluto",Font.PLAIN,18));
        panelSearch.add(jComboBox_destination);
//for actual search the btn
        searchSchedule=new JButton("Find");
        searchSchedule.setBounds(20,100,100,35);
        searchSchedule.setFont(new Font("segoe print",Font.PLAIN,25));
        searchSchedule.addActionListener(this);
        panelSearch.add(searchSchedule);

        searched_schedule=new JTextArea();
        searched_schedule.setBounds(10,140,500,500);
       // searched_schedule.setVisible(false);
        searched_schedule.setEnabled(false);
        searched_schedule.setBackground(new Color(20,40,50));
        searched_schedule.setFont(new Font("pluto",Font.BOLD,20));
//        searched_schedule.setText("Hello");
sp=new JScrollPane(searched_schedule,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(10,140,500,500);
        panelSearch.add(sp);
        sp.setVisible(true);



        //welcome label
        searchWelcome=new JLabel("Search for schedules of buses :");
        searchWelcome.setForeground(new Color(223,25,80));
        searchWelcome.setBounds(10,10,500,30);
        searchWelcome.setFont(new Font("segoe print",Font.BOLD,30));
        panelSearch.add(searchWelcome);
        main.add(panelSearch);
        panelSearch.setVisible(false);




        //profile visible
        profileFrame = new JFrame();
        profileFrame.setBounds(170, 30, 350, 230);
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
        //new Passenger_Interface(23);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==backHome)
        {
            panelSearch.setVisible(false);
            main_Text.setVisible(true);
        }
        if (e.getSource() == searchBtn) {
            main_Text.setVisible(false);
            panelSearch.setVisible(true);
        }
        if(e.getSource()==showProfile)
        {
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
        if(e.getSource()==searchSchedule)
        {
            searched_schedule.setText("");
            findSchedules();
        }

        if(e.getSource()==showHistory)
        {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            catch (ClassNotFoundException m)
            {
                // System.out.println(m.showMessage());
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");

                PreparedStatement preparedStatement = connection.prepareStatement("select * from history where passenger_id=?");
                System.out.println(passengerID);
                preparedStatement.setInt(1,passengerID);
                System.out.println(2);
                resultSet = preparedStatement.executeQuery();
                System.out.println(3);
                history_disp.setText(" ");
                while(resultSet.next()){
                    System.out.println(22);
                    int id=resultSet.getInt(4);
                    System.out.println(id);
                history_disp.setText(history_disp.getText()+"\n"+resultSet.getString(1)+" :"+resultSet.getString(2)+" -> "+resultSet.getString(3));
                history_disp.setLineWrap(true);}

            }catch (Exception u)
            {

            }
        }

        if (e.getSource()==delete)
        {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            catch (ClassNotFoundException m)
            {
                // System.out.println(m.showMessage());
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");

                PreparedStatement preparedStatement = connection.prepareStatement("delete from history where passenger_id=?");
                preparedStatement.setInt(1,passengerID);
                boolean execute = preparedStatement.execute();

                history_disp.setText("");



            }catch (Exception u)
            {

            }
        }


    }


    public void findSchedules(){

        String source_place=jComboBox_source.getSelectedItem().toString();
        String destination_place=jComboBox_destination.getSelectedItem().toString();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException m)
        {
            // System.out.println(m.showMessage());
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");


            PreparedStatement preparedStatement = connection.prepareStatement("select bus_id from bus_info where source=? and destination=?");
            preparedStatement.setString(1,source_place);
            preparedStatement.setString(2,destination_place);
            resultSet=preparedStatement.executeQuery();

            if (resultSet.next())
            {
                bus_id_for_retrival=resultSet.getString(1);
                //System.out.println(bus_id_for_retrival);
            }
            PreparedStatement enterhistory=connection.prepareStatement("insert into history values(?,?,?,?)");
            enterhistory.setString(1,bus_id_for_retrival);
            enterhistory.setString(2,source_place);
            enterhistory.setString(3,destination_place);
            enterhistory.setInt(4,passengerID);
            enterhistory.executeUpdate();

            resultSet.close();
                  System.out.println(bus_id_for_retrival);

            if(!bus_id_for_retrival.isEmpty())
            {
                System.out.println("YES");
            }
           PreparedStatement prps1=connection.prepareStatement("select  * from bus_schedule where bus_schedule.bus_id=?");
            prps1.setString(1,bus_id_for_retrival);
            resultSet2=prps1.executeQuery();
            //System.out.println(resultSet2.next());
            searched_schedule.setText("Schedule for bus with ID : "+bus_id_for_retrival);

            while(resultSet2.next())
            {
                System.out.println("Done");
                String m=resultSet2.getString(1);
                String info=resultSet2.getString(2);
                System.out.println (info);
                searched_schedule.setText(searched_schedule.getText()+" \n \n"+info);
            }
            connection.close();
        }
        catch (SQLException u)
        {
            u.printStackTrace();
        }

    }
    public  void showPassengerProfile(int id)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException m)
        {
            // System.out.println(m.showMessage());
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");

            PreparedStatement preparedStatement = connection.prepareStatement("select * from passenger_bus where id=?");
            preparedStatement.setInt(1,passengerID);
            resultSet=preparedStatement.executeQuery();

            if (resultSet.next())
            {
                passengerName=resultSet.getString(2);
                passengerEmail=resultSet.getString(3);
                passengerPassword=resultSet.getString(4);
                System.out.println(passengerID+passengerPassword+passengerEmail);
            }

            connection.close();

        }
        catch (SQLException u)
        {

        }
        profileInfo.setText("        :::  Profile :::\n  Passenger ID : "+passengerID+"\n  Username : "+passengerName+"\n  Email : "+passengerEmail+"\n  Password : " +passengerPassword);
        profileFrame.setVisible(true);
    }
}
