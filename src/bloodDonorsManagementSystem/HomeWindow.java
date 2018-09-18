package bloodDonorsManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HomeWindow {

    private final ImageIcon icon, poster;

    private final JFrame frameHome = new JFrame("Blood Fighters of MU");

    private final JMenuBar menuBar;
    private final JMenu menuAbout, menuDevelopers, menuHelp, menuExit;

    private final JPanel topPanel = new JPanel();
    private final JPanel midPanel = new JPanel();
    private final JPanel bottomPanel = new JPanel();

    private final JButton btnSearch, btnAdd, btnViewProfile, btnDelete, btnRefresh;

    private final Color clrRed = new Color(195, 5, 5);
    private final Color clrLightRed = new Color(230, 105, 105);
    private final Color clrYellow = new Color(240, 230, 170);
    private final Color clrBlue = new Color(15, 70, 140);
    private final Color clrGreen = new Color(40, 155, 15);

    private final String[] column = {"SL NO", "ID", "Name", "Gender", "Age", "Weight", "Blood Group", "Last Donation", "Mobile No", "Home Division", "Home District"};

    private final JTable tblData;
    private final DefaultTableModel tblModel;

    public HomeWindow() {

        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/home_icon.png"));
        frameHome.setIconImage(icon.getImage());                                //setting window icon

        //setting up the frame
        frameHome.setSize(1000, 600);
        frameHome.setLayout(new BorderLayout());                                //frame layout: Border Layout
        frameHome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);         //setting default close operation
        frameHome.setLocationRelativeTo(null);                                  //to center the window

        menuBar = new JMenuBar();
        menuBar.setBackground(clrLightRed);
        menuBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        menuBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frameHome.setJMenuBar(menuBar);

        menuAbout = new JMenu("About");
        menuBar.add(menuAbout);

        menuDevelopers = new JMenu("Developers");
        menuBar.add(menuDevelopers);

        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);

        menuExit = new JMenu("Exit");
        menuBar.add(menuExit);
        
        menuAbout.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                new About();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        menuDevelopers.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                new Developers();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
        menuHelp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                new Help();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        menuExit.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(frameHome,
                        "Do You Really Want To Exit?", "WARNING!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(frameHome,
                        "Do You Really Want To Exit?", "WARNING!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        frameHome.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frameHome,
                        "Do You Really Want To Exit?", "WARNING!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        //setting up the topPanel
        /* divided topPanel horizontally into two part
        left part will contain an image as poster
        right panel will show current day, time and date
         */
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(1000, 180));                    //setting panel size
        topPanel.setBackground(clrRed);                                         //background color: Red

        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();

        //left panle work
        panelLeft.setLayout(new BorderLayout());
        panelLeft.setBackground(clrRed);                                        //background color: Red

        poster = new ImageIcon(getClass().getResource("images/poster.png"));           //create imageIcon for poster
        JLabel lblPoster = new JLabel();
        lblPoster.setIcon(poster);                                              //set image for poster

        panelLeft.add(lblPoster, BorderLayout.PAGE_START);
        //left panel work done

        //right panel work
        panelRight.setBorder(new EmptyBorder(50, 50, 50, 50));                  //setting panel border
        panelRight.setPreferredSize(new Dimension(350, 180));                   //setting panel size
        panelRight.setBackground(clrRed);                                       //background color: Red
        panelRight.setLayout(new BorderLayout());

        JLabel lblDay = new JLabel();
        lblDay.setFont(new Font("Sans", Font.BOLD, 20));                        //font: Sans, Style: Bold, Size: 20
        lblDay.setForeground(Color.WHITE);                                      //font color: White
        lblDay.setHorizontalAlignment(JLabel.LEFT);                             //left alignment

        JLabel lblTime = new JLabel();
        lblTime.setFont(new Font("Sans", Font.BOLD, 30));                       //font: Sans, Style: Bold, Size: 30
        lblTime.setForeground(Color.WHITE);                                     //font color: White
        lblTime.setHorizontalAlignment(JLabel.CENTER);                          //center alignment

        JLabel lblDate = new JLabel();
        lblDate.setFont(new Font("Sans", Font.BOLD, 18));                       //font: Sans, Style: Bold, Size: 18
        lblDate.setForeground(Color.WHITE);                                     //font color: White
        lblDate.setHorizontalAlignment(JLabel.RIGHT);                           //right alignment

        SimpleDateFormat ftDay = new SimpleDateFormat("EEEE");                  //setting format for day
        SimpleDateFormat ftTime = new SimpleDateFormat("hh:mm:ss a");           //setting format for time
        SimpleDateFormat ftDate = new SimpleDateFormat("dd MMMM yyyy");         //setting format for date

        ActionListener updateClockAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDay.setText(ftDay.format(new Date()));
                lblTime.setText(ftTime.format(new Date()));
                lblDate.setText(ftDate.format(new Date()));
            }
        };

        Timer time = new Timer(100, updateClockAction);                         //set timer to update time of clock
        time.start();                                                           //start the timer

        panelRight.add(lblDay, BorderLayout.PAGE_START);
        panelRight.add(lblTime, BorderLayout.CENTER);
        panelRight.add(lblDate, BorderLayout.PAGE_END);
        //right panel work done

        topPanel.add(panelLeft, BorderLayout.WEST);                             //add panelLeft to topPanel
        topPanel.add(panelRight, BorderLayout.EAST);                            //add panelRight to topPanel
        //topPanel work done

        //setting up the midPanel
        /*divide mid panel vertically into three part
        upper part will containe search and sort button
        middle part will containe doners info
        and lower part will contain some buttons
         */
        midPanel.setBackground(clrLightRed);                                    //background color: Light Red
        midPanel.setLayout(new BorderLayout());

        JPanel panelUpper = new JPanel();
        JPanel panelLower = new JPanel();

        //upper panel work
        panelUpper.setLayout(null);
        panelUpper.setPreferredSize(new Dimension(1000, 45));                   //setting panel size        
        panelUpper.setBackground(clrLightRed);                                  //background color: light red

        JLabel lblSearch = new JLabel("Search By :");
        lblSearch.setFont(new Font("Sans", Font.BOLD, 14));                     //font: Sans, Style: Bold, Size: 20
        lblSearch.setForeground(Color.BLACK);                                   //font color: black
        lblSearch.setBounds(20, 15, 100, 20);                                   //setting location and size for search label

        String search[] = {"Blood Group", "Name", "Home Division", "Home District"};
        JComboBox cbSearch = new JComboBox(search);                             //combobox for search options
        cbSearch.setBounds(120, 13, 150, 25);                                   //setting location and size for search combobox

        JTextField tfSearch = new JTextField();
        tfSearch.setFont(new Font("Sans", Font.PLAIN, 14));                     //setting font for search TextField
        tfSearch.setBounds(280, 13, 200, 25);                                   //setting location and size of TextField

        btnSearch = new JButton("Search");
        btnSearch.setBounds(490, 13, 100, 25);                                  //setting location and size of search button
        btnSearch.setBackground(Color.DARK_GRAY);                               //button background color: Dark Gray
        btnSearch.setForeground(Color.WHITE);                                   //button text color: Whit
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int option = cbSearch.getSelectedIndex();
                String value = tfSearch.getText();

                switch (option) {
                    case 0:
                        value = value.trim();
                        value = "BLOOD_GROUP LIKE '%" + value + "%'";
                        break;
                    case 1:
                        value = value.trim();
                        value = "NAME LIKE '%" + value + "%'";
                        break;
                    case 2:
                        value = value.trim();
                        value = "DIVISION LIKE '%" + value + "%'";
                        break;
                    case 3:
                        value = value.trim();
                        value = "DISTRICT LIKE '%" + value + "%'";
                        break;
                }
                showSearchedData(value);
            }
        });

        panelUpper.add(lblSearch);
        panelUpper.add(cbSearch);
        panelUpper.add(tfSearch);
        panelUpper.add(btnSearch);
        //upper panel work done

        //lower panle work
        panelLower.setLayout(new BorderLayout());
        panelLower.setBackground(clrLightRed);                                  //background color: Light Red
        panelLower.setBorder(new EmptyBorder(5, 15, 5, 15));                    //setting panel border

        tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(column);

        tblData = new JTable();                                                 //created a jTable
        tblData.setModel(tblModel);
        tblData.setBackground(clrYellow);                                       //table background color: Yellow
        tblData.setRowHeight(30);                                               //set row height
        tblData.setFont(new Font("Sans", Font.PLAIN, 14));                      //font: Sans Style: Plain Size: 14
        tblData.setDefaultEditor(Object.class, null);                           //set cell non-editable
        tblData.setAutoCreateRowSorter(true);

        //setting column width of tblData
        tblData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblData.getColumnModel().getColumn(0).setMinWidth(50);
        tblData.getColumnModel().getColumn(0).setMaxWidth(50);
        tblData.getColumnModel().getColumn(1).setMinWidth(60);
        tblData.getColumnModel().getColumn(1).setMaxWidth(60);
        tblData.getColumnModel().getColumn(2).setMinWidth(220);
        tblData.getColumnModel().getColumn(3).setMinWidth(80);
        tblData.getColumnModel().getColumn(3).setMaxWidth(80);
        tblData.getColumnModel().getColumn(4).setMinWidth(80);
        tblData.getColumnModel().getColumn(4).setMaxWidth(80);
        tblData.getColumnModel().getColumn(5).setMinWidth(80);
        tblData.getColumnModel().getColumn(5).setMaxWidth(80);
        tblData.getColumnModel().getColumn(6).setMinWidth(80);
        tblData.getColumnModel().getColumn(6).setMaxWidth(80);
        tblData.getColumnModel().getColumn(7).setMinWidth(150);
        tblData.getColumnModel().getColumn(7).setMaxWidth(150);
        tblData.getColumnModel().getColumn(8).setMinWidth(150);
        tblData.getColumnModel().getColumn(8).setMaxWidth(150);
        tblData.getColumnModel().getColumn(9).setMinWidth(180);
        tblData.getColumnModel().getColumn(10).setMinWidth(180);

        //setting table data in center alignment
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblData.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane sp = new JScrollPane(tblData);                              //added table into a scroll pane

        panelLower.add(sp, BorderLayout.CENTER);
        //lower panel work done

        //here we add an extra panle to put buttons into bottom right corner
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));                   //anchoring buttons into lower right side
        panelBtn.setBackground(clrLightRed);                                    //background color: Light Red
        panelBtn.setBorder(new EmptyBorder(0, 0, 5, 30));                       //setting panel border

        btnAdd = new JButton("Add New Donor");
        btnAdd.setBackground(clrGreen);                                         //button background color: Green
        btnAdd.setForeground(Color.WHITE);                                      //button text color: White
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));                       //set button cursor

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewDonor();
            }

        });

        btnViewProfile = new JButton("View Profile");
        btnViewProfile.setBackground(clrBlue);                                  //button background color: Blue
        btnViewProfile.setForeground(Color.white);                              //button text color: White
        btnViewProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));               //set button cursor
        btnViewProfile.setVisible(false);                                       //set button invisible

        btnViewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int row = tblData.getSelectedRow();                             //getting view index of selected row
                row = tblData.convertRowIndexToModel(row);                      //getting model index of selected row
                int id = Integer.parseInt(tblData.getModel().getValueAt(row, 1).toString());
                System.out.println("ID: " + id + " Selected");
                
                new ViewProfile(id);
            }
        });

        btnDelete = new JButton("Delete");
        btnDelete.setBackground(clrRed);                                        //button background color: Red
        btnDelete.setForeground(Color.white);                                   //button text color: White
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor
        btnDelete.setVisible(false);                                            //set button invisible

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (JOptionPane.showConfirmDialog(frameHome,
                        "Are you sure, you want to delete this data?", "WARNING!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                    int row = tblData.getSelectedRow();                         //getting view index of selected row
                    row = tblData.convertRowIndexToModel(row);                  //getting model index of selected row
                    int id = Integer.parseInt(tblData.getModel().getValueAt(row, 1).toString());
                    System.out.println("ID: " + id + " Selected");

                    Database db = new Database();
                    db.delete(id);
                    showTableData();
                }
            }
        });

        btnRefresh = new JButton("Refresh Table");
        btnRefresh.setBackground(clrYellow);                                    //button background color: Red
        btnRefresh.setForeground(Color.BLACK);                                  //button text color: White
        btnRefresh.setCursor(new Cursor(Cursor.HAND_CURSOR));                   //set button cursor

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showTableData();
            }
        });

        tblData.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int[] indexs = tblData.getSelectedRows();

                //set btnViewProfile & btnDelete visible if exactly 1 row is selected
                if (indexs.length == 1) {
                    btnViewProfile.setVisible(true);
                    btnDelete.setVisible(true);
                } else {
                    btnViewProfile.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });

        panelBtn.add(btnViewProfile);
        panelBtn.add(btnDelete);
        panelBtn.add(btnRefresh);
        panelBtn.add(btnAdd);
        //button panel work done

        midPanel.add(panelUpper, BorderLayout.NORTH);
        midPanel.add(panelLower, BorderLayout.CENTER);
        midPanel.add(panelBtn, BorderLayout.SOUTH);
        //midPanel work done

        //setting up the bottomPanel
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));                    //setting border for bottom panel
        bottomPanel.setPreferredSize(new Dimension(1000, 50));                  //setting panel size
        bottomPanel.setBackground(clrRed);                                      //background color: Red

        JLabel lblFooter = new JLabel();

        String footer = "“Share A Little, Care A Little – Donate Blood.”";
        lblFooter.setText(footer);                                              //set text of footnote
        lblFooter.setFont(new Font("Serif", Font.ITALIC, 18));                  //font: Serif, Style: Italic, Size: 18
        lblFooter.setForeground(Color.WHITE);                                   //cont color: White

        bottomPanel.add(lblFooter);                                             //add footnote to panel
        //bottomPanel work done                

        //adding panels into frame
        frameHome.add(topPanel, BorderLayout.PAGE_START);
        frameHome.add(midPanel, BorderLayout.CENTER);
        frameHome.add(bottomPanel, BorderLayout.PAGE_END);

        //make frame visible
        frameHome.setVisible(true);

        //show table data
        showTableData();
    }

    //this method retrive data from database and show them in table
    public void showTableData() {
        ArrayList<Donor> donor = new ArrayList();
        try {
            Database db = new Database();
            donor = db.getDataForTable();

            int slNo = 1;
            tblModel.setRowCount(0);
            for (int i = 0; i < donor.size(); ++i) {
                Vector newRow = new Vector();
                newRow.addElement(slNo);
                newRow.addElement(donor.get(i).getID());
                newRow.addElement(donor.get(i).getName());
                newRow.addElement(donor.get(i).getGender());
                newRow.addElement(donor.get(i).getAge());
                newRow.addElement(donor.get(i).getWeight());
                newRow.addElement(donor.get(i).getBloodGroup());
                newRow.addElement(donor.get(i).getLastDonation());
                newRow.addElement(donor.get(i).getMobileNo());
                newRow.addElement(donor.get(i).getDivision());
                newRow.addElement(donor.get(i).getDistrict());
                tblModel.addRow(newRow);

                ++slNo;
            }
            if (slNo == 1) {
                JOptionPane.showMessageDialog(frameHome, "No Data Found!", "NO DATA!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException ex) {
            System.out.println("HomeWindow.showTableData(): " + ex);
            ex.printStackTrace();
        }
    }

    //this method show search result on jtable
    public void showSearchedData(String value) {
        ArrayList<Donor> donor = new ArrayList();
        try {
            Database db = new Database();
            donor = db.search(value);

            int slNo = 1;
            tblModel.setRowCount(0);
            for (int i = 0; i < donor.size(); ++i) {
                Vector newRow = new Vector();
                newRow.addElement(slNo);
                newRow.addElement(donor.get(i).getID());
                newRow.addElement(donor.get(i).getName());
                newRow.addElement(donor.get(i).getGender());
                newRow.addElement(donor.get(i).getAge());
                newRow.addElement(donor.get(i).getWeight());
                newRow.addElement(donor.get(i).getBloodGroup());
                newRow.addElement(donor.get(i).getLastDonation());
                newRow.addElement(donor.get(i).getMobileNo());
                newRow.addElement(donor.get(i).getDivision());
                newRow.addElement(donor.get(i).getDistrict());
                tblModel.addRow(newRow);

                ++slNo;
            }
            if (slNo == 1) {
                JOptionPane.showMessageDialog(frameHome, "No Data Found!", "NO DATA!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException ex) {
            System.out.println("HomeWindow.showTableData(): " + ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        new HomeWindow();
    }

}
