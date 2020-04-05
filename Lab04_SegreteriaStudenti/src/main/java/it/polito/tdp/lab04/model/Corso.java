package it.polito.tdp.lab04.model;

public class Corso {
	private String codIns;
	private int numeroCrediti;
	private String nome;
	private int periodoDidattico;
	/**
	 * @param codIns
	 * @param numeroCrediti
	 * @param nome
	 * @param periodoDidattico
	 */
	public Corso(String codIns, int numeroCrediti, String nome, int periodoDidattico) {
		super();
		this.codIns = codIns;
		this.numeroCrediti = numeroCrediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}
	
	
	public String getCodIns() {
		return codIns;
	}
	public void setCodIns(String codIns) {
		this.codIns = codIns;
	}
	public int getNumeroCrediti() {
		return numeroCrediti;
	}
	public void setNumeroCrediti(int numeroCrediti) {
		this.numeroCrediti = numeroCrediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPeriodoDidattico() {
		return periodoDidattico;
	}
	public void setPeriodoDidattico(int periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}


	@Override
	public String toString() {
		return nome;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codIns == null) ? 0 : codIns.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codIns == null) {
			if (other.codIns != null)
				return false;
		} else if (!codIns.equals(other.codIns))
			return false;
		return true;
	}
	
	
	

}
