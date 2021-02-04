package Entity;

public class Trgovac {
	
	private String korisnickoIme, ime, prezime, lozinka, pol, telefon, email, prodajnoMjestoId;
	private int id;
	
	public Trgovac(int id, String korisnickoIme, String ime, String prezime, String lozinka, String pol, String telefon,
			String email, int prodajnoMjestId) {
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.pol = pol;
		this.telefon = telefon;
		this.email = email;
		this.prodajnoMjestId = prodajnoMjestId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Trgovac() {}

	public String getProdajnoMjestoId() {
		return prodajnoMjestoId;
	}

	public void setProdajnoMjestoId(String prodajnoMjestoId) {
		this.prodajnoMjestoId = prodajnoMjestoId;
	}

	private int prodajnoMjestId;
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProdajnoMjestId() {
		return prodajnoMjestId;
	}

	public void setProdajnoMjestId(int prodajnoMjestId) {
		this.prodajnoMjestId = prodajnoMjestId;
	}

	@Override
	public String toString() {
		return "Trgovac [korisnickoIme=" + korisnickoIme + ", ime=" + ime + ", prezime=" + prezime + ", lozinka="
				+ lozinka + ", pol=" + pol + ", telefon=" + telefon + ", email=" + email + ", prodajnoMjestoId="
				+ prodajnoMjestoId + ", prodajnoMjestId=" + prodajnoMjestId + "]";
	}
}
