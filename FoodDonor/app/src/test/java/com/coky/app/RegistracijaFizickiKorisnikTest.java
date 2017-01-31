package com.coky.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Čoky on 30.1.2017..
 */
public class RegistracijaFizickiKorisnikTest {

    @Test
    public void validate() throws Exception {
        Boolean output;
        RegistracijaFizickiKorisnik registracijaFizickiKorisnik = new RegistracijaFizickiKorisnik();
        String testInput1 = "12839";
        String testInput2 = "12793@test.test";
        String testInput3 = "m12312@test";
        String testInput4 = "m12312.test";
        String testInput5 = "m12312.test@test";
        String testInput6 = "a@a.a";
        String testInput7 = "test@test.te5t";
        String testInput8 = "test@test_.test";
        String testInput9 = "test@test.test";
        String testInput10 = "TEST@TEST.TEST";
        String testInput11 = "m21312@test.test";
        String testInput12 = "m_13122@test.test";
        output = registracijaFizickiKorisnik.validate(testInput1);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate(testInput2);
        assertEquals(true, output);
        output = registracijaFizickiKorisnik.validate(testInput3);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate(testInput4);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate(testInput5);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate(testInput6);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate(testInput7);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate(testInput8);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate(testInput9);
        assertEquals(true, output);
        output = registracijaFizickiKorisnik.validate(testInput10);
        assertEquals(true, output);
        output = registracijaFizickiKorisnik.validate(testInput11);
        assertEquals(true, output);
        output = registracijaFizickiKorisnik.validate(testInput12);
        assertEquals(true, output);
    }

    @Test
    public void validate_letters() throws Exception {
        String inputs[] = {"123213","jj1223","wdqw_", "wdefew", "UINOIJ"};
        Boolean output;
        Boolean expecteds[] = {false, false, false, true, true};
        RegistracijaFizickiKorisnik registracijaFizickiKorisnik = new RegistracijaFizickiKorisnik();
        for(int i = 0; i < inputs.length; i++){
            output = registracijaFizickiKorisnik.validate_letters(inputs[i]);
            assertEquals(expecteds[i], output);
        }
        String testInput1 = "123213";
        String testInput2 = "jj1223";
        String testInput3 = "wdqw_";
        String testInput4 = "m12312.test@test";
        String testInput5 = "wdefew";
        String testInput6 = "UINOIJ";
        output = registracijaFizickiKorisnik.validate_letters(testInput1);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate_letters(testInput2);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate_letters(testInput3);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate_letters(testInput4);
        assertEquals(false, output);
        output = registracijaFizickiKorisnik.validate_letters(testInput5);
        assertEquals(true, output);
        output = registracijaFizickiKorisnik.validate_letters(testInput6);
        assertEquals(true, output);
    }

}