package tsi.ccre.entidades;

import tsi.ccre.persistencia.ConfiguracaoDAO;

public class Configuracao extends ConfiguracaoDAO {
	
	public static final String CONFIG_PATH = "config\\configuracoes.txt";
	public static final int NUM_CONFIGS = 4;
	private int lookandfeelSelecionado;
	private int larguraJanela;
	private int alturaJanela;
	private boolean janelaMaximizada;

	public int getLookandfeelSelecionado() {
		return lookandfeelSelecionado;
	}

	public void setLookandfeelSelecionado(int lookandfeelSelecionado) {
		this.lookandfeelSelecionado = lookandfeelSelecionado;
	}

	public int getLarguraJanela() {
		return larguraJanela;
	}

	public void setLarguraJanela(int larguraJanela) {
		this.larguraJanela = larguraJanela;
	}

	public int getAlturaJanela() {
		return alturaJanela;
	}

	public void setAlturaJanela(int alturaJanela) {
		this.alturaJanela = alturaJanela;
	}

	public boolean isJanelaMaximizada() {
		return janelaMaximizada;
	}

	public void setJanelaMaximizada(boolean janelaMaximizada) {
		this.janelaMaximizada = janelaMaximizada;
	}

	public boolean gravar() {
		return super.gravarCongiguracoes(CONFIG_PATH, this);
	}
	
	public void carregar() {
		int configs[] = super.carregarConfiguracoes(CONFIG_PATH);
		
		int i = 0;
		lookandfeelSelecionado = configs[i++];
		larguraJanela = configs[i++];
		alturaJanela = configs[i++];
		
		if(configs[i++] == 1)
			janelaMaximizada = true;
		else
			janelaMaximizada = false;
	}

} // class Configuracao
