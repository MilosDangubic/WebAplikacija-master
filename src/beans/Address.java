package beans;

public class Address implements java.io.Serializable {

	private static final long serialVersionUID = 6992534927381289360L;
	
	private Long id;
	private String street;
	private Integer number;
	private String city;
	private Integer zipCode;

	public Address() {
		street = "";
		number = -1;
		
	}	

	public Address(String street, Integer number, String city, Integer zipCode) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.zipCode = zipCode;
	}
	
	public Address(Long id, String street, Integer number, String city, Integer zipCode) {
		super();
		this.id = id;
		this.street = street;
		this.number = number;
		this.city = city;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStreet(String x) {
		street = x;
	}

	public void setNumber(int x) {
		number = x;
	}

	public String getStreet() {
		return street;
	}

	public Integer getNumber() {
		return number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", number=" + number + ", city=" + city + ", zipCode="
				+ zipCode + "]";
	}

}
