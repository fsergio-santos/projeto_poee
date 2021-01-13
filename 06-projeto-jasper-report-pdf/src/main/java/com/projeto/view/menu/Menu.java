package com.projeto.view.menu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import com.projeto.main.Login;
import com.projeto.view.usuario.TabelaUsuario;

public class Menu extends JFrame {


	private static final long serialVersionUID = 473637651720341513L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu arquivo;
	private JMenuItem usuario;
	private JMenuItem logout;
	
	
	private Login login;
	private JMenu sair;
	private JMenuItem sair_sistema;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Menu frame = new Menu();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public Menu(Login login) {
		this.login = login;
		initComponents();
	}
	
	
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/com/projeto/estrutura/imagens/application_home.png")));
		
		login.setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1115, 559);
		menuBar = new JMenuBar();
		menuBar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setJMenuBar(menuBar);
		
		arquivo = new JMenu("Arquivo");
		arquivo.setMnemonic('A');
		arquivo.setMnemonic(KeyEvent.VK_A);
		menuBar.add(arquivo);
		
		usuario = new JMenuItem("Usuário");
		usuario.setIcon(new ImageIcon(Menu.class.getResource("/com/projeto/estrutura/imagens/user.png")));
		usuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		usuario.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaUsuario tabelaUsuario = new TabelaUsuario();
				centralizaForm(tabelaUsuario);
				contentPane.add(tabelaUsuario);
				tabelaUsuario.setVisible(true);
				
			}
		});
		arquivo.add(usuario);
		
		logout = new JMenuItem("Logout");
		logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				login.setVisible(true);
				login.setLocationRelativeTo(null);
			}
		});
		arquivo.add(logout);
		
		sair = new JMenu("Sair");
		menuBar.add(sair);
		
		sair_sistema = new JMenuItem("Sair");
		sair_sistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		sair.add(sair_sistema);
		
		
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	
	private void centralizaForm(JInternalFrame frame) {
		Dimension desktopSize = this.getSize();
		Dimension internalFrameSize = frame.getSize();
		frame.setLocation((desktopSize.width - internalFrameSize.width) / 2, (desktopSize.height - internalFrameSize.height) /2 );
	}
	
	
	
}
