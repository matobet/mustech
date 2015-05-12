package cz.muni.fi.pv243.mustech;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@JsonRootName("user")
public class User {
    @Id
    private int id;

    private String name;
}
