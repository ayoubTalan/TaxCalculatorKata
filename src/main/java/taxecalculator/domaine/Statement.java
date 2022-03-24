package taxecalculator.domaine;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    private List<Product> products = new ArrayList<>();

    public Statement add(Product product) {
        products.add(product);
        return this;
    }

    public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
