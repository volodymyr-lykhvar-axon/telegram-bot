package com.file.data.repository.currency;

import com.file.data.entity.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Currency Repository.
 *
 * @author Volodymyr Lykhvar
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
