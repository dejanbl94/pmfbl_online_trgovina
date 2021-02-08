package Entity;

public class Proizvod {

	private String naziv, opis;
	private double cijena;
	private int kolicina;
	private int id;

	public Proizvod(String naziv, String opis, double cijena, int kolicina) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.cijena = cijena;
		this.kolicina = kolicina;
	}

	public Proizvod(int id, String naziv, String opis, double cijena) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.cijena = cijena;
		this.id = id;
	}
	
	public Proizvod(String naziv, String opis, double cijena) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.cijena = cijena;
	}

	public Proizvod() {
	}

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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Proizvod [naziv=" + naziv + ", opis=" + opis + ", cijena=" + cijena + ", kolicina=" + kolicina + ", id="
				+ id + "]";
	}

}
