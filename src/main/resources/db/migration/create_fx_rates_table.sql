CREATE TABLE fx_rates (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    source_currency VARCHAR(3) NOT NULL,
    target_currency VARCHAR(3) NOT NULL,
    exchange_rate DECIMAL(10, 6) NOT NULL,
    UNIQUE(date, source_currency, target_currency)
);

