package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.entity.Devise;
import fr.afcepf.algeek.service.DeviseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="", headers = "Accept=application/json")
public class DeviseRestController {

    private final DeviseService deviseService;

    public DeviseRestController(DeviseService deviseService) {
        this.deviseService = deviseService;
    }

    @GetMapping(value = "/all")
    ResponseEntity<List<Devise>> getAllCurrencies() {
        try {
            return new ResponseEntity<>(deviseService.allCurrencies(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/{code}")
    ResponseEntity<Devise> getCurrencyByCode(@PathVariable String code) {
        try {
            return new ResponseEntity<>(deviseService.deviseByCode(code), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/from/{code1}/to/{code2}/amount/{amount}")
    ResponseEntity<Double> convertCurrency(@PathVariable String code1, @PathVariable String code2, @PathVariable double amount) {
        try {
            return new ResponseEntity<>(deviseService.convertTo(code1, code2, amount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
