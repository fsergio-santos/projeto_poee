package com.projeto.main;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.view.menu.Menu;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class Login extends JFrame {


	private static final long serialVersionUID = 3053976870406371634L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField textFieldEmail;
	private JLabel lblNewLabel_1;
	private JTextField textFieldSenha;
	private JButton btnAcesso;
	private JButton btnCancelar;
	private JButton btnSair;
	
	private static Login frame;
	private JPanel panelButton;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		lookAndFeel();
		initComponents();
	}
	
	
	public void lookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/projeto/estrutura/imagens/security.jpg")));
		setTitle("Controle de Acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 738, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(102, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		
		lblNewLabel = new JLabel("E-mail:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldSenha = new JTextField();
		textFieldSenha.setColumns(10);
		
		panelButton = new JPanel();
		panelButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/com/projeto/estrutura/imagens/Approved-icon.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
							.addGap(100))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldSenha, 422, 422, 422)))
							.addGap(41))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(76)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(57)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panelButton.setLayout(null);
		
		btnAcesso = new JButton("Acesso");
		btnAcesso.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAcesso.setIcon(new ImageIcon(Login.class.getResource("/com/projeto/estrutura/imagens/application_go.png")));
		btnAcesso.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAcesso.setBounds(42, 13, 81, 39);
		btnAcesso.setToolTipText(VariaveisProjeto.ACESSO_SISTEMA);
		panelButton.add(btnAcesso);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		btnCancelar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCancelar.setBounds(130, 13, 81, 39);
		btnCancelar.setToolTipText(VariaveisProjeto.CANCELAR_ACESSO);;
		panelButton.add(btnCancelar);
		
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnSair.setIcon(new ImageIcon(Login.class.getResource("/com/projeto/estrutura/imagens/saida.png")));
		btnSair.setMnemonic(KeyEvent.VK_S);
		btnSair.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSair.setBounds(217, 13, 81, 39);
		btnSair.setToolTipText(VariaveisProjeto.ENCERRAR_SISTEMA);
		panelButton.add(btnSair);
		btnSair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_S) {
					System.exit(0);
				}
			}
		});
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				textFieldEmail.requestFocus();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldEmail.requestFocus();
			}
		});
		btnAcesso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				loginSistema();
			}
		});
		btnAcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginSistema();
			}
		});
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	protected void loginSistema() {
		acessoMenu();
		
	}

	private void acessoMenu() {
		Menu menu = new Menu(frame);
		menu.setVisible(true);
		menu.setResizable(false);
		menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
	}
}
