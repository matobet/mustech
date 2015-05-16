package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("poll")
public class Poll extends BaseModel {

    private String question;

    @ManyToOne
    private Issue issue;

    @OneToMany(mappedBy = "poll")
    private List<Option> options;

    @OneToMany(mappedBy = "poll")
    private List<Answer> answers;
}
