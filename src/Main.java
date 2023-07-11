import UI.MainWindow;

import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {

        MainWindow window = new MainWindow();

//        // Salva em banco equipamentos de infraestrutura de rede
//        Infra.salvarNovo("Modem feio", "123", "34567");
//        Infra.salvarNovo("Roteador", "r5634", "12345");
//
//        // Salva em banco equipamentos para o usuario final
//        UsoFinal.salvarNovo("Impressora HP", "12345", "123456");
//        UsoFinal.salvarNovo("PC supimpa", "34728", "123.344");
//
//        // Salva em banco novos funcionarios
//        Funcionario.SalvarNovo("123456","Zezinho", "almoxarife");
//        Funcionario.SalvarNovo("12345","Pedrinho", "programador");
//
//        LinkedHashSet<Infra> equipamentosInfra = Infra.getAll();
//        Iterator<Infra> i = equipamentosInfra.iterator();
//
//        LinkedHashSet<UsoFinal> equipamentosUsoFinal = UsoFinal.getAll();
//        Iterator<UsoFinal> u = equipamentosUsoFinal.iterator();
//
//        LinkedHashSet<Funcionario> funcionarios = Funcionario.getAll();
//        Iterator<Funcionario> f = funcionarios.iterator();
//
//        //Exibindo dados de infra
//        Infra roteador = null;
//        while (i.hasNext()){
//            Infra infra = i.next();
//            if(infra.getMac().equals("r5634"))
//                roteador=infra;
//            infra.listarDados();
//            infra.listarFalhas();
//        }
//
//        //Exibindo dados de equipamento de uso final
//        UsoFinal impressora = null;
//        UsoFinal desktop =null;
//        while (u.hasNext()){
//            UsoFinal usoFinal = u.next();
//            if(usoFinal.getMac().equals("12345"))
//                impressora = usoFinal;
//            if(usoFinal.getMac().equals("34728"))
//                desktop = usoFinal;
//            usoFinal.listarDados();
//            usoFinal.listarFalhas();
//            usoFinal.listarUsuarios();
//        }
//
//        // Adicionando usuarios aos equipamentos
//        impressora.registrarUsuario("123456");
//        impressora.registrarUsuario("12345");
//        desktop.registrarUsuario("12345");
//
//        // Adicionando historico de falhas nos equipamentos
//        if(roteador!=null){
//            roteador.registrarFalha(2,10,2022, "Saiu do ar");
//            roteador.registrarFalha(3,1,2023,"Ataque hacker");
//            roteador.deletarIp();
//            roteador.registrarIp("458.123.123");
//        }
//
//        if(impressora!=null){
//            impressora.registrarFalha(3,2,2023,"Atolou papel");
//        }
//
//        if(desktop!=null){
//            desktop.registrarFalha(06,04,2023,"Programador deletou system32");
//        }
//
//        // Exibindo dados atualizados
//
//        equipamentosInfra = Infra.getAll();
//        i = equipamentosInfra.iterator();
//
//        while (i.hasNext()){
//            Infra infra = i.next();
//            infra.listarDados();
//            infra.listarFalhas();
//        }
//
//        equipamentosUsoFinal = UsoFinal.getAll();
//        u = equipamentosUsoFinal.iterator();
//
//        while (u.hasNext()){
//            UsoFinal usoFinal = u.next();
//            usoFinal.listarDados();
//            usoFinal.listarFalhas();
//            usoFinal.listarUsuarios();
//        }
//
//        // Limpando BD
//
//        i = equipamentosInfra.iterator();
//        u = equipamentosUsoFinal.iterator();
//
//        while (i.hasNext())
//            i.next().apagar();
//        while (u.hasNext())
//            u.next().apagar();
//        while (f.hasNext())
//            f.next().apagar();
    }
}