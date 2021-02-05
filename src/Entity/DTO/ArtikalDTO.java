package Entity.DTO;

public class ArtikalDTO {
	
	public ArtikalDTO() {}

	public ArtikalDTO(int narudzbaId, int proizvodId, int kolicina, double cijenaKomad) {
		super();
		this.narudzbaId = narudzbaId;
		this.proizvodId = proizvodId;
		this.kolicina = kolicina;
		this.cijenaKomad = cijenaKomad;
	}
	
	public ArtikalDTO(int narudzba_id, int proizvod_id, int kolicina, double cijenaPoKomadu, int id) {
		super();
		this.narudzbaId = narudzba_id;
		this.proizvodId = proizvod_id;
		this.kolicina = kolicina;
		this.cijenaKomad = cijenaPoKomadu;
		this.id = id;
	}
	
	private int id, narudzbaId, proizvodId, kolicina;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNarudzbaId() {
		return narudzbaId;
	}
	public void setNarudzbaId(int narudzbaId) {
		this.narudzbaId = narudzbaId;
	}
	public int getProizvodId() {
		return proizvodId;
	}
	public void setProizvodId(int proizvodId) {
		this.proizvodId = proizvodId;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public double getCijenaKomad() {
		return cijenaKomad;
	}
	@Override
	public String toString() {
		return "ArtikalDTO [id=" + id + ", narudzbaId=" + narudzbaId + ", proizvodId=" + proizvodId + ", kolicina="
				+ kolicina + ", cijenaKomad=" + cijenaKomad + "]";
	}

	public void setCijenaKomad(double cijenaKomad) {
		this.cijenaKomad = cijenaKomad;
	}
	private double cijenaKomad;

}
