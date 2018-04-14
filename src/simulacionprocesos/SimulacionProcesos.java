package simulacionprocesos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class SimulacionProcesos {
    
    /* Colas por estado y priordad para los procesos */
    static ArrayDeque<BCP> Nuevo_1 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Nuevo_2 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Nuevo_3 = new ArrayDeque<BCP>();

    static ArrayDeque<BCP> Listo_1 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Listo_2 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Listo_3 = new ArrayDeque<BCP>();

    static ArrayDeque<BCP> Ejecutando_1 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Ejecutando_2 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Ejecutando_3 = new ArrayDeque<BCP>();

    static ArrayDeque<BCP> Bloqueado_1 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Bloqueado_2 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Bloqueado_3 = new ArrayDeque<BCP>();

    static ArrayDeque<BCP> Saliente_1 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Saliente_2 = new ArrayDeque<BCP>();
    static ArrayDeque<BCP> Saliente_3 = new ArrayDeque<BCP>();

    public static void main(String[] args) throws FileNotFoundException {

        /* Lista que contendrá los tokens separados por ";" del archivo txt */
        ArrayList<String> stringsProcesos = new ArrayList<>();

        /* Bandera para saber si la información de los procesos es correcta */
        boolean procesosValidos = true;

        /* Lectura del archivo txt */
        Scanner read = new Scanner(new File("procesos.txt"));
        read.useDelimiter(";");
        while (read.hasNext()) {
            stringsProcesos.add(read.next());
        }
        read.close();

        /* Validación de la estructura de la información de cada proceso */
        procesosValidos = validarStringsProcesos(stringsProcesos);

        /* Crear instancias de la clase BCP para cada proceso y asignarlos a las colas respectivas */
        if (procesosValidos) {
            for (String stringProceso : stringsProcesos) {
                BCP proceso = instanciarBCP(stringProceso);
                asignarProcesoAColas(proceso);
            }

            /* El estado actual de las colas del estado Nuevo */
            while (!Nuevo_1.isEmpty()) {
                System.out.println(Nuevo_1.remove());
            }
            while (!Nuevo_2.isEmpty()) {
                System.out.println(Nuevo_2.remove());
            }
            while (!Nuevo_3.isEmpty()) {
                System.out.println(Nuevo_3.remove());
            }
        } else{
            System.err.println("Información incorrecta provista en el archivo");
        }
    }
    
    private static boolean validarStringsProcesos(ArrayList<String> stringsProcesos){
        /* Expresión regular para validar la estructura de la información de cada proceso del archivo txt */
        String regexProceso = "[0-9]{4}/[0-9]{1}/[0-9]{1}/[0-9]{2}/[0-9]{2}/[5|3]{1}";
        
        for (String stringProceso : stringsProcesos) {
            if (!stringProceso.matches(regexProceso)) {
                return false;
            }
        }
        return true;
    }

    private static BCP instanciarBCP(String stringProceso) {
        int id, estado, prioridad, cantidadInstruciones, instruccionBloqueo, evento;

        String[] datosProceso = stringProceso.split("/");

        id = Integer.parseInt(datosProceso[0]);
        estado = Integer.parseInt(datosProceso[1]);
        prioridad = Integer.parseInt(datosProceso[2]);
        cantidadInstruciones = Integer.parseInt(datosProceso[3]);
        instruccionBloqueo = Integer.parseInt(datosProceso[4]);
        evento = Integer.parseInt(datosProceso[5]);

        return new BCP(id, estado, prioridad, cantidadInstruciones, instruccionBloqueo, evento);
    }

    private static void asignarProcesoAColas(BCP proceso) {
        switch (proceso.getEstado()) {
            case 0:
                switch (proceso.getPrioridad()) {
                    case 1:
                        Nuevo_1.add(proceso);
                        break;
                    case 2:
                        Nuevo_2.add(proceso);
                        break;
                    case 3:
                        Nuevo_3.add(proceso);
                        break;
                }
            case 1:
                switch (proceso.getPrioridad()) {
                    case 1:
                        Listo_1.add(proceso);
                        break;
                    case 2:
                        Listo_2.add(proceso);
                        break;
                    case 3:
                        Listo_3.add(proceso);
                        break;
                }
            case 2:
                switch (proceso.getPrioridad()) {
                    case 1:
                        Ejecutando_1.add(proceso);
                        break;
                    case 2:
                        Ejecutando_2.add(proceso);
                        break;
                    case 3:
                        Ejecutando_3.add(proceso);
                        break;
                }
            case 3:
                switch (proceso.getPrioridad()) {
                    case 1:
                        Bloqueado_1.add(proceso);
                        break;
                    case 2:
                        Bloqueado_2.add(proceso);
                        break;
                    case 3:
                        Bloqueado_3.add(proceso);
                        break;
                }
            case 4:
                switch (proceso.getPrioridad()) {
                    case 1:
                        Saliente_1.add(proceso);
                        break;
                    case 2:
                        Saliente_2.add(proceso);
                        break;
                    case 3:
                        Saliente_3.add(proceso);
                        break;
                }
        }
    }

}
