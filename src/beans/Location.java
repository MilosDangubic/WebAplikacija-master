package beans;

public class Location {
	
	private Long id;
	private Float lat;
	private Float lng;
	private Address address;
	
	public Location(Float lat, Float lng, Address address) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.address = address;
	}
	
	public Location(Long id, Float lat, Float lng, Address address) {
		super();
		this.id = id;
		this.lat = lat;
		this.lng = lng;
		this.address = address;
	}
	

	public Location() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", lat=" + lat + ", lng=" + lng + ", address=" + address + "]";
	}
	
	

}
