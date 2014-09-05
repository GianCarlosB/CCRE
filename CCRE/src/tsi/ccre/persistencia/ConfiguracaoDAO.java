package tsi.ccre.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import tsi.ccre.entidades.Configuracao;

public abstract class ConfiguracaoDAO {
	
	protected boolean gravarCongiguracoes(String path, Configuracao conf) {
		try {
			FileWriter arq = new FileWriter(path);
		    PrintWriter gravarArq = new PrintWriter(arq);
		    
		    gravarArq.println(String.format("lookandfeel=%s", conf.getLookandfeelSelecionado()));
		    gravarArq.println(String.format("largura_janela=%s", conf.getLarguraJanela()));
		    gravarArq.println(String.format("altura_janela=%s", conf.getAlturaJanela()));
		    gravarArq.println(String.format("janela_maximizada=%s", conf.isJanelaMaximizada()));
		    
		    arq.close();
		    
		    return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	} // gravarLookAndFeel()
	
	protected int[] carregarConfiguracoes(String path) {
		int configs[] = new int[Configuracao.NUM_CONFIGS];
		int i = 0;
		
	    try {
			FileReader arq = new FileReader(path);
			BufferedReader lerArq = new BufferedReader(arq);

			/*
			 * a variável "linha" recebe o valor "null" quando o processo
			 * de repetição atingir o final do arquivo texto
			 */
			for(String linha = lerArq.readLine(), valor; linha != null; linha = lerArq.readLine()) {
				valor = linha.substring(linha.indexOf('=') + 1, linha.length());
				
				try {
					if((Integer.parseInt(valor) >= 0 && Integer.parseInt(valor) <= 4 && i == 0) || (Integer.parseInt(valor) >= 0 && i != 0))
						configs[i++] = Integer.parseInt(valor);
				} catch(NumberFormatException e) {
					if(valor.equals("true") && i == 3)
						configs[i++] = 1;
				}
			}
  
			arq.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	    
	    //for(int g = 0; g < configs.length; g++)System.out.println(configs[g]);
		return configs;
	} // carregarLookAndFeel()

} // class ConfiguracaoDAO
