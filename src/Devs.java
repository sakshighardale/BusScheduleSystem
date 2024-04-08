import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.net.URI;
import java.net.URL;

public class Devs extends JFrame {
    JPanel panel;
   // URL sakshi_linkedin, shreya_linkedin, rutwika_linkedin;
    JTextArea area;

    Devs()
    {

        setBounds(120,60,600,600);
        panel=new JPanel();
        panel.setBounds(20,20,550,530);
        setLayout(null);

        panel.setBackground(new Color(107,103,160));
        panel.setLayout(null);
        add(panel);

        area=new JTextArea("\nTeam Members:\n\n#Sakshi Gopal Ghardale(122B1B087)\n\n\n#Rutwika Ganer(122B1B084)\n\n\n#Shreya Gunjkar(122B1B093)");
        area.setFont(new Font("pluto",Font.BOLD,27));
        area.setBackground(new Color(20,3,2));
        panel.add(area);
        area.setBounds(10,10,530,500);
        area.setWrapStyleWord(true);
        area.setEnabled(false);
        setTitle("Know about our Devs!!");
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new Devs();
    }
}
