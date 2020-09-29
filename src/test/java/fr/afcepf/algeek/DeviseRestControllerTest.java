package fr.afcepf.algeek;

import  static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import fr.afcepf.algeek.entity.Devise;
import fr.afcepf.algeek.rest.DeviseRestController;
import fr.afcepf.algeek.service.DeviseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class DeviseRestControllerTest {

    static DeviseRestController controller;
    @Mock
    static DeviseService service;

    @BeforeAll
    static void setUpAll() {
        service = mock(DeviseService.class);
        controller = new DeviseRestController(service);
    }

    @Test
    public void getAllCurrenciesTest(){
        Devise euro = new Devise("EUR", "Euro", 1);
        Devise dollar = new Devise("USD", "US Dollar", 1.16);
        Devise pound = new Devise("GBP", "Livre Sterling", 0.91);
        List<Devise> allDevises = new ArrayList<>();
        allDevises.add(euro);
        allDevises.add(dollar);
        allDevises.add(pound);

        ResponseEntity<List<Devise>> responseOK = new ResponseEntity<>(allDevises, HttpStatus.OK);
        ResponseEntity<List<Devise>> responseFailed = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        when(service.allCurrencies()).thenReturn(allDevises);
        assertEquals(responseOK, controller.getAllCurrencies());
//        when(service.allCurrencies()).thenThrow(new Exception());
//        assertEquals(responseFailed, controller.getAllCurrencies());
    }

    @Test
    public void getCurrencyByCodeTest() {
        Devise euro = new Devise("EUR", "Euro", 1);

        when(service.deviseByCode("EUR")).thenReturn(euro);
        when(service.deviseByCode("PLOP")).thenReturn(null);
        ResponseEntity<Devise> responseOK = new ResponseEntity<>(euro, HttpStatus.OK);

        assertEquals(responseOK, controller.getCurrencyByCode("EUR"));
    }

    @Test
    public void convertCurrencyTest() {
        Double amountOK = 1600.;
        Double amountFailed = null;
        ResponseEntity<Double> responseOK = new ResponseEntity<>(amountOK, HttpStatus.OK);

        when(service.convertTo("EUR","USD",1000.)).thenReturn(1600.);
        assertEquals(responseOK, controller.convertCurrency("EUR","USD",1000.));
    }
}
