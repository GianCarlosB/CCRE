package tsi.ccre.constantes;

public enum TSIMateria5p {
	MAT1(27, "Projeto de Redes de Computadores", 100),
	MAT2(28, "TCP-IP e Roteamento", 83.33),
	MAT3(29, "Ger�ncia de Sistemas de Informa��o", 33.33),
	MAT4(30, "Tecnologias M�veis e Sem Fio", 66.66),
	MAT5(31, "Tecnologia de Banco de Dados Distribuidos", 83.33),
	MAT6(32, "Estatistica", 50);
	
	private int codDisciplina; // c�digo da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga hor�ria da disciplina
	
	private TSIMateria5p(int codDisciplina, String nomeDisciplina,
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
