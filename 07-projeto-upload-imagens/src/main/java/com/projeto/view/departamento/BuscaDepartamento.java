package com.projeto.view.departamento;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import com.projeto.model.models.Departamento;
import com.projeto.model.service.DepartamentoService;
import java.awt.Font;

public class BuscaDepartamento extends JDialog {

	private static final long serialVersionUID = -6848812760393463479L;

	private static final int CODIGO = 0;
	private static final int NOME = 1;

	private final JPanel contentPanel = new JPanel();
	private JLabel lblPesquisaDepartamento;
	private JTextField textFieldBuscaDepartamento;
	private JScrollPane scrollPane;
	private JTable tableDepartamento;

	private TabelaDepartamentoModel tabelaDepartamentoModel;
	private TableRowSorter<TabelaDepartamentoModel> sortTabelaDepartamento;
	private List<Departamento> listaDepartamento;
	private List<RowSorter.SortKey> sortKeys;
	
//	private Integer codigoDepartamento;
//	private String nomeDepartamento;
	
	private Departamento departamento;
	
	private boolean selectDepartamento;
	
	private JButton btnInserirDepartamento;
	
	private int row=0;
	private JPanel buttonPane;
	private JPanel panel;
	
	
	public BuscaDepartamento(JFrame frame, boolean modal) {
		super(frame, modal);
		initComponents();
		iniciarDados();
		
	}
	
	private void iniciarDados(){
		listaDepartamento = new ArrayList<Departamento>();
	}
	

