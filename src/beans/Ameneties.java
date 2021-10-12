package beans;

public class Ameneties {
	private Long id;
	private boolean deleted;
	private String name;
	
	public Ameneties(Long id, boolean deleted, String name) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.name = name;
	}

	public Ameneties() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
