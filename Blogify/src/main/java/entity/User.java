package entity;

import enums.UserRole;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private UserRole role;
    private String password;

    public User(String firstName, String lastName, String email, LocalDate birthDate, UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }
    public User(String firstName, String lastName, String email, LocalDate birthDate, UserRole role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
        this.password = password;
    }

    public User() {}

    public Integer getId() {return this.id;}
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public String getEmail() {return this.email;}
    public LocalDate getBirthDate() {return this.birthDate;}
    public UserRole getRole() {return this.role;}

    public void setId(int id) {this.id = id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}
    public void setRole(UserRole role) {this.role = role;}
}
