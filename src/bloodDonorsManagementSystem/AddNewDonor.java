package bloodDonorsManagementSystem;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
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

public class AddNewDonor {

    private final ImageIcon icon;

    private final JFrame frameAddNewEntry = new JFrame("Add New Donor");

    private final JPanel leftPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();

    private final JButton btnCancel, btnAdd, btnChoose;

    private final Color clrRed = new Color(195, 5, 5);
    private final Color clrBlue = new Color(15, 70, 140);
    private final Color clrYellow = new Color(240, 230, 180);
    private final Color clrCyan = new Color(130, 185, 220);

    private final JLabel lblName, lblFatherName, lblMotherName, lblGender, lblBloodGroup, lblAge, lblWeight, lblMaritalStts, lblNID, lblStatement, lblLastDonation, lblLstDontnEx, lblPrevDisease, lblMobile, lblDistrict, lblDivision, lblAddress, lblImage;
    private final JTextField tfName, tfFatherName, tfMotherName, tfWeight;
    private final JTextArea taDiseas, taAddress;
    private final JComboBox cbGender, cbBloodGroup, cbMaritalStts, cbDivision, cbDistrict;
    private final JSpinner spAge;
    private final SpinnerNumberModel age;
    private final JCheckBox chbSttmnt;
    private final JDateChooser dcLstDonation;
    private final MaskFormatter mskMobile, mskNID;
    private final JFormattedTextField tfMobile, tfNID;

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
    private final String gender[] = {"Male", "Female"};
    private final String maritalStts[] = {"Single", "Married"};
    private String imagePath;

    private Donor donor = new Donor();

