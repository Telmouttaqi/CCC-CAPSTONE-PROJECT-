package ccc.models;

import java.util.Objects;

public class User {

    private int userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userAddress;
    private String userCity;
    private String userState;
    private String userZip;
    private String userPhone;
    private String userEmail;

    public User() {
    }

    public User(int userId, String firstName,
                String middleName,
                String lastName,
                String userAddress,
                String userCity,
                String userState,
                String userZip,
                String userPhone,
                String userEmail) {
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userAddress = userAddress;
        this.userCity = userCity;
        this.userState = userState;
        this.userZip = userZip;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName, user.firstName) && Objects.equals(middleName, user.middleName) && Objects.equals(lastName, user.lastName) && Objects.equals(userAddress, user.userAddress) && Objects.equals(userCity, user.userCity) && Objects.equals(userState, user.userState) && Objects.equals(userZip, user.userZip) && Objects.equals(userPhone, user.userPhone) && Objects.equals(userEmail, user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, middleName, lastName, userAddress, userCity, userState, userZip, userPhone, userEmail);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}