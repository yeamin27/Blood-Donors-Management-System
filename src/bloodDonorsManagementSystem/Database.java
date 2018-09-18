package bloodDonorsManagementSystem;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Database {

    //this method establishe connection with database
    public static Connection ConnectDB() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_blood_donors", "root", "bin");
            System.out.println("Database Connected.");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed.");
            System.out.println("Database.ConnectDB():" + e);
        }

        return con;
    }

    //this method return InputStream for image
    public InputStream getImage(int id) {
        String query = "SELECT IMAGE FROM TBL_DONORS WHERE ID = " + id;

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs;
        InputStream is = null;
        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            rs.next();
            is = rs.getBinaryStream("IMAGE");
            System.out.println(query);
        } catch (SQLException ex) {
            System.out.println("Database.getImage()" + ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Databae Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.viewProfile():" + e);
            }
        }
        return is;
    }

    //this method used for retrieving data to show in profile
    public Donor viewProfile(int id) {
        String query = "SELECT * FROM TBL_DONORS WHERE ID = '" + id + "'";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Donor donor = new Donor();
        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            rs.next();

            donor.setName(rs.getString("NAME"));
            donor.setFathersName(rs.getString("FATHERS_NAME"));
            donor.setMothersName(rs.getString("MOTHERS_NAME"));
            donor.setGender(rs.getString("GENDER"));
            donor.setAge(rs.getInt("AGE"));
            donor.setWeight(rs.getString("WEIGHT"));
            donor.setBloodGroup(rs.getString("BLOOD_GROUP"));
            donor.setMaritalStatus(rs.getString("MARITAL_STATUS"));
            donor.setNID(rs.getString("NID"));
            donor.setDisease(rs.getString("DISEASE"));
            donor.setMobileNo(rs.getString("MOBILE"));
            donor.setDivision(rs.getString("DIVISION"));
            donor.setDistrict(rs.getString("DISTRICT"));
            donor.setAddress(rs.getString("ADDRESS"));
            donor.setLastDonation(rs.getDate("LAST_DONATION"));
            donor.setPicture(rs.getBinaryStream("IMAGE"));

            System.out.println(query);
            System.out.println("Fetched Profile");
        } catch (SQLException e) {
            System.out.println("Database.viewProfile(int id):" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Databae Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.viewProfile():" + e);
            }
        }
        return donor;
    }

    //this method used for specific query in database
    public ArrayList<Donor> search(String value) {
        String query = "SELECT ID, NAME, GENDER, AGE, WEIGHT, BLOOD_GROUP, LAST_DONATION,"
                + " MOBILE, DIVISION, DISTRICT FROM TBL_DONORS"
                + " WHERE " + value;

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Donor> donorsList = new ArrayList();
        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                donorsList.add(new Donor(Integer.parseInt(rs.getObject(1).toString()), rs.getObject(2).toString(),
                        rs.getObject(3).toString(), Integer.parseInt(rs.getObject(4).toString()), rs.getObject(5).toString(), rs.getObject(6).toString(),
                        rs.getObject(7), rs.getObject(8).toString(), rs.getObject(9).toString(), rs.getObject(10).toString()));
            }
            System.out.println(query);
            System.out.println("Data Fetched");
        } catch (SQLException e) {
            System.out.println("Database.search(String value):" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Databae Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.search():" + e);
            }
        }
        return donorsList;
    }

    //this method retrive data from database and return them as ResultSet
    public ArrayList<Donor> getDataForTable() {
        String query = "SELECT ID, NAME, GENDER, AGE, WEIGHT, BLOOD_GROUP, LAST_DONATION,"
                + " MOBILE, DIVISION, DISTRICT FROM TBL_DONORS";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Donor> donorsList = new ArrayList();

        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                donorsList.add(new Donor(Integer.parseInt(rs.getObject(1).toString()), rs.getObject(2).toString(),
                        rs.getObject(3).toString(), Integer.parseInt(rs.getObject(4).toString()), rs.getObject(5).toString(), rs.getObject(6).toString(),
                        rs.getObject(7), rs.getObject(8).toString(), rs.getObject(9).toString(), rs.getObject(10).toString()));
            }
            System.out.println(query);
            System.out.println("Data Fetched");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Occured While Fetching Data!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Database.getDataForTable():" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Databae Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.getDataForTable():" + e);
            }
        }
        return donorsList;
    }

    //this method insert data into database
    public void insert(Donor donor) {
        int age = donor.getAge();
        String name = donor.getName();
        String fname = donor.getFathersName();
        String mname = donor.getMothersName();
        String gender = donor.getGender();
        String weight = donor.getWeight();
        String bloodGroup = donor.getBloodGroup();
        String maritalStts = donor.getMaritalStatus();
        String nid = donor.getNID();
        String disease = donor.getDiseases();
        String mobile = donor.getMobileNo();
        String division = donor.getDivision();
        String district = donor.getDistrict();
        String address = donor.getAddress();
        InputStream isImage = donor.getPicture();
        Date lstDonation = new Date(donor.getLastDonation().getTime());

        String sql = "INSERT INTO TBL_DONORS"
                + "(NAME, FATHERS_NAME, MOTHERS_NAME, GENDER, AGE, WEIGHT,"
                + " BLOOD_GROUP, MARITAL_STATUS, NID, LAST_DONATION, DISEASE,"
                + " MOBILE, DIVISION, DISTRICT, ADDRESS, IMAGE)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, fname);
            pst.setString(3, mname);
            pst.setString(4, gender);
            pst.setInt(5, age);
            pst.setString(6, weight);
            pst.setString(7, bloodGroup);
            pst.setString(8, maritalStts);
            pst.setString(9, nid);
            pst.setDate(10, lstDonation);
            pst.setString(11, disease);
            pst.setString(12, mobile);
            pst.setString(13, division);
            pst.setString(14, district);
            pst.setString(15, address);
            pst.setBlob(16, isImage);

            pst.executeUpdate();

            System.out.println("Data Inserted Successfully.");
        } catch (SQLException e) {
            System.out.println("Database.insert():" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Databae Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.insert():" + e);
            }
        }
    }

    //this method update data
    public void update(Donor donor) {
        int id = donor.getID();
        int age = donor.getAge();
        String name = donor.getName();
        String fname = donor.getFathersName();
        String mname = donor.getMothersName();
        String gender = donor.getGender();
        String weight = donor.getWeight();
        String bloodGroup = donor.getBloodGroup();
        String maritalStts = donor.getMaritalStatus();
        String nid = donor.getNID();
        String disease = donor.getDiseases();
        String mobile = donor.getMobileNo();
        String division = donor.getDivision();
        String district = donor.getDistrict();
        String address = donor.getAddress();
        InputStream isImage = donor.getPicture();
        Date lstDonation = null;
        if(donor.getLastDonation() != null)
            lstDonation = new Date(donor.getLastDonation().getTime());

        String sql = "UPDATE TBL_DONORS SET "
                + "NAME=?, FATHERS_NAME=?, MOTHERS_NAME=?, GENDER=?, AGE=?, WEIGHT=?,"
                + "BLOOD_GROUP=?, MARITAL_STATUS=?, NID=?, LAST_DONATION=?, DISEASE=?, MOBILE=?,"
                + "DIVISION=?, DISTRICT=?, ADDRESS=?, IMAGE=? WHERE ID=" + id;

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(sql);

            System.out.println(sql);

            pst.setString(1, name);
            pst.setString(2, fname);
            pst.setString(3, mname);
            pst.setString(4, gender);
            pst.setInt(5, age);
            pst.setString(6, weight);
            pst.setString(7, bloodGroup);
            pst.setString(8, maritalStts);
            pst.setString(9, nid);
            pst.setDate(10, lstDonation);
            pst.setString(11, disease);
            pst.setString(12, mobile);
            pst.setString(13, division);
            pst.setString(14, district);
            pst.setString(15, address);
            pst.setBlob(16, isImage);

            pst.executeUpdate();

            System.out.println("Data Updated Successfully.");
        } catch (SQLException e) {
            System.out.println("Database.update():" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Databae Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.update():" + e);
            }
        }
    }

    //this method delete data from database
    public void delete(int id) {
        String del = "DELETE FROM TBL_DONORS WHERE ID = '" + id + "'";
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConnectDB();
            pst = conn.prepareStatement(del);
            pst.executeUpdate();

            System.out.println(id + " No Data Deleted Successfully.");
        } catch (SQLException e) {
            System.out.println("Database.delete(int id):" + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Databae Disconnected.");
                }
            } catch (SQLException e) {
                System.out.println("Database.delete(int id):" + e);
            }
        }
    }
}
