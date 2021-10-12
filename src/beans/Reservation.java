package beans;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
	private Long id;
	private Apartment apartment;
	private LocalDate firstDayOfReservation;
	private Integer numberOfNIghts;
	private Float finalPrice;
	private String messageAfterReservation;
	private User guest;
	private StatusOfReservation status;
	
	public Reservation() {}
	
	public Reservation(Apartment apartment, LocalDate firstDayOfReservation, Integer numberOfNIghts, Float finalPrice,
			String messageAfterReservation, User guest, StatusOfReservation status,Long id) {
		super();
		this.apartment = apartment;
		this.firstDayOfReservation = firstDayOfReservation;
		this.numberOfNIghts = numberOfNIghts;
		this.finalPrice = finalPrice;
		this.messageAfterReservation = messageAfterReservation;
		this.guest = guest;
		this.status = status;
		this.id = id;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public LocalDate getFirstDayOfReservation() {
		return firstDayOfReservation;
	}

	public void setFirstDayOfReservation(LocalDate firstDayOfReservation) {
		this.firstDayOfReservation = firstDayOfReservation;
	}

	public Integer getNumberOfNIghts() {
		return numberOfNIghts;
	}

	public void setNumberOfNIghts(Integer numberOfNIghts) {
		this.numberOfNIghts = numberOfNIghts;
	}

	public Float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Float finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getMessageAfterReservation() {
		return messageAfterReservation;
	}

	public void setMessageAfterReservation(String messageAfterReservation) {
		this.messageAfterReservation = messageAfterReservation;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public StatusOfReservation getStatus() {
		return status;
	}

	public void setStatus(StatusOfReservation status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Reservation [id=" + id + ", apartment=" + apartment + ", firstDayOfReservation=" + firstDayOfReservation
				+ ", numberOfNIghts=" + numberOfNIghts + ", finalPrice=" + finalPrice + ", messageAfterReservation="
				+ messageAfterReservation + ", guest=" + guest + ", status=" + status + "]";
	}

	
	
	
	
}
