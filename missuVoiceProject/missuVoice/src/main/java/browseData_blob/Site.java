package browseData_blob;

public class Site {
	private String id;
    private String name;
    private String voice;
    private int cca;
	private byte[] image;
    private byte[] camerapag;

	public Site() {
	}

	public Site(String id,String name,String voice, byte[] image) {
		this.id = id;
        this.name = name;
        this.voice= voice;
		this.image = image;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

    public void setvoice(String voice) {
        this.voice = voice;
    }

    public String getvoice() {
        return voice;
    }

    public void setname(String name) {
        this.name = name;
    }
    public void setcamera(byte[] pag) {
        this.camerapag = pag;
    }
    public byte[] getcamera() {
        return camerapag;
    }


    public String getname() {
        return name;
    }




    public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
