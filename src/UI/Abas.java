package UI;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;



public class Abas extends JTabbedPane {

    private JTable table;
    private DefaultTableModel tableModel;

    public Abas(){

        addTab("Adicionar equipamento", new AbaAddEquip());
        addTab("Infraestrutura", new AbaInfra());
        addTab("Uso Final", new AbaUsoFinal());
        addTab("Registrar Falha", new AbaFalha());
        addTab("Relatório de Falhas", new AbaRelatorioFalha());
        addTab("Cadastrar Funcionários", new AbaFuncionario());
        addTab("Registrar Usuarios", new AbaUsuario());
        addTab("Lista de funcionários", new AbaListaFuncionario());

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int indice = getSelectedIndex();

                switch (indice){
                    case 1:
                        carregarAba1();
                        break;
                    case 2:
                        carregarAba2();
                        break;
                    case 3:
                        carregarAba3();
                        break;
                    case 4:
                        carregarAba4();
                        break;
                    case 6:
                        carregarAba6();
                        break;
                    case 7:
                        carregarAba7();
                        break;
                }
            }
        });

    }

    private void carregarAba1() {
        AbaInfra aba1 = (AbaInfra) this.getComponent(1);
        aba1.desenharAba();
    }

    private void carregarAba2(){
        AbaUsoFinal aba2 = (AbaUsoFinal) this.getComponent(2);
        aba2.desenharAba();
    }

    private void carregarAba3(){
        AbaFalha aba3 = (AbaFalha) this.getComponent(3);
        aba3.desenharAba();
    }

    private void carregarAba4(){
        AbaRelatorioFalha aba4 = (AbaRelatorioFalha) this.getComponent(4);
        aba4.desenharAba();
    }

    private void carregarAba6(){
        AbaUsuario aba6 = (AbaUsuario) this.getComponent(6);
        aba6.desenharAba();
    }

    private void carregarAba7(){
        AbaListaFuncionario aba = (AbaListaFuncionario) this.getComponent(7);
        aba.desenharAba();
    }

}


