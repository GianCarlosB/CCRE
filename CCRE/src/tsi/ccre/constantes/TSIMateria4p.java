package tsi.ccre.constantes;

public enum TSIMateria4p {
	MAT1(20, "Protocolos de Comunicação", 50),
	MAT2(21, "Teoria da Comunicação", 50),
	MAT3(22, "Sistemas Distribuidos", 66.66),
	MAT4(23, "Sistemas Operacionais Distribuidos", 83.33),
	MAT5(24, "Webservices", 33.33),
	MAT6(25, "Engenharia de Software", 50),
	MAT7(26, "Desenvolvimento de Aplicações Web", 83.33);
	
	private int codDisciplina; // código da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga horária da disciplina
	
	private TSIMateria4p(int codDisciplina, String nomeDisciplina,
			double cargaHorariaDisciplina) {
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.cargaHorariaDisciplina = cargaHorariaDisciplina;
	} // construtor()

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public double getCargaHorariaDisciplina() {
		return cargaHorariaDisciplina;
	}
	
} // enum Materia3p
