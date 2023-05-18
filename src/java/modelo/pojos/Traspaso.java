
package modelo.pojos;

public class Traspaso {
    private Integer idTraspaso;
    private String nombreLote;
    private Integer numLote;    
    private String descripcion;
    private String estatus;
    private String motivo; 
    private String loteOriginal;
    private String loteDestino;
    private String fechaCreacion;
    private String fechaModificacion;
    private String fechaCancelacion;
    private Integer idUusuario;
    private String usuario;
    private Integer idLote;

    public Traspaso() {
    }

    public Traspaso(Integer idTraspaso, String nombreLote, Integer numLote, String descripcion, String estatus, String motivo, String loteOriginal, String loteDestino, String fechaCreacion, String fechaModificacion, String fechaCancelacion, Integer idUusuario, String usuario, Integer idLote) {
        this.idTraspaso = idTraspaso;
        this.nombreLote = nombreLote;
        this.numLote = numLote;
        this.descripcion = descripcion;
        this.estatus = estatus;
        this.motivo = motivo;
        this.loteOriginal = loteOriginal;
        this.loteDestino = loteDestino;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaCancelacion = fechaCancelacion;
        this.idUusuario = idUusuario;
        this.usuario = usuario;
        this.idLote = idLote;
    }

    public Integer getIdTraspaso() {
        return idTraspaso;
    }

    public void setIdTraspaso(Integer idTraspaso) {
        this.idTraspaso = idTraspaso;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getLoteOriginal() {
        return loteOriginal;
    }

    public void setLoteOriginal(String loteOriginal) {
        this.loteOriginal = loteOriginal;
    }

    public String getLoteDestino() {
        return loteDestino;
    }

    public void setLoteDestino(String loteDestino) {
        this.loteDestino = loteDestino;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(String fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Integer getIdUusuario() {
        return idUusuario;
    }

    public void setIdUusuario(Integer idUusuario) {
        this.idUusuario = idUusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }
    
    
}
