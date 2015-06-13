package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("answer")
public class Answer extends BaseModel {

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Option option;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Poll poll;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private User user;
}
