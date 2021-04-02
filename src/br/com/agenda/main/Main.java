package br.com.agenda.main;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDao = new ContatoDAO();

        //Insercao de registros
        Contato contato1 = new Contato();
        contato1.setNome("Ana Banana");
        contato1.setIdade(34);
        contato1.setDataCadastro(new Date());
        //contatoDao.save(contato1);

        //Visualizacao total dos registros do banco de dados
        for (Contato c : contatoDao.getContatos()) {
            System.out.println("Contato: " + c.getNome() + ", Idade: " + c.getIdade() + ", Data de cadastro: " + c.getDataCadastro());
        }

        //contatoDao.update(contato1, 2);

    }
}
