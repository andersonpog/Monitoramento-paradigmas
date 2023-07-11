package UI;

import app.Equipamento;
import app.Falha;
import app.Infra;
import app.UsoFinal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashSet;


public class AbaRelatorioFalha extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public AbaRelatorioFalha(){

    }

    public void desenharAba(){
        LinkedHashSet<UsoFinal> equipamentosUsoFinal = UsoFinal.getAll();
        Iterator<UsoFinal> u = equipamentosUsoFinal.iterator();

        LinkedHashSet<Infra> equipamentosInfra = Infra.getAll();
        Iterator<Infra> i = equipamentosInfra.iterator();

        removeAll();

        tableModel = new DefaultTableModel(new Object[]{"Id","Nome","Data", "Ocorrido"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {

                return column > 10;
            }
        };


        table = new JTable(tableModel);

        TableCellRenderer renderer = new MultiLineTableCellRenderer();
        table.getColumnModel().getColumn(3).setCellRenderer(renderer);

        JScrollPane scrollPane = new JScrollPane(table);

        while (u.hasNext()){
            UsoFinal usoFinal = u.next();
            LinkedHashSet<Falha> falhas = usoFinal.getFalhas();
            Iterator<Falha> f = falhas.iterator();
            while (f.hasNext()){
                Falha falha = f.next();
                adicionarLinha(usoFinal, falha);
            }
        }

        while (i.hasNext()){
            Infra infra = i.next();
            LinkedHashSet<Falha> falhas = infra.getFalhas();
            Iterator<Falha> f = falhas.iterator();
            while (f.hasNext()){
                Falha falha = f.next();
                adicionarLinha(infra, falha);
            }
        }

        add(scrollPane, BorderLayout.CENTER);
    }

    private void adicionarLinha(Equipamento usoFinal, Falha falha) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tableModel.addRow(new Object[]{usoFinal.getId(),usoFinal.getNome(),falha.getDataFalha().format(dateTimeFormatter), falha.getDescricao()});
    }

    private static class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {

        public MultiLineTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }
}
