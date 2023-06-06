
package modelo.pojos;

public class Lote {
    private Integer idLote;
    private String nombreLote;
    private Integer numLote;
    private String estatus;
    private Integer idUsuario;
    private String usuario;

    public Lote() {
    }

    public Lote(Integer idLote, String nombreLote, Integer numLote, String estatus, Integer idUsuario, String usuario) {
        this.idLote = idLote;
        this.nombreLote = nombreLote;
        this.numLote = numLote;
        this.estatus = estatus;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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
