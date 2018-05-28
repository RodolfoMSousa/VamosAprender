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
public class CategoriaSugerida {
    private int id;
    private int categoriaId;
    private int turmaId;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the categoriaId
     */
    public int getCategoriaId() {
        return categoriaId;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
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
    
    
}
