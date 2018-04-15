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
    static ArrayDeque<BCP> enProceso  = new ArrayDeque<BCP>(); //Cola que tendra a los procesos que se ejecutan
    

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
        //pido al usuario ue me de la cantidad de ciclos que hará
        System.out.println("¿Cuantas instrucciones serán?");
         Scanner leer_ciclo = new Scanner (System.in); 
         int cantidad_ciclos=leer_ciclo.nextInt(); 
         int temporizador=0;
         int proceso=0;
        for (int i=0;i<cantidad_ciclos;++i)
        {
            
            //Actualizar los diez procesos, añadir si hace falta uno
            while (enProceso.size()<11){
                // Primero actualiza los que estan en ejecucion
                  while(Ejecutando_1.peekFirst()!=null){
                      enProceso.addLast(Ejecutando_1.peekFirst());
                      enProceso.addLast(Ejecutando_1.peekFirst());
                      Ejecutando_1.pop();
                      
                  }
                 while(Ejecutando_2.peekFirst()!=null){
                      enProceso.addLast(Ejecutando_2.peekFirst());
                       Ejecutando_2.pop();
                     
                  }
                 while(Ejecutando_3.peekFirst()!=null){
                     enProceso.addLast(Ejecutando_3.peekFirst());
                      Ejecutando_3.pop();
                     
               // Si no hay ninguno en ejecucion, saca de listos       
                  }
                 while(Listo_1.peekFirst()!=null){
                     enProceso.addLast(Listo_1.peekFirst());
                      Listo_1.pop();
                     
                  }
                 while(Listo_2.peekFirst()!=null){
                     enProceso.addLast(Listo_2.peekFirst());
                      Listo_2.pop();
                     
                  }
                 while(Listo_3.peekFirst()!=null){
                     enProceso.addLast(Listo_3.peekFirst());
                      Listo_3.pop();
                     
                  }
                 
               //Si en listos ya no hay nada, pues saca de nuevo  
                 while(Nuevo_1.peekFirst()!=null){
                     enProceso.addLast(Nuevo_1.peekFirst());
                      Nuevo_1.pop();
                     
                  }
                 while(Nuevo_2.peekFirst()!=null){
                     enProceso.addLast(Nuevo_2.peekFirst());
                      Nuevo_2.pop();
                     
                  }
                 while(Nuevo_3.peekFirst()!=null){
                     enProceso.addLast(Nuevo_3.peekFirst());
                      Nuevo_3.pop();
                     
                  }
            }
                 
 //Actualizar Bloqueados que esperan algo del Disco o de la memoria
                 //Bloqueado 1
            if(Bloqueado_1.peekFirst().getEvento()==3){
                     Bloqueado_1.peekFirst().setInstruccioneseguidas(1);//Suma 1 a la instruccion
                     
                  } else   if(Bloqueado_1.peekFirst().getEvento()==5){
                     Bloqueado_1.peekFirst().setInstruccioneseguidas(1);
                     
                  } 
            
            //Si ya pasaron las instrucciones que esperaba los manda a  listo
            if((Bloqueado_1.peekFirst().getEvento()==3)&&(Bloqueado_1.peekFirst().getInstruccioneseguidas()==15)){
                     Bloqueado_1.peekFirst().setInstruccioneseguidas(0);
                     Listo_1.addLast(Bloqueado_1.peekFirst());
                     Bloqueado_1.pop();
                     
                  } 
            if((Bloqueado_1.peekFirst().getEvento()==5)&&(Bloqueado_1.peekFirst().getInstruccioneseguidas()==27)){
                     Bloqueado_1.peekFirst().setInstruccioneseguidas(0);
                      Listo_1.addLast(Bloqueado_1.peekFirst());
                     Bloqueado_1.pop();
                  } 
   //Actualizar Bloqueados que esperan algo del Disco o de la memoria
                  //Bloqueado 2
            if(Bloqueado_2.peekFirst().getEvento()==3){
                     Bloqueado_2.peekFirst().setInstruccioneseguidas(1);
                     
                  } else if(Bloqueado_2.peekFirst().getEvento()==5){
                     Bloqueado_2.peekFirst().setInstruccioneseguidas(1);
                     
                  } 
            //Si ya pasaron las instrucciones que esperaba los manda a  listo
            if((Bloqueado_2.peekFirst().getEvento()==3)&&(Bloqueado_2.peekFirst().getInstruccioneseguidas()==15)){
                     Bloqueado_2.peekFirst().setInstruccioneseguidas(0);//significa que vuelve a cero
                     Listo_2.addLast(Bloqueado_2.peekFirst());
                     Bloqueado_2.pop();
                  } 
            if((Bloqueado_2.peekFirst().getEvento()==5)&&(Bloqueado_2.peekFirst().getInstruccioneseguidas()==27)){
                      Bloqueado_2.peekFirst().setInstruccioneseguidas(0);//significa que vuelve a cero
                      Listo_2.addLast(Bloqueado_2.peekFirst());
                     Bloqueado_2.pop();
                  } 
    //Actualizar Bloqueados que esperan algo del Disco o de la memoria
                  //Bloqueado 3
            if(Bloqueado_3.peekFirst().getEvento()==3){
                     Bloqueado_3.peekFirst().setInstruccioneseguidas(1);
                     
                  } else if(Bloqueado_3.peekFirst().getEvento()==5){
                     Bloqueado_3.peekFirst().setInstruccioneseguidas(1);
                     
                  } 
            //Si ya pasaron las instrucciones que esperaba los manda a  listo
            if((Bloqueado_3.peekFirst().getEvento()==3)&&(Bloqueado_3.peekFirst().getInstruccioneseguidas()==15)){
                     Bloqueado_3.peekFirst().setInstruccioneseguidas(0);//significa que vuelve a cero
                     Listo_3.addLast(Bloqueado_3.peekFirst());
                     Bloqueado_3.pop();
                  } 
            if((Bloqueado_3.peekFirst().getEvento()==5)&&(Bloqueado_3.peekFirst().getInstruccioneseguidas()==27)){
                      Bloqueado_3.peekFirst().setInstruccioneseguidas(0); //significa que vuelve a cero
                      Listo_3.addLast(Bloqueado_3.peekFirst());
                     Bloqueado_3.pop();
                  } 
    
            
//Actualizar el temporitzador del ciclo y las instrucciones seguidas
      
        temporizador=+temporizador;
        enProceso.peekFirst().setInstruccioneseguidas(1);
        
        
        
//bajar Prioridad cuando se ejecuten mas de tres instrucciones
        if (enProceso.peekFirst().getInstruccioneseguidas()==3)
        {
           enProceso.peekFirst().setInstruccioneseguidas(0);//significa que vuelve a cero
           if (enProceso.peekFirst().getPrioridad()==1) {
               Bloqueado_1.addLast(enProceso.peekFirst());
               enProceso.pop();
       
           }else if (enProceso.peekFirst().getPrioridad()==2) {
               Bloqueado_2.addLast(enProceso.peekFirst());
               enProceso.pop();
        } 
        
 //Cambiar de procesos cuando el temporizador llega a cinco    
        
        if (temporizador==5){
            enProceso.pop();
            temporizador=0;
        }    
            
//Actualizar cambio de pilas de en proceso a Salientes cuando han sido terminados o a bloqueados     
        
        if (enProceso.peekFirst().getCantidadInstrucciones()==0){
            Saliente_1.add(enProceso.peekFirst());
                      enProceso.pop();
        
        }else
        {
            enProceso.peekFirst().setCantidadInstrucciones(enProceso.peekFirst().getCantidadInstrucciones()-1);
        }
        
//actualizar procesos cuando se encuentra una intruccion de bloqueo y tiene prioridad 1
         if (((enProceso.peekFirst().getInstruccionBloqueo()==enProceso.peekFirst().getInstruccionesejecutadas()))&&(enProceso.peekFirst().getPrioridad()==1)){
            Bloqueado_1.add(enProceso.peekFirst());
            enProceso.pop();
        }       //actualizar procesos cuando se encuentra una intruccion de bloqueo y tiene prioridad 2
         else if (((enProceso.peekFirst().getInstruccionBloqueo()==enProceso.peekFirst().getInstruccionesejecutadas()))&&(enProceso.peekFirst().getPrioridad()==2)){
            Bloqueado_2.add(enProceso.peekFirst());
                      enProceso.pop();
        } //actualizar procesos cuando se encuentra una intruccion de bloqueo y tiene prioridad 3
         else if (((enProceso.peekFirst().getInstruccionBloqueo()==enProceso.peekFirst().getInstruccionesejecutadas()))&&(enProceso.peekFirst().getPrioridad()==3)){
            Bloqueado_3.add(enProceso.peekFirst());
                      enProceso.pop();
        }
            
                
        
        
        
        };
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
