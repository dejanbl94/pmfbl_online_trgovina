package Entity;


public class ProdajnoMjesto {
	
	private String grad, drzava, adresa, telefon;
	private int id;

	public ProdajnoMjesto(String grad, String drzava, String adresa, String telefon) {
		super();
		this.grad = grad;
		this.drzava = drzava;
		this.adresa = adresa;
		this.telefon = telefon;
	}
	
	public ProdajnoMjesto() {}


	@Override
	public String toString() {
		return "ProdajnoMjesto [grad=" + grad + ", drzava=" + drzava + ", adresa=" + adresa + ", telefon=" + telefon
				+ ", id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
}
