import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static final String URL = "jdbc:mysql://localhost/computer-database-db";
	public static final String LOGIN = "admincdb";
	public static final String PASSWORD = "qwerty1234";
	public static final Scanner READER = new Scanner(System.in);
	public static Connection cn =null;
	public static Statement st =null;
	

	public static String showComputer(){

		try {
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			String sql = "SELECT * FROM computer";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("***Liste des ordinateurs***\n");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"|"+rs.getString(2));
			}
			rs.close();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String showComputerDetail(){
		try {
			String namePC = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			System.out.println("Veuillez entrer le nom du PC: ");
			namePC = READER.next();
			String sql = "SELECT * FROM computer WHERE name ='"+namePC+"'";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("***Liste des ordinateurs***\n");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getString(4)+" | "+rs.getString(5));
			}
			rs.close();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String showCompany(){
		try {
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			String sql = "SELECT * FROM company";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("***Liste des entreprises***\n");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"|"+rs.getString(2));
			}
			rs.close();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String createComputer() {
		try {
			String namePC = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			System.out.println("Veuillez entrer le nom du nouveau PC à créer: ");
			namePC = READER.nextLine();
			String sql = "INSERT INTO computer (name) VALUES ('"+namePC+"')";

			st.executeUpdate(sql);
			System.out.println("***Ordinateur créé***\n");
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String modifyComputer() {
		try {
			String oldName = null;
			String newName = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			//Scanner reader2 = new Scanner(System.in);
			System.out.println("Veuillez entrer le nom du PC à modifier: ");
			oldName = READER.nextLine();	
			System.out.println("Veuillez entrer le nouveau nom du PC: ");
			newName = READER.nextLine();	
			String sql = "UPDATE computer set name ='"+newName+"' where name = '"+oldName+"'";

			st.executeUpdate(sql);
			System.out.println("***Ordinateur créé***\n");
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String deleteComputer() {	
		try {
			String namePC = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			//Scanner reader2 = new Scanner(System.in);
			System.out.println("Veuillez entrer le nom du PC à supprimer: ");
			namePC = READER.nextLine();
			String sql ="DELETE from computer where name='"+namePC+"'";

			st.executeUpdate(sql);
			System.out.println("***Ordinateur créé***\n");
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static void CLI() {
		boolean quit = false;
		do {
			System.out.println("\nBonjour, vous êtes dans une interface en ligne de commande");
			System.out.println("Menu:");
			System.out.println("1 - Liste des ordinateurs");
			System.out.println("2 - Liste des entreprises");
			System.out.println("3 - Afficher les détails d'un ordinateur");
			System.out.println("4 - Créer un ordinateur");
			System.out.println("5 - Modifier un ordinateur");
			System.out.println("6 - Supprimer un ordinateur");
			System.out.println("0 - Quitter\n");
			System.out.println("Pour choisir une option, veuillez entrer un chiffre:");
			String caseNumber = READER.nextLine();
			/*if(caseNumber.equals("0")) {
				quit = true;
			}
			if(caseNumber.equals("1")) {
				showComputer();
			}
			if(caseNumber.equals("2")) {
				showCompany();
			}
			if(caseNumber.equals("3")) {
				showComputerDetail();
			}
			if(caseNumber.equals("4")) {
				createComputer();
			}
			if(caseNumber.equals("5")) {
				modifyComputer();
			}
			if(caseNumber.equals("6")) {
				deleteComputer();
			}*/
			
			switch(caseNumber) {
			case("0"):
				quit = true;
			break;
			case("1"):
				showComputer();
			break;
			case("2"):
				showCompany();
			break;
			case("3"):
				showComputerDetail();
			break;
			case("4"):
				createComputer();
			break;
			case("5"):
				modifyComputer();
			break;
			case("6"):
				deleteComputer();
			break;
			default:
			break;
			}
		}while(!quit);
		System.out.println("Au revoir.");
		READER.close();
	}

	public static void main(String[] args) {
		CLI();
	}
}