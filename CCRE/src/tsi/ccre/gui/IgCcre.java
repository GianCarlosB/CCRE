package tsi.ccre.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tsi.ccre.constantes.TSIMateria1p;
import tsi.ccre.constantes.TSIMateria2p;
import tsi.ccre.constantes.TSIMateria3p;
import tsi.ccre.constantes.TSIMateria4p;
import tsi.ccre.constantes.TSIMateria5p;
import tsi.ccre.constantes.TSIMateria6p;
import tsi.ccre.entidades.Configuracao;
import tsi.ccre.entidades.Disciplina;
import javax.swing.ImageIcon;

public class IgCcre extends JFrame {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -6107492905448294459L;
	
	public final String NOME_PROGRAMA = "CCRE";
	private final String NOME_MODULO;
	private final String CALCULO = "CRE = (N1 x H1) + (N2 x H2) + ... + (Nk x Hk) / (H1 + H2 + ... + Hk)";
	private final String MSG_SELECAO = "Selecione at\u00E9 qual per\u00EDodo cursou integralmente:";
	
	private Configuracao conf;
	private final int LARGURA = 800;
	private final int ALTURA = 600;
	
	// rótulos com os nomes dos lookandfeels
	private final String ROTULO_LF_METAL = "Metal";
	private final String ROTULO_LF_NIMBUS = "Nimbus";
	private final String ROTULO_LF_MOTIF = "CDE/Motif";
	private final String ROTULO_LF_WINDOWS = "Windows";
	private final String ROTULO_LF_WINDOWS_CLASSIC = "Windows Classic";
	
	private UIManager.LookAndFeelInfo looksAndFell[];
	private final String LOOK_AND_FEEL_NAMES[] = {ROTULO_LF_METAL, ROTULO_LF_NIMBUS, ROTULO_LF_MOTIF,
			ROTULO_LF_WINDOWS, ROTULO_LF_WINDOWS_CLASSIC};
	
	private String periodos[] = {"1º período", "2º período", "3º período", "4º período", "5º período", "6º período"};
	private final int NUM_PERIODOS = 6;
	private int periodoSelecionado;
	
	// lista com as matérias dos períodos
	private List<TSIMateria1p> listaMat1p;
	private List<TSIMateria2p> listaMat2p;
	private List<TSIMateria3p> listaMat3p;
	private List<TSIMateria4p> listaMat4p;
	private List<TSIMateria5p> listaMat5p;
	private List<TSIMateria6p> listaMat6p;
	
	// lista com todas as disciplinas até o período cursado
	private List<Disciplina> disciplinas;
	
	private final int NUMERO_COLUNAS_TABELA = 4; // número de colunas da tabela
	
	// nomes das colunas da tabela
	private final String COLUNA_CODIGO = "Código da Disciplina";
	private final String COLUNA_DISCIPLINA = "Disciplina";
	private final String COLUNA_CARGA_HORARIA = "Carga Horária";
	private final String COLUNA_NOTA = "Nota";
	
	private final String[] COLUNAS_TABELA = {COLUNA_CODIGO, COLUNA_DISCIPLINA, COLUNA_CARGA_HORARIA, COLUNA_NOTA};
	private String[][] linhasTabela = new String[0][NUMERO_COLUNAS_TABELA];
	
	private JPanel contentPane;
	private JPanel painelAbas[];
	private JTable tabelaPaineis[];
	private JRadioButtonMenuItem looksAndFellRadioButtonMenuItem[];
	private ButtonGroup lookAndFeelButtonGroup;
	private JComboBox<Object> periodosComboBox;
	private JTabbedPane periodosTabbedPane;
	private JTextField creTextField;
	private JMenu mnTemas;
	
