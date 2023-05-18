
package modelo.pojos;

public class Cria {
    private Integer idCria;
    private String numArete;
    private String sexo;
    private String fechaNac;
    private String estatus;
    private Integer idRaza;
    private String observaciones;
    private Integer idUusuario;

    public Cria() {
    }

    public Cria(Integer idCria, String numArete, String sexo, String fechaNac, String estatus, Integer idRaza, String observaciones, Integer idUusuario) {
        this.idCria = idCria;
        this.numArete = numArete;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
        this.estatus = estatus;
        this.idRaza = idRaza;
        this.observaciones = observaciones;
        this.idUusuario = idUusuario;
    }

    public Integer getIdCria() {
        return idCria;
    }

    public void setIdCria(Integer idCria) {
        this.idCria = idCria;
    }

    public String getNumArete() {
        return numArete;
    }

    public void setNumArete(String numArete) {
        this.numArete = numArete;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Integer idRaza) {
        this.idRaza = idRaza;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdUusuario() {
        return idUusuario;
    }

    public void setIdUusuario(Integer idUusuario) {
        this.idUusuario = idUusuario;
    }
    
    
}
