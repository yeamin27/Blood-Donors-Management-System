package bloodDonorsManagementSystem;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

public class ViewProfile {

    private final ImageIcon icon;

    private final JFrame frameViewProfile = new JFrame("View Profile");
    private final CardLayout cardLayout = new CardLayout();

    private final Color clrRed = new Color(195, 5, 5);
    private final Color clrBlue = new Color(15, 70, 140);
    private final Color clrYellow = new Color(240, 230, 180);
    private final Color clrCyan = new Color(130, 185, 220);

    private final Border margin = new EmptyBorder(10, 10, 10, 10);
    private final Border blueline = BorderFactory.createLineBorder(clrBlue);

    private final JPanel mainPanle = new JPanel();
    private final JPanel cardView = new JPanel();
    private final JPanel cardEdit = new JPanel();

    private final JPanel c1leftPanel = new JPanel();
    private final JPanel c2leftPanel = new JPanel();
    private final JPanel c1rightPanel = new JPanel();
    private final JPanel c2rightPanel = new JPanel();

    private JButton btnEdit, btnBack, btnCancel, btnUpdate, btnChoose;

    private JLabel c1lblName, c1lblFatherName, c1lblMotherName, c1lblGender, c1lblBloodGroup, c1lblAge, c1lblWeight, c1lblMaritalStts, c1lblNID, c1lblLastDonation, c1lblPrevDisease, c1lblMobile, c1lblDistrict, c1lblDivision, c1lblAddress, c1lblImage;
    private JLabel lblName, lblFatherName, lblMotherName, lblGender, lblBloodGroup, lblAge, lblWeight, lblMaritalStts, lblNID, lblStatement, lblLastDonation, lblLstDontnEx, lblPrevDisease, lblMobile, lblDistrict, lblDivision, lblAddress, lblImage;

    private JTextField c1tfName, c1tfFName, c1tfMName, c1tfGender, c1tfAge, c1tfWeight;
    private JTextField c1tfBloodGroup, c1tfMaritalStatus, c1tfNid, c1tfLastDonation;
    private JTextField c1tfMobile, c1tfDivision, c1tfDistrict;
    private JTextArea c1taDisease, c1taAddress;

    private JTextField tfName, tfFatherName, tfMotherName, tfWeight;
    private JTextArea taDiseas, taAddress;
    private JComboBox cbGender, cbBloodGroup, cbMaritalStts, cbDivision, cbDistrict;
    private JSpinner spAge;
    private SpinnerNumberModel ages;
    private JCheckBox chbSttmnt;
    private JDateChooser dcLstDonation;
    private MaskFormatter mskMobile, mskNID;
    private JFormattedTextField tfMobile, tfNID;

    private final String divisions[] = {"Barisal", "Chittagong", "Dhaka", "Khulna", "Mymensingh", "Rajshahi", "Rangpur", "Sylhet"};

    private final String districts[][] = {{"Pirojpur", "Patuakhali", "Jhalokati", "Bhola", "Barisal", "Barguna"},
    {"Rangamati", "Noakhali", "Lakshmipur", "Khagrachhari", "Feni", "Cox's Bazar", "Comilla", "Chittagong", "Chandpur", "Brahmanbaria", "Bandarban"},
    {"Tangail", "Shariatpur", "Rajbari", "Narsingdi", "Narayanganj", "Munshiganj", "Manikganj", "Madaripur", "Kishoreganj", "Gopalganj", "Gazipur", "Faridpur", "Dhaka"},
    {"Satkhira", "Narail", "Meherpur", "Magura", "Kushtia", "Khulna", "Jhenaidah", "Jessore", "Chuadanga", "Bagerhat"},
    {"Sherpur", "Netrokona", "Mymensingh", "Jamalpur"},
    {"Sirajganj", "Rajshahi", "Pabna", "Natore", "Naogaon", "Joypurhat", "Chapai Nawabganj", "Bogra"},
    {"Thakurgaon", "Rangpur", "Panchagarh", "Nilphamari", "Lalmonirhat", "Kurigram", "Gaibandha", "Dinajpur"},
    {"Sylhet", "Sunamganj", "Moulvibazar", "Habiganj"}};

    private final String bloodGroups[] = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    private final String genders[] = {"Male", "Female"};
    private final String maritalSttss[] = {"Single", "Married"};

    private String imagePath;
    private boolean imageChanged;

