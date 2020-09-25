package fr.afcepf.algeek.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "devises")
public class Devise {

    @Id
    private String id;
    private String name;
    private double changeRateForOneEuro;

    public Devise (String id, String name, double changeRateForOneEuro) {
        this.id = id;
        this.name = name;
        this.changeRateForOneEuro =  changeRateForOneEuro;
    }


}
