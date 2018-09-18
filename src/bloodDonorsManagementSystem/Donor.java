package bloodDonorsManagementSystem;

import java.io.InputStream;
import java.util.Date;

public class Donor {
    private int id, age;
    private String name, fathersName, mothersName, gender, weight, bloodGroup, maritalStatus, nid, mobileNo, diseases, division, district, address;
    private Date lastDonation;
    private InputStream picture;
    
    Donor() {
        
    }
    
    Donor(int age, String name, String fathersName, String mothersName, String gender, String weight, String bloodGroup,
            String maritalStatus, String nid, String mobileNo, String diseases, String division, String district,
            String address, Date lastDonation, InputStream picture) {
        
        setAge(age);
        setName(name);
        setFathersName(fathersName);
        setMothersName(mothersName);
        setGender(gender);
        setWeight(weight);
        setBloodGroup(bloodGroup);
        setMaritalStatus(maritalStatus);
        setNID(nid);
        setMobileNo(mobileNo);
        setDisease(diseases);
        setDivision(division);
        setDistrict(district);
        setAddress(address);
        setLastDonation(lastDonation);
        setPicture(picture);
    }
    
    Donor(int id, String name, String gender, int age, String weight, String bloodGroup, Object lastDonation,
          String mobileNo, String division, String district) {
        setID(id);
        setName(name);
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setBloodGroup(bloodGroup);
        setLastDonation(lastDonation);
        setMobileNo(mobileNo);
        setDivision(division);
        setDistrict(district);
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }
    
    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }   

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    
    public void setNID(String nid) {
        this.nid = nid;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    
    public void setDisease(String diseases) {
        this.diseases = diseases;
    }
    
    public void setDivision(String division) {
        this.division = division;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setLastDonation(Date lastDonation) {
        if(lastDonation != null)
            this.lastDonation = lastDonation;
        else
            this.lastDonation = null;
    }
    
    public void setLastDonation(Object lastDonation) {
        if(lastDonation != null)
            this.lastDonation = java.sql.Date.valueOf(lastDonation.toString());
        else
            this.lastDonation = null;
    }
    
    public  void setPicture(InputStream picture) {
        this.picture = picture;
    }
    
    public int getID() {
        return id;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getName() {
        return name;
    }
    
    public String getFathersName() {
        return fathersName;
    }
    
    public String getMothersName() {
        return mothersName;
    }
    
    public String getGender() {
        return  gender;
    }
    
    public  String getWeight() {
        return weight;
    }
    
    public String getBloodGroup() {
        return bloodGroup;
    }
    
    public String getMaritalStatus() {
        return maritalStatus;
    }
    
    public String getNID() {
        return nid;
    }
    
    public String getMobileNo() {
        return mobileNo;
    }
    
    public String getDiseases() {
        return diseases;
    }
    
    public String getDivision() {
        return division;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public String getAddress() {
        return address;
    }
    
    public Date getLastDonation() {
        return lastDonation;
    }
    
    public InputStream getPicture() {
        return picture;
    }
    
}
