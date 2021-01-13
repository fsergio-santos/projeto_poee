package com.projeto.estrutura.util;

import java.util.Objects;

import javax.swing.JOptionPane;

public class VariaveisProjeto {

	
	public static final String PERSISTENCE_UNIT_NAME = "projeto";
	
	//Configurações para a geração de relatório
	public static final String DIRETORIO_RELATORIO = "reports/";
	public static final String SUFIXO_RELATORIO_COMPILADO = ".jasper";
	public static final String SUFIXO_RELATORIO_FONTE = ".jrxml";
	
	
	
	public static final Integer INCLUSAO = 1;
	public static final Integer ALTERACAO = 2;
	public static final Integer EXCLUSAO = 3;
	public static final Integer CONSULTA = 4;
	
	public static final Integer ERRO_INCLUSAO = 10;
	public static final Integer ERRO_ALTERACAO = 20;
	public static final Integer ERRO_EXCLUSAO = 30;
	
	public static final String ERRO_INCLUSAO_REGISTRO = "Erro na Inclusão do Registro, verifique com seu administrador!";
	public static final String ERRO_ALTERACAO_REGISTRO ="Erro na Alteração do Registro, verifique com seu administrador!";
	public static final String ERRO_EXCLUSAO_REGISTRO ="Erro na  Esclusão do Registro, verifique com seu administrador!";
	
	public static final String SUCESSO_INCLUSAO_REGISTRO = "Inclusão do Registro realizada com sucesso!";
	public static final String SUCESSO_ALTERACAO_REGISTRO = "Alteração do Registro realizada com sucesso!";
	public static final String SUCESSO_EXCLUSAO_REGISTRO ="Exclusão do Registro realizada com sucesso!";
	
		
	public static final Integer INCLUSAO_REALIZADA = 1;
	public static final Integer ALTERACAO_REALIZADA = 2;
	public static final Integer EXCLUSAO_REALIZADA = 3;
	
	public static final Integer DIGITACAO_OK = 100;
	
	
	// classe de usuário 
	
	public static final Integer USUARIO_USER_NAME = 201;
	public static final Integer USUARIO_EMAIL = 202;
	public static final Integer USUARIO_PASSWORD = 203;
			
	
	// classe de departamento
	public static final Integer DEPARTAMENTO_NOME = 300;
	
	
	public static final String LIMPA_CAMPO = "";
	
	
	
	// funções de navegação e mensagens nos botões 
	public static final String INCLUIR_CADASTRO = "Incluir Novo Registro";
	public static final String ALTERAR_CADASTRO = "Alterar Registro";
	public static final String EXCLUIR_CADASTRO = "Excluir Registro";
	public static final String ENCERRAR_CADASTRO = "Encerrar o Cadastro";
	public static final String PESQUISAR_REGISTRO = "Pequisa Registro no banco de dados";
	public static final String EMITIR_RELATORIO  = "Emissão de Relatório";
	public static final String CANCELAR_RELATORIO = "Cancelar Emissão do Relatório";
	public static final String SELECAO_CONFIRMADA = "Confirma o Registro Selecionado";
	public static final String CANCELA_SELECAO = "Cancelar a Seleção do Registro";
	public static final String ACESSO_SISTEMA = "Acesso ao Sistema";
	public static final String CANCELAR_ACESSO = "Cancelar acesso ao Sistema";
	public static final String CARREGAR_FOTO = "Adicionar Foto";
	public static final String EXCLUIR_FOTO = "Excluir Foto";
	public static final String ENCERRAR_SISTEMA ="Sair do Sistema";
	
	
	public static final String PRIMEIRO = "Primeiro";
	public static final String ANTERIOR = "Anterior";
	public static final String PROXIMO  = "Próximo";
	public static final String ULTIMO   = "Último";
	
	
	
	
	public static boolean digitacaoCampo(Integer texto) {
		
		if ( Objects.isNull(texto)) {
			return true;
		}
		
		if ("".equals(String.valueOf(texto))){
			return true;
		}
		
		return false;
	}
	
	public static boolean digitacaoCampo(String texto) {
			
		if ( Objects.isNull(texto)) {
			return true;
		}
		
		if ("".equals(texto.trim())){
			return true;
		}
		return false;
	}
	
	public static Integer convertToInteger(String texto) {
		return Integer.parseInt(texto);
	}
	
	
	public static void showMensagem(String mensagem, String status, int option ) {
		JOptionPane.showMessageDialog(null, mensagem, status, option );
	}

	
}
