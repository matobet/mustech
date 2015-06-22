package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
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

    @OneToMany(mappedBy = "issue")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Poll> polls;

    @OneToMany(mappedBy = "issue")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private Set<User> concernedUsers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Issue issue = (Issue) o;

        if (!name.equals(issue.name)) return false;
        if (description != null ? !description.equals(issue.description) : issue.description != null) return false;
        if (!createdBy.equals(issue.createdBy)) return false;
        if (!createdAt.equals(issue.createdAt)) return false;
        return !(expiresAt != null ? !expiresAt.equals(issue.expiresAt) : issue.expiresAt != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + createdBy.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + (expiresAt != null ? expiresAt.hashCode() : 0);
        return result;
    }
}
