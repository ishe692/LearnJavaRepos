
public class LearningJava {
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		Book book1 = new Book("R.G. Letourneau Mover of Men and Mountains","R.G. Letourneau",1973,20.0,30.0);
		
		System.out.println(" the cost price is : $"+ book1.getCostPrice());
		book1.setSellPrice(100.00);
		System.out.println(" the sell price is : $"+ book1.getSellPrice());
		
	}

}
