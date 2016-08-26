package dm.book.rulethirtyfour.browseData_blob;

public class Site {
	private String id;
	private byte[] image;

	public Site() {
	}

	public Site(String id, byte[] image) {
		this.id = id;
		this.image = image;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
