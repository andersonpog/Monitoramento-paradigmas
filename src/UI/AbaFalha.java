package UI;

import app.Equipamento;
import app.Infra;
import app.UsoFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AbaFalha extends JPanel {

    public AbaFalha(){

        super(new GridBagLayout());

    }

    public void desenharAba(){
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel labelEquioamento = new JLabel("Equipamento");
        JComboBox<Equipamento> comboBoxEquip = new JComboBox<Equipamento>();

        LinkedHashSet<Infra> equipamentosInfra = Infra.getAll();
        Iterator<Infra> i = equipamentosInfra.iterator();

        while (i.hasNext())
            comboBoxEquip.addItem(i.next());

        LinkedHashSet<UsoFinal> equipamentosUsoFinal = UsoFinal.getAll();
        Iterator<UsoFinal> u = equipamentosUsoFinal.iterator();

        while (u.hasNext())
            comboBoxEquip.addItem(u.next());

        JButton buttonEnviar = new JButton("Enviar");

        JLabel labelDia = new JLabel("Dia");
        JTextField textFieldDia = new JTextField(2);
        JLabel labelMês = new JLabel("Mês");
        JTextField textFieldMes = new JTextField(2);
        JLabel labelAno = new JLabel("Ano");
        JTextField textFieldAno = new JTextField(4);

        JLabel labelDescricao = new JLabel("Descrição");
        JTextArea textDescricao = new JTextArea();
        textDescricao.setPreferredSize(new Dimension(150, 100));
        textDescricao.setLineWrap(true);

        // Adiciona os componentes
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelEquioamento,constraints);

        constraints.gridx = 1;
        add(comboBoxEquip, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelDia,constraints);

        constraints.gridx = 1;
        add(textFieldDia, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(labelMês, constraints);

        constraints.gridx = 1;
        add(textFieldMes,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(labelAno, constraints);

        constraints.gridx = 1;
        add(textFieldAno,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(labelDescricao, constraints);

        constraints.gridx = 1;
        add(textDescricao,constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        add(buttonEnviar, constraints);


        // Configura botão Enviar
        buttonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dia = textFieldDia.getText();
                String mes = textFieldMes.getText();
                String ano = textFieldAno.getText();
                String descricao = textDescricao.getText();
                Equipamento equipamento = (Equipamento) comboBoxEquip.getSelectedItem();

                String formato = "dd/MM/yyyy";

                boolean valido = validarData(dia+"/"+mes+"/"+ano,formato);
                boolean texto=true;

                if(textDescricao.getText().equals("")){
                    texto = false;
                    JOptionPane.showMessageDialog(null, "Preencha a descrição da falha!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }

                if(!valido){
                    JOptionPane.showMessageDialog(null, "Data inválida!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }

                if(texto && valido){

                    equipamento.registrarFalha(Integer.parseInt(dia),Integer.parseInt(mes),Integer.parseInt(ano),descricao);
                    textFieldDia.setText("");
                    textFieldMes.setText("");
                    textFieldAno.setText("");
                    textDescricao.setText("");

                    JOptionPane.showMessageDialog(null, "Falha registrada com sucesso!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    private boolean validarData(String dataTexto, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        sdf.setLenient(false); // Desativa a tolerância para datas inválidas

        try {
            Date data = sdf.parse(dataTexto);
            return true; // A data é válida
        } catch (ParseException e) {
            return false; // A data é inválida
        }
    }
}
