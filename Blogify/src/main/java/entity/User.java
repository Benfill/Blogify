package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.UserRole;


@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String first_name;
    private String second_name;
    private String email;
    private Date birth_date;
    private String password;
    private UserRole role;

    public User(){

    }


    public User(String first_name , String second_name , String email , String password , Date birthDate , UserRole role){
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.birth_date = birthDate;
        this.role = role;

    }


    public User(Long id ,String first_name , String second_name , String email , String password , Date birthDate , UserRole role){
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.password = password;
        this.birth_date = birthDate;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    
   



}
