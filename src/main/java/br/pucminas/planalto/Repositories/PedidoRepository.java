package br.pucminas.planalto.Repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.pucminas.planalto.Model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query(value = "SELECT p FROM Pedido p WHERE p.usuario.id = :id", nativeQuery = true)
    Optional<Pedido> findByUserId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Pedido WHERE idusuario = :id", nativeQuery = true)
    Optional<List<Pedido>> findByIdCliente(@Param("id") Integer id);

    @Query(value = """ 
      SELECT EXTRACT(DOW FROM p.datapedido) AS diaSemana, 
             SUM(p.valorpedido) AS totalValorPedidos
      FROM pedido p
      WHERE p.datapedido >= CURRENT_DATE - INTERVAL '7 days'
      GROUP BY EXTRACT(DOW FROM p.datapedido)
      ORDER BY diaSemana
      """, nativeQuery = true)
  List<Object[]> findTotalValorPedidosPorDiaSemanaUltimaSemana();


    @Query(value = """
        SELECT COALESCE(SUM(p.valorpedido), 0) 
        FROM pedido p
        WHERE p.datapedido = CURRENT_DATE
        """, nativeQuery = true)
    Double findValorTotalPedidosDoDia();

    @Query(value = """
        SELECT p.statuspedido, COUNT(p.idpedido) AS total
        FROM pedido p
        WHERE p.datapedido = CURRENT_DATE
        GROUP BY p.statuspedido
        """, nativeQuery = true)
    List<Object[]> findPedidosPorStatusDoDia();

    @Query(value = """
    SELECT c.nomecliente AS nome, COALESCE(SUM(p.valorpedido), 0) AS totalValor
    FROM pedido p
    JOIN usuario u ON p.idusuario = u.idusuario
    JOIN cliente c ON c.idusuario = u.idusuario
    WHERE EXTRACT(MONTH FROM p.datapedido) = EXTRACT(MONTH FROM CURRENT_DATE)
      AND EXTRACT(YEAR FROM p.datapedido) = EXTRACT(YEAR FROM CURRENT_DATE)
    GROUP BY c.nomecliente
    ORDER BY totalValor DESC
    """, nativeQuery = true)
    List<Object[]> findTopClientesPorValorPedidoMes();

    @Query(value = """
        SELECT COALESCE(AVG(p.valorpedido), 0)
        FROM pedido p
        WHERE EXTRACT(MONTH FROM p.datapedido) = EXTRACT(MONTH FROM CURRENT_DATE)
          AND EXTRACT(YEAR FROM p.datapedido) = EXTRACT(YEAR FROM CURRENT_DATE)
        """, nativeQuery = true)
    Double findTicketMedioPedidosMes();

    @Query(value = """
    SELECT b.nomebebida AS bebida, COALESCE(SUM(pb.quantidade), 0) AS totalVendas
    FROM pedido p
    JOIN pedido_bebida pb ON p.idpedido = pb.idpedido
    JOIN bebida b ON pb.idbebida = b.idbebida
    WHERE EXTRACT(MONTH FROM p.datapedido) = EXTRACT(MONTH FROM CURRENT_DATE) 
      AND EXTRACT(YEAR FROM p.datapedido) = EXTRACT(YEAR FROM CURRENT_DATE)
    GROUP BY b.nomebebida
    ORDER BY totalVendas DESC
    """, nativeQuery = true)
    List<Object[]> findBebidasMaisVendidasMes();
}
