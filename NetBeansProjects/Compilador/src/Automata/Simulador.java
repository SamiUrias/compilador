/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

/**
 * Esta clase realiza la simulacion de un AFN
 * @author Moises Urias
 */
public class Simulador {
    
    private boolean cadenaAceptada; /*La cadena es aceptada por el atuomata*/
    
    private int estadoInicial;
    private int estadoAceptacion;
    
    private Automata automata; /*Automata que se estara simlando*/
    
    public Simulador(Automata automata)
    {
        this.automata = automata;
    }
    
    public boolean aceptacion(Automata automata, String cadena)
    {
        this.automata = automata;
        return cadenaAceptada;
    }
    
    /**
     * Este metodo hace la simulacion del AFN
     */
    public boolean simular(String cadena){
        /*Esta variable almacena si la cadena es aceptada o no por el automata*/
        boolean aceptada;
        
        System.out.println("Dentro del simulador");
        /*Se encuentran los estados inciales y finales del automata*/
        this.estadoInicial = automata.getEstadoInicial();
        this.estadoAceptacion = automata.getEstadoFinal();
        
        /*Se genera un nuevo subset con el estado inicial del automata*/
        Subset subset = new Subset(estadoInicial, automata.getTransiciones());
        
        /*Se le realiza un eclosure al subset*/
        System.out.println("Se hace el eClosure");
        subset = OpExtra.eClosure(subset);
        String c = cadena.substring(0,1);
        
        for (int i = 1; i<=cadena.length();i++   ) {
            System.out.println("C = " + c);
            subset = OpExtra.mover(subset, c);
            subset = OpExtra.eClosure(subset);
            if (i < cadena.length())
                c = cadena.substring(i,i+1);
        }
        
        if (subset.Nodos.contains(estadoAceptacion))
        {
            System.out.println("La cadena es aceptada por el automata");
            aceptada = true;
        }
        else{
            System.out.println("La cadena nno es aceptada por el automata");
            aceptada = false;
        }
        
        return aceptada;
    }
    
    
}
