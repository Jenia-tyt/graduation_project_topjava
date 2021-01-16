package ru.restaurants.to;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.NONE;

public class ToUser extends AbstractTo {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 30)
    @SafeHtml (whitelistType = SafeHtml.WhiteListType.NONE)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate voteLast;

    @Email
    @Size(min = 3, max = 30)
    @SafeHtml (whitelistType = SafeHtml.WhiteListType.NONE)
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 100)
    private String password;

    private String role;

    public ToUser(){}

    public ToUser(Integer id, String name, LocalDate voteLast, String email, String password, String role) {
        super(id);
        this.name = name;
        this.voteLast = voteLast;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public ToUser(ToUser toUser){
        this(toUser.getId(), toUser.getName(), toUser.getVoteLast(), toUser.getEmail(), toUser.getPassword(), toUser.getRole());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getVoteLast() {
        return voteLast;
    }

    public void setVoteLast(LocalDate voteLast) {
        this.voteLast = voteLast;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
