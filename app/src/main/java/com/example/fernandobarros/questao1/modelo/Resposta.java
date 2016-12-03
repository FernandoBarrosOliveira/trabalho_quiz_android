package com.example.fernandobarros.questao1.modelo;

import java.io.Serializable;

/**
 * Created by Fernando on 18/11/2016.
 */

public class Resposta implements Serializable {
    private Integer id;
    private Integer idQuestao;
    private String resposta;
    private boolean valorResposta;

    public Resposta (){}


    public Resposta(String resposta, boolean valorResposta){
        this.resposta = resposta;
        this.valorResposta = valorResposta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isValorResposta() {
        return valorResposta;
    }

    public void setValorResposta(boolean valorResposta) {
        this.valorResposta = valorResposta;
    }

    @Override
    public String toString() {
        return this.getResposta();
    }
}
