package cz.muni.fi.pv243.mustech;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    private int id;

    private String name;
}
