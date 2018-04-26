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
        
        Scanner in = new Scanner(System.in);

        /* Lista que contendrá los tokens separados por ";" del archivo txt */
        ArrayList<String> stringsProcesos = new ArrayList<>();

        /* Cantidad de instrucciones del usuario */
        System.out.print("Introduzca la cantidad de ciclos: ");
        int ciclosDelUsuario = in.nextInt();
        
        /* Lectura del archivo txt */
        Scanner read = new Scanner(new File("procesos.txt"));
        read.useDelimiter(";");
        while (read.hasNext()) {
            stringsProcesos.add(read.next());
        }
        read.close();

        /* Validación de la información de cada proceso */
        stringsProcesos = validarStringsProcesos(stringsProcesos);
        
        
        if (stringsProcesos.isEmpty()) {
            System.err.println("No se encontraron procesos válidos en el archivo");
            return;
        }
        
        /* Crear instancias de la clase BCP para cada proceso y asignarlos a las colas respectivas */
        for (String stringProceso : stringsProcesos) {
            BCP proceso = instanciarBCP(stringProceso);
            asignarProcesoAColas(proceso);
        }
        
        /* Estructura de simulación del procesador */
        for (int i = 0; i < ciclosDelUsuario; i++) {
            //System.out.println(i);
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

    }
    
    private static ArrayList<String> validarStringsProcesos(ArrayList<String> stringsProcesos){
        ArrayList<String> procesosValidados = new ArrayList<>();
        ArrayList<Integer> idsProcesosValidados = new ArrayList<>();
        
        int contador = 0;
        
        /* Expresión regular para validar la estructura de la información de cada proceso del archivo txt */
        String regexProceso = "[0-9]{4}/[0-9]{1}/[0-9]{1}/[0-9]{3}/[0-9]{3}/[0|5|3]{1}";
        
        for (String stringProceso : stringsProcesos) {
            stringProceso = stringProceso.replaceAll("\\s", "");
            if (stringProceso.matches(regexProceso)) {
                String[] datosProceso = stringProceso.split("/");
                if(!datosProceso[0].matches("[0-9]{4}")){
                    System.err.println("El ID de proceso: " + datosProceso[0] + " no tiene un ID válido." + " Proceso: " + stringProceso);
                    continue;
                }
                if(!datosProceso[1].matches("[0-4]{1}")){
                    System.err.println("El estado de proceso: " + datosProceso[1] + " no es válido." + " Proceso: " + stringProceso);
                    continue;
                }
                if(!datosProceso[2].matches("[1-3]{1}")){
                    System.err.println("La prioridad de proceso: " + datosProceso[2] + " no es válida." + " Proceso: " + stringProceso);
                    continue;
                }
                if(!datosProceso[3].matches("[0-9]{3}")){
                    System.err.println("La cantidad de instrucciones de proceso: " + datosProceso[3] + " no es válida." + " Proceso: " + stringProceso);
                    continue;
                }
                if(!datosProceso[4].matches("[0-9]{3}")){
                    System.err.println("La instrucción de bloqueo de proceso: " + datosProceso[4] + " no es válida." + " Proceso: " + stringProceso);
                    continue;
                }
                if(datosProceso[4].matches("0{3}")){
                    if(datosProceso[5].matches("[5|3]{1}")){
                        System.err.println("No puede haber evento sin instrucción de bloqueo." + " Proceso: " + stringProceso);
                        continue;
                    }
                }
                if(!datosProceso[5].matches("[0|5|3]{1}")){
                    System.err.println("El evento de bloqueo: " + datosProceso[5] + " no es válido." + " Proceso: " + stringProceso);
                    continue;
                }
                if(Integer.parseInt(datosProceso[4]) > Integer.parseInt(datosProceso[3])){
                    System.err.println("La instrucción de bloqueo no puede ser mayor que las instrucciones totales." + " Proceso: " + stringProceso);
                    continue;
                }
                
                if(contador == 0){
                    procesosValidados.add(stringProceso);
                    idsProcesosValidados.add(Integer.parseInt(datosProceso[0]));
                } else{
                    if(idsProcesosValidados.contains(Integer.parseInt(datosProceso[0]))){
                        System.err.println("El ID de proceso: "+ datosProceso[0] +" ya existe." + " Proceso: " + stringProceso);
                        continue;
                    } else{
                        procesosValidados.add(stringProceso);
                        idsProcesosValidados.add(Integer.parseInt(datosProceso[0]));
                    }
                }
            } else{
                System.err.println("El proceso: '" + stringProceso + "' no cumple con la estructura.");
            }
            contador++;
        }

        return procesosValidados;
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
