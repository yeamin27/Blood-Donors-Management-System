package bloodDonorsManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class About {

    JFrame frameAbout = new JFrame("About");

    private final ImageIcon icon;
    private final JPanel panelMain;
    private final JLabel lblAbout, lblFetures;
    private final JTextArea taAbout, taFetures;
    private final JButton btnOk;

    private final Color clrYellow = new Color(240, 230, 170);
    private final Color clrBlue = new Color(15, 70, 140);

    public About() {
        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/about_icon.png"));
        frameAbout.setIconImage(icon.getImage());                               //setting window icon

        //setting up the frame
        frameAbout.setSize(400, 300);
        frameAbout.setLayout(new BorderLayout());                               //frame layout: Border Layout
        frameAbout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);           //setting default close operation
        frameAbout.setLocationRelativeTo(null);                                 //to center the window
        frameAbout.setResizable(false);                                         //disable window resize

        //setting up the panel 
        panelMain = new JPanel();
        panelMain.setBackground(clrYellow);                                     //background color: Yellow
        panelMain.setLayout(null);

        Border margin = new EmptyBorder(10, 10, 10, 10);                        //create margin for titled border
        Border blueline = BorderFactory.createLineBorder(clrBlue);              //create line for titled border

        TitledBorder ttlDevInfo = BorderFactory.createTitledBorder(blueline);   //create titled border
        ttlDevInfo.setTitle(" About ");                                         //set title
        ttlDevInfo.setTitleColor(clrBlue);                                      //title color: Blue

        panelMain.setBorder(new CompoundBorder(margin, ttlDevInfo));            //set border(outer border: margin, inner border: title)
        panelMain.setBackground(clrYellow);                                     //background color: Yellow
        panelMain.setPreferredSize(new Dimension(400, 300));                    //setting panel size

        lblAbout = new JLabel("About:");
        lblAbout.setBounds(25, 30, 340, 20);
        panelMain.add(lblAbout);

        taAbout = new JTextArea();
        taAbout.setText("This appliction store information of Blood Donors.");
        taAbout.setBounds(25, 50, 340, 30);
        taAbout.setBorder(blueline);
        taAbout.setEditable(false);
        panelMain.add(taAbout);

        lblFetures = new JLabel("Fetures:");
        lblFetures.setBounds(25, 90, 340, 20);
        panelMain.add(lblFetures);

        taFetures = new JTextArea();
        taFetures.setText("* Add new donor's information.\n"
                + "* Update existing donor's information\n"
                + "* Delete donor's information\n"
                + "* Search by donor's blood groups, name,\n"
                + "   home division & home district\n"
                + "* Sort donor's list");
        taFetures.setBounds(25, 110, 340, 100);
        taFetures.setBorder(blueline);
        taFetures.setEditable(false);
        panelMain.add(taFetures);

        btnOk = new JButton("OK");
        btnOk.setBackground(clrBlue);                                           //button background color: Red
        btnOk.setForeground(Color.white);                                       //button text color: White
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));                        //set button cursor
        btnOk.setBounds(150, 220, 80, 25);
        panelMain.add(btnOk);

        //after clicking this button this window will be closed
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frameAbout.dispose();
            }
        });

        //panel work done
        frameAbout.add(panelMain, BorderLayout.CENTER);
        //make frame visible
        frameAbout.setVisible(true);
    }

}