    private String name, fname, mname, gender, bloodGroup, weight, maritalStts, nid, disease, mobile, division, district, address;
    private InputStream isImage;
    private Date date;
    private int age, id;

    public ViewProfile(int id) {

        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/view_profile_icon.png"));
        frameViewProfile.setIconImage(icon.getImage());                         //set frame icon

        //setting up the frame
        frameViewProfile.setSize(600, 540);
        frameViewProfile.setLayout(new BorderLayout());                         //frame layout: Border Layout
        frameViewProfile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     //setting default close operation
        frameViewProfile.setResizable(false);                                   //disable resize button
        frameViewProfile.setLocationRelativeTo(null);                           //to center the window

        mainPanle.setLayout(cardLayout);
        cardView.setLayout(new BorderLayout());
        cardEdit.setLayout(new BorderLayout());

        this.id = id;

        getProfile(id);
        initCard1();
        initCard2();

        mainPanle.add(cardView, "view");
        mainPanle.add(cardEdit, "edit");

        frameViewProfile.add(mainPanle);

        //make frame visible
        frameViewProfile.setVisible(true);
    }

    private void initCard1() {
        //setting up left panel        
        TitledBorder ttlPersonInfo = BorderFactory.createTitledBorder(blueline);//create titled border
        ttlPersonInfo.setTitle(" Personal Information ");                       //set title
        ttlPersonInfo.setTitleColor(clrBlue);                                   //title color: Blue

        c1leftPanel.setBorder(new CompoundBorder(margin, ttlPersonInfo));       //set border(outer border: margin, inner border: title)
        c1leftPanel.setBackground(clrYellow);                                   //background color: Yellow
        c1leftPanel.setPreferredSize(new Dimension(300, 540));                  //setting panel size
        c1leftPanel.setLayout(null);

        c1lblName = new JLabel("Name:");
        c1lblName.setBounds(30, 30, 100, 20);
        c1leftPanel.add(c1lblName);

        c1tfName = new JTextField(name);
        c1tfName.setEditable(false);
        c1tfName.setBounds(30, 50, 240, 25);
        c1leftPanel.add(c1tfName);

        c1lblFatherName = new JLabel("Father's Name:");
        c1lblFatherName.setBounds(30, 80, 150, 20);
        c1leftPanel.add(c1lblFatherName);

        c1tfFName = new JTextField(fname);
        c1tfFName.setEditable(false);
        c1tfFName.setBounds(30, 100, 240, 25);
        c1leftPanel.add(c1tfFName);

        c1lblMotherName = new JLabel("Mother's Name:");
        c1lblMotherName.setBounds(30, 130, 150, 20);
        c1leftPanel.add(c1lblMotherName);

        c1tfMName = new JTextField(mname);
        c1tfMName.setEditable(false);
        c1tfMName.setBounds(30, 150, 240, 25);
        c1leftPanel.add(c1tfMName);

        c1lblGender = new JLabel("Gender:");
        c1lblGender.setBounds(30, 180, 80, 20);
        c1leftPanel.add(c1lblGender);

        c1tfGender = new JTextField(gender);
        c1tfGender.setEditable(false);
        c1tfGender.setBounds(30, 200, 80, 25);
        c1leftPanel.add(c1tfGender);

        c1lblAge = new JLabel("Age:");
        c1lblAge.setBounds(120, 180, 50, 20);
        c1leftPanel.add(c1lblAge);

        c1tfAge = new JTextField(String.valueOf(age));
        c1tfAge.setEditable(false);
        c1tfAge.setBounds(120, 200, 50, 25);
        c1leftPanel.add(c1tfAge);

        c1lblWeight = new JLabel("Weight (KG):");
        c1lblWeight.setBounds(180, 180, 90, 20);
        c1leftPanel.add(c1lblWeight);

        c1tfWeight = new JTextField(weight);
        c1tfWeight.setEditable(false);
        c1tfWeight.setBounds(180, 200, 90, 25);
        c1leftPanel.add(c1tfWeight);

        c1lblBloodGroup = new JLabel("Blood Group:");
        c1lblBloodGroup.setBounds(30, 230, 100, 20);
        c1leftPanel.add(c1lblBloodGroup);

        c1tfBloodGroup = new JTextField(bloodGroup);
        c1tfBloodGroup.setEditable(false);
        c1tfBloodGroup.setBounds(30, 250, 100, 25);
        c1leftPanel.add(c1tfBloodGroup);

        c1lblMaritalStts = new JLabel("Marital Status:");
        c1lblMaritalStts.setBounds(160, 230, 110, 20);
        c1leftPanel.add(c1lblMaritalStts);

        c1tfMaritalStatus = new JTextField(maritalStts);
        c1tfMaritalStatus.setEditable(false);
        c1tfMaritalStatus.setBounds(160, 250, 110, 25);
        c1leftPanel.add(c1tfMaritalStatus);

        c1lblNID = new JLabel("NID: (18 digit)");
        c1lblNID.setBounds(30, 280, 100, 20);
        c1leftPanel.add(c1lblNID);

        c1tfNid = new JTextField(nid);
        c1tfNid.setEditable(false);
        c1tfNid.setBounds(30, 300, 240, 25);
        c1leftPanel.add(c1tfNid);

        c1lblLastDonation = new JLabel("Last Donation Date:");
        c1lblLastDonation.setBounds(30, 330, 200, 20);
        c1leftPanel.add(c1lblLastDonation);

        c1tfLastDonation = new JTextField("");
        if (date != null) {
            c1tfLastDonation.setText(date.toString());
        }
        c1tfLastDonation.setEditable(false);
        c1tfLastDonation.setBounds(30, 350, 240, 25);
        c1leftPanel.add(c1tfLastDonation);

        c1lblPrevDisease = new JLabel("Previous Diseases Report:");
        c1lblPrevDisease.setBounds(30, 380, 250, 20);
        c1leftPanel.add(c1lblPrevDisease);

        c1taDisease = new JTextArea(disease);
        c1taDisease.setEditable(false);
        c1taDisease.setLineWrap(true);

        JScrollPane scrlPaneDss = new JScrollPane();
        scrlPaneDss.setBounds(30, 400, 240, 80);
        scrlPaneDss.setViewportView(c1taDisease);
        c1leftPanel.add(scrlPaneDss);
        //left panel work done

        //setting up right panel
        c1rightPanel.setBackground(clrYellow);                                  //background color: Yellow
        c1rightPanel.setPreferredSize(new Dimension(300, 540));                 //setting panel size
        c1rightPanel.setLayout(new BorderLayout());

        //we divided right panel into three part vertically
        //upper panel will contain donors picture
        //mid panel will contain contact info
        //and lower panel will contain buttons
        JPanel upperPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        //upper panel work
        TitledBorder ttlPicture = BorderFactory.createTitledBorder(blueline);   //create titled border
        ttlPicture.setTitle(" Donor's Picture ");                               //set title
        ttlPicture.setTitleColor(clrBlue);                                      //title color: Blue

        upperPanel.setBorder(new CompoundBorder(margin, ttlPicture));           //set border(outer border: margin, inner border: title)
        upperPanel.setBackground(clrYellow);                                    //background color: Yellow
        upperPanel.setPreferredSize(new Dimension(300, 275));                   //setting panel size
        upperPanel.setLayout(null);

        c1lblImage = new JLabel();
        c1lblImage.setBounds(25, 30, 250, 225);
        c1lblImage.setBorder(blueline);

        BufferedImage bf = null;
        Database db = new Database();
        isImage = db.getImage(id);
        try {
            bf = ImageIO.read(isImage);
        } catch (IOException ex) {
            System.out.println("ViewProfile.initCard1():");
            ex.printStackTrace();
        }
        c1lblImage.setIcon(resizeImage(bf));
        upperPanel.add(c1lblImage);
        //upper panel work done

        //mid panel work
        TitledBorder ttlContctInfo = BorderFactory.createTitledBorder(blueline);//create titled border

        ttlContctInfo.setTitle(" Contact Information ");                        //set title
        ttlContctInfo.setTitleColor(clrBlue);                                   //title color: Blue

        midPanel.setBorder(new CompoundBorder(
                new EmptyBorder(0, 10, 0, 10), ttlContctInfo));                 //set border(outer border: margin, inner border: title)
        midPanel.setBackground(clrYellow);                                      //background color: Yellow
        midPanel.setPreferredSize(new Dimension(300, 165));                     //setting panel size
        midPanel.setLayout(null);

        c1lblMobile = new JLabel("Mobile No:");
        c1lblMobile.setBounds(30, 20, 100, 20);
        midPanel.add(c1lblMobile);

        c1tfMobile = new JTextField(mobile);
        c1tfMobile.setEditable(false);
        c1tfMobile.setBounds(30, 40, 240, 25);
        midPanel.add(c1tfMobile);

        c1lblDivision = new JLabel("Home Division:");
        c1lblDivision.setBounds(30, 70, 120, 20);
        midPanel.add(c1lblDivision);

        c1tfDivision = new JTextField(division);
        c1tfDivision.setEditable(false);
        c1tfDivision.setBounds(30, 90, 115, 25);
        midPanel.add(c1tfDivision);

        c1lblDistrict = new JLabel("Home District:");
        c1lblDistrict.setBounds(150, 70, 120, 20);
        midPanel.add(c1lblDistrict);

        c1tfDistrict = new JTextField(district);
        c1tfDistrict.setEditable(false);
        c1tfDistrict.setBounds(150, 90, 120, 25);
        midPanel.add(c1tfDistrict);

        c1lblAddress = new JLabel("Address:");
        c1lblAddress.setBounds(30, 120, 100, 20);
        midPanel.add(c1lblAddress);

        c1taAddress = new JTextArea(address);
        c1taAddress.setEditable(false);
        c1taAddress.setLineWrap(true);

        JScrollPane scrlPaneAdrs = new JScrollPane();
        scrlPaneAdrs.setBounds(30, 140, 240, 35);
        scrlPaneAdrs.setViewportView(c1taAddress);

        midPanel.add(scrlPaneAdrs);
        //mid panel work done

        //lower panel work
        lowerPanel.setBackground(clrYellow);                                    //background color: Yellow
        lowerPanel.setPreferredSize(new Dimension(300, 50));                    //setting panel size
        lowerPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        lowerPanel.setLayout(new BorderLayout());

        btnEdit = new JButton("Edit Profile");
        btnEdit.setBackground(clrBlue);                                         //button background color: Blue
        btnEdit.setForeground(Color.WHITE);                                     //button text color: White
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));                      //set button cursor

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.show(mainPanle, "edit");
            }
        });

        btnBack = new JButton("Back");
        btnBack.setBackground(clrBlue);                                       //button background color: clrRed
        btnBack.setForeground(Color.WHITE);                                   //button text color: White
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frameViewProfile.dispose();
            }

        });

        lowerPanel.add(btnEdit, BorderLayout.WEST);
        lowerPanel.add(btnBack, BorderLayout.EAST);
        //lowr panel work done

        c1rightPanel.add(upperPanel, BorderLayout.NORTH);
        c1rightPanel.add(midPanel, BorderLayout.CENTER);
        c1rightPanel.add(lowerPanel, BorderLayout.SOUTH);
        //right panle work done

        cardView.add(c1leftPanel, BorderLayout.WEST);
        cardView.add(c1rightPanel, BorderLayout.EAST);
    }

    private void initCard2() {
        //setting up left panel        
        TitledBorder ttlPersonInfo = BorderFactory.createTitledBorder(blueline);//create titled border
        ttlPersonInfo.setTitle(" Personal Information ");                       //set title
        ttlPersonInfo.setTitleColor(clrBlue);                                   //title color: Blue

        c2leftPanel.setBorder(new CompoundBorder(margin, ttlPersonInfo));       //set border(outer border: margin, inner border: title)
        c2leftPanel.setBackground(clrYellow);                                   //background color: Yellow
        c2leftPanel.setPreferredSize(new Dimension(300, 540));                  //setting panel size
        c2leftPanel.setLayout(null);

        lblName = new JLabel("Name:");
        lblName.setBounds(30, 30, 100, 20);
        c2leftPanel.add(lblName);

        tfName = new JTextField(name);
        tfName.setBounds(30, 50, 240, 25);
        c2leftPanel.add(tfName);

        lblFatherName = new JLabel("Father's Name:");
        lblFatherName.setBounds(30, 80, 150, 20);
        c2leftPanel.add(lblFatherName);

        tfFatherName = new JTextField(fname);
        tfFatherName.setBounds(30, 100, 240, 25);
        c2leftPanel.add(tfFatherName);

        lblMotherName = new JLabel("Mother's Name:");
        lblMotherName.setBounds(30, 130, 150, 20);
        c2leftPanel.add(lblMotherName);

        tfMotherName = new JTextField(mname);
        tfMotherName.setBounds(30, 150, 240, 25);
        c2leftPanel.add(tfMotherName);

        lblGender = new JLabel("Gender:");
        lblGender.setBounds(30, 180, 80, 20);
        c2leftPanel.add(lblGender);

        cbGender = new JComboBox(genders);
        cbGender.setBounds(30, 200, 80, 25);
        if (!gender.trim().equals("")) {
            cbGender.setSelectedItem(gender);
        }
        c2leftPanel.add(cbGender);

        lblAge = new JLabel("Age:");
        lblAge.setBounds(120, 180, 50, 20);
        c2leftPanel.add(lblAge);

        ages = new SpinnerNumberModel(18, 18, 65, 1);
        spAge = new JSpinner(ages);
        spAge.setBounds(120, 200, 50, 25);
        if (!String.valueOf(age).trim().equals("")) {
            spAge.setValue(age);
        }
        c2leftPanel.add(spAge);

        lblWeight = new JLabel("Weight (KG):");
        lblWeight.setBounds(180, 180, 90, 20);
        c2leftPanel.add(lblWeight);

        tfWeight = new JTextField(weight);
        tfWeight.setBounds(180, 200, 90, 25);
        c2leftPanel.add(tfWeight);

        lblBloodGroup = new JLabel("Blood Group:");
        lblBloodGroup.setBounds(30, 230, 100, 20);
        c2leftPanel.add(lblBloodGroup);

        cbBloodGroup = new JComboBox(bloodGroups);
        cbBloodGroup.setBounds(30, 250, 100, 25);
        if (!bloodGroup.trim().equals("")) {
            cbBloodGroup.setSelectedItem(bloodGroup);
        }
        c2leftPanel.add(cbBloodGroup);

        lblMaritalStts = new JLabel("Marital Status:");
        lblMaritalStts.setBounds(160, 230, 110, 20);
        c2leftPanel.add(lblMaritalStts);

        cbMaritalStts = new JComboBox(maritalSttss);
        cbMaritalStts.setBounds(160, 250, 110, 25);
        if (!maritalStts.trim().equals("")) {
            cbMaritalStts.setSelectedItem(maritalStts);
        }
        c2leftPanel.add(cbMaritalStts);

        lblNID = new JLabel("NID: (18 digit)");
        lblNID.setBounds(30, 280, 100, 20);
        c2leftPanel.add(lblNID);

        mskNID = new MaskFormatter();
        try {
            mskNID.setMask("##################");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frameViewProfile, "'NID' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println("ViewProfile.initCard2():");
            ex.printStackTrace();
        }
        tfNID = new JFormattedTextField(mskNID);
        tfNID.setText(nid);
        tfNID.setBounds(30, 300, 240, 25);
        c2leftPanel.add(tfNID);

        chbSttmnt = new JCheckBox();
        chbSttmnt.setBounds(30, 330, 20, 20);
        chbSttmnt.setBackground(clrYellow);
        c2leftPanel.add(chbSttmnt);

        lblStatement = new JLabel("I have donated blood earlier.");
        lblStatement.setBounds(55, 330, 300, 20);
        lblStatement.setForeground(clrBlue);
        c2leftPanel.add(lblStatement);

        lblLastDonation = new JLabel("Last Donation Date:*");
        lblLastDonation.setBounds(30, 350, 200, 20);
        lblLastDonation.setEnabled(false);
        c2leftPanel.add(lblLastDonation);

        lblLstDontnEx = new JLabel("(e.g. 05/04/2018)");
        lblLstDontnEx.setBounds(30, 370, 200, 20);
        lblLstDontnEx.setForeground(Color.GRAY);
        lblLstDontnEx.setEnabled(false);
        c2leftPanel.add(lblLstDontnEx);

        dcLstDonation = new JDateChooser();
        dcLstDonation.setBounds(30, 390, 240, 25);
        dcLstDonation.setDateFormatString("dd/MM/yyyy");
        dcLstDonation.setMaxSelectableDate(new Date());
        dcLstDonation.setEnabled(false);
        c2leftPanel.add(dcLstDonation);

        lblPrevDisease = new JLabel("Previous Diseases Report (If Any):");
        lblPrevDisease.setBounds(30, 420, 250, 20);
        c2leftPanel.add(lblPrevDisease);

        taDiseas = new JTextArea(disease);
        taDiseas.setLineWrap(true);

        JScrollPane scrlPaneDss = new JScrollPane();
        scrlPaneDss.setBounds(30, 440, 240, 40);
        scrlPaneDss.setViewportView(taDiseas);
        c2leftPanel.add(scrlPaneDss);

        if (date != null) {
            chbSttmnt.setSelected(true);
            dcLstDonation.setDate(date);
            lblLstDontnEx.setEnabled(true);
            dcLstDonation.setEnabled(true);
        }

        chbSttmnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (chbSttmnt.isSelected()) {
                    lblLastDonation.setEnabled(true);
                    lblLstDontnEx.setEnabled(true);
                    dcLstDonation.setEnabled(true);
                } else {
                    lblLastDonation.setEnabled(false);
                    lblLstDontnEx.setEnabled(false);
                    dcLstDonation.setDate(null);
                    dcLstDonation.setEnabled(false);
                }
            }
        });

        //left panel work done
        //setting up right panel
        c2rightPanel.setBackground(clrYellow);                                  //background color: Yellow
        c2rightPanel.setPreferredSize(new Dimension(300, 540));                 //setting panel size
        c2rightPanel.setLayout(new BorderLayout());

        //we divided right panel into three part vertically
        //upper panel will contain donors picture
        //mid panel will contain contact info
        //and lower panel will contain buttons
        JPanel upperPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        //upper panel work
        TitledBorder ttlChoosePic = BorderFactory.createTitledBorder(blueline); //create titled border
        ttlChoosePic.setTitle(" Choose Picture ");                              //set title
        ttlChoosePic.setTitleColor(clrBlue);                                    //title color: Blue

        upperPanel.setBorder(new CompoundBorder(margin, ttlChoosePic));         //set border(outer border: margin, inner border: title)
        upperPanel.setBackground(clrYellow);                                    //background color: Yellow
        upperPanel.setPreferredSize(new Dimension(300, 275));                   //setting panel size
        upperPanel.setLayout(null);

        lblImage = new JLabel();
        lblImage.setBounds(40, 30, 220, 205);
        lblImage.setBorder(blueline);
        
        BufferedImage bf = null;
        Database db = new Database();
        isImage = db.getImage(id);
        try {
            bf = ImageIO.read(isImage);
        } catch (IOException ex) {
            System.out.println("ViewProfile.initCard2()");
            ex.printStackTrace();
        }
        lblImage.setIcon(resizeImage1(bf));
        upperPanel.add(lblImage);

        btnChoose = new JButton("Select Picture");
        btnChoose.setBounds(80, 240, 150, 20);
        btnChoose.setBackground(clrCyan);
        btnChoose.setForeground(Color.WHITE);
        upperPanel.add(btnChoose);

        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //create a file chooser to select image
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                //filter the files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
                fileChooser.addChoosableFileFilter(filter);

                imagePath = null;
                imageChanged = false;
                int result = fileChooser.showSaveDialog(null);
                //if the user click on save in Jfilechooser
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imageChanged = true;
                    imagePath = selectedFile.getAbsolutePath();
                    lblImage.setIcon(resizeImage(imagePath));
                }//if the user click on cancel in Jfilechooser
                else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("No File Selected");
                }
            }

        });
        //upper panel work done

        //mid panel work
        TitledBorder ttlContctInfo = BorderFactory.createTitledBorder(blueline);//create titled border

        ttlContctInfo.setTitle(" Contact Information ");                        //set title
        ttlContctInfo.setTitleColor(clrBlue);                                   //title color: Blue

        midPanel.setBorder(new CompoundBorder(
                new EmptyBorder(0, 10, 0, 10), ttlContctInfo));                 //set border(outer border: margin, inner border: title)
        midPanel.setBackground(clrYellow);                                      //background color: Yellow
        midPanel.setPreferredSize(new Dimension(300, 165));                     //setting panel size
        midPanel.setLayout(null);

        lblMobile = new JLabel("Mobile No:");
        lblMobile.setBounds(30, 20, 100, 20);
        midPanel.add(lblMobile);

        mskMobile = new MaskFormatter();
        try {
            mskMobile.setMask("+8801#########");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frameViewProfile, "'Mobile No' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println("ViewProfile.initCard2()");
            ex.printStackTrace();
        }
        tfMobile = new JFormattedTextField(mskMobile);
        tfMobile.setText(mobile);
        tfMobile.setBounds(30, 40, 240, 25);
        midPanel.add(tfMobile);

        lblDivision = new JLabel("Home Division:");
        lblDivision.setBounds(30, 70, 120, 20);
        midPanel.add(lblDivision);

        cbDivision = new JComboBox(divisions);
        cbDivision.setBounds(30, 90, 115, 25);
        if (!division.trim().equals("")) {
            cbDivision.setSelectedItem(division);
        }
        midPanel.add(cbDivision);

        lblDistrict = new JLabel("Home District:");
        lblDistrict.setBounds(150, 70, 120, 20);
        midPanel.add(lblDistrict);

        cbDistrict = new JComboBox();
        cbDistrict.setBounds(150, 90, 120, 25);
        for (int i = 0; i < districts[0].length; ++i) {
            cbDistrict.addItem(districts[0][i]);
        }
        if (!district.trim().equals("")) {
            cbDistrict.removeAllItems();
            int index = cbDivision.getSelectedIndex();
            for (int i = 0; i < districts[index].length; ++i) {
                cbDistrict.addItem(districts[index][i]);
            }
            cbDistrict.setSelectedItem(district);
        }
        midPanel.add(cbDistrict);

        lblAddress = new JLabel("Address:");
        lblAddress.setBounds(30, 120, 100, 20);
        midPanel.add(lblAddress);

        taAddress = new JTextArea(address);
        taAddress.setLineWrap(true);

        JScrollPane scrlPaneAdrs = new JScrollPane();
        scrlPaneAdrs.setBounds(30, 140, 240, 35);
        scrlPaneAdrs.setViewportView(taAddress);

        midPanel.add(scrlPaneAdrs);

        cbDivision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbDistrict.removeAllItems();
                int index = cbDivision.getSelectedIndex();
                for (int i = 0; i < districts[index].length; ++i) {
                    cbDistrict.addItem(districts[index][i]);
                }
            }
        });
        //mid panel work done

        //lower panel work
        lowerPanel.setBackground(clrYellow);                                    //background color: Yellow
        lowerPanel.setPreferredSize(new Dimension(300, 50));                    //setting panel size
        lowerPanel.setBorder(new EmptyBorder(10, 30, 10, 30));
        lowerPanel.setLayout(new BorderLayout());

        btnUpdate = new JButton("Update Profile");
        btnUpdate.setBackground(clrBlue);                                       //button background color: Blue
        btnUpdate.setForeground(Color.WHITE);                                   //button text color: White
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean ok = true;

                String uname = tfName.getText();
                String ufname = tfFatherName.getText();
                String umname = tfMotherName.getText();
                String ugender = cbGender.getSelectedItem().toString();
                String ubloodGroup = cbBloodGroup.getSelectedItem().toString();
                String uweight = tfWeight.getText();
                String umaritalStts = cbMaritalStts.getSelectedItem().toString();
                String unid = tfNID.getText();
                String udisease = taDiseas.getText();
                String umobile = tfMobile.getText();
                String udivision = cbDivision.getSelectedItem().toString();
                String udistrict = cbDistrict.getSelectedItem().toString();
                String uaddress = taAddress.getText();
                Date udate = dcLstDonation.getDate();
                int uage = (int) spAge.getModel().getValue();

                InputStream uisImage = null;
                if (!imageChanged) {
                    uisImage = db.getImage(id);
                } else {
                    try {
                        uisImage = Files.newInputStream(Paths.get(imagePath));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frameViewProfile, "'Picture' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
                        System.out.println("ViewProfile.initCard2.actionPerformed()" + ex);
                        ok = false;
                    }
                }

                if (!tfWeight.getText().isEmpty()) {
                    try {
                        Float.parseFloat(tfWeight.getText());
                    } catch (NumberFormatException ex) {
                        ok = false;
                        tfWeight.setText("");
                        JOptionPane.showMessageDialog(frameViewProfile, "'Weight' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
                        System.out.println("ViewProfile.tfWeight.getText(): " + ex);
                    }
                }

                if (chbSttmnt.isSelected() && udate == null) {
                    ok = false;
                    JOptionPane.showMessageDialog(frameViewProfile, "'Last Donation Date' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
                }

                if (uname.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (uname.length() > 30) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Name' is too long (MAX 30 character).", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ufname.equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Father's Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ufname.length() > 30) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Father's Name' is too long (MAX 30 character)", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (umname.length() > 30) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Mother's Name' is too long (MAX 30 character)", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (umobile.trim().equals("+8801")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Mobile No' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (uweight.trim().equals("")) {
                    JOptionPane.showMessageDialog(frameViewProfile, "'Weight' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
                } else if (ok == true) {
                    Donor donor = new Donor();
                    try {
                        donor.setID(id);
                        donor.setName(uname);
                        donor.setFathersName(ufname);
                        donor.setMothersName(umname);
                        donor.setAge(uage);
                        donor.setWeight(uweight);
                        donor.setGender(ugender);
                        donor.setBloodGroup(ubloodGroup);
                        donor.setLastDonation(udate);
                        donor.setDisease(udisease);
                        donor.setMaritalStatus(umaritalStts);
                        donor.setNID(unid);
                        donor.setMobileNo(umobile);
                        donor.setDivision(udivision);
                        donor.setDistrict(udistrict);
                        donor.setAddress(uaddress);
                        donor.setPicture(uisImage);

                        Database db = new Database();
                        db.update(donor);

                        JOptionPane.showMessageDialog(frameViewProfile, "Profile Updated Successfully.", "Messege.", JOptionPane.INFORMATION_MESSAGE);
                        btnCancel.setText("Done");
                        refreshProfile(id);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frameViewProfile, "Something Went Wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
                        System.out.println("ViewProfile.update():");
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(clrRed);                                        //button background color: clrRed
        btnCancel.setForeground(Color.WHITE);                                   //button text color: White
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanle, "view");
            }
        });

        lowerPanel.add(btnUpdate, BorderLayout.WEST);
        lowerPanel.add(btnCancel, BorderLayout.EAST);
        //lowr panel work done

        c2rightPanel.add(upperPanel, BorderLayout.NORTH);
        c2rightPanel.add(midPanel, BorderLayout.CENTER);
        c2rightPanel.add(lowerPanel, BorderLayout.SOUTH);
        //right panle work done

        cardEdit.add(c2leftPanel, BorderLayout.WEST);
        cardEdit.add(c2rightPanel, BorderLayout.EAST);
    }

    public ImageIcon resizeImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();

        image = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    public ImageIcon resizeImage(BufferedImage bf) {
        ImageIcon imageIcon = new ImageIcon(bf);
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(c1lblImage.getWidth(), c1lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon retImage = new ImageIcon(image);
        return retImage;
    }

    public ImageIcon resizeImage1(BufferedImage bf) {
        ImageIcon imageIcon = new ImageIcon(bf);
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon retImage = new ImageIcon(image);
        return retImage;
    }

    private void getProfile(int id) {
        Donor donor;
        Database db = new Database();
        try {
            donor = db.viewProfile(id);

            name = donor.getName();
            fname = donor.getFathersName();
            mname = donor.getMothersName();
            gender = donor.getGender();
            age = donor.getAge();
            weight = donor.getWeight();
            bloodGroup = donor.getBloodGroup();
            maritalStts = donor.getMaritalStatus();
            nid = donor.getNID();
            date = donor.getLastDonation();
            disease = donor.getDiseases();
            mobile = donor.getMobileNo();
            division = donor.getDivision();
            district = donor.getDistrict();
            address = donor.getAddress();
            isImage = donor.getPicture();
        } catch (HeadlessException ex) {
            System.out.println("ViewProfile.getProfile(int id): " + ex);
            ex.printStackTrace();
        }
    }

    //this method refresh the profile during view mode
    private void refreshProfile(int id) {
        getProfile(id);
        try {
            c1tfName.setText(name);
            c1tfFName.setText(fname);
            c1tfMName.setText(mname);
            c1tfGender.setText(gender);
            c1tfAge.setText(String.valueOf(age));
            c1tfWeight.setText(weight);
            c1tfBloodGroup.setText(bloodGroup);
            c1tfMaritalStatus.setText(maritalStts);
            c1tfNid.setText(nid);
            if (date != null) {
                c1tfLastDonation.setText(date.toString());
            } else if (date == null) {
                c1tfLastDonation.setText("");
            }
            c1taDisease.setText(disease);
            c1tfMobile.setText(mobile);
            c1tfDivision.setText(division);
            c1tfDistrict.setText(district);
            c1taAddress.setText(address);
            BufferedImage bf = null;
            try {
                bf = ImageIO.read(isImage);
            } catch (IOException ex) {
                System.out.println("ViewProfile.refreshProfile(int id)");
                ex.printStackTrace();
            }
            c1lblImage.setIcon(resizeImage(bf));
        } catch (HeadlessException ex) {
            System.out.println("ViewProfile.refreshProfile(int id):");
            ex.printStackTrace();
        }
    }

}
