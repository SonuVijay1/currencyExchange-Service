package com.foreignExchange.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foreignExchange.domain.FxRate;

@Repository
public interface FxRateRepository extends JpaRepository<FxRate, Long> {
	List<FxRate> findTop3ByTargetCurrencyOrderByDateDesc(String targetCurrency);
    FxRate findByDateAndTargetCurrency(LocalDate date, String targetCurrency);

}
