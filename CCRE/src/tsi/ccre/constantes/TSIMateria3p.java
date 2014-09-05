package tsi.ccre.constantes;

public enum TSIMateria3p {
	MAT1(14, "Redes de Computadores", 100),
	MAT2(15, "Tecnologia de Orientação a Objetos", 133.33),
	MAT3(16, "Linguagem de Programação Visual", 66.66),
	MAT4(17, "Estruturas de Dados II", 50),
	MAT5(18, "Gestão de Projetos", 33.33),
	MAT6(19, "Empreendedorismo", 33.33);
	
	private int codDisciplina; // código da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga horária da disciplina
	
	private TSIMateria3p(int codDisciplina, String nomeDisciplina,
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
