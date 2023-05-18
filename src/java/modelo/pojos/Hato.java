
package modelo.pojos;

public class Hato {
    
    private String numArete;
    private String sexo;
    private String descripcion;
    //private imagen 1 y 2
    private String tipoGanado;
    private String estatus;
    private String diaDeAlta;
    private String diaDeModificacion;
    private String diaDeBaja;
    private String motivoDeBaja;
    private Integer idUsuario;
    private String usuario;
    private Integer idRaza;
    private String raza;
    private Integer idLote;
    private String lote;
    private Integer idRancho;
    private String rancho;

    public Hato() {
    }

    public Hato(String numArete, String sexo, String descripcion, String tipoGanado, String estatus, String diaDeAlta, String diaDeModificacion, String diaDeBaja, String motivoDeBaja, Integer idUsuario, String usuario, Integer idRaza, String raza, Integer idLote, String lote, Integer idRancho, String rancho) {
        this.numArete = numArete;
        this.sexo = sexo;
        this.descripcion = descripcion;
        this.tipoGanado = tipoGanado;
        this.estatus = estatus;
        this.diaDeAlta = diaDeAlta;
        this.diaDeModificacion = diaDeModificacion;
        this.diaDeBaja = diaDeBaja;
        this.motivoDeBaja = motivoDeBaja;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.idRaza = idRaza;
        this.raza = raza;
        this.idLote = idLote;
        this.lote = lote;
        this.idRancho = idRancho;
        this.rancho = rancho;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoGanado() {
        return tipoGanado;
    }

    public void setTipoGanado(String tipoGanado) {
        this.tipoGanado = tipoGanado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDiaDeAlta() {
        return diaDeAlta;
    }

    public void setDiaDeAlta(String diaDeAlta) {
        this.diaDeAlta = diaDeAlta;
    }

    public String getDiaDeBaja() {
        return diaDeBaja;
    }

    public void setDiaDeBaja(String diaDeBaja) {
        this.diaDeBaja = diaDeBaja;
    }

    public String getMotivoDeBaja() {
        return motivoDeBaja;
    }

    public void setMotivoDeBaja(String motivoDeBaja) {
        this.motivoDeBaja = motivoDeBaja;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Integer idRaza) {
        this.idRaza = idRaza;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getIdRancho() {
        return idRancho;
    }

    public void setIdRancho(Integer idRancho) {
        this.idRancho = idRancho;
    }

    public String getRancho() {
        return rancho;
    }

    public void setRancho(String rancho) {
        this.rancho = rancho;
    }

    public String getDiaDeModificacion() {
        return diaDeModificacion;
    }

    public void setDiaDeModificacion(String diaDeModificacion) {
        this.diaDeModificacion = diaDeModificacion;
    }
    
    
}
