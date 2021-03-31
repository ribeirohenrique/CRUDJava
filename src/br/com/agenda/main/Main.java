package br.com.agenda.main;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatorDao = new ContatoDAO();

        //Insercao de registros
        Contato contato1 = new Contato();
        contato1.setNome("Juan Navalha");
        contato1.setIdade(25);
        contato1.setDataCadastro(new Date());

        contatorDao.save(contato1);

        //Visualizacao total dos registros do banco de dados
        for (Contato c : contatorDao.getContatos()) {
            System.out.println("Contato: " + c.getNome());
        }

    }
}
