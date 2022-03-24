package taxecalculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

import taxecalculator.domaine.Product;
import taxecalculator.domaine.Statement;

public class TaxeService implements TaxeCalculator,Printer {
	
    public static final BigDecimal FIVE_PERCENT = new BigDecimal("0.05");
    public static final BigDecimal ZERO_PERCENT = new BigDecimal("0.00");
    public static final String CURRENCY = "€";
    public static final String MONTANT = "\nMontant des taxes : ";
    public static final String TOTAL = "Total : ";
    public static final String SEPERATOR = " : ";
    public static final String NEWLINE = "\n";

	@Override
	public BigDecimal calculateTaxeRate(Product product) {
		BigDecimal additional_tax = product.isImported() ? FIVE_PERCENT : ZERO_PERCENT;
		
		return round(product.getPrice().multiply(product.getProductType().getTaxRate())).add(round(product.getPrice().multiply(additional_tax)));	
	}

	@Override
	public BigDecimal round(BigDecimal amount) {
		BigDecimal value = amount.setScale(2, RoundingMode.CEILING);
        BigDecimal reminder = value.remainder(FIVE_PERCENT);
        
        return reminder.equals(ZERO_PERCENT) ? value : value.add(FIVE_PERCENT.subtract(reminder));
	}

	@Override
	public BigDecimal calculatePriceWithTaxe(Product product) {
		
		 return round(product.getPrice().add(calculateTaxeRate(product)));
	}

	@Override
	public String printProduct(Product product) {
		StringBuilder string = new StringBuilder(product.getName());
		
		return string.append(getOriginPrice(product)).append(CURRENCY).append(SEPERATOR).append(calculatePriceWithTaxe(product)).append(CURRENCY).toString();
	}
    	
	private BigDecimal getOriginPrice( Product product ) {
		
		return product.getPrice().divide(new BigDecimal(product.getUnit()));
	}

	@Override
	public BigDecimal calculateTotalTaxe( Statement statement) {
		
		return round(statement.getProducts().stream().map(product -> calculateTaxeRate(product)).reduce(BigDecimal::add).get());
	}

	@Override
	public BigDecimal calculateTotalAmountWithTaxe(Statement statement) {
		
		return round(statement.getProducts().stream().map(product -> calculatePriceWithTaxe(product)).reduce(BigDecimal::add).get());
	}

	@Override
	public String printStatement(Statement statement) {
		
		return statement.getProducts().stream().map(product -> printProduct(product)).collect(Collectors.joining(NEWLINE)).concat(
				MONTANT).concat(calculateTotalTaxe(statement).toString()).concat(CURRENCY).concat(NEWLINE)
    			.concat(TOTAL).concat(calculateTotalAmountWithTaxe(statement).toString()).concat(CURRENCY);
	}
    
    

    
}
