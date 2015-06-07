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
@JsonRootName("option")
public class Option extends BaseModel {

    private String value;

    @OneToMany(mappedBy = "option")
    private List<Answer> answers;

    @ManyToOne
    private Poll poll;
}
