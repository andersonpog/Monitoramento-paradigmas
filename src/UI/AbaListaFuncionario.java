package UI;

import app.Funcionario;
import app.FuncionarioController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class AbaListaFuncionario extends JPanel{

    private JTable table;
    private DefaultTableModel tableModel;
    public AbaListaFuncionario(){
    }

    public void desenharAba(){
        LinkedHashSet<Funcionario> funcionarios = Funcionario.getAll();
        Iterator<Funcionario> f = funcionarios.iterator();


        removeAll();

        tableModel = new DefaultTableModel(new Object[]{"CPF","Nome", "cargo", "Remover",}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 2;
            }
        };

        table = new JTable(tableModel);



        table.getColumnModel().getColumn(3).setCellRenderer(new DeleteButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new DeleteButtonEditor(new JCheckBox()));



        JScrollPane scrollPane = new JScrollPane(table);

        while (f.hasNext()){
            Funcionario funcionario = f.next();
            adicionarLinha(funcionario);
        }

        add(scrollPane, BorderLayout.CENTER);
    }

    private void adicionarLinha(Funcionario funcionario) {

        JButton botao = new JButton("Atualizar");
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = table.getSelectedRow();

            }
        });

        tableModel.addRow(new Object[]{funcionario.getCpf(), funcionario.getNome(), funcionario.getCargo()});
    }





    private class DeleteButtonRenderer extends JButton implements TableCellRenderer {
        public DeleteButtonRenderer() {
            setOpaque(true);
            setText("Apagar");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }
            return this;
        }
    }

    private class DeleteButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int editingRow;

        public DeleteButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();

                    String cpf = (String) tableModel.getValueAt(editingRow, 0);
                    FuncionarioController f = new FuncionarioController();
                    int s = f.delete(cpf);
                    if(s>0)
                        JOptionPane.showMessageDialog(null, "Operação feita com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    desenharAba();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            editingRow = row;
            button.setText("Apagado");
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return tableModel.getValueAt(editingRow, 2);
        }
    }
}
