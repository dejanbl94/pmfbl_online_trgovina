package Entity.DTO;

public class ProizvodDTO {

	private int kupacId, trgovacId, proizvodId, kolicina;
	private String naziv, opis, datumNarudzbe, napomena;
	public ProizvodDTO(int kupacId, int trgovacId, int proizvodId, int kolicina, String naziv, String opis,
			String datumNarudzbe, String napomena, double cijenaKomad) {
		super();
		this.kupacId = kupacId;
		this.trgovacId = trgovacId;
		this.proizvodId = proizvodId;
		this.kolicina = kolicina;
		this.naziv = naziv;
		this.opis = opis;
		this.datumNarudzbe = datumNarudzbe;
		this.napomena = napomena;
		this.cijenaKomad = cijenaKomad;
	}
	
	public ProizvodDTO() {}
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
	private double cijenaKomad;
	
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
	public void setCijenaKomad(double cijenaKomad) {
		this.cijenaKomad = cijenaKomad;
	}
	@Override
	public String toString() {
		return "ProizvodDTO [kupacId=" + kupacId + ", trgovacId=" + trgovacId + ", proizvodId=" + proizvodId
				+ ", kolicina=" + kolicina + ", datumNarudzbe=" + datumNarudzbe + ", napomena=" + napomena
				+ ", cijenaKomad=" + cijenaKomad + "]";
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
	public String getNapomena() {
		return napomena;
	}
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
}
