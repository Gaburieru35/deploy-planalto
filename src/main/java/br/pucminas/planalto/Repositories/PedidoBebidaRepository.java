package br.pucminas.planalto.Repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.pucminas.planalto.Model.PedidoBebida;
import br.pucminas.planalto.Model.Listagens.ListagemPedidoModel;

public interface PedidoBebidaRepository extends JpaRepository<PedidoBebida, Integer> {
    @Query(value = "SELECT p.idpedido, p.valorpedido, " +
                   "STRING_AGG(CONCAT(b.nomebebida, ' x', pb.quantidade), ', ') AS bebidas, " +
                   "p.datapedido, c.nomecliente, p.statusPedido, p.br_code " +
                   "FROM pedido p " +
                   "JOIN pedido_bebida pb ON p.idpedido = pb.idpedido " +
                   "JOIN bebida b ON b.idbebida = pb.idbebida " +
                   "join cliente c on c.idusuario = p.idusuario " +
                   "WHERE p.idusuario = :idUsuario " +
                   "GROUP BY p.idpedido order by p.idpedido desc", 
           nativeQuery = true) 
    List<Object[]> findListagemPorUsuarioEId(@Param("idUsuario") Long idUsuario);

    @Query(value = "SELECT p.idpedido, p.valorpedido, " +
                   "STRING_AGG(CONCAT(b.nomebebida, ' x', pb.quantidade), ', ') AS bebidas, " +
                   "p.datapedido, c.nomecliente, p.statusPedido, p.br_code, " +
                   "CONCAT('Rua ', c.ruacliente, ', ', c.bairrocliente, ', ', c.numerocliente) AS enderecoCliente " +
                   "FROM pedido p " +
                   "JOIN pedido_bebida pb ON p.idpedido = pb.idpedido " +
                   "JOIN bebida b ON b.idbebida = pb.idbebida " +
                   "join cliente c on c.idusuario = p.idusuario " +
                   "GROUP BY p.idpedido order by p.idpedido desc", 
           nativeQuery = true) 
    List<Object[]> findListagem();
    
    default List<ListagemPedidoModel> findListagemModel(Long idUsuario) {
        List<Object[]> results = findListagemPorUsuarioEId(idUsuario);
        List<ListagemPedidoModel> listagemPedidoModels = new ArrayList<>();

        for (Object[] result : results) {
            Long id = ((Number) result[0]).longValue(); // Convertendo para Long
            Double valorPedido = ((Number) result[1]).doubleValue(); // Convertendo para Double
            String bebidas = (String) result[2]; // Bebidas concatenadas
            LocalDate data = ((java.sql.Date) result[3]).toLocalDate(); // Se for uma data SQL
            String nomeCliente = (String) result[4]; // Nome do cliente
            Integer statusPedido = ((Number) result[5]).intValue(); // Status do pedido
            String brCode = (String) result[6]; // Código de barras
            String enderecoCliente = ""; // Endereço do cliente

            listagemPedidoModels.add(new ListagemPedidoModel(id, valorPedido, bebidas, data, nomeCliente, statusPedido, brCode, enderecoCliente));
        }

        return listagemPedidoModels;
    }

    default List<ListagemPedidoModel> findTodosPedidosListagemModel() {
        List<Object[]> results = findListagem();
        List<ListagemPedidoModel> listagemPedidoModels = new ArrayList<>();

        for (Object[] result : results) {
            Long id = ((Number) result[0]).longValue(); // Convertendo para Long
            Double valorPedido = ((Number) result[1]).doubleValue(); // Convertendo para Double
            String bebidas = (String) result[2]; // Bebidas concatenadas
            LocalDate data = ((java.sql.Date) result[3]).toLocalDate(); // Se for uma data SQL
            String nomeCliente = (String) result[4]; // Nome do cliente
            Integer statusPedido = ((Number) result[5]).intValue(); // Status do pedido
            String brCode = (String) result[6]; // Código de barras
            String enderecoCliente = (String) result[7]; // Endereço do cliente

            listagemPedidoModels.add(new ListagemPedidoModel(id, valorPedido, bebidas, data, nomeCliente, statusPedido, brCode, enderecoCliente));
        }

        return listagemPedidoModels;
    }

}
