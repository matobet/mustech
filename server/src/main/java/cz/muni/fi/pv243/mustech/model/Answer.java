package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("answer")
public class Answer extends BaseModel {

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("option_id")
    private Option option;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("poll_id")
    private Poll poll;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private User user;
}
