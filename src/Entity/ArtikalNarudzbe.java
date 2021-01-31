package Entity;

public class ArtikalNarudzbe {
	
	private int narudzba_id, proizvod_id, kolicina;
	private double cijenaPoKomadu;
	private int id;
	
	public ArtikalNarudzbe(int narudzba_id, int proizvod_id, int kolicina, double cijenaPoKomadu, int id) {
		super();
		this.narudzba_id = narudzba_id;
		this.proizvod_id = proizvod_id;
		this.kolicina = kolicina;
		this.cijenaPoKomadu = cijenaPoKomadu;
		this.id = id;
	}

	public int getNarudzba_id() {
		return narudzba_id;
	}

	public void setNarudzba_id(int narudzba_id) {
		this.narudzba_id = narudzba_id;
	}

	public int getProizvod_id() {
		return proizvod_id;
	}

	public void setProizvod_id(int proizvod_id) {
		this.proizvod_id = proizvod_id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getCijenaPoKomadu() {
		return cijenaPoKomadu;
	}

	public void setCijenaPoKomadu(double cijenaPoKomadu) {
		this.cijenaPoKomadu = cijenaPoKomadu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ArtikalNarudzbe [narudzba_id=" + narudzba_id + ", proizvod_id=" + proizvod_id + ", kolicina=" + kolicina
				+ ", cijenaPoKomadu=" + cijenaPoKomadu + ", id=" + id + "]";
	}
}
