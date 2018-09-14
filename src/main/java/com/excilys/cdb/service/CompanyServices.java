package com.excilys.cdb.service;

import com.excilys.cdb.persistence.CompanyDao;

/**
 * This class that delivers the requests the to the class CompanyDao
 * @author Nassim BOUKHARI
 */
public class CompanyServices {
	
	/**
	 * This method displays all the companies
	 * @author Nassim BOUKHARI
	 */
	public static void showCompanies(){
		CompanyDao.getAllCompanies();
	}
}