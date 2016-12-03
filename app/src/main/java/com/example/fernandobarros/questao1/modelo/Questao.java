package com.example.fernandobarros.questao1.modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Fernando on 17/11/2016.
 */

public class Questao implements Serializable {
    private Integer id;
    private int enderecoImagem;
    private String questao;
    private ArrayList<Resposta> respostas = new ArrayList<Resposta>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public int getEnderecoImagem() {
        return enderecoImagem;
    }

    public void setEnderecoImagem(int enderecoImagem) {
        this.enderecoImagem = enderecoImagem;
    }

    public ArrayList<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(ArrayList<Resposta> respostas) {
        this.respostas = respostas;
    }

    public void adicionarResposta(String resposta, boolean valorResposta){
        Resposta resp = new Resposta(resposta, valorResposta);
        this.respostas.add(resp);

    }
}
