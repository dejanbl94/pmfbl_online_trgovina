package Entity;

public class Proizvod {
	
	private String naziv, opis;
	private double cijena;
	private int kolicina;
	
	public Proizvod(String naziv, String opis, double cijena, int kolicina) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.cijena = cijena;
		this.kolicina = kolicina;
	}
	
	public Proizvod() {}
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

}
