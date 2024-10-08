package entity;

import enums.UserStatus;

import java.time.LocalDate;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private UserStatus role;

    public User(String firstName, String lastName, String email, LocalDate birthDate, UserStatus role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }
    public User() {}

    public int getId() {return this.id;}
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public String getEmail() {return this.email;}
    public LocalDate getBirthDate() {return this.birthDate;}
    public UserStatus getRole() {return this.role;}

    public void setId(int id) {this.id = id;}
}
