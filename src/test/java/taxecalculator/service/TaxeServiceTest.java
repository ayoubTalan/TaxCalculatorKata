package taxecalculator.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import taxecalculator.domaine.Product;
import taxecalculator.domaine.ProductType;
import taxecalculator.domaine.Statement;

public class TaxeServiceTest {

	
	private  Product product;
	private TaxeService taxeService;
	
	
	@Before
	public void setup() {
		product =  new Product("1 CD musical à ",new ProductType("NON"),1,new BigDecimal("14.99"),false);
		taxeService = new TaxeService();
	}
	
	@Test
	public void calculate_Price_With_Taxe() {
		
		assertEquals(new BigDecimal("18.00"),taxeService.calculatePriceWithTaxe(product));	
	}
	
	@Test 
	public void printProduct(){
		
		assertEquals( "1 CD musical à 14.99€ : 18.00€"  ,taxeService.printProduct(product));	
	}
	
	@Test 
	public void firstBasketDetails() {
		
		Product livre = new Product("2 livres à ",new ProductType("BOOK"),2,new BigDecimal("12.49"),false);
		Product cd_musical = new Product("1 CD musical à ",new ProductType("OTHER"),1,new BigDecimal("14.99"),false);
		Product chocolat = new Product("3 barres de chocolat à ",new ProductType("FOOD"),3,new BigDecimal("0.85"),false);
		Statement firstStatement = new Statement();
		firstStatement.add(livre).add(cd_musical).add(chocolat);
		String expected =
				"2 livres à 12.49€ : 27.50€\n"+
				"1 CD musical à 14.99€ : 18.00€\n"+
				"3 barres de chocolat à 0.85€ : 2.55€\n"+
				"Montant des taxes : 5.50€\n"+
				"Total : 48.05€";
		
		assertEquals(expected ,taxeService.printStatement(firstStatement) );
	}
	
	
	
	@Test
	public void secondBasketDetails() {
		Product boite_chocolat = new Product("2 boîtes de chocolats importé à ",new ProductType("FOOD"),2,new BigDecimal("10"),true);	
		Product parfum = new Product("3 flacons de parfum importé à ",new ProductType("NON"),3,new BigDecimal("47.50"),true);
		Statement statementSecondBasket = new Statement();
		statementSecondBasket.add(boite_chocolat).add(parfum);
		String expected = 
				"2 boîtes de chocolats importé à 10€ : 21.00€\n"+
				"3 flacons de parfum importé à 47.50€ : 178.15€\n"+
				"Montant des taxes : 36.65€\n"
				 + "Total : 199.15€";
		
		assertEquals(expected ,taxeService.printStatement(statementSecondBasket) );
	}
	
	@Test
	public void thirdBasketDetails() {
		
        Product flacons_de_parfum = new Product("2 flacons de parfum importé à ",new ProductType("OTHER"),2,new BigDecimal("27.99"),true);
		Product flacon_de_parfum = new Product("1 flacon de parfum à ",new ProductType("OTHER"),1,new BigDecimal("18.99"),false);
		Product pilules  = new Product("3 boîtes de pilules contre la migraine à ",new ProductType("MEDCINE"),3,new BigDecimal("9.75"),false);	
		Product boîtes_de_chocolats = new Product("2 boîtes de chocolats importés à ",new ProductType("FOOD"),2,new BigDecimal("11.25"),true);
		Statement statementThirdBasket = new Statement();
		statementThirdBasket.add(flacons_de_parfum).add(flacon_de_parfum).add(pilules).add(boîtes_de_chocolats);
		String expected = 
				"2 flacons de parfum importé à 27.99€ : 70.00€\n"+
			    "1 flacon de parfum à 18.99€ : 22.80€\n"+
				"3 boîtes de pilules contre la migraine à 9.75€ : 29.25€\n"+
				"2 boîtes de chocolats importés à 11.25€ : 23.65€\n"+
				"Montant des taxes : 18.95€\n"+
				"Total : 145.70€";
		
		assertEquals(expected ,taxeService.printStatement(statementThirdBasket));
	}
	
}
