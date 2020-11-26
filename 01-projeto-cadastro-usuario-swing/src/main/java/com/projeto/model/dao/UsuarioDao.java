package com.projeto.model.dao;

import javax.persistence.EntityManager;

import com.projeto.model.models.Usuario;

public class UsuarioDao extends GenericDao<Usuario, Integer > {

	public UsuarioDao(EntityManager entityManager) {
		super(entityManager);
	}
	

}
