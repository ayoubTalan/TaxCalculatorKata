package taxecalculator.domaine;

import java.math.BigDecimal;

public class ProductType {

	String type;
	
	public ProductType(String type) {
        this.type = type;
    }
	
	public BigDecimal getTaxRate() {
		 switch(type) {
		 	case "FOOD":
		 		return BigDecimal.ZERO; 
		 	case "MEDCINE":
		 		return BigDecimal.ZERO;
		 	case "BOOK":
		 		return new BigDecimal("0.10");
		 	default:
		 		return new BigDecimal("0.20");
		 }
	}

}
