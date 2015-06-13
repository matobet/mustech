package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonRootName("option")
public class Option extends BaseModel {

    @Column(nullable = false, length = 255)
    @Size(min = 1, max = 255)
    private String value;

    @OneToMany(mappedBy = "option")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("answer_ids")
    private List<Answer> answers;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("poll_id")
    private Poll poll;
}
