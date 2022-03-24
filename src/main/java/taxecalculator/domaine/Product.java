package taxecalculator.domaine;

import java.math.BigDecimal;

public class Product {
	
	private String name;
	private ProductType productType;
	private BigDecimal price;
	private boolean isImported;
	private int unit ;
	
	public Product() {}
	
	public Product(String name, ProductType productType,int unit , BigDecimal price, boolean isImported) {
		this.name = name;
		this.productType = productType;
		this.unit = unit == 0 ? 1 : unit;
		this.price = price.multiply(new BigDecimal(unit));
		this.isImported = isImported;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}
	
	public int getUnit() {
		return unit;
	}
	
	public void setUnit(int unit) {
		this.unit = unit;
	}
}
