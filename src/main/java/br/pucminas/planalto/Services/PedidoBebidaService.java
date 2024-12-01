package br.pucminas.planalto.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.pucminas.planalto.Model.Bebida;
import br.pucminas.planalto.Model.Pedido;
import br.pucminas.planalto.Model.PedidoBebida;
import br.pucminas.planalto.Model.Listagens.ListagemPedidoModel;
import br.pucminas.planalto.Repositories.BebidaRepository;
import br.pucminas.planalto.Repositories.PedidoBebidaRepository;
import br.pucminas.planalto.Repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;

@Service
public class PedidoBebidaService {
 
    @Autowired
    private PedidoBebidaRepository pedidoBebidaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BebidaRepository bebidaRepository;

    @Transactional
    public PedidoBebida create(PedidoBebida pb){
        pb.setId(null);
        Bebida b = this.bebidaRepository.findById(pb.getBebida().getId()).orElseThrow(() -> new RuntimeException(
            "Bebida não encontrada na base de dados"
        ));
        Pedido p = this.pedidoRepository.findById(pb.getPedido().getId()).orElseThrow(() -> new RuntimeException(
            "Pedido não encontrado na base de dados"
        ));
        this.pedidoBebidaRepository.saveAndFlush(pb);
        return pb;
    }

    @Transactional
    public List<PedidoBebida> getAll(){
        List<PedidoBebida> pb = this.pedidoBebidaRepository.findAll();
        return pb;
    }

    public PedidoBebida findById(Integer id){
        PedidoBebida pb = this.pedidoBebidaRepository.findById(id).orElseThrow(() -> new RuntimeException(
            "PedidoBebida não encontrado na base de dados"
        ));
        return pb;
    }

    public List<ListagemPedidoModel> buscarPedidosPorUsuarioEId(Long idUsuario) {
        return pedidoBebidaRepository.findListagemModel(idUsuario);
    }

    public List<ListagemPedidoModel> buscarTodosPedidos() {
        return pedidoBebidaRepository.findTodosPedidosListagemModel();
    }



    @Transactional
    public PedidoBebida update(PedidoBebida pb){
        PedidoBebida newPedidoBebida = findById(pb.getId());
        newPedidoBebida.setBebida(pb.getBebida());
        newPedidoBebida.setQuantidade(pb.getQuantidade());

        return this.pedidoBebidaRepository.save(newPedidoBebida);
    }
}
