package fr.afcepf.algeek;

import  static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import fr.afcepf.algeek.entity.Devise;
import fr.afcepf.algeek.repository.DeviseRepository;
import fr.afcepf.algeek.service.DeviseService;
import fr.afcepf.algeek.service.DeviseServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviseServiceImplTest {

    static DeviseService deviseService;
    @Mock
    static DeviseRepository deviseRepository;


    @BeforeAll
    static void setUpAll() {
        deviseRepository = mock(DeviseRepository.class);
        deviseService = new DeviseServiceImpl(deviseRepository);

    }

    @Test
    public void allCurrenciesTest(){
        Devise euro = new Devise("EUR", "Euro", 1);
        Devise dollar = new Devise("USD", "US Dollar", 1.16);
        Devise pound = new Devise("GBP", "Livre Sterling", 0.91);
        List<Devise> allDevises = new ArrayList<>();
        allDevises.add(euro);
        allDevises.add(dollar);
        allDevises.add(pound);

        when(deviseRepository.findAll()).thenReturn(allDevises);
        List<Devise> testedDevises = deviseService.allCurrencies();

        assertEquals(3, testedDevises.size(), "ok");
        assertEquals(euro, testedDevises.get(0));
    }

    @Test
    public void deviseByCodeTest(){
        Devise euro = new Devise("EUR", "Euro", 1);
        Optional<Devise> optionalEuro = Optional.of(euro);

        when(deviseRepository.findById("EUR")).thenReturn(optionalEuro);
        when(deviseRepository.findById("PLOP")).thenReturn(Optional.empty());

        assertEquals(euro, deviseService.deviseByCode("EUR"));
        assertEquals(null, deviseService.deviseByCode("PLOP"));
    }

    @Test
    public void convertToTest(){
        Devise euro = new Devise("EUR", "Euro", 1);
        Optional<Devise> optionalEuro = Optional.of(euro);
        Devise dollar = new Devise("USD", "US Dollar", 1.16);
        Optional<Devise> optionalDollar = Optional.of(dollar);
        Devise zeroDevise = new Devise("ZER", "Zero Devise", 0);
        Optional<Devise> optionalZero = Optional.of(zeroDevise);

        when(deviseRepository.findById("EUR")).thenReturn(optionalEuro);
        when(deviseRepository.findById("USD")).thenReturn(optionalDollar);
        when(deviseRepository.findById("ZER")).thenReturn(optionalZero);
        when(deviseRepository.findById("PLOP")).thenReturn(Optional.empty());

        Double eurToUsd = 1160.;
        Double usdToEur = 1000.;
        Double eurToNull = null;
        Double nullToEur = null;

        assertEquals(eurToUsd, deviseService.convertTo("EUR", "USD", 1000), 0.1);
        assertEquals(usdToEur, deviseService.convertTo("USD", "EUR", 1160), 0.1);
        assertEquals(eurToNull, deviseService.convertTo("EUR", "PLOP", 1000));
        assertEquals(nullToEur, deviseService.convertTo("PLOP", "EUR", 1000));
    }
}
