package Entity;

public class Narudzba {
	
	private int id, kupacId, trgovacId;
	private String datumNarudzbe, datumIsporuke, napomena;

	public Narudzba(int id, int kupacId, int trgovacId, String datumNarudzbe, String datumIsporuke, String napomena) {
		super();
		this.id = id;
		this.kupacId = kupacId;
		this.trgovacId = trgovacId;
		this.datumNarudzbe = datumNarudzbe;
		this.datumIsporuke = datumIsporuke;
		this.napomena = napomena;
	}
	
	public Narudzba() {}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getKupacId() {
		return kupacId;
	}

	public void setKupacId(int kupacId) {
		this.kupacId = kupacId;
	}

	public int getTrgovacId() {
		return trgovacId;
	}

	public void setTrgovacId(int trgovacId) {
		this.trgovacId = trgovacId;
	}

	public String getDatumNarudzbe() {
		return datumNarudzbe;
	}

	public void setDatumNarudzbe(String datumNarudzbe) {
		this.datumNarudzbe = datumNarudzbe;
	}

	public String getDatumIsporuke() {
		return datumIsporuke;
	}

	public void setDatumIsporuke(String datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	@Override
	public String toString() {
		return "Narudzba [id =" + id + ", kupacId=" + kupacId + ", trgovacId=" + trgovacId + ", datumNarudzbe=" + datumNarudzbe
				+ ", datumIsporuke=" + datumIsporuke + ", napomena=" + napomena + "]";
	}
}
