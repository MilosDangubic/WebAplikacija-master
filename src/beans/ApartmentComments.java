package beans;

public class ApartmentComments {
	private Long id;
	private String guestCommName;
	private Apartment apartmant;
	private String text;
	private Float rate;
	private boolean approved;
	
	
	public ApartmentComments(Long id,String guestCommName, Apartment apartmant, String text, Float rate, boolean approved) {
		super();
		this.id=id;
		this.guestCommName = guestCommName;
		this.apartmant = apartmant;
		this.text = text;
		this.rate = rate;
		this.approved = approved;
	}
	
	public ApartmentComments(String guestCommName, Apartment apartmant, String text, Float rate, boolean approved) {
		super();
		this.guestCommName = guestCommName;
		this.apartmant = apartmant;
		this.text = text;
		this.rate = rate;
		this.approved = approved;
	}


	public String getGuestCommName() {
		return guestCommName;
	}


	public void setGuestCommName(String guestCommName) {
		this.guestCommName = guestCommName;
	}


	public Apartment getApartmant() {
		return apartmant;
	}


	public void setApartmant(Apartment apartmant) {
		this.apartmant = apartmant;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Float getRate() {
		return rate;
	}


	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public boolean isApproved() {
		return approved;
	}


	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	@Override
	public String toString() {
		return "ApartmanComments [guestCommName=" + guestCommName + ", apartmant=" + apartmant + ", text=" + text
				+ ", rate=" + rate + "]";
	}
	
	
}
