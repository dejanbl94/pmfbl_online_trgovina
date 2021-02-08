package QueryBuilder;

public final class QueryBuilder {

	/**Helper klasa**/
	
	private QueryBuilder() {
	}

	public static class Kupac {
		
		public static final String GET = "SELECT * FROM kupac WHERE korisnicko_ime = ? AND lozinka = ?";

		public static final String GET_BY_IME = "SELECT * FROM kupac WHERE korisnicko_ime = ?";

		public static final String INSERT = "INSERT INTO kupac ( korisnicko_ime, ime, prezime, lozinka, telefon, adresa, grad, drzava, postanski_broj, pol, email ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	public static class Trgovac {

		public static final String GET_ALL = "SELECT * FROM trgovac";
		
		public static final String GET = "SELECT * FROM trgovac WHERE korisnicko_ime = ? AND lozinka = ?";

		public static final String GET_BY_IME = "SELECT * FROM trgovac WHERE korisnicko_ime = ?";
		
		public static final String GET_BY_PRODAJNO_MJESTO = "SELECT * FROM trgovac WHERE prodajno_mjesto_id = ?";

		public static final String INSERT = "INSERT INTO trgovac ( korisnicko_ime, ime, prezime, lozinka, pol, telefon, email, prodajno_mjesto_id ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
	}
	
	public static class Narudzba {
		
		public static final String GET_FOR_KUPAC = "SELECT * FROM narudzba WHERE kupac_id = ?";
		
		public static final String GET_FOR_TRGOVAC = "SELECT * FROM narudzba WHERE trgovac_id = ?";
		
		public static final String GET_ARTIKAL_NARUDZBE = "SELECT * FROM artikal_narudzbe WHERE narudzba_id = ?";
		
		public static final String DELETE = "DELETE FROM narudzba WHERE id = ?";
		
		public static final String INSERT = "INSERT INTO narudzba ( kupac_id, trgovac_id, datum_narudzbe, datum_isporuke, napomena) VALUES (?, ?, ?, ?, ?)";
		
		public static final String GET_LAST_ID = "SELECT id FROM seminarski_ors1.narudzba ORDER BY id DESC LIMIT 1;";
		
		public static final String UPDATE_TRGOVAC_FOR_NARUDZBA = "UPDATE narudzba SET trgovac_id = ? WHERE id = ?";
		
		public static final String SET_ORDER_FOR_SHIPPING = "UPDATE narudzba SET datum_isporuke = ?, napomena = ? WHERE id = ?";
	}
	
	public static class Proizvod {
		
		public static final String GET_PROIZVOD_NARUDZBE = "SELECT * FROM artikal_narudzbe AS an INNER JOIN proizvod AS pr ON an.proizvod_id = pr.id WHERE narudzba_id = ?";
		
		public static final String GET_ALL = "SELECT * FROM proizvod";
		
		public static final String INSERT = "INSERT INTO proizvod (naziv, opis, cijena) VALUES (?, ?, ?)";
	}
	
	public static class Artikal {

		public static final String INSERT = "INSERT INTO artikal_narudzbe (narudzba_id, proizvod_id, kolicina, cijena_po_komadu) VALUES (?, ?, ?, ?)";
	}
	
	public static class ProdajnoMjesto {
		
		public static final String GET_ALL = "SELECT * FROM prodajno_mjesto";
		
		public static final String INSERT = "INSERT INTO prodajno_mjesto (grad, drzava, adresa, telefon) VALUES (?, ?, ?, ?)";
	}
}
