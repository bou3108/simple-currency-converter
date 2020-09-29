package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.entity.Devise;
import fr.afcepf.algeek.service.DeviseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class exposes the methods of the API.
 * It exposes JSON objects as currencies (Devise) or results of conversion operations.
 * All methods are reachable through http requests sent to the url of the API extended by the mapping parameters
 *
 * @see fr.afcepf.algeek.entity.Devise
 * @author bou3108
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="", headers = "Accept=application/json")
public class DeviseRestController {

    /**
     * the service handling currencies and change rates
     * @see fr.afcepf.algeek.service.DeviseService
     */
    private final DeviseService deviseService;

    /**
     * the constructor of the class. Injects its DeviseService.
     * @param deviseService
     */
    public DeviseRestController(DeviseService deviseService) {
        this.deviseService = deviseService;
    }

    /**
     * This method returns all currencies stored in DB.
     * @return a ResponseEntity which contains a java.util.list of Devise and a HttpStatus 200 if found,
     * or a HttpStatus 404 if not.
     *
     * @see java.util.List
     * @see fr.afcepf.algeek.entity.Devise
     * @see org.springframework.http.ResponseEntity;
     * @see org.springframework.http.HttpStatus;
     * @see fr.afcepf.algeek.service.DeviseService
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<Devise>> getAllCurrencies() {
        try {
            return new ResponseEntity<>(deviseService.allCurrencies(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * This methode returns a currency stored in DB
     *
     * @param code the id of the currency asked for, passed in the url
     * @return a ResponseEntity that contains a Devise and a HttpStatus 200 if found,
     * or a HtppStatus 404 if not.
     *
     * @see fr.afcepf.algeek.entity.Devise
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpStatus
     * @see fr.afcepf.algeek.service.DeviseService
     */
    @GetMapping(value = "/{code}")
    public ResponseEntity<Devise> getCurrencyByCode(@PathVariable String code) {
        try {
            return new ResponseEntity<>(deviseService.deviseByCode(code), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * This method returns a converted amount, from a source currency (Devise) to a target currency
     *
     * @param code1 the id of the source currency, passed in the url
     * @param code2 the id of the target currency, passed in the url
     * @param amount the amount to convert, expressed in the source currency
     *
     * @return a ResponseEntity which contains a HttpStatus 404 if one or the other currency is not found in db,
     * or the converted amount, expressed in the target currency, and a HttpStatus 200 if ok
     *
     * @see org.springframework.http.ResponseEntity
     * @see org.springframework.http.HttpStatus
     * @see fr.afcepf.algeek.service.DeviseService
     */
    @GetMapping(value = "/from/{code1}/to/{code2}/amount/{amount}")
    public ResponseEntity<Double> convertCurrency(@PathVariable String code1, @PathVariable String code2, @PathVariable double amount) {
        try {
            return new ResponseEntity<>(deviseService.convertTo(code1, code2, amount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
