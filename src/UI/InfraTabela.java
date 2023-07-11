package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InfraTabela extends DefaultTableModel {

    public InfraTabela(){
        super(new Object[]{"ID","Nome", "MAC", "IP","Atualizar IP"}, 0);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 3;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex==4){
            return JButton.class;
        }
        return super.getColumnClass(columnIndex);
    }
}
