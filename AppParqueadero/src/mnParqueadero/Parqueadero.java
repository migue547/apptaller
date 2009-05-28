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

import java.util.ArrayList; 

public class Parqueadero {
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    /** Tarifa por hora en el parqueadero */
    
    private float tarifa;
    /** Valor recibido en la caja del parqueadero */
    
    private float caja;
        
    /** Hora actual en el parqueadero */
    private int horaActual;
    
    /** Indica si el parqueadero esta abierto */
    private boolean abierto;


    private ArrayList<Puesto> puestos;
        //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un parqueadero con su información básica. <br>
     * <b>post: </b> Se creó un parqueadero con la tarifa establecida
     * @param nTarifa - Tarifa del parqueadero
     * 
     */
    public Parqueadero( int tarifa )
    {
        horaActual = 6;
        abierto = true;
        this.tarifa = tarifa;
        caja = 0;
        puestos =  new ArrayList<Puesto>();
        for(int i=1;i<=90;i++)
        {
            Puesto puesto= new Puesto(i);
            puestos.add(puesto);
        }
    }
    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    /**
     * Indica si el parqueadero está abierto
     * @return Retorna true si el parqueadero está abierto. False en caso contrario
     */
    
    public boolean getAbierto () {
        return abierto;
    }
    
    /**
     * Retorna la tarifa por hora del parqueadero
     * @return tarifa
     */

    public float getTarifa () {
        return tarifa;

    }
     /**
     * Indica la cantidad de dinero que hay en la caja.
     * @return ingresos
     */
    public float getCaja () {
        return caja;
    }
    /**
     * Retorna la hora actual
     * @return horaActual
     */
    public int getHoraActual () {
        return horaActual;
    }

    /**
     * asigna a horaActual el parametro hora
     * @param hora
     */
    public void setHoraActual(int hora)
    {
        this.horaActual = hora;
    }

    /**
     * Asigna a caja el valor caja
     */

    public void setCaja(float caja)
    {
        this.caja = caja;
    }
    /**
     * asigna a abierto el valor abierto
     */
    public void setAbierto(boolean valor)
    {
        this.abierto = valor;
    }

     /**
     * Avanza una hora en el parqueadero. Si la hora actual es igual a la hora de cierre, el parqueadero se cierra.
     */

    public void avanzarHoraActual () {
         if( horaActual < 20 )
        {
            horaActual = ( horaActual + 1 );
        }
        if( horaActual == 20 )
            abierto = false;
   
    }
    /**
     * Cambia la tarifa actual del parqueadero
     * @param nTarifa - Tarifa nueva del parqueadero
     */
    public void setTarifa (float val) {
        this.tarifa = val;
    }
/**
     * Ingresa un un carro al parqueadero <br>
     * <b>post: </b>El carro quedó parqueado en el puesto indicado
     * @param placa - Placa del carro que ingresa - placa!=null
     * @return puesto en el que debe parquear. <br>
     *         Si el parqueadero está lleno retorna la excepcion NO_HAY_PUESTO. <br>
     *         Si el parqueadero está cerrado retorna la excepcion PARQUEADERO_CERRADO.
 */

    public Puesto entrarCarro (String placa)throws Exception 
    {
        if( !abierto )
            throw new Exception("PARQUEADERO_CERRADO");
        
        // Buscar en el parqueadero un carro con la placa indicada
        Puesto puesto = buscarPuestoCarro( placa.toUpperCase( ) );
        if( puesto!=null )
            throw new Exception("CARRO YA EXISTE");;
         // Buscar un puesto libre para el carro y agregarlo
            puesto = buscarPuestoLibre( );
            if( puesto !=null )
            {
                Carro carro = new Carro( placa, horaActual );
                puesto.parquearCarro(carro);
                puesto.setNumeroCarros();
            }
            return puesto;        
    }
    /**
     * Sirve para sacar un carro del parqueadero y saber la cantidad de dinero que debe pagar <br>
     * <b>post: </b> El carro salió del parqueadero y el puesto que ocupaba, ahora está libre
     * @param placa - Placa del carro que va a salir - placa != null
     * @return Retorna el valor a pagar. Si el carro no se encontraba dentro del parqueadero entonces retorna CARRO_NO_EXISTE. Si el parqueadero ya estaba cerrado retorna
     *         PARQUEADERO_CERRADO.
     */

    public float sacarCarro (String placa) throws Exception {
         if( !abierto )
            throw new Exception("PARQUEADERO_CERRADO");

        Puesto puesto = buscarPuestoCarro( placa.toUpperCase( ) );
        if( puesto==null )
            throw new Exception("CARRO NO  EXISTE");;
               
        Carro carro = puesto.getCarro();
        int nHoras = carro.darTiempoParqueo( horaActual );
        float porPagar = nHoras * tarifa;
        caja = caja + porPagar;
        puesto.sacarCarro();
        return porPagar;
    }

    /**
     * Indica la cantidad de puestos libres que hay
     * @return espaciosVacios
     */
    
    public int calcularPuestosLibres () {
         int puestosLibres = 0;
        for( Puesto p:puestos )
            if( !p.estaOcupado() )
                puestosLibres = puestosLibres + 1;
        return puestosLibres;

    
    }

    /**
     * Busca un puesto libre en el parqueadero y lo retorna. Si no encuentra retorna el valor NO_HAY_PUESTO
     * @return número del puesto libre
     */

    private Puesto buscarPuestoLibre () {
        
        Puesto puesto = null;
        for( Puesto p:puestos )
            if( !p.estaOcupado() )
                return p;        
        return puesto;
    }
/**
     * Indica el número de puesto en el que se encuentra el carro con una placa dada. Si no lo encuentra retorna el valor CARRO_NO_EXISTE
     * @param placa - Placa del carro que se busca - placa != null
     * @return número del puesto en el que se encuentra el carro
     */

    private Puesto buscarPuestoCarro (String placa) {
      Puesto puesto = null;
        for( Puesto p:puestos )
            if( p.tieneCarroConPlaca(placa) )
                return p;        
        return puesto;
    }
/**
     * Indica si el puesto i está ocupado
     * @param i - El puesto que se quiere saber si está ocupado - i >= 0 y i < puestos.length
     * @return Retorna true si el puesto está ocupado. False en caso contrario.
     */
   
    public boolean estaOcupado (int puesto) {
        return puestos.get(puesto).estaOcupado();
    }
    /**
     * permite obtener la hora de entrada de un carro dada su placa
     * @param placa
     * @return
     * @throws java.lang.Exception
     */
    public Carro obtenerHoraEntrada(String placa)
            throws Exception {
         if( !abierto )
            throw new Exception("PARQUEADERO_CERRADO");

        Puesto puesto = buscarPuestoCarro( placa.toUpperCase( ) );
        if( puesto==null )
            throw new Exception("CARRO NO  EXISTE");
               
        Carro carro = puesto.getCarro();
        //int horaEntrada = carro.getHoraLLegada();
        //int nHoras = carro.darTiempoParqueo( horaActual );
        return carro;
    }
    
    /**
     * permite obtener  la placa del carro dado un puesto 
     */
      public String obtenerPlacaParqueadero(int puesto)
      {
          Carro carro = puestos.get(puesto).getCarro();
          String placa = carro.getPlaca();
          return placa;
      }

      public int obtenerCarrosPuesto(int puesto)
      {
          return puestos.get(puesto).getNumeroCarros();
      }







}

