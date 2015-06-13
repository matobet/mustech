package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("item_id")
    private Issue issue;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "poll")
    @JsonProperty("option_ids")
    private List<Option> options;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "poll")
    @JsonProperty("answer_ids")
    private List<Answer> answers;
}
