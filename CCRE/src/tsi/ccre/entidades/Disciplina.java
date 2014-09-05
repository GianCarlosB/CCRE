package tsi.ccre.entidades;

import java.util.List;

public class Disciplina {

	private int codDisciplina; // código da disciplina
	private String nomeDisciplina; // nome da disciplina
	private double cargaHorariaDisciplina; // carga horária da disciplina
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
	 * por parâmetro
	 * fórmula usada: CRE = (N1 x H1) + (N2 x H2) + ... + (Nk x Hk) / (H1 + H2 + ... + Hk)
	 */
	public double calcularCRE(List<Disciplina> disciplinas) {
		double cre = 0;
		double somaPesos = 0;
		
		// loop para percorrer a lista de disciplinas
		for(Disciplina d : disciplinas) {
			// se a nota for igual a -1 siginifica que a disciplina corrente não entrará na contagem
			if(d.getNota() != -1) {
				// soma de todas as notas multiplicadas com o seu respectivo peso (carga horária)
				cre += (d.getNota() * d.getCargaHorariaDisciplina());
				
				// soma de todas as cargas horárias
				somaPesos += d.getCargaHorariaDisciplina();
			}	
		}
		
		// retorna a soma de todas as notas multiplicadas com o seu respectivo peso dividido ela soma dos pesos
		return cre /= somaPesos;
	} // calcularCRE()

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Código: ").append(codDisciplina)
				.append("\nNome: ").append(nomeDisciplina)
				.append("\nCarga Horária: ").append(cargaHorariaDisciplina)
				.append("\nNota: ").append(nota);
		return builder.toString();
	}
	
} // class Disciplina
