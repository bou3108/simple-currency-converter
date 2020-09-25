package fr.afcepf.algeek.service;

import fr.afcepf.algeek.entity.Devise;
import fr.afcepf.algeek.repository.DeviseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviseServiceImpl implements DeviseService {

    @Autowired
    private DeviseRepository dao;

    @Override
    public List<Devise> allCurrencies() {
        return dao.findAll();
    }

    @Override
    public Devise deviseByCode(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public double convertTo(String currencyFrom, String currencyTo, double amount) {
        Devise source = dao.findById(currencyFrom).get();
        Devise target = dao.findById(currencyTo).get();
        return amount * target.getChangeRateForOneEuro() / source.getChangeRateForOneEuro();
    }
}
