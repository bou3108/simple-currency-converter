package fr.afcepf.algeek.service;

import fr.afcepf.algeek.entity.Devise;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface DeviseService {
    List<Devise> allCurrencies();
    Devise deviseByCode(String id);
    double convertTo(String currencyFrom, String currencyTo, double amount);
}
