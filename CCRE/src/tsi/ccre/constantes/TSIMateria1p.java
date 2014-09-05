package tsi.ccre.constantes;

public enum TSIMateria1p {
	MAT1(1, "Arquitetura de Computadores", 50),
	MAT2(2, "Inform�tica Instrumental", 50),
	MAT3(3, "L�gica de Programa��o", 100),
	MAT4(4, "Desenvolvimento de P�ginas Web", 50),
	MAT5(5, "Fundamentos Matem�ticos da Computa��o", 100),
	MAT6(6, "Reda��o T�cnica", 33.33),
	MAT7(7, "Gest�o Empresarial B�sica", 33.33);
	
	private int codDisciplina; // c�digo da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga hor�ria da disciplina
	
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
