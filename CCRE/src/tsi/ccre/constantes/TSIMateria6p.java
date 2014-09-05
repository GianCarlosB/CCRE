package tsi.ccre.constantes;

public enum TSIMateria6p {
	MAT1(33, "Seguran�a em Sistemas Computacionais", 83.33),
	MAT2(34, "Administra��o e Ger�ncia de Redes de Computadores", 66.40),
	MAT3(35, "Ger�ncia e Configura��o de Servi�os Internet", 83.20),
	MAT4(36, "Programa��o para Dispositivos M�veis e Sem Fio", 50),
	MAT5(37, "Servi�os de Suporte a Aplica��es Distribuidas", 83.20),
	MAT6(38, "Com�rcio Eletr�nico", 50);
	
	private int codDisciplina; // c�digo da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga hor�ria da disciplina
	
	private TSIMateria6p(int codDisciplina, String nomeDisciplina,
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
