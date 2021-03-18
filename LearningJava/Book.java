
public class Book {
	
	
	private String title;
	private String author;
	private int year;
	private double costPrice;
	private double sellPrice;
	private int id;
	private static int nextID =0;
	
	public Book(String title, String author, int year, double costPrice, double sellPrice) {
		
		this.title = title;
		this.author = author;
		this.year = year;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.id = nextID++;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public int getId() {
		return id;
	}

	public void setSellPrice(double d) {
		// TODO Auto-generated method stub
		this.sellPrice = d;
	}
	

}
