package com.projeto.view.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.model.models.PrintJasperReport;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.JasperReportsService;
import com.projeto.model.service.UsuarioService;

public class RelUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnNewButton;

	
	public RelUsuario(JFrame frame, boolean modal) {

		super(frame, modal);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnNewButton = new JButton("Relatório SQL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imprimeRelatorioPorSQL();
			}
		});
		btnNewButton.setBounds(10, 194, 97, 23);
		btnNewButton.setActionCommand("");
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						imprimeRelatorioPorLista();
						
					}

					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void imprimeRelatorioPorSQL() {
		PrintJasperReport printJasperReport = new PrintJasperReport();
		JasperReportsService jasperReportsService = new JasperReportsService();
		printJasperReport.setFile("rel_usuario_sql");
		setVisible(false);
		jasperReportsService.generateSQLReports(printJasperReport);
	}

	private void imprimeRelatorioPorLista() {
		UsuarioService usuarioService = new UsuarioService();
		PrintJasperReport printJasperReport = new PrintJasperReport();
		JasperReportsService jasperReportsService = new JasperReportsService();

	    List<Usuario> listaUsuario = usuarioService.findAll();
		
		printJasperReport.setFile("rel_usuario");
		printJasperReport.setCollection(listaUsuario);
		setVisible(false);
		jasperReportsService.generateListReports(printJasperReport);
	}

}
