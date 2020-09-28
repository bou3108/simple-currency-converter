package fr.afcepf.algeek.service;

import fr.afcepf.algeek.entity.Devise;
import fr.afcepf.algeek.repository.DeviseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * DeviseServiceImpl is an implementation class for DeviseService while using MongoDB as database
 * It allows CRUD methods for the Devise class and conversion methods
 *
 * @see fr.afcepf.algeek.service.DeviseService
 * @see fr.afcepf.algeek.entity.Devise
 *
 * @author bou3108
 */
@Slf4j
@Service
public class DeviseServiceImpl implements DeviseService {

    /**
     * The data access interface to a MongoDB database
     * This is injected by Spring Data MongoDB
     *
     * @see fr.afcepf.algeek.repository.DeviseRepository
     */

    private DeviseRepository dao;

    public DeviseServiceImpl(DeviseRepository deviseRepository) {
        this.dao = deviseRepository;
    }

    /**
     * This method returns all the currencies (Devise) stored in DB
     * @return a List of Devise
     *
     * @see fr.afcepf.algeek.entity.Devise
     * @see java.util.List
     */
    @Override
    public List<Devise> allCurrencies() {
        return dao.findAll();
    }

    /**
     * This method returns a Devise
     * @param id the code of the Devise (e.g. "USD")
     * @return the Devise matching with the code if found, null if not.
     */
    @Override
    public Devise deviseByCode(String id) {
        return dao.findById(id).orElse(null);
    }

    /**
     * This method returns an amount passed in parameter, converted from a currency to an other one
     * @param currencyFrom the id of the source currency
     * @param currencyTo the id of the target currency
     * @param amount the amount to convert, expressed in the source currency
     *
     * @return the converted amount, expressed in the target currency
     */
    @Override
    public Double convertTo(String currencyFrom, String currencyTo, double amount) {
        try {
            Devise source = dao.findById(currencyFrom).orElse(null);
            Devise target = dao.findById(currencyTo).orElse(null);
            if(source != null && target != null){
                return amount * target.getChangeRateForOneEuro() / source.getChangeRateForOneEuro();
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return  null;
        }
    }
}
