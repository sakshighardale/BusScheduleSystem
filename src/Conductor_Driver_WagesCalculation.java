import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;

public class Conductor_Driver_WagesCalculation extends JFrame implements ActionListener {
    int userId;
    String userType;
    JPanel Date_View, TripsMarking;
    JLabel date, month, year, trips, total_trips_no;
    String[] month_date = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    JSpinner date_spinner, month_spinner, year_spinner, trips_spinner;
    SpinnerModel model1, model2, model3, model4;
    Font font, heading, font1;
    JButton Find;


    JTextArea total_wage;
    //heading labels
    JLabel mark_Trips, see_wage;

    //left Panel
    JLabel now_Date /*,now_month, now_year*/;

    JLabel current_Date, current_Month, current_Year;
    JButton mark_trip;


    String str_Date;


    //Resultset
    ResultSet resultSet, resultSet2;

    Conductor_Driver_WagesCalculation(int id, int user) //user : 22->conductor  33-->driver
    {
        userId = id;
        if (user == 22) {
            userType = "Conductor";
        } else {
            userType = "Driver";
        }


        setResizable(false);
        font = new Font("puto", Font.PLAIN, 24);
        font1 = new Font("Arial", Font.PLAIN, 22);
        heading = new Font("georgia", Font.BOLD, 30);
        setLayout(null);
        setTitle("Daily Wage!!");
        setBounds(370, 100, 800, 500);


        TripsMarking = new JPanel();
        TripsMarking.setBounds(20, 40, 400, 400);
        TripsMarking.setLayout(null);
        TripsMarking.setBackground(new Color(236, 104, 11));
        add(TripsMarking);

        mark_Trips = new JLabel("Trips For Today");
        mark_Trips.setBounds(79, 5, 300, 40);
        mark_Trips.setFont(heading);
        TripsMarking.add(mark_Trips);

        now_Date = new JLabel("Date :");
        now_Date.setBounds(10, 70, 150, 50);
        now_Date.setFont(new Font("pluto",Font.PLAIN,30));
        TripsMarking.add(now_Date);

//    now_month=new JLabel("Month :");
//    now_month.setBounds(10,80,150,30);
//
//    now_month.setFont(font);
//    TripsMarking.add(now_month);
//
        trips = new JLabel("Trips :");
        trips.setBounds(10, 145, 150, 50);
        trips.setFont(new Font("pluto",Font.PLAIN,30));
        TripsMarking.add(trips);


        //for date, month, and year
        LocalDate int_date = LocalDate.now();
        str_Date = String.valueOf(int_date);
        System.out.println(str_Date);
        String str_year = String.valueOf(Year.now());
        int year_today = Integer.parseInt(str_year);
        current_Date = new JLabel();
        current_Date.setBounds(170, 70, 200, 50);
        current_Date.setFont(font1);
        current_Date.setOpaque(true);
        current_Date.setBackground(Color.white);
        current_Date.setText(str_Date);
        TripsMarking.add(current_Date);

//
//    now_year=new JLabel("Year :");
//   now_year.setBounds(10,120,150,30);
//    now_year.setFont(font);
//    TripsMarking.add(now_year);


        model4 = new SpinnerNumberModel(1, 1, 5, 1);
        trips_spinner = new JSpinner(model4);
        trips_spinner.setFont(font1);
        trips_spinner.setBounds(170, 155, 40, 30);
        TripsMarking.add(trips_spinner);

        mark_trip = new JButton("Mark");
        mark_trip.setBounds(80, 230, 200, 40);
        mark_trip.setFont(font);
        TripsMarking.add(mark_trip);
        mark_trip.addActionListener(this);


        //right panel


        Date_View = new JPanel();
        Date_View.setLayout(null);
        Date_View.setBackground(new Color(55, 227, 29, 255));
        Date_View.setBounds(450, 40, 300, 400);
        add(Date_View);
        //
        see_wage = new JLabel("View Wage");
        see_wage.setBounds(79, 2, 300, 35);
        see_wage.setFont(heading);
        Date_View.add(see_wage);
        date = new JLabel("Date :");
        date.setBounds(10, 45, 150, 30);
        date.setFont(font);
        Date_View.add(date);

        month = new JLabel("Month :");
        month.setBounds(10, 85, 150, 30);

        month.setFont(font);
        Date_View.add(month);

        year = new JLabel("Year :");
        year.setBounds(10, 125, 150, 30);
        year.setFont(font);
        Date_View.add(year);

        String str = String.valueOf(Year.now());
        int y = Integer.parseInt(str);
        model1 = new SpinnerNumberModel(01, 01, 31, 1);
        model2 = new SpinnerNumberModel(01, 01, 12, 1);
        model3 = new SpinnerNumberModel(y, y - 100, y, 1);


        date_spinner = new JSpinner(model1);
        month_spinner = new JSpinner(model2);
        year_spinner = new JSpinner(model3);


        date_spinner.setBounds(170, 45, 100, 30);
        date_spinner.setFont(font1);
        month_spinner.setBounds(170, 85, 100, 30);
        month_spinner.setFont(font1);
        year_spinner.setBounds(170, 125, 100, 30);
        year_spinner.setFont(font1);

        //Trips Print from table directly on basis of dates
        total_trips_no = new JLabel("0");
        total_trips_no.setBounds(170, 165, 40, 30);
        total_trips_no.setOpaque(true);
        total_trips_no.setFont(font1);
        total_trips_no.setBackground(Color.white);
        Date_View.add(total_trips_no);


        Find = new JButton("Find Wage");

        Find.setBounds(40, 225, 200, 40);
        Find.setFont(font1);
        Date_View.add(Find);
        Find.addActionListener(this);

        trips = new JLabel("Trips :");
        trips.setBounds(10, 165, 150, 30);
        trips.setFont(font);
        Date_View.add(trips);

        total_wage=new JTextArea("");
        total_wage.setText("Total Wage :");
        total_wage.setBounds(15,290,275,40);
        total_wage.setFont(new Font("pluto",Font.PLAIN,23));
        Date_View.add(total_wage);

        Date_View.add(date_spinner);
        Date_View.add(month_spinner);
        Date_View.add(year_spinner);


        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
      //new Conductor_Driver_WagesCalculation(61, 22);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // create table daily_wage (id int ,usertype varchar(10), now_date varchar(20), wage int, check_valid int);


        if (e.getSource() == mark_trip) {

            int tTrips = (int) model4.getValue();

            JOptionPane.showMessageDialog(mark_trip, "No. of trips= " + tTrips + " Total Wage for a day : Rs." + tTrips * 200);
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException m) {
                // System.out.println(m.showMessage());
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");

                PreparedStatement preparedStatement = connection.prepareStatement("insert into daily_wage values(?,?,?,?,?)");

                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, userType);
                preparedStatement.setString(3, str_Date);
                preparedStatement.setInt(4, tTrips * 200);
                preparedStatement.setInt(5,tTrips );


                preparedStatement.executeUpdate();


                connection.close();
//            }
            } catch (SQLException u) {
                u.printStackTrace();
            }
        }


        if (e.getSource() == Find) {
            String monthToFind;
            if ((int) month_spinner.getValue() >= 10)
                monthToFind = "" + month_spinner.getValue();
            else {
                monthToFind = "0" + month_spinner.getValue();
            }
            String dateToFind;
            if ((int) date_spinner.getValue() >= 10)
                dateToFind = "" + date_spinner.getValue();
            else {
                dateToFind = "0" + date_spinner.getValue();
            }

            String dateFind = year_spinner.getValue() + "-" + monthToFind + "-" + dateToFind;
            System.out.println(dateFind);
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException m) {
                // System.out.println(m.showMessage());
            }
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sakshi22");
                // create table daily_wage (id int ,usertype varchar(10), now_date varchar(20), wage int, check_valid int);

                PreparedStatement retrive_check_valid = connection.prepareStatement("select * from daily_wage  where now_date=? and id=? and usertype=?");
                retrive_check_valid.setString(1, dateFind);
                retrive_check_valid.setInt(2, userId);
                retrive_check_valid.setString(3, userType);
                resultSet = retrive_check_valid.executeQuery();
                System.out.println(resultSet);
                int total_Wage=0;
                int total_trips=0;
                while (resultSet.next()) {
                    total_Wage+= resultSet.getInt(4);
                    total_trips+=resultSet.getInt(5);
                }
                System.out.println(total_Wage+ " "+total_trips);

                total_trips_no.setText(""+total_trips);
                total_wage.setText("Total : RS. "+total_Wage);

//                System.out.println(check_insert_or_not);
//
//                if (check_insert_or_not != 1 || check_insert_or_not != 0) {
//                    JOptionPane.showMessageDialog(null, "Already updated!!");
//            } else {
//            PreparedStatement preparedStatement = connection.prepareStatement("insert into daily_wage values(?,?,?,?,?)");
//
//            preparedStatement.setInt(1, userId);
//            preparedStatement.setString(2, userType);
//            preparedStatement.setString(3, str_Date);
//            preparedStatement.setInt(4, y * 200);
//            preparedStatement.setInt(5, 1);
//
//
//            preparedStatement.executeUpdate();
//
//
//            connection.close();
////            }
                }


             catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
