import java.sql.SQLException;

import Database.DAO.ArtikalDAO;
import Database.DAO.KupacDAO;
import Entity.Kupac;
import Entity.DTO.ArtikalDTO;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		KupacDAO kupacDAO = new KupacDAO();
		ArtikalDAO artikalDAO = new ArtikalDAO();
		boolean postoji;
		try {
			postoji = kupacDAO.exists("Milos", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji1 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji2 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean postoji3 = kupacDAO.exists("Milos", "d324e075a64b676698d58f0e14b9a766");
			System.out.println(postoji);
			Kupac kupac = kupacDAO.getBy("Milos");
			System.out.println(postoji1);
			System.out.println(postoji2);
			System.out.println(postoji3);
			System.out.println(kupac);
			java.util.List<ArtikalDTO> lista = artikalDAO.getAll(10100, null);
			System.out.println(lista);
			
			// Test insert
			//Kupac k = new Kupac("Dejan", "Dejan", "Tamamovic", "test", "+38765291191", "Milana Cvijetica 7", "Banja Luka", "BiH", "78000", "M", "dejanbltamamovic@gmail.com");
			//System.out.println(kupacDAO.add(k));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
