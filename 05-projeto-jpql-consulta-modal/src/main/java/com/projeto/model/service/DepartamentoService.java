package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.DepartamentoDao;
import com.projeto.model.models.Departamento;

public class DepartamentoService extends ConexaoBancoService {

	private DepartamentoDao departamentoDao;

	public DepartamentoService() {
		this.departamentoDao = new DepartamentoDao(this.getEntityManager());
	}

	public Integer save(Departamento departamento) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();
		
		toReturn = validarDigitacao(departamento);

		if ( toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getDepartamentoDao().save(departamento);
				trx.commit();
                toReturn = VariaveisProjeto.INCLUSAO_REALIZADA;
			} catch (Exception ex) {
				ex.printStackTrace();
				if ( trx.isActive() ) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_INCLUSAO;

			} finally {
				this.close();
			}
		} 
		return toReturn; 
	}


	public Integer update(Departamento departamento) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(departamento);
		
		if  ( toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getDepartamentoDao().update(departamento);
				trx.commit();
				toReturn = VariaveisProjeto.ALTERACAO_REALIZADA;

			} catch (Exception ex) {
				ex.printStackTrace();
				if ( trx.isActive() ) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;

			} finally {
				this.close();
			}
		} 
		return toReturn; 
	}


	public Integer delete(Departamento departamento) {
		Integer toReturn = 0;
		EntityTransaction trx = this.getTransaction();
		try {

			trx.begin();
			Departamento departamentoEncontrado = this.getDepartamentoDao().findById(departamento.getId());
			this.getDepartamentoDao().remove(departamentoEncontrado);
			trx.commit();
			toReturn = VariaveisProjeto.EXCLUSAO_REALIZADA;

		} catch (Exception ex) {
			ex.printStackTrace();
			if ( trx.isActive() ) {
				trx.rollback();
			}
			toReturn = VariaveisProjeto.ERRO_EXCLUSAO;

		} finally {
			this.close();
		}

		return toReturn; 
	}





	public Departamento findById(Integer id) {
		return this.getDepartamentoDao().findById(id);
	}


	public List<Departamento> findAll(){
		return this.getDepartamentoDao().findAll(Departamento.class);
	}



	public Integer validarDigitacao(Departamento departamento) {

		if ( VariaveisProjeto.digitacaoCampo(departamento.getNome())) {
			return VariaveisProjeto.DEPARTAMENTO_NOME;
		}
		
		return VariaveisProjeto.DIGITACAO_OK;
	}


	public DepartamentoDao getDepartamentoDao() {
		return departamentoDao;
	}

	public Integer countTotalRegister() {
		return departamentoDao.countTotalRegister(Departamento.class);
	}

	public List<Departamento> listDepartamentoPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		return departamentoDao.listDepartamentoPaginacao(numeroPagina,defaultPagina);
	}














}
