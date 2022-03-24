package taxecalculator.service;

import java.math.BigDecimal;

import taxecalculator.domaine.Product;
import taxecalculator.domaine.Statement;

public interface TaxeCalculator {
	 BigDecimal calculateTaxeRate(Product product);
	 BigDecimal round(BigDecimal amount);
	 BigDecimal calculatePriceWithTaxe(Product product);
	 BigDecimal calculateTotalTaxe(Statement statement);
	 BigDecimal calculateTotalAmountWithTaxe(Statement statement);
}
