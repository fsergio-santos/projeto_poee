package com.projeto.view.usuario;


import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.estrutura.util.imagem.ImageFilter;
import com.projeto.estrutura.util.imagem.ImagePreview;
import com.projeto.model.models.Departamento;
import com.projeto.model.models.Foto;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.LocalFotoStorageService;
import com.projeto.model.service.UsuarioService;
import com.projeto.view.departamento.BuscaDepartamento;


public class UsuarioGUI extends JDialog {
	
	private static final long serialVersionUID = -6617720784946875271L;
	
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JPasswordField passwordFieldSenha;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnAdmin;
	private JButton btnSair;
	
	private JLabel checkNome;
	private JLabel checkEmail; 
	private JLabel checkSenha;

    private boolean status = true; 
    
    
    private JTable tabelaUsuario;
    private TabelaUsuarioModel tabelaUsuarioModel;
    private int linha=0;
    private int acao = 0;
    private JLabel lblDepartamento;
    private JTextField textFieldNomeDepartamento;
    private JButton btnAddDepartamento;
    
    
    private Departamento departamento;
    private JPanel panelCadastro;
    private JLabel lblNewLabel;
    private JTextField textFieldCodigo;
    private JPanel panelButton;
    
    private String nomeFoto;
    private JLabel lblIconFoto;
    private JButton btnFoto;
    private JButton btnExcluirFoto;
    
   
	
	
    public UsuarioGUI(JFrame frame, boolean modal, JTable tabelaUsuario, TabelaUsuarioModel tabelaUsuarioModel, int linha, int acao) {
		
		super(frame, modal);

		initComponents();
		
		this.tabelaUsuario = tabelaUsuario;
		this.tabelaUsuarioModel = tabelaUsuarioModel;
		this.linha = linha;
        this.acao = acao;
		
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoUsuario();
		
	}
	
	
	
	private void configuraAcaoUsuario() {
		
		if (this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setEnabled(false);
			btnExcluir.setEnabled(false);
		}
		if (this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setEnabled(false);
			btnIncluir.setEnabled(false);
			buscarUsuario();
		}
		if (this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnIncluir.setEnabled(false);
			btnAlterar.setEnabled(false);
			buscarUsuario();
		}
	}



