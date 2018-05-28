/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.faculdade.com.vamosaprender.app.entidades;

/**
 *
 * @author Rodolfo
 */
public class Turma {
    private int turmaId;
    private String nome;
    
    public Turma(){
        
    }
    public Turma(int turmaId, String nome){
        this.nome = nome;
        this.turmaId = turmaId;
    }

    /**
     * @return the turmaId
     */
    public int getTurmaId() {
        return turmaId;
    }

    /**
     * @param turmaId the turmaId to set
     */
    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
