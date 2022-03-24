package taxecalculator.service;

import taxecalculator.domaine.Product;
import taxecalculator.domaine.Statement;

public interface Printer {

	String printProduct(Product product);
	String printStatement(Statement statement);
}
