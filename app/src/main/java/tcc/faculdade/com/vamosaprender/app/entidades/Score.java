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
public class Score {
    private int  jogoI ,alunoI, pontuacao, categoriaI;
    
    public Score(){
        
    }
    
    public Score(int jogoId, int alunoId, int pontuacao, int categoriaId){
        setJogoId(jogoId);
        setAlunoId(alunoId);
        setPontuacao(pontuacao);
        setCategoriaId(categoriaId);
    }


    /**
     * @return the jogoId
     */
    public int getJogoId() {
        return jogoI;
    }

    /**
     * @param jogoId the jogoId to set
     */
    public void setJogoId(int jogoId) {
        this.jogoI = jogoId;
    }

    /**
     * @return the alunoId
     */
    public int getAlunoId() {
        return alunoI;
    }

    /**
     * @param alunoId the alunoId to set
     */
    public void setAlunoId(int alunoId) {
        this.alunoI = alunoId;
    }

    /**
     * @return the pontuacao
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao the pontuacao to set
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * @return the datacadastro
     */
//    public int getDatacadastro() {
//        return datacadastro;
//    }
//
//    /**
//     * @param datacadastro the datacadastro to set
//     */
//    public void setDatacadastro(int datacadastro) {
//        this.datacadastro = datacadastro;
//    }

    /**
     * @return the categoriaId
     */
    public int getCategoriaId() {
        return categoriaI;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(int categoriaId) {
        this.categoriaI = categoriaId;
    }
}
