package entities.Products;

//Class inherits Product
public class parkingPass extends Product{

	//General parking pass constructor
	public parkingPass(String productCode, String productType, String cost) {
		super(productCode, productType, cost);
		// TODO Auto-generated constructor stub
	}

	//getters and setters
	@Override
	public String getCost() {
		// TODO Auto-generated method stub
		return cost;
	}

	//returns 0 because there is no discount for equipment based on date purchased
	@Override
	public int getMonth() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
