package tsi.ccre.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class IgSobre extends JDialog {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 8199052092197249345L;

	private final String NOME_MODULO;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public IgSobre(IgCcre igCcre) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.NOME_MODULO = igCcre.NOME_PROGRAMA + " - Sobre o CCRE";
		
		Color wetAsphalt = new Color(52, 73, 94);
		setTitle(NOME_MODULO);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel tituloPanel = new JPanel();
		tituloPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tituloPanel.setBackground(wetAsphalt);
		contentPanel.add(tituloPanel, BorderLayout.NORTH);
		tituloPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitulo = new JLabel("  Informa\u00E7\u00F5es do Software:");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		tituloPanel.add(lblTitulo, BorderLayout.WEST);
		
		JPanel sobrePanel = new JPanel();
		contentPanel.add(sobrePanel, BorderLayout.CENTER);
		sobrePanel.setLayout(new MigLayout("", "[][grow]", "[][grow][][][][]"));
		
		JLabel labelImgSobre = new JLabel("");
		labelImgSobre.setIcon(new ImageIcon(IgSobre.class.getResource("/tsi/ccre/recursos/imagens/Device-Calculator-48.png")));
		sobrePanel.add(labelImgSobre, "cell 0 0");
		
		JLabel lblAgndaEletrnica = new JLabel("    CCRE");
		lblAgndaEletrnica.setFont(new Font("SansSerif", Font.BOLD, 18));
		sobrePanel.add(lblAgndaEletrnica, "cell 1 0,alignx left");
		
		JPanel separadorPanel = new JPanel();
		sobrePanel.add(separadorPanel, "cell 0 1 2 1,grow");
		separadorPanel.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		separadorPanel.add(separator, BorderLayout.CENTER);
		
		JLabel lblNome = new JLabel("Calculador do Coeficiente de Rendimento Escolar");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sobrePanel.add(lblNome, "cell 1 2");
		
		JLabel lblCurso = new JLabel("Tecnologia em Sistemas para Internet - IFSUDMG");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sobrePanel.add(lblCurso, "cell 1 3");
		
		JLabel lblVersao = new JLabel("Vers\u00E3o 1.0 - Copyright \u00A9 2014");
		lblVersao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sobrePanel.add(lblVersao, "cell 1 4");
		
		JLabel lblAutor = new JLabel("Criado por: Gian Carlos Barros Hon\u00F3rio");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 12));
		sobrePanel.add(lblAutor, "cell 1 5");
		
		setLocationRelativeTo(igCcre);
		setVisible(true);
	} // construtor
	
} // class IgSobre
