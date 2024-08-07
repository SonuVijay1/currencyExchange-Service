package com.foreignExchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.foreignExchange.domain.FxRate;
import com.foreignExchange.domain.FxRateResponse;
import com.foreignExchange.repo.FxRateRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FxRateService {

	@Autowired
	private FxRateRepository fxRateRepository;

	private RestTemplate restTemplate = new RestTemplate();

	public FxRate getFxRate(String targetCurrency) {
		LocalDate today = LocalDate.now();
		Optional<FxRate> fxRateOptional = Optional
				.ofNullable(fxRateRepository.findByDateAndTargetCurrency(today, targetCurrency));

		if (fxRateOptional.isPresent()) {
			return fxRateOptional.get();
		} else {
			String apiUrl = "https://api.frankfurter.app/latest?from=USD&to=" + targetCurrency;
			FxRate fxRate = fetchFxRateFromApi(apiUrl, targetCurrency);
			fxRateRepository.save(fxRate);
			return fxRate;
		}
	}

	private FxRate fetchFxRateFromApi(String apiUrl, String targetCurrency) {
		// ResponseEntity<String> response = restTemplate.getForEntity(apiUrl,
		// String.class);
		FxRateResponse response = restTemplate.getForObject(apiUrl, FxRateResponse.class);
		FxRate fxRate = new FxRate();

		if (response != null && response.getRates() != null) {
			double exchangeRate = response.getRates().get(targetCurrency);

			fxRate.setDate(LocalDate.now());
			fxRate.setSourceCurrency("USD");
			fxRate.setTargetCurrency(targetCurrency);
			fxRate.setExchangeRate(exchangeRate);
		}

		return fxRate;
	}

	public List<FxRate> getLatestFxRates(String targetCurrency) {
		return fxRateRepository.findTop3ByTargetCurrencyOrderByDateDesc(targetCurrency);
	}

}
