package cz.muni.fi.pv243.mustech.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@JsonRootName("option")
public class Option extends BaseModel {

    @Column(nullable = false)
    @Size(min = 1, max = 255)
    private String value;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Answer> answers;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Poll poll;
}
