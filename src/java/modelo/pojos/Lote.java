
package modelo.pojos;

public class Lote {
    private Integer idLote;
    private String nombreLote;
    private Integer numLote;
    private String status;
    private Integer idUsuario;
    private String usuario;

    public Lote() {
    }

    public Lote(Integer idLote, String nombreLote, Integer numLote, String status, Integer idUsuario, String usuario) {
        this.idLote = idLote;
        this.nombreLote = nombreLote;
        this.numLote = numLote;
        this.status = status;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public String getNombreLote() {
        return nombreLote;
    }

    public void setNombreLote(String nombreLote) {
        this.nombreLote = nombreLote;
    }

    public Integer getNumLote() {
        return numLote;
    }

    public void setNumLote(Integer numLote) {
        this.numLote = numLote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
