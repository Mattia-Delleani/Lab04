package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO dao;
	private StudenteDAO sdao;
	
	public Model() {
		dao = new CorsoDAO();
		sdao = new StudenteDAO();
	}
	
	
	public List<Corso> getCoursesList(){
		
		return dao.getTuttiICorsi(); 
	}
	
	public Studente getStudentByID(Integer matricola) {
		Studente s = new Studente(matricola, null, null, null);
		return sdao.getStudente(s);
		
	}
	
	public List<Studente> getStudentsByCourse(Corso corso){
		
		return dao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCoursesByStudent(Studente studente){
		
		return sdao.getCoursesByStudent(studente);
	}

}
