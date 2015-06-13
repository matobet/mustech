package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(nullable = false, length = 255)
    @Size(min = 1, max = 255)
    private String name;

    @Column(length = 255)
    @Size(max = 255)
    private String description;

    @ManyToOne(optional = false)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonProperty("creation_date")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonProperty("expiration_date")
    private Date expiresAt;

    @OneToMany(mappedBy = "issue")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("issue_ids")
    private List<Poll> polls;

    @OneToMany(mappedBy = "issue")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("post_ids")
    private List<Post> posts;
}
