
package modelo.pojos;

public class Veterinario {
    private Integer idVisita;
    private String nombreVisita;
    private String fechaVisita;
    private String motivo;
    private String observaciones;
    private String estatus;
    private String numArete;
    private Integer idUsuario;
    private String usuario;

    public Veterinario() {
    }

    public Veterinario(Integer idVisita, String nombreVisita, String fechaVisita, String motivo, String observaciones, String estatus, String numArete, Integer idUsuario, String usuario) {
        this.idVisita = idVisita;
        this.nombreVisita = nombreVisita;
        this.fechaVisita = fechaVisita;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.estatus = estatus;
        this.numArete = numArete;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public Integer getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Integer idVisita) {
        this.idVisita = idVisita;
    }

    public String getNombreVisita() {
        return nombreVisita;
    }

    public void setNombreVisita(String nombreVisita) {
        this.nombreVisita = nombreVisita;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNumArete() {
        return numArete;
    }

    public void setNumArete(String numArete) {
        this.numArete = numArete;
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
    
    
     
}
