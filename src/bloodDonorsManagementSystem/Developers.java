
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

public class Developers {
    JFrame frameDevelopers = new JFrame("Developers Information");
    
    private final ImageIcon icon;    
    private final JPanel panelMain;    
    private final JButton btnOk;
    private final JTextArea taDev1, taDev2, taCntct1, taCntct2;
    private final JLabel lblDev1, lblDev2, lblCntct1, lblCntct2;
    
    private final Color clrYellow = new Color(240, 230, 170);
    private final Color clrBlue = new Color(15, 70, 140);

    public Developers() {
        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/info_icon.png"));
        frameDevelopers.setIconImage(icon.getImage());                          //setting window icon

        //setting up the frame
        frameDevelopers.setSize(600, 400);
        frameDevelopers.setLayout(new BorderLayout());                          //frame layout: Border Layout
        frameDevelopers.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      //setting default close operation
        frameDevelopers.setLocationRelativeTo(null);                            //to center the window
        frameDevelopers.setResizable(false);                                    //disable window resize
        
        //setting up the panel 
        
        panelMain = new JPanel();
        panelMain.setBackground(clrYellow);                                     //background color: Yellow
        panelMain.setLayout(null);
        
        Border margin = new EmptyBorder(10, 10, 10, 10);                        //create margin for titled border
        Border blueline = BorderFactory.createLineBorder(clrBlue);              //create line for titled border
        
        TitledBorder ttlDevInfo = BorderFactory.createTitledBorder(blueline);   //create titled border
        ttlDevInfo.setTitle(" Developers ");                                    //set title
        ttlDevInfo.setTitleColor(clrBlue);                                      //title color: Blue

        panelMain.setBorder(new CompoundBorder(margin, ttlDevInfo));            //set border(outer border: margin, inner border: title)
        panelMain.setBackground(clrYellow);                                     //background color: Yellow
        panelMain.setPreferredSize(new Dimension(600, 400));                    //setting panel size
        
        lblDev1 = new JLabel("Developer 1:");
        lblDev1.setBounds(30, 40, 100, 20);
        panelMain.add(lblDev1);
        
        taDev1 = new JTextArea();
        taDev1.setBounds(30, 65, 250, 150);
        taDev1.setLineWrap(true);
        taDev1.setBorder(blueline);
        taDev1.setEditable(false);
        taDev1.setText("Bin Yeamin Chowdhury\n"
        + "ID: 161-115-150\n"
        + "Department: CSE\n"
        + "Batch: 38\n"
        + "Section: D\n"
        + "Metropolitan University, Sylhet.");
        panelMain.add(taDev1);
        
        lblDev2 = new JLabel("Developer 2:");
        lblDev2.setBounds(300, 40, 100, 20);
        panelMain.add(lblDev2);
        
        taDev2 = new JTextArea();
        taDev2.setBounds(300, 65, 250, 150);
        taDev2.setLineWrap(true);
        taDev2.setBorder(blueline);
        taDev2.setEditable(false);
        taDev2.setText("Ali Ahmed\n"
        + "ID: 161-115-128\n"
        + "Department: CSE\n"
        + "Batch: 38\n"
        + "Section: D\n"
        + "Metropolitan University, Sylhet.");
        panelMain.add(taDev2);
        
        lblCntct1 = new JLabel("Contact:");
        lblCntct1.setBounds(30, 220, 100, 20);
        panelMain.add(lblCntct1);
        
        taCntct1 = new JTextArea();
        taCntct1.setBounds(30, 245, 250, 50);
        taCntct1.setLineWrap(true);
        taCntct1.setBorder(blueline);
        taCntct1.setEditable(false);
        taCntct1.setText("Mobile: +8801779-882804\n"
        + "Gmail: binyeamin.chowdhury@gmail.com");
        panelMain.add(taCntct1);
        
        lblCntct2 = new JLabel("Contact:");
        lblCntct2.setBounds(300, 220, 100, 20);
        panelMain.add(lblCntct2);
        
        taCntct2 = new JTextArea();
        taCntct2.setBounds(300, 245, 250, 50);
        taCntct2.setLineWrap(true);
        taCntct2.setBorder(blueline);
        taCntct2.setEditable(false);
        taCntct2.setText("Mobile: +8801715-766535\n"
        + "Gmail: aliahmedcse128@gmail.com");
        panelMain.add(taCntct2);
        
        btnOk = new JButton("OK");
        btnOk.setBackground(clrBlue);                                           //button background color: Red
        btnOk.setForeground(Color.white);                                       //button text color: White
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));                        //set button cursor
        btnOk.setBounds(250, 310, 80, 25);
        panelMain.add(btnOk);
        
        //after clicking this button this window will be closed
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frameDevelopers.dispose();
            }
        });
        
        //panel work done
        
        frameDevelopers.add(panelMain, BorderLayout.CENTER);
        //make frame visible
        frameDevelopers.setVisible(true);
    }
}
