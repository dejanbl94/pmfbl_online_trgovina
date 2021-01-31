package QueryBuilder;

public final class QueryBuilder {

	// Ovo je samo staticna helper klasa, ne trebaju nam instance.
	private QueryBuilder() {
	}

	public static class Kupac {
		
		public static final String GET = "SELECT * FROM kupac WHERE korisnicko_ime = ? AND lozinka = ?";

		public static final String GET_BY_IME = "SELECT * FROM kupac WHERE korisnicko_ime = ?";

		public static final String INSERT = "INSERT INTO kupac ( korisnicko_ime, ime, prezime, lozinka, telefon, adresa, grad, drzava, postanski_broj, pol, email ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	public static class Trgovac {

		public static final String GET = "SELECT * FROM trgovac WHERE korisnicko_ime = ? AND lozinka = ?";

		public static final String GET_BY_IME = "SELECT * FROM trgovac WHERE korisnicko_ime = ?";

		public static final String INSERT = "INSERT INTO trgovac ( korisnicko_ime, ime, prezime, lozinka, pol, email, prodajno_mjesto_id ) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	}
	
	public static class Narudzba {
		
		public static final String GET_FOR_KUPAC = "SELECT * FROM narudzba WHERE kupac_id = ?";
	}
}
