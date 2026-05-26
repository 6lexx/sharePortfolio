package fr.utc.miage.shares;

import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ExchangeTradedFundTest {

    private final Company COMPANY = new Company("Company");
    private final ExchangeTradedFund CORRECT_ETF = new ExchangeTradedFund("ETF");

    private final Float VALEUR_REPARTITION = 0.5f;
    private final Float VALEUR_REPARTITION_FALSE = -0.5f;
    private final Float VALEUR_REPARTITION_SUPERIOR = 1.5f;
    private final Map<Company, Float> REPARTITION = Map.of(COMPANY, VALEUR_REPARTITION);

    private final Jour JOUR = new Jour(2012,1);
    private final Jour AUTRE_JOUR = new Jour(2000,1);
    private final Float VALEUR_COURS = 12.45f;
    private final Map<Jour,Float> MAP_COURS = Map.of(JOUR,VALEUR_COURS);
    private final Float DEFAULT_ACTION_VALUE = 0f ;

    @Test
    void TestgetRepartitionsWithValideParam(){
        assertDoesNotThrow(CORRECT_ETF::getRepartitions);
    }


    @Test
    void TestConstructorWithValideParam(){
        ExchangeTradedFund etf = new ExchangeTradedFund("ETF");
        assertEquals("ETF", etf.getLibelle());
    }

    @Test
    void TestsetRepartitionsForCompanyWithValideParam(){
        CORRECT_ETF.setRepartitionsForCompany(COMPANY, VALEUR_REPARTITION);
        assertEquals(VALEUR_REPARTITION, CORRECT_ETF.getRepartitionForCompany(COMPANY));
    }

    @Test
    void TestsetRepartitionsForCompanyWithInvalideParamInferior(){
        assertThrows(IllegalArgumentException.class,()->CORRECT_ETF.setRepartitionsForCompany(COMPANY, VALEUR_REPARTITION_FALSE));
    }

    @Test
    void TestsetRepartitionsForCompanyWithInvalideParamSuperior(){
        assertThrows(IllegalArgumentException.class,()->CORRECT_ETF.setRepartitionsForCompany(COMPANY, VALEUR_REPARTITION_SUPERIOR));
    }

    @Test
    void TestsetRepartitionsWithValideParam(){
        CORRECT_ETF.setRepartitions(REPARTITION);
        assertEquals(REPARTITION, CORRECT_ETF.getRepartitions());
    }

    @Test
    void TestgetRepartitionForCompanyWithValideParam(){
        assertDoesNotThrow(() ->  CORRECT_ETF.getRepartitionForCompany(COMPANY));
    }

    @Test
    void testGetMapCours() {
        assertDoesNotThrow(CORRECT_ETF::getMapCours);
    }

    @Test
    void testSetMapCours() {
        CORRECT_ETF.setMapCours(JOUR, VALEUR_COURS);
        assertEquals(MAP_COURS, CORRECT_ETF.getMapCours());
    }

    @Test
    void testValeurWithContainsKey() {
        CORRECT_ETF.setMapCours(JOUR, VALEUR_COURS);
        assertEquals(VALEUR_COURS,CORRECT_ETF.valeur(JOUR));
    }

    @Test
    void testValeurWithoutKey() {
        assertEquals(DEFAULT_ACTION_VALUE, CORRECT_ETF.valeur(AUTRE_JOUR));
    }
}