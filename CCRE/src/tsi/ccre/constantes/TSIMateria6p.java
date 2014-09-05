package tsi.ccre.constantes;

public enum TSIMateria6p {
	MAT1(33, "Segurança em Sistemas Computacionais", 83.33),
	MAT2(34, "Administração e Gerência de Redes de Computadores", 66.40),
	MAT3(35, "Gerência e Configuração de Serviços Internet", 83.20),
	MAT4(36, "Programação para Dispositivos Móveis e Sem Fio", 50),
	MAT5(37, "Serviços de Suporte a Aplicações Distribuidas", 83.20),
	MAT6(38, "Comércio Eletrônico", 50);
	
	private int codDisciplina; // código da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga horária da disciplina
	
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
