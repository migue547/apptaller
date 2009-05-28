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


public class Puesto {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Eventual carro en el puesto. Si no hay ninguno, carro == null */

    private Carro carro;
    /** Numero del puesto dentro del parqueadero */

    private int numero;

    private int numCarros;
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un puesto vacío <br>
     * <b>post: </b>Se creó un puesto vacío
     * @param numero- Número de puesto
     */

     public Puesto (int numero) {
         carro = null;
        this.numero = numero;
        this.numCarros=0;

    }
//-----------------------------------------------------------------
    // Métodos
    //------------------------------------------------------------
         /**
     * Retorna el número del puesto
     * @return numero
     */
    public int getNumero () {
        return numero;
    }

    /**
     * Retorna el numero de carros que se han parqueado en un puesto
     */

    public int getNumeroCarros()
    {
        return numCarros;
    }

    public void setNumeroCarros()
    {
        numCarros = numCarros+1;
    }
    /**
     * Indica si el carro tiene la placa recibida
     * @param placa
     * @return true si tiene la placa, false de lo contrario
     */
    public boolean tieneCarroConPlaca( String placa )
    {
        if( carro == null )
            return false;
        else if( carro.tienePlaca( placa ) )
            return true;
        else
            return false;
    }
    
    /**
     * Retorna el carro del puesto. Si no hay ningún carro retorna null.
     * @return carro
     * */
    public Carro getCarro () {
        return carro;
    }
     /**
     * Indica si el puesto está ocupado.
     * @return Retorna true si el puesto está ocupado. Retorna false en caso contrario.
     */

    public boolean estaOcupado () {
        return carro!=null;
    }
    /**
     * Parquea un carro en el puesto <br>
     * <b>pre: </b>El puesto está vacío <br>
     * <b>post: </b> El puesto ahora está ocupado por el carro carroP
     * @param carro - Carro que se está parqueando - carroP != null
     */
    public void parquearCarro (Carro carro) {
        this.carro=carro;
    }
     /**
     * Saca el carro del puesto. <br>
     * <b>post: </b>El puesto está vacío
     */

    public void sacarCarro () {
        carro=null;
    }

    

}

