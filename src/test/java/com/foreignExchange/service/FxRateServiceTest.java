package com.foreignExchange.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.foreignExchange.domain.FxRate;
import com.foreignExchange.repo.FxRateRepository;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FxRateServiceTest {

    @Mock
    private FxRateRepository fxRateRepository;

    @InjectMocks
    private FxRateService fxRateService;

    @Test
    public void testGetFxRate() {
        MockitoAnnotations.openMocks(this);

        FxRate fxRate = new FxRate();
        fxRate.setDate(LocalDate.now());
        fxRate.setSourceCurrency("USD");
        fxRate.setTargetCurrency("EUR");
        fxRate.setExchangeRate(0.85D);

        when(fxRateRepository.findByDateAndTargetCurrency(LocalDate.now(), "EUR")).thenReturn(fxRate);

        FxRate result = fxRateService.getFxRate("EUR");

        assertEquals(fxRate.getExchangeRate(), result.getExchangeRate());
    }
}

