package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import model.Computer;

/**
 * This class does all the functionnalities about computers
 * @author Nassim BOUKHARI
 */
public class ComputerDao {

	private static final String URL = "jdbc:mysql://localhost/computer-database-db";
	private static final String LOGIN = "admincdb";
	private static final String PASSWORD = "qwerty1234";
	public static final Scanner READER = new Scanner(System.in);
	private static Connection cn;
	private static Statement st;

	/**
	 * This method displays all the computers
	 * @author Nassim BOUKHARI
	 */
	public static void getAllComputers() {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * This method displays all the details about a computer
	 * @author Nassim BOUKHARI
	 */
	public static void getComputerDetails() {
		try {
			int idPC = '0';
			//Computer.setId(idPC);
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			System.out.println("Veuillez entrer l'id de l'ordinateur: ");
			idPC = Integer.parseInt(READER.next());
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a computer
	 * @author Nassim BOUKHARI
	 */
	public static void setComputer() {
		try {
			String namePC = null;
			String introducedPC = null;
			String discontinuedPC = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			System.out.println("Veuillez entrer le nom du nouvel ordinateur à créer: ");
			namePC = READER.nextLine();
			System.out.println("Veuillez entrer la date de lancement du nouvel ordinateur à créer: ");
			introducedPC = READER.nextLine();
			System.out.println("Veuillez entrer la date d'arrêt du nouvel ordinateur à créer: ");
			discontinuedPC = READER.nextLine();
			String sql = "INSERT INTO computer (name,introduced,discontinued) VALUES ('"+namePC+"','"+introducedPC+"','"+discontinuedPC+"')";
			st.executeUpdate(sql);
			String sqlSelect = "SELECT * FROM computer WHERE name='"+namePC+"' and introduced ='"+introducedPC+"'";
			st.executeQuery(sqlSelect);
			/*while(getPC.next())
				System.out.println("\nVoici les informations concernant l'ordinateur que vous venez de créer: \nNom: "+getPC.getString(2)+"\nDate de lancement: "+getPC.getString(3)+"\nDate d'arrêt: "+getPC.getString(4)+"\n");
			 */
		} catch (SQLException e) {
			//System.out.println("Le nom que vous avez entré n'est pas valide.\n");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method updates a computer
	 * @author Nassim BOUKHARI
	 */
	public static void updateComputer() {
		try {
			boolean quit = false;
			int idPC = '0';
			String choice = null;
			String columnToModify = null;
			String newValue = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			System.out.println("Veuillez entrer l'id de l'ordinateur à modifier: ");
			idPC = Integer.parseInt(READER.nextLine());
			if(idPC > 0) {
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
			}
			else {
				System.out.println("L'id que vous avez entré est incorrect.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method deletes a computer
	 * @author Nassim BOUKHARI
	 */
	public static void removeComputer() {
		try {
			String idPC = null;
			cn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			st = cn.createStatement();
			//Scanner reader2 = new Scanner(System.in);
			System.out.println("Veuillez entrer l'id de l'ordinateur à supprimer: ");
			idPC = READER.nextLine();
			String sql ="DELETE from computer where name='"+idPC+"'";

			st.executeUpdate(sql);
			System.out.println("***Ordinateur créé***\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}