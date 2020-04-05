package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudente(Studente studente) {

		final String sql="select * from studente where matricola=?";
		Studente s = studente;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
				st.close();
				conn.close();
			}else {
				st.close();
				conn.close();
			}
			
		}catch(SQLException sqe) {
			System.out.println("Eccezzione SQL");
		}
		return s;
	}
	
	public List<Corso> getCoursesByStudent(Studente studente){
		
		final String sql ="SELECT c.codins, c.nome, c.crediti, c.pd " + 
				"FROM corso AS c, iscrizione AS i, studente AS s " + 
				"WHERE s.matricola =? AND s.matricola=i.matricola AND i.codins=c.codins " + 
				"GROUP BY c.codins, c.nome, c.crediti, c.pd";
		List<Corso> corsi = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Corso cTemp = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsi.add(cTemp);
				
			}
			st.close();
			conn.close();
			return corsi;
		}catch(SQLException sqe) {
			throw new RuntimeException("Errore Db", sqe);
		}
		
	}

}
