package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("poll")
public class Poll extends BaseModel {

    @Column(nullable = false)
    @Size(max = 255)
    private String question;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Issue issue;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "poll")
    private List<Option> options;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "poll")
    private List<Answer> answers;
}
