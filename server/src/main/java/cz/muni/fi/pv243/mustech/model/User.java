package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "USERS")
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("user")
public class User extends BaseModel {

    private static final long serialVersionUID = 695884515481618L;

    @Column
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
    }

}
