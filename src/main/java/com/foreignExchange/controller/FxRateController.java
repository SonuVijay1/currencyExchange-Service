package com.foreignExchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foreignExchange.domain.FxRate;
import com.foreignExchange.service.FxRateService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FxRateController {

    @Autowired
    private FxRateService fxRateService;

    @GetMapping("/fx")
    public FxRate getFxRate(@RequestParam String targetCurrency) {
        return fxRateService.getFxRate(targetCurrency);
    }

    @GetMapping("/{targetCurrency}")
    public List<FxRate> getFxRates(@PathVariable String targetCurrency) {
        return fxRateService.getLatestFxRates(targetCurrency);
    }
}