	/**
	 * cria a janela principal
	 */
	public IgCcre() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				encerrarPrograma();
			}
		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				salvarConfigsJanela();
			}
		});
		
		// inicializa as listas com os dados das matérias dos períodos
		listaMat1p = new ArrayList<TSIMateria1p>(Arrays.asList(TSIMateria1p.values()));
		listaMat2p = new ArrayList<TSIMateria2p>(Arrays.asList(TSIMateria2p.values()));
		listaMat3p = new ArrayList<TSIMateria3p>(Arrays.asList(TSIMateria3p.values()));
		listaMat4p = new ArrayList<TSIMateria4p>(Arrays.asList(TSIMateria4p.values()));
		listaMat5p = new ArrayList<TSIMateria5p>(Arrays.asList(TSIMateria5p.values()));
		listaMat6p = new ArrayList<TSIMateria6p>(Arrays.asList(TSIMateria6p.values()));
		
		// inicializa o objeto de manipulação da configuração
		conf = new Configuracao();
		
		// carrega as configurações do programa do arquivo de configuração
		conf.carregar();
		
		if(conf.isJanelaMaximizada())
			setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		this.NOME_MODULO = ".: " + NOME_PROGRAMA + " - Calculador do Coeficiente de Rendimento Escolar :.";
		setTitle(NOME_MODULO);
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCcre.class.getResource("/tsi/ccre/recursos/imagens/Device-Calculator-256.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(LARGURA, ALTURA));
		setBounds(100, 100, conf.getLarguraJanela(), conf.getAlturaJanela());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic(KeyEvent.VK_A);
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encerrarPrograma();
			}
		});
		mntmSair.setMnemonic(KeyEvent.VK_S);
		mnArquivo.add(mntmSair);
		
		JMenu mnAparencia = new JMenu("Apar\u00EAncia");
		mnAparencia.setMnemonic(KeyEvent.VK_P);
		menuBar.add(mnAparencia);
		
		mnTemas = new JMenu("Temas");
		mnTemas.setMnemonic(KeyEvent.VK_T);
		mnAparencia.add(mnTemas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel cabecalhoPanel = new JPanel();
		FlowLayout fl_cabecalhoPanel = (FlowLayout) cabecalhoPanel.getLayout();
		fl_cabecalhoPanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(cabecalhoPanel, BorderLayout.NORTH);
		
		JLabel lblSelecPeriodo = new JLabel(MSG_SELECAO);
		lblSelecPeriodo.setDisplayedMnemonic(KeyEvent.VK_S);
		cabecalhoPanel.add(lblSelecPeriodo);
		
		periodosComboBox = new JComboBox<Object>(periodos);
		periodosComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAbas();
			}
		});
		lblSelecPeriodo.setLabelFor(periodosComboBox);
		cabecalhoPanel.add(periodosComboBox);
		
		JPanel rodapePanel = new JPanel();
		rodapePanel.setBorder(new TitledBorder(null, "Coeficiente de Rendimento Escolar", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(59, 59, 59)));
		contentPane.add(rodapePanel, BorderLayout.SOUTH);
		rodapePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCre = new JLabel("CRE:");
		lblCre.setFont(new Font("SansSerif", Font.PLAIN, 18));
		rodapePanel.add(lblCre);
		
		creTextField = new JTextField();
		creTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		creTextField.setEditable(false);
		lblCre.setLabelFor(creTextField);
		rodapePanel.add(creTextField);
		creTextField.setColumns(4);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setIcon(new ImageIcon(IgCcre.class.getResource("/tsi/ccre/recursos/imagens/Branch-Engineering-24.png")));
		btnCalcular.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibirCRE();
			}
		});
		btnCalcular.setMnemonic(KeyEvent.VK_C);
		rodapePanel.add(btnCalcular);
		
		JLabel lblCalculo = new JLabel(CALCULO);
		lblCalculo.setFont(new Font("SansSerif", Font.PLAIN, 10));
		rodapePanel.add(lblCalculo);
		
		// define o painel de abas e seus respectivos paineis
		periodosTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(periodosTabbedPane, BorderLayout.CENTER);
		
		// inicializa as tabelas do painel de abas
		tabelaPaineis = new JTable[NUM_PERIODOS];
		
		// inicializa os paineis do painel de abas
		painelAbas = new JPanel[NUM_PERIODOS];
		inicializarPaineis();
		
		// define as opções para configurar o look-and-feel. 
		looksAndFellRadioButtonMenuItem = new JRadioButtonMenuItem[LOOK_AND_FEEL_NAMES.length];
		
		// obtém informações sobre os look-and-feel instalados pelo JDK.
		looksAndFell = UIManager.getInstalledLookAndFeels();
		
		// inicializa os botões de rádio do menu 'temas'
		inicializarBotoesDeRadio();
		
		JMenuItem mntmRestaurarTamanhoPadro = new JMenuItem("Restaurar Tamanho Padr\u00E3o");
		mntmRestaurarTamanhoPadro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restaurarTamanhoPadrao();
			}
		});
		mntmRestaurarTamanhoPadro.setMnemonic(KeyEvent.VK_R);
		mnAparencia.add(mntmRestaurarTamanhoPadro);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setMnemonic(KeyEvent.VK_J);
		menuBar.add(mnAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre o CCRE");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgSobre(IgCcre.this);
			}
		});
		mntmSobre.setMnemonic(KeyEvent.VK_S);
		mnAjuda.add(mntmSobre);
		
		// adiciona todos os botões de rádio de lookandfeel a um grupo de botões
		lookAndFeelButtonGroup = new ButtonGroup();
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[0]);
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[1]);
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[2]);
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[3]);
		lookAndFeelButtonGroup.add(looksAndFellRadioButtonMenuItem[4]);
		
		alterarLookAndFell(LOOK_AND_FEEL_NAMES[conf.getLookandfeelSelecionado()], conf.getLookandfeelSelecionado());
		
		// adiciona as abas de acordo com a opção selecionada na caixa de combinação (que neste caso será apenas a primeira aba)
		addAbas();

		setLocationRelativeTo(null);
		setVisible(true);
	} // construtor()
	
	/**
	 * Inicializa e preenche todos os paineis que serão utilizados no painel de abas
	 */
	private void inicializarPaineis() {
		painelAbas[0] = new JPanel();
		painelAbas[0].setLayout(new BorderLayout(0, 0));
		painelAbas[0].add(preecherPainel(1), BorderLayout.CENTER);
		
		painelAbas[1] = new JPanel();
		painelAbas[1].setLayout(new BorderLayout(0, 0));
		painelAbas[1].add(preecherPainel(2), BorderLayout.CENTER);
		
		painelAbas[2] = new JPanel();
		painelAbas[2].setLayout(new BorderLayout(0, 0));
		painelAbas[2].add(preecherPainel(3), BorderLayout.CENTER);
		
		painelAbas[3] = new JPanel();
		painelAbas[3].setLayout(new BorderLayout(0, 0));
		painelAbas[3].add(preecherPainel(4), BorderLayout.CENTER);
		
		painelAbas[4] = new JPanel();
		painelAbas[4].setLayout(new BorderLayout(0, 0));
		painelAbas[4].add(preecherPainel(5), BorderLayout.CENTER);
		
		painelAbas[5] = new JPanel();
		painelAbas[5].setLayout(new BorderLayout(0, 0));
		painelAbas[5].add(preecherPainel(6), BorderLayout.CENTER);
	} // inicializarPaineis()
	
	/**
	 * Inicializa os botões de rádio do menu 'temas'
	 */
	private void inicializarBotoesDeRadio() {
		looksAndFellRadioButtonMenuItem[0] = new JRadioButtonMenuItem(ROTULO_LF_METAL);
		looksAndFellRadioButtonMenuItem[0].setSelected(true);
		looksAndFellRadioButtonMenuItem[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[0], 0);
			}
		});
		looksAndFellRadioButtonMenuItem[0].setMnemonic(KeyEvent.VK_M);
		mnTemas.add(looksAndFellRadioButtonMenuItem[0]);
		
		looksAndFellRadioButtonMenuItem[1] = new JRadioButtonMenuItem(ROTULO_LF_NIMBUS);
		looksAndFellRadioButtonMenuItem[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[1], 1);
			}
		});
		looksAndFellRadioButtonMenuItem[1].setMnemonic(KeyEvent.VK_N);
		mnTemas.add(looksAndFellRadioButtonMenuItem[1]);
		
		looksAndFellRadioButtonMenuItem[2] = new JRadioButtonMenuItem(ROTULO_LF_MOTIF);
		looksAndFellRadioButtonMenuItem[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[2], 2);
			}
		});
		looksAndFellRadioButtonMenuItem[2].setMnemonic(KeyEvent.VK_C);
		mnTemas.add(looksAndFellRadioButtonMenuItem[2]);
		
		looksAndFellRadioButtonMenuItem[3] = new JRadioButtonMenuItem(ROTULO_LF_WINDOWS);
		looksAndFellRadioButtonMenuItem[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[3], 3);
			}
		});
		looksAndFellRadioButtonMenuItem[3].setMnemonic(KeyEvent.VK_W);
		mnTemas.add(looksAndFellRadioButtonMenuItem[3]);
		
		looksAndFellRadioButtonMenuItem[4] = new JRadioButtonMenuItem(ROTULO_LF_WINDOWS_CLASSIC);
		looksAndFellRadioButtonMenuItem[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarLookAndFell(LOOK_AND_FEEL_NAMES[4], 4);
			}
		});
		looksAndFellRadioButtonMenuItem[4].setMnemonic(KeyEvent.VK_I);
		mnTemas.add(looksAndFellRadioButtonMenuItem[4]);
	} // inicializarBotoesDeRadio()
	
	/**
	 * Altera a aparência e o comportamento (look-and-feel) da interface gráfica usando o nome do 
	 * lookAndFell. Ativa o item de menu correspondente ao look-and-feel.
	 */
	private void alterarLookAndFell(String lookAndFell, int lookAndFellItemMenu) {
		try {
			 for (int lf = 0; lf < looksAndFell.length; lf++)
				if (lookAndFell.equalsIgnoreCase(looksAndFell[lf].getName())) 
				{
					// muda o valor do lookandfeel selecionado
					conf.setLookandfeelSelecionado(lf);
					
					// configura o novo look-and-feel carregando a classe de aparência e comportamento através do método getClassName.
					UIManager.setLookAndFeel(looksAndFell[lf].getClassName());
					
					// seleciona o item de menu correspondente ao look-and-feel.
					looksAndFellRadioButtonMenuItem[lookAndFellItemMenu].setSelected(true);
					
					// atualiza os paineis
					inicializarPaineis();
					addAbas();
					
					break;
				}
			/*
			 * altera a aparência e o comportamento de cada componente anexado ao argumento do método 
			 * updateComponentTreeUI da classe SwingUtilities para a nova aparência e o novo comportamento 
			 * escolhido pelo usuário. 
			 */
			SwingUtilities.updateComponentTreeUI(this);
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "NOME_PROGRAMA" + " - ERRO", 0);
			ex.printStackTrace();
		}
	} // alterarLookAndFell()
	
	/**
	 * Retorna um painel com uma tabela com os dados do período desejado.
	 */
	private JPanel preecherPainel(int periodo) {
		JPanel panel = null; // painel que será retornado
		List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		
		/*
		 *  determina qual o período selecionado, e preenche a lista de disciplinas com os dados
		 *  das matérias do período.
		 */
		switch(periodo) {
		case 1:
			for(TSIMateria1p mp1 : listaMat1p)
				listaDisciplinas.add(new Disciplina(mp1.getCodDisciplina(), mp1.getNomeDisciplina(), mp1.getCargaHorariaDisciplina()));
			break;
		case 2:
			for(TSIMateria2p mp2 : listaMat2p)
				listaDisciplinas.add(new Disciplina(mp2.getCodDisciplina(), mp2.getNomeDisciplina(), mp2.getCargaHorariaDisciplina()));
			break;
		case 3:
			for(TSIMateria3p mp3 : listaMat3p)
				listaDisciplinas.add(new Disciplina(mp3.getCodDisciplina(), mp3.getNomeDisciplina(), mp3.getCargaHorariaDisciplina()));
			break;
		case 4:
			for(TSIMateria4p mp4 : listaMat4p)
				listaDisciplinas.add(new Disciplina(mp4.getCodDisciplina(), mp4.getNomeDisciplina(), mp4.getCargaHorariaDisciplina()));
			break;
		case 5:
			for(TSIMateria5p mp5 : listaMat5p)
				listaDisciplinas.add(new Disciplina(mp5.getCodDisciplina(), mp5.getNomeDisciplina(), mp5.getCargaHorariaDisciplina()));
			break;
		case 6:
			for(TSIMateria6p mp6 : listaMat6p)
				listaDisciplinas.add(new Disciplina(mp6.getCodDisciplina(), mp6.getNomeDisciplina(), mp6.getCargaHorariaDisciplina()));
			break;
		} // fim switch
		
		// loop para percorrer a lista de disciplinas e adiciona-las a tabela
		for(int i = 0; i < listaDisciplinas.size(); i++) {
			panel = new JPanel();
			
			panel.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane, BorderLayout.CENTER);
			
			tabelaPaineis[periodo - 1] = new JTable();
			tabelaPaineis[periodo - 1].setFont(new Font("SansSerif", Font.BOLD, 14));
			tabelaPaineis[periodo - 1].getTableHeader().setFont(new Font("Segoe Condensed", Font.BOLD, 16));
			tabelaPaineis[periodo - 1].setRowHeight(50);
			tabelaPaineis[periodo - 1].setModel(new DefaultTableModel(linhasTabela, COLUNAS_TABELA) {
		        private static final long serialVersionUID = 1L;
		        
		        // permite que apenas a última coluna seja editada (coluna de nota)
		        public boolean isCellEditable(int row, int column) {                
		        	return column == 3 ? true : false;
		        };
		    });
			
			// define o tamanho de cada coluna
			tabelaPaineis[periodo - 1].getColumnModel().getColumn(0).setPreferredWidth(50);
			tabelaPaineis[periodo - 1].getColumnModel().getColumn(1).setPreferredWidth(300);
			tabelaPaineis[periodo - 1].getColumnModel().getColumn(2).setPreferredWidth(10);
			tabelaPaineis[periodo - 1].getColumnModel().getColumn(3).setPreferredWidth(10);
			
			scrollPane.setViewportView(tabelaPaineis[periodo - 1]);
			
			DefaultTableModel modelo = ((DefaultTableModel)(tabelaPaineis[periodo - 1].getModel()));
			
			Object[] linha = new Object[NUMERO_COLUNAS_TABELA];
			
			// adiciona os dados da disciplina na linha da tabela
			for(int j = 0; j < listaDisciplinas.size(); j++) {
				linha[0] = listaDisciplinas.get(j).getCodDisciplina();
				linha[1] = listaDisciplinas.get(j).getNomeDisciplina();
				linha[2] = listaDisciplinas.get(j).getCargaHorariaDisciplina();
				linha[3] = "";
				modelo.addRow(linha);
			} // fim for
		} // fim for
		
		return panel;
	} // preecherPainel()
	
	/**
	 * Adiciona as abas ao painel central de acordo com o período selecionado na caixa de combinação.
	 */
	private void addAbas() {
		String itemSelecionado = periodosComboBox.getSelectedItem().toString(); // variável com o texto do item selecionado na caixa de combinação
		
		// loop para encontrar o período que foi selecionado na caixa de combinação
		for(int i = 0; i < periodos.length; i++)
			if(itemSelecionado.equals(periodos[i])) {
				periodoSelecionado = i;
				break;
			}
		
		periodosTabbedPane.removeAll(); // remove todas as abas
		for(int i = 0; i <= periodoSelecionado; i++)
			periodosTabbedPane.addTab(periodos[i], painelAbas[i]); // adiciona a aba ao painel
	} // addAbas()
	
	/**
	 * reliza as devidas validações e exibe na tela (caso os dados tenham sido informados corretamente)
	 * o valor do cre
	 */
	private void exibirCRE() {
		// variáveis para armazenar os dados da disicplina
		int codDisciplina = 0;
		String nomeDisciplina = "";
		double cargaHorariaDisciplina = 0;
		double nota = 0;
		
		/*
		 *  variável que indica se há pelo menos um elemento válido na coluna de notas,
		 *  o que significa que é possível calcular o cre
		 */
		boolean valorValido = false;
		
		// strings de armazenamento dos dados das notas que não foram preenchidas corretamente
		String codDisciplinaErro = "";
		String codDisciplinaAviso = "";
		
		// componente visual usado para exibir os dados das notas que não foram preenchidas corretamente caso necessário
		JTextArea jTextArea = new JTextArea(10, 50);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		jTextArea.setEditable(false);
		jTextArea.setFont(new Font("Segoe Condensed", Font.BOLD, 16));
		
		// inicializando a lista para armazenar todas as disciplinas (até o período selecionado)
		disciplinas = new ArrayList<Disciplina>();
		
		// loop para percorrer as tabelas (até o período selecionado)
		for(int i = 0; i <= periodoSelecionado; i++) {
			// loop para percorrer as linhas da tabela
			for(int linha = 0, coluna = -1; linha < tabelaPaineis[i].getRowCount(); linha++, coluna = -1) {
				try {
					// armazenando os dados da tabela nas variáveis dos dados da disicplina
					codDisciplina = Integer.parseInt(tabelaPaineis[i].getModel().getValueAt(linha, ++coluna).toString());
					nomeDisciplina = tabelaPaineis[i].getModel().getValueAt(linha, ++coluna).toString();
					cargaHorariaDisciplina = Double.parseDouble(tabelaPaineis[i].getModel().getValueAt(linha, ++coluna).toString());
					
					// se a coluna "nota" foi preenchida então o dado é armazenado
					if(!tabelaPaineis[i].getModel().getValueAt(linha, ++coluna).toString().equals("")) {
						nota = Double.parseDouble(tabelaPaineis[i].getModel().getValueAt(linha, coluna).toString());
						
						// valida se a nota está entre os valores permitidos
						if(nota >= 0 && nota <= 100) {
							disciplinas.add(new Disciplina(codDisciplina, nomeDisciplina, cargaHorariaDisciplina, nota));
							valorValido = true;
						}
						else
							codDisciplinaErro += String.format("\n    %dº período - código da disciplina: %d", i + 1, codDisciplina);
					}
					// senão a nota recebe -1 (significa que está disciplina não entrará no cálculo do cre)
					else {
						nota = -1;
						codDisciplinaAviso += String.format("\n    %dº período - código da disciplina: %d", i + 1, codDisciplina);
					}
				} catch(NumberFormatException e) {
					codDisciplinaErro += String.format("\n    %dº período - código da disciplina: %d", i + 1, codDisciplina);
				}
			} // fim for
		} // fim for
		
		// exibe uma mensagem de erro informando as linhas que não foram preenchidas corretamente
		if(!codDisciplinaErro.equals("")) {
			jTextArea.setText(String.format("As notas das seguintes disciplinas não foram preenchidas corretamente:\n"
					+ "OBS: As notas devem receber apenas números no intervalo de 0 a 100.\n%s" , codDisciplinaErro));
			JOptionPane.showMessageDialog(this, jScrollPane, NOME_PROGRAMA + " - Erro", 0);
		}
		else {
			/*
			 *  exite uma mensagem de aviso informando as disciplinas que não tiveram as notas preenchidas
			 *  e portanto não entrarão no cálculo do cre
			 */
			if(!codDisciplinaAviso.equals("")) {
				jTextArea.setText(String.format(String.format("As notas das seguintes disciplinas não foram preenchidas:\n"
						+ "OBS: Tais notas não entrarão no cálculo do Coeficiente de Rendimento Escolar.\n%s" , codDisciplinaAviso)));
				JOptionPane.showMessageDialog(this, jScrollPane, NOME_PROGRAMA + " - Aviso", 2);
			}
			
			// caso haja pelo menos uma nota válida o cálculo do cre é realizado e exibido na tela
			if(valorValido)
				creTextField.setText(String.format("%.2f", new Disciplina().calcularCRE(disciplinas)));
		}
	} // calcularCRE()
	
	/**
	 * restaura o tamanho da janela para as dimenssões padrão e a posiciona no centro da tela
	 */
	private void restaurarTamanhoPadrao() {
		setBounds(100, 100, LARGURA, ALTURA);
		setLocationRelativeTo(null);
	} // restaurarTamanhoPadrao()
	
	/**
	 * salva as configurações da janela (dimenssões e se está ou não maximizada)
	 */
	private void salvarConfigsJanela() {
		if(getExtendedState() != 0)
			conf.setJanelaMaximizada(true);
		else {
			conf.setJanelaMaximizada(false);
			conf.setLarguraJanela(getWidth());
			conf.setAlturaJanela(getHeight());
		}
	} // salvarConfigs()
	
	/**
	 * termina o programa gravando as configurações e liberando os recursos
	 */
	private void encerrarPrograma() {
		conf.gravar();
		
		dispose();
		System.exit(0);
	} // encerrarPrograma()

}// class IgCcre