	private void initComponents() {
		setTitle("Cadastro de Usuário");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 947, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		
		panelCadastro = new JPanel();
		panelCadastro.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelCadastro.setBounds(15, 28, 879, 284);
		
		lblNewLabel = new JLabel("Código:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(124, 30, 55, 14);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setLabelFor(textFieldCodigo);
		textFieldCodigo.setBounds(183, 27, 84, 22);
		textFieldCodigo.setText("");
		textFieldCodigo.setEditable(false);
		textFieldCodigo.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNome.setBounds(134, 67, 45, 14);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setLabelFor(textFieldNome);
		textFieldNome.setBounds(184, 62, 359, 25);
		textFieldNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if ( verificaDigitacaoDoNome() ) {
				    textFieldNome.requestFocus();	
				} else {
				   digitacaoNomeValido();
			   	}
			}
		});
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if ( verificaDigitacaoDoNome() ) {
					   textFieldNome.requestFocus();	
					} else {
					   digitacaoNomeValido();
				    }	
				}
			}
		});
		textFieldNome.setColumns(10);
		
		checkNome = new JLabel("");
		checkNome.setBounds(553, 64, 53, 25);
		checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(136, 110, 45, 14);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setLabelFor(textFieldEmail);
		textFieldEmail.setBounds(184, 105, 359, 25);
		textFieldEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if ( verificaDigitacaoDoEmail() ) {
					   textFieldEmail.requestFocus();	
					} else {
					   digitacaoEmailValido();
					}	
			}
		});
		textFieldEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if ( verificaDigitacaoDoEmail() ) {
					   textFieldEmail.requestFocus();	
					} else {
					   digitacaoEmailValido();
					}	
					passwordFieldSenha.requestFocus();
				}
			}
		});
		textFieldEmail.setColumns(10);
		
		checkEmail = new JLabel("");
		checkEmail.setBounds(555, 107, 51, 25);
		checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(126, 154, 53, 14);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setLabelFor(passwordFieldSenha);
		passwordFieldSenha.setBounds(184, 148, 359, 25);
		passwordFieldSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if ( verificaDigitacaoSenha() ) {
				   passwordFieldSenha.requestFocus();	
				} else {
				   digitacaoSenhaValida();
				}	
			}
		});
		passwordFieldSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					if ( verificaDigitacaoSenha() ) {
					   passwordFieldSenha.requestFocus();	
			    	} else {
					   digitacaoSenhaValida();
					}	
					rdbtnAtivo.requestFocus();
				}
			}
		});
		
		checkSenha = new JLabel("");
		checkSenha.setBounds(556, 151, 50, 16);
		checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartamento.setBounds(75, 195, 104, 14);
		
		textFieldNomeDepartamento = new JTextField();
		textFieldNomeDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepartamento.setLabelFor(textFieldNomeDepartamento);
		textFieldNomeDepartamento.setBounds(184, 191, 226, 25);
		textFieldNomeDepartamento.setEditable(false);
		textFieldNomeDepartamento.setColumns(10);
		
		btnAddDepartamento = new JButton("Departamento");
		btnAddDepartamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddDepartamento.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAddDepartamento.setBounds(420, 190, 121, 25);
		btnAddDepartamento.setMnemonic(KeyEvent.VK_D);
		btnAddDepartamento.setToolTipText(VariaveisProjeto.PESQUISAR_REGISTRO);
		btnAddDepartamento.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/search.png")));
	
		contentPane.setLayout(null);
		contentPane.add(panelCadastro);
		
		panelCadastro.setLayout(null);
		panelCadastro.add(lblSenha);
		panelCadastro.add(passwordFieldSenha);
		panelCadastro.add(lblNome);
		panelCadastro.add(lblEmail);
		panelCadastro.add(lblNewLabel);
		panelCadastro.add(textFieldCodigo);
		panelCadastro.add(textFieldEmail);
		panelCadastro.add(textFieldNome);
		panelCadastro.add(checkEmail);
		panelCadastro.add(checkNome);
		panelCadastro.add(checkSenha);
		panelCadastro.add(lblDepartamento);
		panelCadastro.add(textFieldNomeDepartamento);
		panelCadastro.add(btnAddDepartamento);
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.setBounds(184, 245, 83, 23);
		panelCadastro.add(rdbtnAtivo);
		
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(284, 245, 84, 23);
		panelCadastro.add(rdbtnAdmin);
		
		lblIconFoto = new JLabel("");
		lblIconFoto.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblIconFoto.setBounds(642, 62, 168, 111);
		panelCadastro.add(lblIconFoto);
		
		btnFoto = new JButton("");
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarFoto();
			}
		});
		btnFoto.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnFoto.setToolTipText("Carregar Foto do Usuário");
		btnFoto.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/camera-icon.png")));
		btnFoto.setBounds(638, 192, 81, 39);
		panelCadastro.add(btnFoto);
		
		btnExcluirFoto = new JButton("");
		btnExcluirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFoto();
			}
		});
		btnExcluirFoto.setToolTipText("Excluir Foto do Usuário");
		btnExcluirFoto.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		btnExcluirFoto.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExcluirFoto.setBounds(729, 192, 81, 39);
		panelCadastro.add(btnExcluirFoto);
		
		panelButton = new JPanel();
		panelButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelButton.setBounds(278, 346, 362, 58);
		contentPane.add(panelButton);
		panelButton.setLayout(null);
		
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIncluir.setToolTipText(VariaveisProjeto.INCLUIR_CADASTRO);
		btnIncluir.setMnemonic('i');
		btnIncluir.setMnemonic(KeyEvent.VK_I);
		btnIncluir.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnIncluir.setBounds(10, 11, 81, 39);
		panelButton.add(btnIncluir);
		btnIncluir.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAlterar.setToolTipText(VariaveisProjeto.ALTERAR_CADASTRO);
		btnAlterar.setMnemonic('a');
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAlterar.setBounds(97, 11, 81, 39);
		panelButton.add(btnAlterar);
		btnAlterar.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setToolTipText(VariaveisProjeto.EXCLUIR_CADASTRO);
		btnExcluir.setMnemonic('e');
		btnExcluir.setMnemonic(KeyEvent.VK_E);
		btnExcluir.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExcluir.setBounds(184, 11, 81, 39);
		panelButton.add(btnExcluir);
		btnExcluir.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSair.setToolTipText(VariaveisProjeto.ENCERRAR_CADASTRO);
		btnSair.setMnemonic('s');
		btnSair.setMnemonic(KeyEvent.VK_S);
		btnSair.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSair.setBounds(271, 11, 81, 39);
		panelButton.add(btnSair);
		btnSair.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharUsuario();
			}
		});
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
	
		});
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirUsuario();
			}
		});
		rdbtnAtivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					rdbtnAdmin.requestFocus();
				}
			}
		});
		btnAddDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscaDepartamento();
			}
		});
		btnAddDepartamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_D) {
					buscaDepartamento();
				}
			}
		});
	}
	

	
	

	protected void excluirFoto() {
		Usuario usuario = tabelaUsuarioModel.getUsuario(this.linha);
		nomeFoto = usuario.getFoto();
		LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
		localFotoStorageService.remover(nomeFoto);
		lblIconFoto.setIcon(null);
		lblIconFoto.revalidate();
	}



	protected void carregarFoto() {
		
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setAcceptAllFileFilterUsed(false);
		fc.setAccessory(new ImagePreview(fc));
		int returnVal = fc.showDialog(lblIconFoto, "Anexar");
		
		if (lblIconFoto.getIcon() != null) {
			excluirFoto();
		}
		
		if ( returnVal == JFileChooser.APPROVE_OPTION ) {
			try {
				File file = fc.getSelectedFile();
				FileInputStream fin = new FileInputStream(file);
				BufferedImage img = ImageIO.read(fin);
				ImageIcon icon = new ImageIcon(img);
				lblIconFoto.setIcon(icon);
				lblIconFoto.setHorizontalAlignment(SwingConstants.CENTER);
				LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
				Foto foto = new Foto();
				foto.setNomeArquivo(file.getName());
				foto.setInputStream(fin);
				foto.setFile(file);
				nomeFoto = localFotoStorageService.armazenar(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}






	protected void buscaDepartamento() {
		
		departamento = new Departamento();
		
		BuscaDepartamento buscaDepartamento = new BuscaDepartamento(new JFrame(), true);
		buscaDepartamento.setLocationRelativeTo(null);
		buscaDepartamento.setVisible(true);
		if (buscaDepartamento.isSelectDepartamento()) {
			departamento = buscaDepartamento.getDepartamento();
			textFieldNomeDepartamento.setText(departamento.getNome());
		}
		
	}



	private boolean verificaDigitacaoDoNome() {
		if ( VariaveisProjeto.digitacaoCampo(textFieldNome.getText())) {
		     status = false;
			 mudaStatusCheckNome();
			 return true; 
		}
		return false;
	}
	
	private void digitacaoNomeValido() {
		status = true;
		mudaStatusCheckNome();
		checkNome.setVisible(true);	
		textFieldEmail.requestFocus();
	}
	
	private boolean verificaDigitacaoDoEmail() {
		if ( VariaveisProjeto.digitacaoCampo(textFieldEmail.getText())) {
		     status = false;
			 mudaStatusCheckEmail();
			 return true; 
		}
		return false;
	}
	
	private void digitacaoSenhaValida() {
		status = true;
	    mudaStatusCheckSenha();
		checkSenha.setVisible(true);	
		rdbtnAtivo.requestFocus();
	}
	

	private boolean verificaDigitacaoSenha() {
		if ( VariaveisProjeto.digitacaoCampo(new String(passwordFieldSenha.getPassword()))) {
		     status = false;
			 mudaStatusCheckSenha();
			 return true; 
		}
		return false;
	}
	
	private void digitacaoEmailValido() {
		status = true;
	    mudaStatusCheckEmail();
		checkEmail.setVisible(true);	
		passwordFieldSenha.requestFocus();
	}
	
	private void mudaStatusCheckNome() {
		checkNome.setVisible(true);
		if (status == false ) {
			checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void mudaStatusCheckEmail() {
		checkEmail.setVisible(true);
		if (status == false ) {
			checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void mudaStatusCheckSenha() {
		checkSenha.setVisible(true);
		if (status == false ) {
			checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void desabilitaCheckCampos() {
		checkNome.setVisible(false);
		checkEmail.setVisible(false);
		checkSenha.setVisible(false);
	}

	

	protected void incluirUsuario() {
		
		Integer toReturn = 0;
		
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.save(usuario);
		
		erroDigitacao(toReturn);
		
		if ( toReturn == VariaveisProjeto.ERRO_INCLUSAO ) {
			VariaveisProjeto.showMensagem(VariaveisProjeto.ERRO_INCLUSAO_REGISTRO,
					   	 "Erro",JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			VariaveisProjeto.showMensagem(VariaveisProjeto.SUCESSO_INCLUSAO_REGISTRO,
					     "OK",JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaUsuarioModel.saveUsuario(usuario);
			tabelaUsuario.setModel(tabelaUsuarioModel);
			tabelaUsuarioModel.fireTableDataChanged();
			usuario = new Usuario();
		}
	}
	
	protected void alterarUsuario() {
		
		Integer toReturn = 0;
		
	    Usuario usuario = pegarDadosUsuario();
	    
	    UsuarioService usuarioService = new UsuarioService();
	    
		toReturn = usuarioService.update(usuario);
		
		erroDigitacao(toReturn);
		
		if ( toReturn == VariaveisProjeto.ERRO_ALTERACAO ) {
			VariaveisProjeto.showMensagem(VariaveisProjeto.ERRO_ALTERACAO_REGISTRO,
					   	 "Erro",JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			VariaveisProjeto.showMensagem(VariaveisProjeto.SUCESSO_ALTERACAO_REGISTRO,
					     "OK",JOptionPane.OK_OPTION);
			
			tabelaUsuarioModel.updateUsuario(usuario, this.linha);
			tabelaUsuario.setModel(tabelaUsuarioModel);
			tabelaUsuarioModel.fireTableDataChanged();
		
			
			limpaTextoCampo();
			usuario = new Usuario();
		}
	}

	private void erroDigitacao(Integer toReturn) {
		
		if ( toReturn == VariaveisProjeto.USUARIO_USER_NAME ) {
			 status = false;
			 mudaStatusCheckNome();
			 VariaveisProjeto.showMensagem("Erro na digitação do Nome, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.USUARIO_EMAIL ) {
			 status = false;
			 mudaStatusCheckNome();
			 VariaveisProjeto.showMensagem("Erro na digitação do E-mail, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.USUARIO_PASSWORD ) {
			 status = false;
			 mudaStatusCheckNome();
			 VariaveisProjeto.showMensagem("Erro na digitação da Senha, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	protected void excluirUsuario() {
		
		Integer toReturn = 0;
		
		Usuario usuario = pegarDadosUsuario();
		
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.delete(usuario);
		
		if ( toReturn == VariaveisProjeto.ERRO_EXCLUSAO ) {
			VariaveisProjeto.showMensagem(VariaveisProjeto.ERRO_EXCLUSAO_REGISTRO,
					   	 "Erro",JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			VariaveisProjeto.showMensagem(VariaveisProjeto.SUCESSO_EXCLUSAO_REGISTRO,
					     "OK",JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaUsuarioModel.fireTableDataChanged();
			usuario = new Usuario();
		}
		
	}

	private void fecharUsuario() {
		dispose();
	}
	
	private void buscarUsuario() {
	
		Usuario usuario = new Usuario();
		
		usuario = tabelaUsuarioModel.getUsuario(this.linha);
				
		textFieldCodigo.setText(String.valueOf(usuario.getId()));
		textFieldNome.setText(usuario.getUsername());
		textFieldEmail.setText(usuario.getEmail());
		passwordFieldSenha.setText(usuario.getPassword());
		textFieldNomeDepartamento.setText(usuario.getDepartamento().getNome());
	    nomeFoto = usuario.getFoto();    
	    departamento = new Departamento();
	    departamento.setId(usuario.getDepartamento().getId());
	    departamento.setNome(usuario.getDepartamento().getNome());
	    
	    if ( !Objects.isNull(nomeFoto) ) {
	 	    LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
		    String fileInput = localFotoStorageService.recuperar(nomeFoto);
		    File file = new File(fileInput);
		    FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				BufferedImage img = ImageIO.read(fis);
				ImageIcon imagem = new ImageIcon(img);
				lblIconFoto.setIcon(imagem);
				lblIconFoto.setHorizontalAlignment(SwingConstants.CENTER);
			}  catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
		if (usuario.isAdmin())
			rdbtnAdmin.setSelected(true);
		
		if ( usuario.isAtivo())
			rdbtnAtivo.setSelected(true);
	}

	
	public Usuario pegarDadosUsuario() {
		
		Usuario usuario = new Usuario();
		
		
		if (VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())){
		 textFieldCodigo.requestFocus(); 
		}
		
	    if (VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false ) {
	    	usuario.setId(Integer.valueOf(textFieldCodigo.getText()));
	    }
	    
		usuario.setUsername(textFieldNome.getText());
		usuario.setEmail(textFieldEmail.getText());
		usuario.setPassword(new String(passwordFieldSenha.getPassword()));
		usuario.setDepartamento(departamento);
		usuario.setFoto(nomeFoto);
		
		
		if (rdbtnAtivo.isSelected()) {
			usuario.setAtivo(true);
		} else  {
			usuario.setAtivo(false);
		}
		
		if (rdbtnAdmin.isSelected()) {
			usuario.setAdmin(true);
		} else {
			usuario.setAdmin(false);
		}
		
		
		return usuario;
	}
	
	private void limpaTextoCampo() {
		textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldEmail.setText(VariaveisProjeto.LIMPA_CAMPO);
		passwordFieldSenha.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNomeDepartamento.setText(VariaveisProjeto.LIMPA_CAMPO);
		rdbtnAdmin.setSelected(false);
		rdbtnAtivo.setSelected(false);
	}
}
