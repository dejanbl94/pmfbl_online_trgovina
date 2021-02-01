import java.sql.SQLException;

import Database.DAO.KupacDAO;
import Entity.Kupac;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		KupacDAO kupacDAO = new KupacDAO();
		boolean postoji;
		try {
			postoji = kupacDAO.exists("Milos", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji1 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji2 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean postoji3 = kupacDAO.exists("Milos22", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji4 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji5 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean postoji6 = kupacDAO.exists("Milos2", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji7 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji8 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean postoji9 = kupacDAO.exists("Milos2", "d324e075a64b676698d58f0e14b9a766");
			boolean postoji11 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean posto123 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean post3123oji6 = kupacDAO.exists("Milos2", "d324e075a64b676698d58f0e14b9a766");
			boolean po12312stoji7 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean post1231oji8 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean post1231oji9 = kupacDAO.exists("Milos2", "d324e075a64b676698d58f0e14b9a766");
			boolean posqweqtoji7 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean postoqweji8 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean postqweqwoji9 = kupacDAO.exists("Milos2", "d324e075a64b676698d58f0e14b9a766");
			boolean postojiwqeqw11 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean postqweqwo123 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean post31qweqw23oji6 = kupacDAO.exists("Milos2", "d324e075a64b676698d58f0e14b9a766");
			boolean po12qweqw312stoji7 = kupacDAO.exists("Milos123", "d324e075a64b676698d58f0e14b9a766");
			boolean postqweqw1231oji8 = kupacDAO.exists("Milos2", "d324e075a642b676698d58f0e14b9a766");
			boolean posteqq1231oji9 = kupacDAO.exists("Milos2", "d324e075a64b676698d58f0e14b9a766");
			Kupac kupac = kupacDAO.getBy("Janko");
			System.out.println(postoji1);
			System.out.println(postoji2);
			System.out.println(postoji3);
			System.out.println(postoji4);
			System.out.println(postoji5);
			System.out.println(postoji6);
			System.out.println(postoji7);
			System.out.println(postoji8);
			System.out.println(postoji9);
			System.out.println(kupac);
			
			// Test insert
			Kupac k = new Kupac("Dejan", "Dejan", "Tamamovic", "test", "+38765291191", "Milana Cvijetica 7", "Banja Luka", "BiH", "78000", "M", "dejanbltamamovic@gmail.com");
			System.out.println(kupacDAO.add(k));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
