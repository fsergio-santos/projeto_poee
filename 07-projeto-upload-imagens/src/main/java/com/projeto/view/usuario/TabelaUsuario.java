package com.projeto.view.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;
import java.awt.Font;


public class TabelaUsuario extends JInternalFrame {

private static final long serialVersionUID = -4444187456836841069L;

	
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int EMAIL = 2;
		
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabelaUsuario;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JPanel panelPagination;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;
	private JLabel lblPesquisar;
	private JTextField textFieldNomeUsuario;
	private JLabel lblPagina;
	private JLabel lblInicio;
	private JLabel lblde;
	private JLabel lblfinal;
	
	private TabelaUsuarioModel tabelaUsuarioModel;
	private TableRowSorter<TabelaUsuarioModel> sortTabelaUsuario;
	private List<RowSorter.SortKey> sortKeys;
	
	private int row=0;
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;
	private JLabel lblTotalRegistros;
	private JLabel totalRegistros;
	private JPanel panelButton;
	private JPanel panelBusca;
	private JButton btnNewButton;
	

	
	public TabelaUsuario() {
		initComponents();
        iniciaPaginacao(); 
		
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 928, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBounds(32, 82, 842, 308);
		
		panelPagination = new JPanel();
		panelPagination.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelPagination.setBounds(268, 397, 350, 64);
		
		lblNewLabel = new JLabel("Página:");
		lblNewLabel.setBounds(32, 422, 61, 14);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(103, 419, 121, 20);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "10", "15", "20"}));
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				iniciaPaginacao();
			}
		});
		
		lblPagina = new JLabel("Página ");
		lblPagina.setBounds(652, 419, 54, 14);
		
		lblInicio = new JLabel("10");
		lblInicio.setBounds(709, 419, 27, 14);
		
		lblde = new JLabel("de");
		lblde.setBounds(727, 419, 18, 14);
		
		lblfinal = new JLabel("50");
		lblfinal.setBounds(753, 419, 27, 14);
		
		lblTotalRegistros = new JLabel("total de Registros");
		lblTotalRegistros.setBounds(786, 419, 100, 14);
		
		totalRegistros = new JLabel("");
		totalRegistros.setBounds(850, 401, 0, 0);
		btnPrimeiro = new JButton("");
		btnPrimeiro.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPrimeiro.setBounds(11, 12, 81, 39);
		btnPrimeiro.setToolTipText(VariaveisProjeto.PRIMEIRO);
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}
		});
		btnPrimeiro.setToolTipText("Primeiro");
		btnPrimeiro.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		btnAnterior = new JButton("");
		btnAnterior.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAnterior.setBounds(88, 12, 81, 39);
		btnAnterior.setToolTipText(VariaveisProjeto.ANTERIOR);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numeroPagina > 1) {
					numeroPagina = numeroPagina - 1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setToolTipText("Anterior");
		btnAnterior.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		btnProximo = new JButton("");
		btnProximo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnProximo.setBounds(169, 12, 81, 39);
		btnProximo.setToolTipText(VariaveisProjeto.PROXIMO);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( numeroPagina < totalPagina ) {
					numeroPagina = numeroPagina + 1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setToolTipText("Próximo");
		btnProximo.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		btnUltimo = new JButton("");
		btnUltimo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnUltimo.setBounds(250, 12, 81, 39);
		btnUltimo.setToolTipText(VariaveisProjeto.ULTIMO);
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setToolTipText("Último");
		btnUltimo.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		
		tabelaUsuario = new JTable();
		tabelaUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selecionaUsuario(e);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(comboBox);
		contentPane.add(panelPagination);
		panelPagination.setLayout(null);
		panelPagination.add(btnPrimeiro);
		panelPagination.add(btnAnterior);
		panelPagination.add(btnProximo);
		panelPagination.add(btnUltimo);
		contentPane.add(lblPagina);
		contentPane.add(lblInicio);
		contentPane.add(lblde);
		contentPane.add(lblfinal);
		contentPane.add(lblTotalRegistros);
		contentPane.add(totalRegistros);
		contentPane.add(scrollPane);
		
		panelButton = new JPanel();
		panelButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelButton.setBounds(256, 472, 373, 58);
		contentPane.add(panelButton);
		panelButton.setLayout(null);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIncluir.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnIncluir.setBounds(17, 9, 81, 39);
		btnIncluir.setToolTipText(VariaveisProjeto.INCLUIR_CADASTRO);
		panelButton.add(btnIncluir);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirUsuario();
				iniciaPaginacao();
			}
			
		});
		btnIncluir.setMnemonic(KeyEvent.VK_I);
		btnIncluir.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAlterar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAlterar.setBounds(105, 9, 81, 39);
		btnAlterar.setToolTipText(VariaveisProjeto.ALTERAR_CADASTRO);
		panelButton.add(btnAlterar);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
				iniciaPaginacao();
			}
		});
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExcluir.setBounds(193, 9, 81, 39);
		
		panelButton.add(btnExcluir);
		btnExcluir.setMnemonic(KeyEvent.VK_E);
		btnExcluir.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		btnExcluir.setToolTipText(VariaveisProjeto.EXCLUIR_CADASTRO);
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSair.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSair.setBounds(278, 9, 81, 39);
		panelButton.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setMnemonic(KeyEvent.VK_S);
		btnSair.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_go.png")));
		btnSair.setToolTipText(VariaveisProjeto.ENCERRAR_CADASTRO);
		panelBusca = new JPanel();
		panelBusca.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelBusca.setBounds(33, 15, 841, 49);
		contentPane.add(panelBusca);
		panelBusca.setLayout(null);
		
		
		lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_P) {
					textFieldNomeUsuario.requestFocus();
				}
				
			}
		});
		lblPesquisar.setDisplayedMnemonic('P');
		lblPesquisar.setDisplayedMnemonic(KeyEvent.VK_P);
		lblPesquisar.setBounds(10, 16, 74, 14);
		panelBusca.add(lblPesquisar);
		
		textFieldNomeUsuario = new JTextField();
		lblPesquisar.setLabelFor(textFieldNomeUsuario);
		textFieldNomeUsuario.setBounds(107, 13, 687, 20);
		textFieldNomeUsuario.setToolTipText(VariaveisProjeto.PESQUISAR_REGISTRO);
		panelBusca.add(textFieldNomeUsuario);
		textFieldNomeUsuario.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filtraNomeUsuario();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
	            filtraNomeUsuario();			
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				filtraNomeUsuario();
			}
		});
		
		textFieldNomeUsuario.setColumns(10);
		
		btnNewButton = new JButton("Relatório");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelUsuario relUsuario = new RelUsuario(new JFrame(), true);
				relUsuario.setLocationRelativeTo(null);
				relUsuario.setVisible(true);
			}
		});
		btnNewButton.setBounds(680, 488, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	
	

	protected void selecionaUsuario(MouseEvent e) {
		
		row = tabelaUsuario.getSelectedRow();
		
		 if ( tabelaUsuario.getRowSorter() != null ) {
			row =  tabelaUsuario.getRowSorter().convertRowIndexToModel(row);
		 }
	}

	protected void filtraNomeUsuario() {
		
		RowFilter<TabelaUsuarioModel, Object> rowFilter = null;
		String filter = textFieldNomeUsuario.getText();
		try {
			rowFilter = RowFilter.regexFilter(filter);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaUsuario.setRowFilter(rowFilter);
	}


	protected void alterarUsuario() {
		if ( tabelaUsuario.getSelectedRow() != -1 && tabelaUsuario.getSelectedRow() < tabelaUsuarioModel.getRowCount()) {
			UsuarioGUI usuario = new UsuarioGUI(new JFrame(), true, tabelaUsuario, tabelaUsuarioModel, row, VariaveisProjeto.ALTERACAO);
			usuario.setLocationRelativeTo(null);
			usuario.setVisible(true);
		}
		
	}


	private void incluirUsuario() {
		UsuarioGUI usuario = new UsuarioGUI(new JFrame(), true, tabelaUsuario, tabelaUsuarioModel, 0, VariaveisProjeto.INCLUSAO);
		usuario.setLocationRelativeTo(null);
        usuario.setResizable(false);
		usuario.setVisible(true);
	}
	
	private void iniciaPaginacao() {
	      
		totalData = buscaTotalRegistroUsuario();
		
		defaultPagina = Integer.valueOf(comboBox.getSelectedItem().toString());
		
		Double totalPaginasExistenes = Math.ceil(totalData.doubleValue() / defaultPagina.doubleValue());
	
		totalPagina = totalPaginasExistenes.intValue();
		
		if ( numeroPagina.equals(1)) {
			btnPrimeiro.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnPrimeiro.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if ( numeroPagina.equals(totalPagina)) {
			btnUltimo.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnUltimo.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if (numeroPagina > totalPagina ) {
			numeroPagina = 1;
		}
			
		tabelaUsuarioModel = new TabelaUsuarioModel();
		tabelaUsuarioModel.setListaUsuario(carregaListaUsuario(numeroPagina, defaultPagina));
		tabelaUsuario.setModel(tabelaUsuarioModel);
		tabelaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		sortTabelaUsuario = new TableRowSorter<TabelaUsuarioModel>(tabelaUsuarioModel);
		tabelaUsuario.setRowSorter(sortTabelaUsuario);
		
		sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(CODIGO, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(NOME, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(EMAIL, SortOrder.ASCENDING));
		
		tabelaUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaUsuario.getColumnModel().getColumn(CODIGO).setPreferredWidth(11);
		tabelaUsuario.getColumnModel().getColumn(NOME).setPreferredWidth(100);
		tabelaUsuario.getColumnModel().getColumn(EMAIL).setPreferredWidth(100);
		
		scrollPane.setViewportView(tabelaUsuario);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblfinal.setText(String.valueOf(totalPagina));
		totalRegistros.setText(String.valueOf(totalData));
		
	}

	
	
	
	private List<Usuario> carregaListaUsuario(Integer numeroPagina, Integer defaultPagina) {

		UsuarioService usuarioService = new UsuarioService();

		List<Usuario> listaUsuario  = new ArrayList<Usuario>();
		
		listaUsuario = usuarioService.listUsuarioPaginacao( ( defaultPagina * (numeroPagina - 1 )), defaultPagina);
		
		return listaUsuario;
	}

	private Integer buscaTotalRegistroUsuario() {
		
		Integer totalRegistro = 0;
		
		UsuarioService usuarioService = new UsuarioService();
		
		totalRegistro = usuarioService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTable() {
		return tabelaUsuario;
	}
}
