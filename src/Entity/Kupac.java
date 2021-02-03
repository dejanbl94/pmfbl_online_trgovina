package Entity;

public class Kupac {
	
	private String korisnickoIme, ime, prezime, lozinka, telefon, adresa, grad, drzava, postanskiBroj, pol, email;
	private int id;
	public Kupac(int id, String korisnickoIme, String ime, String prezime, String telefon, String adresa, String grad,
			String drzava, String postanskiBroj, String email, String pol, String lozinka) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.postanskiBroj = postanskiBroj;
		this.email = email;
		this.pol = pol;
		this.lozinka = lozinka;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Kupac() {}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public String getLozinka() {
		return this.lozinka;
	}
	
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
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

	public String getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(String postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	public String getEmail() {
		return email;
	}
	
	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getPol() {
		return pol;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Kupac [korisnickoIme=" + korisnickoIme + ", ime=" + ime + ", prezime=" + prezime + ", telefon="
				+ telefon + ", adresa=" + adresa + ", grad=" + grad + ", drzava=" + drzava + ", postanskiBroj="
				+ postanskiBroj + ", pol=" + pol + ", email=" + email + "]";
	}
}
