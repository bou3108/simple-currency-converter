package fr.afcepf.algeek.service;

import fr.afcepf.algeek.entity.Devise;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Devise is the interface for the service handling currencies and change rates.
 * It allows CRUD methods for the Devise class and conversion methods
 *
 * @see fr.afcepf.algeek.entity.Devise
 *
 * @author bou3108
 */
@Service
public interface DeviseService {
    /**
     * This method returns all the currencies (Devise) stored in DB
     * @return a List of Devise
     *
     * @see fr.afcepf.algeek.entity.Devise
     * @see java.util.List
     */
    List<Devise> allCurrencies();

    /**
     * This method returns a Devise
     * @param id the code of the Devise (e.g. "USD")
     * @return the Devise matching with the code if found, null if not.
     */
    Devise deviseByCode(String id);

    /**
     * This method returns an amount passed in parameter, converted from a currency to an other one
     * @param currencyFrom the id of the source currency
     * @param currencyTo the id of the target currency
     * @param amount the amount to convert, expressed in the source currency
     *
     * @return the converted amount, expressed in the target currency, if both currencies are found in dbs; null if not.
     */
    Double convertTo(String currencyFrom, String currencyTo, double amount);
}
