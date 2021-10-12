package beans;

public class ApartmentContent {
	private Long id;
	private String content;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ApartmentContent(Long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	@Override
	public String toString() {
		return "ApartmanContent [id=" + id + ", content=" + content + "]";
	}
	
	
}
