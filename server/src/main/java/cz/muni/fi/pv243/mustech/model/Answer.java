package cz.muni.fi.pv243.mustech.model;

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
    private Poll poll;

    @ManyToOne
    private User user;
}
