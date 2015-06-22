package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity(name = "USERS")
@Data
@EqualsAndHashCode(callSuper = false)
@JsonRootName("user")
public class User extends BaseModel {

    private static final long serialVersionUID = 695884515481618L;

    @Column(nullable = false, length = 30)
    @Size(min = 3, max = 30)
    private String name;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false, length = 50)
    @Size(min = 6, max = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "concernedUsers")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Issue> issues = new HashSet<>();

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        if (!email.equals(user.email)) return false;
        if (!password.equals(user.password)) return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
