/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
  * Universidad de los Andes
 * Universidad Autonoma de Manizales
 * Universidad Nacional de Colombia
 * Departamento de Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2
 * Ejercicio: parqueadero
 * Autor: Oscar Hernan Franco - 20-Febrero-2009
 * Autor: Pablo Barvo - 6-Sept-2005
 * Autor: Mario Sánchez - 17/06/2005
 * Autor: Juan Sebastián Montaña Ortega - 03/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package mnParqueadero;


public class Carro {

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Placa del carro */
    private String placa;
     /** Hora de llegada del carro al parqueadero: valor entre 6 y 20 */
    private int horaLLegada;

    
    /**
     * Crea un carro con la información básica
     * @param placa - Placa del carro - placa != null
     * @param horaLlegada - Hora de ingreso
     */
    public Carro (String placa, int horaLlegada) {
        this.placa=placa;
        this.horaLLegada=horaLlegada;
    }
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
     /**
     * Retorna la placa del carro
     * @return placa
     */
    public String getPlaca () {
        return placa;
    }
    /**
     * Retorna la hora de llegada del carro
     * @return horaLlegada
     */
    public int getHoraLLegada () {
        return horaLLegada;
    }

    /**
     * Indica si la placa del carro es igual a la recibida por parámetro
     * @param placa - Placa contra la que se está comparando - placa != null
     * @return Retorna true si las placas son iguales; en caso contrario retorna false
     */

    public boolean tienePlaca (String placa) {
        
        if( this.placa.equalsIgnoreCase( placa ) )
            return true;
        else
            return false;

    }

     /**
     * Calcula el número de horas que debe pagar el carro según el tiempo que
     * lleva el auto en el parqueadero.
     * @param horaSalida - Hora a la que el carro sale del parqueadero 
     * - horaSalida >= horaLlegada
     * @return Tiempo en parqueadero
     */

    public int darTiempoParqueo (int horaSalida) {
             return horaSalida - horaLLegada + 1;
    }

}

