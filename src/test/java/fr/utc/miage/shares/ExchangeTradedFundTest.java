package fr.utc.miage.shares;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ExchangeTradedFundTest {

    private final Company COMPANY = new Company("Company");
    private final ExchangeTradedFund CORRECT_ETF = new ExchangeTradedFund("ETF");

    private final Map<Company, Float> REPARTITION = Map.of(COMPANY, 0.5f);    

    @Test
    void TestgetRepartitionsWithValideParam(){
        CORRECT_ETF.setRepartitions(REPARTITION);
        assertEquals(REPARTITION, CORRECT_ETF.getRepartitions());
    }

    

    @Test
    void TestgetRepartitionForCompanyWithValideParam(){
        CORRECT_ETF.setRepartitions(REPARTITION);
        assertEquals(0.5f, CORRECT_ETF.getRepartitionForCompany(COMPANY));
    }

    @Test
    void TestConstructorWithValideParam(){
        ExchangeTradedFund etf = new ExchangeTradedFund("ETF");
        assertEquals("ETF", etf.getLibelle());
    }

    @Test
    void TestsetRepartitionsForCompanyWithValideParam(){
        CORRECT_ETF.setRepartitionsForCompany(COMPANY, 0.3f);
        assertEquals(0.3f, CORRECT_ETF.getRepartitionForCompany(COMPANY));
    }

}