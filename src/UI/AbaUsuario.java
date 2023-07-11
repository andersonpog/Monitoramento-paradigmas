package UI;

import app.Funcionario;
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

public class AbaUsuario extends JPanel {

    private JPanel cadastroUsuario;

    private JPanel listaUsuario;

    private JTable table;
    private DefaultTableModel tableModel;

    public AbaUsuario(){
        super(new BorderLayout());
        cadastroUsuario = new JPanel(new GridLayout(2, 2));
        listaUsuario = new JPanel(new GridBagLayout());
        add(cadastroUsuario,BorderLayout.NORTH);
        add(listaUsuario,BorderLayout.SOUTH);
    }

    public void desenharAba(){
        preencheCadastro();
        preencheTabela();
        add(cadastroUsuario,BorderLayout.NORTH);
        add(listaUsuario,BorderLayout.SOUTH);
    }

    private void preencheCadastro(){
        cadastroUsuario = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);


        JLabel labelNome = new JLabel("Nome:");
        JTextField textFieldNome = new JTextField(20);

        JLabel labelMAC = new JLabel("MAC:");
        JTextField textFieldMAC = new JTextField(20);

        JLabel labelIP = new JLabel("IP:");
        JTextField textFieldIP = new JTextField(20);

        JLabel labelEquip = new JLabel("Equipamento:");
        JComboBox<UsoFinal> comboBoxEquip = new JComboBox<UsoFinal>();

        LinkedHashSet<UsoFinal> equipamentosUsoFinal = UsoFinal.getAll();
        Iterator<UsoFinal> u = equipamentosUsoFinal.iterator();

        while (u.hasNext())
            comboBoxEquip.addItem(u.next());


        JLabel labelFunc = new JLabel("Funcionário:");
        JComboBox<Funcionario> comboBoxFunc = new JComboBox<Funcionario>();

        LinkedHashSet<Funcionario> funcionarios = Funcionario.getAll();
        Iterator<Funcionario> f = funcionarios.iterator();

        while (f.hasNext())
            comboBoxFunc.addItem(f.next());

        JButton buttonEnviar = new JButton("Enviar");

        // Adiciona os componentes ao contêiner principal
        constraints.gridx = 0;
        constraints.gridy = 0;
        cadastroUsuario.add(labelEquip, constraints);

        constraints.gridx = 1;
        cadastroUsuario.add(comboBoxEquip, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        cadastroUsuario.add(labelFunc, constraints);

        constraints.gridx = 1;
        cadastroUsuario.add(comboBoxFunc, constraints);

//        constraints.gridx = 0;
//        constraints.gridy = 2;
//        add(labelIP, constraints);
//
//        constraints.gridx = 1;
//        add(textFieldIP, constraints);
//
//        constraints.gridx = 0;
//        constraints.gridy = 3;
//        add(labelTipo, constraints);
//
//        constraints.gridx = 1;
//        add(comboBoxEquip, constraints);
//
        constraints.gridx = 1;
        constraints.gridy = 4;
        cadastroUsuario.add(buttonEnviar, constraints);

        // Adiciona o contêiner principal à janela principal

        // Configura a ação do botão Enviar
        buttonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os valores dos campos
                String nome = textFieldNome.getText();
                String mac = textFieldMAC.getText();
                String ip = textFieldIP.getText();
                Funcionario funcionario = (Funcionario) comboBoxFunc.getSelectedItem();
                UsoFinal usoFinal = (UsoFinal) comboBoxEquip.getSelectedItem();

                // Realiza ações com os valores obtidos
//                System.out.println("Nome: " + nome);
//                System.out.println("MAC: " + mac);
//                System.out.println("IP: " + ip);
//                System.out.println("Tipo: " + tipo);
                int s = usoFinal.registrarUsuario(funcionario.getCpf());

//                System.out.println(tipo.getNome()+usoFinal.getNome());
                if(s>0)
                JOptionPane.showMessageDialog(null, "Usuário salvo!", "Aviso", JOptionPane.WARNING_MESSAGE);
                else{
                    JOptionPane.showMessageDialog(null, "Usuário já presente!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }

                desenharAba();
            }
        });
    }

    private void preencheTabela(){
        listaUsuario = new JPanel();

        LinkedHashSet<UsoFinal> equipamentosUsoFinal = UsoFinal.getAll();
        Iterator<UsoFinal> u = equipamentosUsoFinal.iterator();

        removeAll();

        tableModel = new DefaultTableModel(new Object[]{"Id","Equipamento","Usuário", "CPF", "Remover"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 3;
            }
        };


        table = new JTable(tableModel);
        table.getColumnModel().getColumn(4).setCellRenderer(new DeleteButtonRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new DeleteButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);

        while (u.hasNext()){
            UsoFinal usoFinal = u.next();
            LinkedHashSet<Funcionario> usuarios = usoFinal.getUsuarios();
            Iterator<Funcionario> f = usuarios.iterator();
            while (f.hasNext()){
                Funcionario funcionario = f.next();
                adicionarLinha(usoFinal, funcionario);
            }
        }

        listaUsuario.add(scrollPane, BorderLayout.CENTER);
    }

    private void adicionarLinha(UsoFinal usoFinal, Funcionario funcionario) {
        tableModel.addRow(new Object[]{usoFinal.getId(),usoFinal.getNome(),funcionario.getNome(), funcionario.getCpf()});
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
                    String cpf = (String) tableModel.getValueAt(editingRow,3);
                    UsoFinalController u = new UsoFinalController();
                    u.deleteUsuario(cpf,id);
                    JOptionPane.showMessageDialog(null, "Usuário removido!", "Aviso", JOptionPane.WARNING_MESSAGE);
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
