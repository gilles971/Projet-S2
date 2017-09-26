package Table;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public class TableDeModel  extends AbstractTableModel{
	
	private Object donnees[][];
	private String titres[];
 
	public TableDeModel(Object donnees[][], String titres[]){ 
    this.donnees = donnees; 
    this.titres = titres;
	}
	
	public int getColumnCount(){ 
    return donnees[0].length; 
	}
	
	public Object getValueAt(int parm1, int parm2){ 
    return donnees[parm1][parm2]; 
	}
	
	public int getRowCount() { 
    return donnees.length; 
	}
	
	
	public String getColumnName(int col){ 
    return titres[col]; 
	}
	
	public boolean isCellEditable(int row, int col){
		return false;
	}
	
	public void setDataVector(Object[][] datavector, Object[] columnIdentifiers){
		this.donnees = datavector;
	}
	
}
