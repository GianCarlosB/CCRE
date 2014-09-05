package tsi.ccre.constantes;

public enum TSIMateria1p {
	MAT1(1, "Arquitetura de Computadores", 50),
	MAT2(2, "Informática Instrumental", 50),
	MAT3(3, "Lógica de Programação", 100),
	MAT4(4, "Desenvolvimento de Páginas Web", 50),
	MAT5(5, "Fundamentos Matemáticos da Computação", 100),
	MAT6(6, "Redação Técnica", 33.33),
	MAT7(7, "Gestão Empresarial Básica", 33.33);
	
	private int codDisciplina; // código da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga horária da disciplina
	
	private TSIMateria1p(int codDisciplina, String nomeDisciplina,
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
	
} // enum Materia1p
