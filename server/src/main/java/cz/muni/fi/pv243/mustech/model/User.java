package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

@Entity(name = "USERS")
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("user")
public class User extends BaseModel {

    private static final long serialVersionUID = 695884515481618L;

    @Column(nullable = false, length = 30)
    @Size(min = 3, max = 30)
    private String name;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 50)
    @Size(min = 6, max = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
    }

}
