package com.projeto.view.departamento;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Departamento;

public class TabelaDepartamentoModel extends AbstractTableModel {

	private static final long serialVersionUID = -5904517453998014283L;

	private final String colunas[] = {"Código","Nome"};
	
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	
	private List<Departamento> listaDepartamento;
	
		
	public List<Departamento> getListaDepartamento() {
		return listaDepartamento;
	}

	public void setListaDepartamento(List<Departamento> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}
	
	
	public Departamento getDepartamento(int rowIndex) {
		return getListaDepartamento().get(rowIndex);
	}

	
	public void saveDepartamento(Departamento departamento) {
		getListaDepartamento().add(departamento);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
	
	public void updateDepartamento(Departamento departamento, int rowIndex) {
		getListaDepartamento().set(rowIndex, departamento);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	
	public void removeDepartamento(int rowIndex) {
		getListaDepartamento().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	
	public void removeAll() {
		getListaDepartamento().clear();
		fireTableDataChanged();
	}
	
	
	@Override
	public int getRowCount() {
		return getListaDepartamento().size();
	}
	

	@Override
	public int getColumnCount() {
		return getColunas().length;
	}
	
	
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Departamento departamento = getListaDepartamento().get(rowIndex);
		switch(columnIndex) {
		case CODIGO:
			return  departamento.getId();
		case NOME:
			return departamento.getNome();
		default:
			return departamento;
		}
	}
	
	
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case CODIGO:
			return  Integer.class;
		case NOME:
			return String.class;
		default:
			return null;
		}
		
	}
	

	public String[] getColunas() {
		return colunas;
	}
}
