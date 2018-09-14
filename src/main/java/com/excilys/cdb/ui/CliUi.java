package com.excilys.cdb.ui;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import com.excilys.cdb.service.*;

/**
 * This class is the CLI
 * @author Nassim BOUKHARI
 */
public class CliUi {
	

	public static final Scanner READER = new Scanner(System.in);
	
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
			String caseNumber = READER.nextLine();

			switch(caseNumber) {
			case("0"):
				quit = true;
			break;
			case("1"):
				ComputerServices.showComputers();
			break;
			case("2"):
				CompanyServices.showCompanies();
			break;
			case("3"):
				ComputerServices.showComputerDetail();
			break;
			case("4"):
				ComputerServices.createComputer();
			break;
			case("5"):
				ComputerServices.modifyComputer();
			break;
			case("6"):
				ComputerServices.deleteComputer();
			break;
			default:
				System.out.println("Je n'ai pas compris votre requête, veuillez recommencer.");
				break;
			}
		}while(!quit);
		System.out.println("Au revoir.");
		READER.close();
	}
	public static void main(String[] args) {
		CliUi.Cli();
	}
}