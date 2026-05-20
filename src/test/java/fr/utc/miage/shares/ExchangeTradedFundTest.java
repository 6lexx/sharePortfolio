package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ExchangeTradedFundTest {

    private final Company COMPANY = new Company("Company");
    private final ExchangeTradedFund CORRECT_ETF = new ExchangeTradedFund("ETF");
    private final ExchangeTradedFund INCORRECT_ETF = new ExchangeTradedFund(null);

    private final Map<Company, Float> REPARTITION = Map.of(COMPANY, 0.5f);

    @Test
    public void TestgetRepartitionsWithValideParam(){
       assertEquals(new HashMap<>(), CORRECT_ETF.getRepartitions());
    }

    @Test
    public void TestgetRepartitionsWithInvalideParam(){
        assertEquals(new HashMap<>(), INCORRECT_ETF.getRepartitions());
    }
    

}
