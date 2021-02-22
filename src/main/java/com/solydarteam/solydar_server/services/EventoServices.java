package com.solydarteam.solydar_server.services;

import com.solydarteam.solydar_server.Organizacion;
import com.solydarteam.solydar_server.Responsable;
import com.solydarteam.solydar_server.evento.CategoriaEvento;
import com.solydarteam.solydar_server.evento.Evento;
import com.solydarteam.solydar_server.pedido.TipoPedido;

import java.util.Date;

public class EventoServices {

    private Evento nuevoEvento;
    private final Organizacion organizacion;
    private final Responsable responsable;

    public EventoServices(Organizacion organizacion, Responsable responsable){
        this.organizacion = organizacion;
        this.responsable = responsable;
    }

    public void crearEvento(
            String titulo,
            String descripcion,
            String aQuienAyuda,
            CategoriaEvento categoriaEvento,
            Date fechaDeRealizacion,
            Responsable responsable
    ) throws Exception{
        nuevoEvento = organizacion.crearNuevoEvento(titulo,
                descripcion,
                aQuienAyuda,
                categoriaEvento,
                fechaDeRealizacion,
                responsable);

    }

    public void agregarItemAlPedido(String descripcion, int cantidad, TipoPedido tipoPedido, String urlImagen) throws Exception{
        if (nuevoEvento != null){

            nuevoEvento.getPedidoSolicitado().agregarItem(
                    descripcion,
                    cantidad,
                    tipoPedido,
                    urlImagen);
        }
        else
            throw new Exception("No se creo evento");
    }

    public void publicarEvento(){
        try {
            organizacion.registrarAMisEventos(nuevoEvento);
            System.out.println("SE REGISTRO UN EVENTO EN LA ORGANIZACION");
            System.out.println(nuevoEvento.toString());;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
