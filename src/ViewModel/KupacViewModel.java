package ViewModel;

public class KupacViewModel {
	
	public String getIme() {
		return ime;
	}

	public KupacViewModel(String ime, String korisnickoIme, String prezime, String adresa, String grad, String email,
			String postanskiBroj, String telefon) {
		super();
		this.ime = ime;
		this.korisnickoIme = korisnickoIme;
		this.prezime = prezime;
		this.adresa = adresa;
		this.grad = grad;
		this.email = email;
		this.postanskiBroj = postanskiBroj;
		this.telefon = telefon;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(String postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	private String ime, korisnickoIme, prezime, adresa, grad, email, postanskiBroj, telefon;

}
