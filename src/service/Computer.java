package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Computer {
	public static final String URL = "jdbc:mysql://localhost/computer-database-db";
	public static final String LOGIN = "admincdb";
	public static final String PASSWORD = "qwerty1234";
	public static final Scanner READER = new Scanner(System.in);
	public static Connection cn = null;
	public static Statement st = null;

	public static String showComputers(){

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
			String idPC = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			System.out.println("Veuillez entrer l'id de l'ordinateur: ");
			idPC = READER.next();
			String sql = "SELECT * FROM computer WHERE id ='"+idPC+"'";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("***Détails de l'ordinateur***\n");
			if(rs.next()) {
				System.out.println("Id: "+rs.getInt(1)+"\nName: "+rs.getString(2)+"\nDate de lancement: "+rs.getString(3)+"\nDate d'arrêt: "+rs.getString(4)+"\nId de l'entreprise: "+rs.getString(5));
			}
			else {
				System.out.println("L'ordinateur que vous avez spécifié n'existe pas.");
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
			System.out.println("Veuillez entrer le nom du nouvel ordinateur à créer: ");
			namePC = READER.nextLine();
			namePC = namePC.replaceAll("'", "\'");
			String sql = "INSERT INTO computer (name) VALUES ('"+namePC+"')";
			
			st.executeUpdate(sql);
			System.out.println("***Ordinateur créé***\n");
			return "success";
		} catch (Exception e) {
			System.out.println("Le nom que vous avez entrée n'est pas valide.\n");
			return e.getMessage();
		}
	}

	public static String modifyComputer() {
		try {
			boolean quit = false;
			String idPC = null;
			String choice = null;
			String columnToModify = null;
			String newValue = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			System.out.println("Veuillez entrer l'id de l'ordinateur à modifier: ");
			idPC = READER.nextLine();
			String PC = "SELECT * FROM computer WHERE id="+idPC+"";
			ResultSet getName = st.executeQuery(PC);
			while(getName.next())
				System.out.println("\nVoici les informations concernant l'ordinateur que vous pouvez modifier: \nNom: "+getName.getString(2)+"\nDate de lancement: "+getName.getString(3)+"\nDate d'arrêt: "+getName.getString(4)+"\n");
			do {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Que voulez vous modifier? ");
				System.out.println("1 - Le nom\n2 - La date de lancement\n3 - La date d'arrêt\n0 - Quitter la modification");
				choice = READER.nextLine();
				switch(choice) {
				case("0"):
					quit = true;
					System.out.println("Vous avez choisi de quitter la modification.");
				break;
				case("1"):
					try {
					columnToModify = "name";
					System.out.println("Veuillez entrer le nouveau nom de l'ordinateur: ");
					newValue = READER.nextLine();
					System.out.println("Voici le nouveau nom de l'ordinateur: "+newValue+"\n");
					String sql = "UPDATE computer set "+columnToModify+" ='"+newValue+"' where id = '"+idPC+"'";
					st.executeUpdate(sql);
					}
					catch(Exception e) {
						System.out.println("Le nom que vous avez entrée n'est pas valide.\n");
					}
				break;
				case("2"):
					try {
						columnToModify = "introduced";
						System.out.println("Veuillez entrer la nouvelle date du lancement de l'ordinateur (au format YYYY-mm-dd): ");
						newValue = READER.nextLine();
						System.out.println("Voici la nouvelle date du lancement de l'ordinateur: "+newValue+"\n");
						String sql2 = "UPDATE computer set "+columnToModify+" ='"+newValue+"' where id = '"+idPC+"'";
						st.executeUpdate(sql2);
					}
					catch(Exception e) {
						System.out.println("La date que vous avez entrée n'est pas valide.\n");
					}
				break;
				case("3"):
					try {
					columnToModify = "discontinued";
					System.out.println("Veuillez entrer la nouvelle date de l'arrêt de l'ordinateur (au format YYYY-mm-dd): ");
					newValue = READER.nextLine();
					System.out.println("Voici la nouvelle date de l'arrêt de l'ordinateur: "+newValue+"\n");
					String sql3 = "UPDATE computer set "+columnToModify+" ='"+newValue+"' where id = '"+idPC+"'";
					st.executeUpdate(sql3);
					}
				catch(Exception e) {
					System.out.println("La date que vous avez entrée n'est pas valide.");
				}
				break;
				default:
					System.out.println("Je n'ai pas compris votre requête, veuillez recommencer.\n");
				break;
				}
			}while(!quit);
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
			System.out.println("Veuillez entrer le nom de l'ordinateur à supprimer: ");
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
}
