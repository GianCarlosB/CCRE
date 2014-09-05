package tsi.ccre.constantes;

public enum TSIMateria2p {
	MAT1(8, "Sistemas Operacionais", 100),
	MAT2(9, "Projeto de Sistemas de Banco de Dados", 66.66),
	MAT3(10, "Estruturas de Dados I", 83.33),
	MAT4(11, "An�lise de Sistemas", 50),
	MAT5(12, "Linguagem de Programa��o", 83.33),
	MAT6(13, "Metodologia de Pesquisa Cient�fica e Tecnol�gica", 33.33);
	
	private int codDisciplina; // c�digo da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga hor�ria da disciplina
	
	private TSIMateria2p(int codDisciplina, String nomeDisciplina,
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
	
} // enum Materia2p
