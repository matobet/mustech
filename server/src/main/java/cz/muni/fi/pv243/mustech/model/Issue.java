package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("issue")
public class Issue extends BaseModel {

    @Column(nullable = false)
    @Size(min = 1, max = 255)
    private String name;

    @Column(length = 255)
    @Size(max = 255)
    private String description;

    @ManyToOne(optional = false)
    @JsonIdentityReference(alwaysAsId = true)
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date expiresAt;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Poll> polls;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Post> posts;
}
