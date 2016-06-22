package br.ufc.model;

public enum Papel {
	LEITOR("Leitor"), EDITOR("Editor"), JORNALISTA("Jornalista");
	
	private String papel;
	
	Papel(String papel){
		this.setPapel(papel);;
	}
	
	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}	
}
