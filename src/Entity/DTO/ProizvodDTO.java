package Entity.DTO;

import java.util.List;

public class ProizvodDTO {

	private int kupacId, proizvodId, kolicina, narudzbaId;
	private List<Integer> trgovciId;
	private String naziv, opis, datumNarudzbe, napomena;
	
	public ProizvodDTO(int kupacId, int narudzbaId, List<Integer> trgovciId, int proizvodId, int kolicina, String naziv, String opis,
			String datumNarudzbe, String napomena, double cijenaKomad) {
		super();
		this.narudzbaId = narudzbaId;
		this.kupacId = kupacId;
		this.trgovciId = trgovciId;
		this.proizvodId = proizvodId;
		this.kolicina = kolicina;
		this.naziv = naziv;
		this.opis = opis;
		this.datumNarudzbe = datumNarudzbe;
		this.napomena = napomena;
		this.cijenaKomad = cijenaKomad;
	}
	
	public int getNarudzbaId() {
		return narudzbaId;
	}

	public void setNarudzbaId(int narudzbaId) {
		this.narudzbaId = narudzbaId;
	}

	public List<Integer> getTrgovciId() {
		return trgovciId;
	}

	public void setTrgovciId(List<Integer> trgovciId) {
		this.trgovciId = trgovciId;
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
		return "ProizvodDTO [kupacId=" + kupacId + ", proizvodId=" + proizvodId + ", kolicina=" + kolicina
				+ ", narudzbaId=" + narudzbaId + ", trgovciId=" + trgovciId + ", naziv=" + naziv + ", opis=" + opis
				+ ", datumNarudzbe=" + datumNarudzbe + ", napomena=" + napomena + ", cijenaKomad=" + cijenaKomad + "]";
	}

	public int getKupacId() {
		return kupacId;
	}
	public void setKupacId(int kupacId) {
		this.kupacId = kupacId;
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
