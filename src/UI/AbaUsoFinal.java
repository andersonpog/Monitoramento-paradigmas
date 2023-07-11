package UI;

import app.UsoFinal;
import app.UsoFinalController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class AbaUsoFinal extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public AbaUsoFinal(){

    }

    public void desenharAba(){
        LinkedHashSet<UsoFinal> equipamentosUsoFinal = UsoFinal.getAll();
        Iterator<UsoFinal> u = equipamentosUsoFinal.iterator();

        removeAll();

        tableModel = new DefaultTableModel(new Object[]{"Id","Nome","IP", "Atualizar IP","Deletar"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Torna as colunas antes de IP não editável
                return column > 1;
            }
        };

        table = new JTable(tableModel);

        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

        table.getColumnModel().getColumn(4).setCellRenderer(new DeleteButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new DeleteButtonEditor(new JCheckBox()));

        JTextField textField = new JTextField();
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(textField));

        JScrollPane scrollPane = new JScrollPane(table);

        while (u.hasNext()){
            UsoFinal usoFinal = u.next();
            adicionarLinha(usoFinal);
        }

        add(scrollPane, BorderLayout.CENTER);
    }

    private void adicionarLinha(UsoFinal usoFinal) {
        JButton botao = new JButton("Atualizar");
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linha = table.getSelectedRow();

            }
        });

        tableModel.addRow(new Object[]{usoFinal.getId(),usoFinal.getNome(), usoFinal.getIp(), botao});
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setText("Atualizar");
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

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int editingRow;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    String ipModificado = (String) tableModel.getValueAt(editingRow, 3);
                    int id = (int) tableModel.getValueAt(editingRow, 0);
                    UsoFinalController u = new UsoFinalController();
                    int s = u.updateIp(id,ipModificado);
                    if(s>0)
                        JOptionPane.showMessageDialog(null, "Operação feita com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    desenharAba();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            editingRow = row;
            button.setText("Salvo");
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return tableModel.getValueAt(editingRow, 2);
        }
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

                    int id = (int) tableModel.getValueAt(editingRow, 0);
                    UsoFinalController u = new UsoFinalController();
                    int s = u.delete(id);
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
