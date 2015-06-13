package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("post")
public class Post extends BaseModel {

    @Column(nullable = false)
    @Size(min = 1, max = 255)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Issue issue;
}
