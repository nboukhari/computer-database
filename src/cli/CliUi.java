package cli;
import java.util.concurrent.TimeUnit;
import service.*;
public class CliUi {

	public static void Cli(){
		boolean quit = false;
		do {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			String caseNumber = Company.READER.nextLine();

			switch(caseNumber) {
			case("0"):
				quit = true;
			break;
			case("1"):
				Computer.showComputers();
			break;
			case("2"):
				Company.showCompanies();
			break;
			case("3"):
				Computer.showComputerDetail();
			break;
			case("4"):
				Computer.createComputer();
			break;
			case("5"):
				Computer.modifyComputer();
			break;
			case("6"):
				Computer.deleteComputer();
			break;
			default:
				System.out.println("Je n'ai pas compris votre requête, veuillez recommencer.");
				break;
			}
		}while(!quit);
		System.out.println("Au revoir.");
		Computer.READER.close();
	}
}
