package com.TimePass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends JFrame implements ActionListener{
    JLabel home;
    JPanel panel1,panelMain, panelSearch;
    JMenuBar bar;
    JMenu search;
    //JMenuItem passenger,driver, conductor;
    JButton searchBtn,createAcc,loginBtn;
    ImageIcon searchIcon;


    Main()
    {
        searchIcon=new ImageIcon("com/TimePass/search.png");
    this.setBounds(400,0,900,900);
    setTitle("HOME-PAGE");
   // setResizable(false);
    panelMain=new JPanel();
    panelMain.setBackground(Color.LIGHT_GRAY);
    panelMain.setBounds(0,0,900,90);
    add(panelMain);
    home=new JLabel("HOME");
    home.setFont(new Font("Bookman Old Style",Font.BOLD,70));
    home.setForeground(Color.red);
    home.setBounds(360,0,500,50);
    panelMain.add(home);

    //panel left
    panel1=new JPanel();
    panel1.setBounds(0,60,200,900);
    panel1.setBackground(Color.GRAY);
    add(panel1);
//    login=new JLabel();
//    login.setBounds(10,100,100, 40);
//   // login.setFont(new Font("Bookman Old Style",Font.BOLD,70));
//    login.setForeground(Color.BLACK);
//    login.setIcon(searchIcon);
//    panel1.add(login);


    //menubar
    bar=new JMenuBar();
    bar.setBounds(200,90,700,50);
    add(bar);

//    search=new JMenu("Search");
//    search.setFont(new Font("segoe print",Font.BOLD,40));
//    search.setBounds(500,0,200,40);
//    bar.add(search);
    //search.addActionListener(this);

    searchBtn=new JButton("Search");
    searchBtn.setBounds(400,0,200,30);
    searchBtn.setFont(new Font("segoe print",Font.BOLD,30));
    searchBtn.addActionListener(this);
    bar.add(searchBtn);

    createAcc=new JButton("Create Account");
    createAcc.setFont(new Font("segoe print",Font.BOLD,30));
    createAcc.setBounds(400,0,200,30);
    bar.add(createAcc);
    createAcc.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          //  Registration registration=new Registration();
        }
    });
    loginBtn=new JButton("login");
    loginBtn.setBounds(400,0,200,30);
    loginBtn.setFont(new Font("segoe print",Font.BOLD,30));
    loginBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
    bar.add(loginBtn);


    //search-panel
        panelSearch=new JPanel();
        panelSearch.setBounds(200,90,400,400);
        panelSearch.setBackground(Color.blue);

    searchBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            add(panelSearch);
        }
    });


    setLayout(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

    }


    public static void main(String[] args) {
        new Main();
    }

    @java.lang.Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()==searchBtn)
    {

        add(panelSearch);
    }
    }

    }
