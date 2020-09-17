package entities.Products;

//Class inherits Product
public class equipment extends Product{

	//General equipment constructor
	public equipment(String productCode, String productType,String name, String cost) {
		super(productCode, productType, cost);
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	//Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCost() {
		return cost;
	}

	//returns 0 because there is no discount for equipment based on date purchased
	public int getMonth() {
		return 0;
	}

	

}
