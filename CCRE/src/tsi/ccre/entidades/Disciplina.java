package tsi.ccre.entidades;

import java.util.List;

public class Disciplina {

	private int codDisciplina; // c�digo da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga hor�ria da disciplina
	private double nota; // nota na disciplina
	
	public Disciplina() {
		super();
	}

	public Disciplina(int codDisciplina, String nomeDisciplina,
			double cargaHorariaDisciplina, double nota) {
		super();
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.cargaHorariaDisciplina = cargaHorariaDisciplina;
		this.nota = nota;
	}
	
	public Disciplina(int codDisciplina, String nomeDisciplina,
			double cargaHorariaDisciplina) {
		super();
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.cargaHorariaDisciplina = cargaHorariaDisciplina;
	}

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public double getCargaHorariaDisciplina() {
		return cargaHorariaDisciplina;
	}

	public void setCargaHorariaDisciplina(double cargaHorariaDisciplina) {
		this.cargaHorariaDisciplina = cargaHorariaDisciplina;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
	
	/**
	 * calcula o coeficiente de rendimento escolar baseado nos dados da lista de disciplinas passada
	 * por par�metro
	 * f�rmula usada: CRE = (N1 x H1) + (N2 x H2) + ... + (Nk x Hk) / (H1 + H2 + ... + Hk)
	 */
	public double calcularCRE(List<Disciplina> disciplinas) {
		double cre = 0;
		double somaPesos = 0;
		
		// loop para percorrer a lista de disciplinas
		for(Disciplina d : disciplinas) {
			// se a nota for igual a -1 siginifica que a disciplina corrente n�o entrar� na contagem
			if(d.getNota() != -1) {
				// soma de todas as notas multiplicadas com o seu respectivo peso (carga hor�ria)
				cre += (d.getNota() * d.getCargaHorariaDisciplina());
				
				// soma de todas as cargas hor�rias
				somaPesos += d.getCargaHorariaDisciplina();
			}	
		}
		
		// retorna a soma de todas as notas multiplicadas com o seu respectivo peso dividido ela soma dos pesos
		return cre /= somaPesos;
	} // calcularCRE()

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("C�digo: ").append(codDisciplina)
				.append("\nNome: ").append(nomeDisciplina)
				.append("\nCarga Hor�ria: ").append(cargaHorariaDisciplina)
				.append("\nNota: ").append(nota);
		return builder.toString();
	}
	
} // class Disciplina
