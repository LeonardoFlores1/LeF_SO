package simulacionprocesos;

public class BCP {
    private int id;
    private int estado;
    private int prioridad;
    private int cantidadInstrucciones;
    private int instruccionBloqueo;
    private int evento;
    
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
}
