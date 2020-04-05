package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		StudenteDAO sdao = new StudenteDAO();
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		Studente s = new Studente(1461201, null, null, null);
		
		System.out.println(sdao.getStudente(s).toString());
		Corso corso = new Corso("01KSUPG",0,null,0);
		System.out.println(cdao.getStudentiIscrittiAlCorso(corso).toString());
	}

}
