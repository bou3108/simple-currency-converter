package fr.afcepf.algeek.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Devise is the main entity used to handle currencies and change rates.
 * It is mapped with a MongoDB database.
 *
 * @author bou3108
 */
@Getter
@Document(collection = "devises")
public class Devise {

    /**
     * The public code for the currency (e.g. "USD", "EUR", ...)
     */
    @Id
    private String id;
    /**
     * The full name of the currency
     */
    private String name;
    /**
     * the change rate of the currency; standard is 1€.
     */
    private double changeRateForOneEuro;

    /**
     * this is the overload constructor of the class
     *
     * @param id the code of the currency. It is the _id mapped by Spring Data MongoDB
     * @param name the full name of the currency
     * @param changeRateForOneEuro the change rate of the currency, for 1€
     */
    public Devise (String id, String name, double changeRateForOneEuro) {
        this.id = id;
        this.name = name;
        this.changeRateForOneEuro =  changeRateForOneEuro;
    }
}
