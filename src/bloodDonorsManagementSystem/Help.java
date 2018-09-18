
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

public class Help {
    JFrame frameHelp = new JFrame("Help");
    
    private final ImageIcon icon;    
    private final JPanel panelMain;    
    private final JButton btnOk;
    private final JLabel lblAddHlp, lblViewHlp, lblDelHlp, lblUpdtHlp, lblSrchHlp;
    private final JTextArea taAdd, taView, taDel, taUpdt, taSrch;
    
    private final Color clrYellow = new Color(240, 230, 170);
    private final Color clrBlue = new Color(15, 70, 140);

    public Help() {
        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/help_icon.png"));
        frameHelp.setIconImage(icon.getImage());                                //setting window icon

        //setting up the frame
        frameHelp.setSize(400, 580);
        frameHelp.setLayout(new BorderLayout());                                //frame layout: Border Layout
        frameHelp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);            //setting default close operation
        frameHelp.setLocationRelativeTo(null);                                  //to center the window
        frameHelp.setResizable(false);                                          //disable window resize
        
        //setting up the panel 
        
        panelMain = new JPanel();
        panelMain.setBackground(clrYellow);                                     //background color: Yellow
        panelMain.setLayout(null);
        
        Border margin = new EmptyBorder(10, 10, 10, 10);                        //create margin for titled border
        Border blueline = BorderFactory.createLineBorder(clrBlue);              //create line for titled border
        
        TitledBorder ttlDevInfo = BorderFactory.createTitledBorder(blueline);   //create titled border
        ttlDevInfo.setTitle(" Help ");                                          //set title
        ttlDevInfo.setTitleColor(clrBlue);                                      //title color: Blue

        panelMain.setBorder(new CompoundBorder(margin, ttlDevInfo));            //set border(outer border: margin, inner border: title)
        panelMain.setBackground(clrYellow);                                     //background color: Yellow
        panelMain.setPreferredSize(new Dimension(400, 580));                    //setting panel size
        
        lblAddHlp = new JLabel();
        lblAddHlp.setText("To Add New Donor:");
        lblAddHlp.setBounds(25, 30, 340, 20);
        panelMain.add(lblAddHlp);
        
        taAdd = new JTextArea();
        taAdd.setText("Click on 'Add New Donor' button.\n"
                + "A new window will pop-up.\n"
                + "Fill all the field and click 'Add Donor' button.");
        taAdd.setBounds(25, 50, 340, 50);
        taAdd.setBorder(blueline);
        taAdd.setEditable(false);
        panelMain.add(taAdd);
        
        lblDelHlp = new JLabel();
        lblDelHlp.setText("To Delete A Donor:");
        lblDelHlp.setBounds(25, 110, 340, 20);
        panelMain.add(lblDelHlp);
        
        taDel = new JTextArea();
        taDel.setText("Select donor from donors list whose information you\n"
                + "want to delete. Then click the 'Delete' button below\n"
                + "the donors list. After confirmation donors information\n"
                + "will be deleted parmanently from database.");
        taDel.setBounds(25, 130, 340, 70);
        taDel.setBorder(blueline);
        taDel.setEditable(false);
        panelMain.add(taDel);
        
        lblSrchHlp = new JLabel();
        lblSrchHlp.setText("To Search A Donor:");
        lblSrchHlp.setBounds(25, 210, 340, 20);
        panelMain.add(lblSrchHlp);
        
        taSrch = new JTextArea();
        taSrch.setText("Select the field where data should be searched.\n"
                + "Write the information of desired donor in the textfiled.\n"
                + "After clicking 'Search' button, list of blood donors will\n"
                + "be displayed in the table.");
        taSrch.setBounds(25, 230, 340, 70);
        taSrch.setBorder(blueline);
        taSrch.setEditable(false);
        panelMain.add(taSrch);
        
        lblViewHlp = new JLabel();
        lblViewHlp.setText("To View Profile of A Donor:");
        lblViewHlp.setBounds(25, 310, 340, 20);
        panelMain.add(lblViewHlp);
        
        taView = new JTextArea();
        taView.setText("Select donor from donors list whose profile you want\n"
                + "to look up. Then click the 'View Profile' button below\n"
                + "the donors list. A new window will pop-up and donors\n"
                + "full info will be displayed there.");
        taView.setBounds(25, 330, 340, 70);
        taView.setBorder(blueline);
        taView.setEditable(false);
        panelMain.add(taView);
        
        lblUpdtHlp = new JLabel();
        lblUpdtHlp.setText("To Update Information of Exixting Donor:");
        lblUpdtHlp.setBounds(25, 410, 340, 20);
        panelMain.add(lblUpdtHlp);
        
        taUpdt = new JTextArea();
        taUpdt.setText("Open Profile of the donor whose information you want\n"
                + "to update. Click 'Edit Profile' button. Update the\n"
                + "information of desired fileds and click 'Update' button.");
        taUpdt.setBounds(25, 430, 341, 50);
        taUpdt.setBorder(blueline);
        taUpdt.setEditable(false);
        panelMain.add(taUpdt);
        
        btnOk = new JButton("OK");
        btnOk.setBackground(clrBlue);                                           //button background color: Red
        btnOk.setForeground(Color.white);                                       //button text color: White
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));                        //set button cursor
        btnOk.setBounds(150, 495, 80, 25);
        panelMain.add(btnOk);
        
        //after clicking this button this window will be closed
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frameHelp.dispose();
            }
        });
        
        //panel work done
        
        frameHelp.add(panelMain, BorderLayout.CENTER);
        //make frame visible
        frameHelp.setVisible(true);
    }
    
}
