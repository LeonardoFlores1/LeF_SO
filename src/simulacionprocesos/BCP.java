package simulacionprocesos;

public class BCP {
    private int id;
    private int estado;
    private int prioridad;
    private int cantidadInstrucciones;
    private int instruccionBloqueo;
    private int evento;
    private int instruccionesjecutadas=0;
    private int contadoresperabloqueo=0;
    private int instruccionessimultaneas=0;
    
    
    public BCP(int id, int estado, int prioridad, int cantidadInstrucciones, int instruccionBloqueo, int evento){
        this.id = id;
        this.estado = estado;
        this.prioridad = prioridad;
        this.cantidadInstrucciones = cantidadInstrucciones;
        this.instruccionBloqueo = instruccionBloqueo;
        this.evento = evento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getCantidadInstrucciones() {
        return cantidadInstrucciones;
    }

    public void setCantidadInstrucciones(int cantidadInstrucciones) {
        this.cantidadInstrucciones = cantidadInstrucciones;
    }

    public int getInstruccionBloqueo() {
        return instruccionBloqueo;
    }

    public void setInstruccionBloqueo(int instruccionBloqueo) {
        this.instruccionBloqueo = instruccionBloqueo;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }   
    
    @Override
    public String toString(){
        return "id: " + this.id + ", " +
                "estado: " + this.estado + ", " +
                "prioridad: " + this.prioridad + ", " +
                "cantidadInstrucciones: " + this.cantidadInstrucciones + ", " +
                "instruccionBloqueo: " + this.instruccionBloqueo + ", " +
                "evento: " + this.evento;
    }

    /**
     * @return the instruccionesjecutadas
     */
    public int getInstruccionesjecutadas() {
        return instruccionesjecutadas;
    }

    /**
     * @param instruccionesjecutadas the instruccionesjecutadas to set
     */
    public void setInstruccionesjecutadas(int instruccionesjecutadas) {
        this.instruccionesjecutadas =this.instruccionesjecutadas+ instruccionesjecutadas;
      this.instruccionessimultaneas = this.instruccionessimultaneas+ instruccionesjecutadas;
   
    }

    /**
     * @return the contadoresperabloqueo
     */
    public int getContadoresperabloqueo() {
        return contadoresperabloqueo;
    }

    /**
     * @param contadoresperabloqueo the contadoresperabloqueo to set
     */
    public void setContadoresperabloqueo(int contadoresperabloqueo) {
        this.contadoresperabloqueo = this.contadoresperabloqueo+contadoresperabloqueo;
    }

    /**
     * @return the instruccionessimultaneas
     */
    public int getInstruccionessimultaneas() {
        return instruccionessimultaneas;
    }

    /**
     * @param instruccionessimultaneas the instruccionessimultaneas to set
     */
    public void setInstruccionessimultaneas(int instruccionessimultaneas) {
        this.instruccionessimultaneas = instruccionessimultaneas;
    }
}
