package fr.afcepf.algeek;

import fr.afcepf.algeek.entity.Devise;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DeviseTest {

    @Test
    public void checkFieldsDevise() {
        Devise devise = new Devise();
        devise.setId("CHF");
        devise.setName("Franc suisse");
        devise.setChangeRateForOneEuro(1.5);
        Devise devise2 = devise;

        assertEquals("CHF", devise.getId());
        assertEquals("Franc suisse", devise.getName());
        assertEquals(1.5, devise.getChangeRateForOneEuro());
        assertEquals(devise2, devise);
    }
}