    public AddNewDonor() {

        //adding image for icon
        icon = new ImageIcon(getClass().getResource("images/add_donor_icon.png"));
        frameAddNewEntry.setIconImage(icon.getImage());                         //set frame icon

        //setting up the frame
        frameAddNewEntry.setSize(600, 540);
        frameAddNewEntry.setLayout(new BorderLayout());                         //frame layout: Border Layout
        frameAddNewEntry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     //setting default close operation
        frameAddNewEntry.setResizable(false);                                   //disable resize button
        frameAddNewEntry.setLocationRelativeTo(null);                           //to center the window

        Border margin = new EmptyBorder(10, 10, 10, 10);                        //create margin for titled border
        Border blueline = BorderFactory.createLineBorder(clrBlue);              //create line for titled border

        //setting up left panel        
        TitledBorder ttlPersonInfo = BorderFactory.createTitledBorder(blueline);//create titled border
        ttlPersonInfo.setTitle(" Personal Information ");                       //set title
        ttlPersonInfo.setTitleColor(clrBlue);                                   //title color: Blue

        leftPanel.setBorder(new CompoundBorder(margin, ttlPersonInfo));         //set border(outer border: margin, inner border: title)
        leftPanel.setBackground(clrYellow);                                     //background color: Yellow
        leftPanel.setPreferredSize(new Dimension(300, 540));                    //setting panel size
        leftPanel.setLayout(null);

        lblName = new JLabel("Name:*");
        lblName.setBounds(30, 30, 100, 20);
        leftPanel.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(30, 50, 240, 25);
        leftPanel.add(tfName);

        lblFatherName = new JLabel("Father's Name:*");
        lblFatherName.setBounds(30, 80, 150, 20);
        leftPanel.add(lblFatherName);

        tfFatherName = new JTextField();
        tfFatherName.setBounds(30, 100, 240, 25);
        leftPanel.add(tfFatherName);

        lblMotherName = new JLabel("Mother's Name:");
        lblMotherName.setBounds(30, 130, 150, 20);
        leftPanel.add(lblMotherName);

        tfMotherName = new JTextField();
        tfMotherName.setBounds(30, 150, 240, 25);
        leftPanel.add(tfMotherName);

        lblGender = new JLabel("Gender:");
        lblGender.setBounds(30, 180, 80, 20);
        leftPanel.add(lblGender);

        cbGender = new JComboBox(gender);
        cbGender.setBounds(30, 200, 80, 25);
        leftPanel.add(cbGender);

        lblAge = new JLabel("Age:");
        lblAge.setBounds(120, 180, 50, 20);
        leftPanel.add(lblAge);

        age = new SpinnerNumberModel(18, 18, 65, 1);
        spAge = new JSpinner(age);
        spAge.setBounds(120, 200, 50, 25);
        leftPanel.add(spAge);

        lblWeight = new JLabel("Weight (KG):*");
        lblWeight.setBounds(178, 180, 95, 20);
        leftPanel.add(lblWeight);

        tfWeight = new JTextField();
        tfWeight.setBounds(180, 200, 90, 25);
        leftPanel.add(tfWeight);

        lblBloodGroup = new JLabel("Blood Group:");
        lblBloodGroup.setBounds(30, 230, 100, 20);
        leftPanel.add(lblBloodGroup);

        cbBloodGroup = new JComboBox(bloodGroups);
        cbBloodGroup.setBounds(30, 250, 100, 25);
        leftPanel.add(cbBloodGroup);

        lblMaritalStts = new JLabel("Marital Status:");
        lblMaritalStts.setBounds(160, 230, 110, 20);
        leftPanel.add(lblMaritalStts);

        cbMaritalStts = new JComboBox(maritalStts);
        cbMaritalStts.setBounds(160, 250, 110, 25);
        leftPanel.add(cbMaritalStts);

        lblNID = new JLabel("NID: (18 digit)");
        lblNID.setBounds(30, 280, 100, 20);
        leftPanel.add(lblNID);

        mskNID = new MaskFormatter();
        try {
            mskNID.setMask("##################");
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frameAddNewEntry, "'NID' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        tfNID = new JFormattedTextField(mskNID);
        tfNID.setBounds(30, 300, 240, 25);
        leftPanel.add(tfNID);

        chbSttmnt = new JCheckBox();
        chbSttmnt.setBounds(30, 330, 20, 20);
        chbSttmnt.setBackground(clrYellow);
        leftPanel.add(chbSttmnt);

        lblStatement = new JLabel("I have donated blood earlier.");
        lblStatement.setBounds(55, 330, 300, 20);
        lblStatement.setForeground(clrBlue);
        leftPanel.add(lblStatement);

        lblLastDonation = new JLabel("Last Donation Date:*");
        lblLastDonation.setBounds(30, 350, 200, 20);
        lblLastDonation.setEnabled(false);
        leftPanel.add(lblLastDonation);

        lblLstDontnEx = new JLabel("(e.g. 05/04/2018)");
        lblLstDontnEx.setBounds(30, 370, 200, 20);
        lblLstDontnEx.setForeground(Color.GRAY);
        lblLstDontnEx.setEnabled(false);
        leftPanel.add(lblLstDontnEx);

        dcLstDonation = new JDateChooser();
        dcLstDonation.setBounds(30, 390, 240, 25);
        dcLstDonation.setDateFormatString("dd/MM/yyyy");
        dcLstDonation.setMaxSelectableDate(new Date());
        dcLstDonation.setEnabled(false);
        leftPanel.add(dcLstDonation);

        lblPrevDisease = new JLabel("Previous Diseases Report (If Any):");
        lblPrevDisease.setBounds(30, 420, 250, 20);
        leftPanel.add(lblPrevDisease);

        taDiseas = new JTextArea();
        taDiseas.setLineWrap(true);

        JScrollPane scrlPaneDss = new JScrollPane();
        scrlPaneDss.setBounds(30, 440, 240, 40);
        scrlPaneDss.setViewportView(taDiseas);
        leftPanel.add(scrlPaneDss);

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
        rightPanel.setBackground(clrYellow);                                    //background color: Yellow
        rightPanel.setPreferredSize(new Dimension(300, 540));                   //setting panel size
        rightPanel.setLayout(new BorderLayout());

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
        lblImage.setIcon(resizeImage(new ImageIcon(getClass().getResource("images/user.png"))));
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
                int result = fileChooser.showSaveDialog(null);
                //if the user click on save in Jfilechooser
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
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

        lblMobile = new JLabel("Mobile No:*");
        lblMobile.setBounds(30, 20, 100, 20);
        midPanel.add(lblMobile);

        mskMobile = new MaskFormatter();
        try {
            mskMobile.setMask("+8801#########");
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Mobile No' is invalid.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        tfMobile = new JFormattedTextField(mskMobile);
        tfMobile.setBounds(30, 40, 240, 25);
        midPanel.add(tfMobile);

        lblDivision = new JLabel("Home Division:");
        lblDivision.setBounds(30, 70, 120, 20);
        midPanel.add(lblDivision);

        cbDivision = new JComboBox(divisions);
        cbDivision.setBounds(30, 90, 115, 25);
        midPanel.add(cbDivision);

        lblDistrict = new JLabel("Home District:");
        lblDistrict.setBounds(150, 70, 120, 20);
        midPanel.add(lblDistrict);

        cbDistrict = new JComboBox();
        cbDistrict.setBounds(150, 90, 120, 25);
        for (int i = 0; i < districts[0].length; ++i) {
            cbDistrict.addItem(districts[0][i]);
        }
        midPanel.add(cbDistrict);

        lblAddress = new JLabel("Address:");
        lblAddress.setBounds(30, 120, 100, 20);
        midPanel.add(lblAddress);

        taAddress = new JTextArea();
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
        lowerPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        lowerPanel.setLayout(new BorderLayout());

        btnAdd = new JButton("Add Donor");
        btnAdd.setBackground(clrBlue);                                          //button background color: Blue
        btnAdd.setForeground(Color.WHITE);                                      //button text color: White
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));                       //set button cursor

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                insertData();
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(clrRed);                                        //button background color: clrRed
        btnCancel.setForeground(Color.WHITE);                                   //button text color: White
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));                    //set button cursor

        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frameAddNewEntry.dispose();
            }

        });

        lowerPanel.add(btnAdd, BorderLayout.WEST);
        lowerPanel.add(btnCancel, BorderLayout.EAST);
        //lowr panel work done

        rightPanel.add(upperPanel, BorderLayout.NORTH);
        rightPanel.add(midPanel, BorderLayout.CENTER);
        rightPanel.add(lowerPanel, BorderLayout.SOUTH);
        //right panle work done

        frameAddNewEntry.add(leftPanel, BorderLayout.WEST);
        frameAddNewEntry.add(rightPanel, BorderLayout.EAST);

        //make frame visible
        frameAddNewEntry.setVisible(true);
    }

    private ImageIcon resizeImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();

        image = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    private ImageIcon resizeImage(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon retImage = new ImageIcon(image);
        return retImage;
    }

    private void insertData() {
        boolean ok = true;

        String name = tfName.getText();
        String fname = tfFatherName.getText();
        String mname = tfMotherName.getText();
        String gender = cbGender.getSelectedItem().toString();
        String bloodGroup = cbBloodGroup.getSelectedItem().toString();
        String weight = tfWeight.getText();
        String maritalStts = cbMaritalStts.getSelectedItem().toString();
        String nid = tfNID.getText();
        String disease = taDiseas.getText();
        String mobile = tfMobile.getText();
        String division = cbDivision.getSelectedItem().toString();
        String district = cbDistrict.getSelectedItem().toString();
        String address = taAddress.getText();

        Date date = dcLstDonation.getDate();
        int age = (int) spAge.getModel().getValue();

        InputStream isImage = null;
        if (imagePath == null) {
            isImage = getClass().getResourceAsStream("images/user.png");
        } else {
            try {
                isImage = Files.newInputStream(Paths.get(imagePath));
            } catch (IOException ex) {
                ok = false;
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frameAddNewEntry, "'Picture' is invalid.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!tfWeight.getText().isEmpty()) {
            try {
                Float.parseFloat(tfWeight.getText());
            } catch (NumberFormatException ex) {
                ok = false;
                tfWeight.setText("");
                JOptionPane.showMessageDialog(frameAddNewEntry, "'Weight' is invalid.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                System.out.println("AddNewDonor.tfWeight.getText(): " + ex);
            }
        }
        if (chbSttmnt.isSelected() && date == null) {
            ok = false;
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Last Donation Date' is invalid.",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }

        if (name.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (name.length() > 30) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Name' is too long (MAX 30 character).", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (fname.equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Father's Name' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (fname.length() > 30) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Father's Name' is too long (MAX 30 character)", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (mname.length() > 30) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Mother's Name' is too long (MAX 30 character)", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (mobile.trim().equals("+8801")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Mobile No' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (weight.trim().equals("")) {
            JOptionPane.showMessageDialog(frameAddNewEntry, "'Weight' Can't Be Empty.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (ok == true) {
            try {
                donor.setName(name);
                donor.setFathersName(fname);
                donor.setMothersName(mname);
                donor.setAge(age);
                donor.setWeight(weight);
                donor.setGender(gender);
                donor.setBloodGroup(bloodGroup);
                donor.setLastDonation(date);
                donor.setDisease(disease);
                donor.setMaritalStatus(maritalStts);
                donor.setNID(nid);
                donor.setMobileNo(mobile);
                donor.setDivision(division);
                donor.setDistrict(district);
                donor.setAddress(address);
                donor.setPicture(isImage);

                Database db = new Database();
                db.insert(donor);
                int choice = JOptionPane.showConfirmDialog(frameAddNewEntry,
                        "Data Added Successfully.\nDo you want to add another?", "Messege.",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                //if user choose NO then dispose this window
                //otherwise clear all the field                        
                if (choice == JOptionPane.NO_OPTION) {
                    frameAddNewEntry.dispose();
                } else {
                    tfName.setText("");
                    tfFatherName.setText("");
                    tfMotherName.setText("");
                    tfWeight.setText("");
                    tfNID.setText("");
                    taDiseas.setText("");
                    tfMobile.setText("");
                    taAddress.setText("");
                    chbSttmnt.setSelected(false);
                    dcLstDonation.setDate(null);
                    dcLstDonation.setEnabled(false);
                    cbGender.setSelectedIndex(0);
                    cbBloodGroup.setSelectedIndex(0);
                    cbMaritalStts.setSelectedIndex(0);
                    cbDivision.setSelectedIndex(0);
                    cbDistrict.setSelectedIndex(0);
                    spAge.setValue(18);
                    lblImage.setIcon(resizeImage(new ImageIcon(getClass().getResource("images/user.png"))));
                }
            } catch (Exception ex) {
                System.out.println("AddNewEntry.getData():");
                ex.printStackTrace();
            }
        }
    }

}