	private void initComponents() {
		setTitle("Busca Departamento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscaDepartamento.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		setResizable(false);
		setSelectDepartamento(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 818, 511);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBounds(18, 71, 784, 283);

		btnInserirDepartamento = new JButton("Cadastrar Departamento");
		btnInserirDepartamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInserirDepartamento.setBounds(43, 375, 174, 39);
		btnInserirDepartamento.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnInserirDepartamento.setToolTipText(VariaveisProjeto.INCLUIR_CADASTRO);
		btnInserirDepartamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_C ) {
					inserirDepartamento();
				}
				
			}
		});
		btnInserirDepartamento.setMnemonic(KeyEvent.VK_C);
		btnInserirDepartamento.setIcon(new ImageIcon(
				BuscaDepartamento.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		{
			buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonPane.setBounds(0, 435, 812, 47);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 11));
				okButton.setBounds(635, 5, 81, 39);
				okButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				okButton.setToolTipText(VariaveisProjeto.SELECAO_CONFIRMADA);
				okButton.setIcon(new ImageIcon(BuscaDepartamento.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
				okButton.setMnemonic(KeyEvent.VK_O);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selecionaDepartamento();
					}
				});
				buttonPane.setLayout(null);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
				cancelButton.setBounds(721, 5, 81, 39);
				cancelButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				cancelButton.setToolTipText(VariaveisProjeto.CANCELA_SELECAO);
				cancelButton.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						setSelectDepartamento(false);
						dispose();
					}
				});
				cancelButton.setMnemonic(KeyEvent.VK_A);
				cancelButton.setIcon(new ImageIcon(BuscaDepartamento.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setSelectDepartamento(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		tabelaDepartamentoModel = new TabelaDepartamentoModel();
	    listaDepartamento = carregarListaDepartemento();
		tabelaDepartamentoModel.setListaDepartamento(listaDepartamento);
		tableDepartamento = new JTable();
		tableDepartamento.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tableDepartamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selecionaDepartamento(e);
			}
		});
		tableDepartamento.setModel(tabelaDepartamentoModel);
		tableDepartamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDepartamento.setFillsViewportHeight(true);
		sortTabelaDepartamento = new TableRowSorter<TabelaDepartamentoModel>(tabelaDepartamentoModel);
		tableDepartamento.setRowSorter(sortTabelaDepartamento);
		
		sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(CODIGO, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(NOME, SortOrder.ASCENDING));	
		
		tableDepartamento.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tableDepartamento.getColumnModel().getColumn(CODIGO).setPreferredWidth(11);
		tableDepartamento.getColumnModel().getColumn(NOME).setPreferredWidth(100);
	
   		scrollPane.setViewportView(tableDepartamento);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.setLayout(null);
		contentPanel.add(buttonPane);
		contentPanel.add(scrollPane);
		contentPanel.add(btnInserirDepartamento);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(19, 16, 781, 44);
		contentPanel.add(panel);
		panel.setLayout(null);
		
				lblPesquisaDepartamento = new JLabel("Pesquisar Departamento:");
				lblPesquisaDepartamento.setBounds(14, 15, 129, 14);
				panel.add(lblPesquisaDepartamento);
				lblPesquisaDepartamento.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_P) {
							textFieldBuscaDepartamento.requestFocus();
						}
					}
				});
				lblPesquisaDepartamento.setDisplayedMnemonic(KeyEvent.VK_P);
				
						textFieldBuscaDepartamento = new JTextField();
						textFieldBuscaDepartamento.setBounds(146, 12, 608, 20);
						panel.add(textFieldBuscaDepartamento);
						textFieldBuscaDepartamento.setToolTipText(VariaveisProjeto.PESQUISAR_REGISTRO);
						textFieldBuscaDepartamento.getDocument().addDocumentListener(new DocumentListener() {
							
							@Override
							public void removeUpdate(DocumentEvent e) {
								filtraNomeDepartamento();
								
							}
							
							@Override
							public void insertUpdate(DocumentEvent e) {
								filtraNomeDepartamento();
							}
							
							@Override
							public void changedUpdate(DocumentEvent e) {
								filtraNomeDepartamento();
							}
						});
						
						textFieldBuscaDepartamento.setColumns(15);
						lblPesquisaDepartamento.setLabelFor(textFieldBuscaDepartamento);

	}



	protected void inserirDepartamento() {
		// TODO Auto-generated method stub
		
	}

	protected void selecionaDepartamento(MouseEvent e) {
		row = tableDepartamento.getSelectedRow();
		
		 if ( tableDepartamento.getRowSorter() != null ) {
			row =  tableDepartamento.getRowSorter().convertRowIndexToModel(row);
		 }
		
	}

	protected void selecionaDepartamento() {
		if ( tableDepartamento.getSelectedRow() != -1 && 
			 tableDepartamento.getSelectedRow() < tabelaDepartamentoModel.getRowCount() ) {
			 //setCodigoDepartamento(Integer.valueOf(tableDepartamento.getValueAt(tableDepartamento.getSelectedRow(), CODIGO).toString()));
			 //setNomeDepartamento(tableDepartamento.getValueAt(tableDepartamento.getSelectedRow(), NOME).toString());
			
//			 row = tableDepartamento.getSelectedRow();
//		
//			 if ( tableDepartamento.getRowSorter() != null ) {
//				row =  tableDepartamento.getRowSorter().convertRowIndexToModel(row);
//			 }
		
			 departamento = tabelaDepartamentoModel.getDepartamento(row);
			 
			 setSelectDepartamento(true);
			 dispose();
		} else {
			setSelectDepartamento(false);	
		}
		
	}

	private List<Departamento> carregarListaDepartemento() {
		DepartamentoService departamentoService = new DepartamentoService();
		listaDepartamento = departamentoService.findAll();
		return listaDepartamento;
	}
	
	
	private void filtraNomeDepartamento() {
		RowFilter<TabelaDepartamentoModel, Object> rowFilter = null;
		String filter = textFieldBuscaDepartamento.getText();
		try {
			rowFilter = RowFilter.regexFilter(filter);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaDepartamento.setRowFilter(rowFilter);
		
	}


	
	public boolean isSelectDepartamento() {
		return selectDepartamento;
	}

	public void setSelectDepartamento(boolean selectDepartamento) {
		this.selectDepartamento = selectDepartamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
