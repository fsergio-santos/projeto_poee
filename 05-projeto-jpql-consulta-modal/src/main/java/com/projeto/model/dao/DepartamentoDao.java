package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Departamento;

public class DepartamentoDao extends GenericDao<Departamento, Integer > {

	public DepartamentoDao(EntityManager entityManager) {
		super(entityManager);
	}

	
	@SuppressWarnings("unchecked")
	public List<Departamento> listDepartamentoPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Departamento> listaDepartamento = new ArrayList<Departamento>();
		
		boolean ativo = true;
		
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Departamento u WHERE u.ativo =:ativo")
											 .setParameter("ativo", ativo)
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);
		listaDepartamento = query.getResultList();
		
		return listaDepartamento;
	}
	

}
