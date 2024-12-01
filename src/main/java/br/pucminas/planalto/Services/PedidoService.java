package br.pucminas.planalto.Services;

import java.math.BigDecimal;
import java.util.*;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNodes.JsonNode;

import br.pucminas.planalto.Model.Cliente;
import br.pucminas.planalto.Model.Pedido;
import br.pucminas.planalto.Repositories.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;
    
    public List<Pedido> getAll(){
        List<Pedido> p = this.pedidoRepository.findAll();
        return p;
    }

    public Pedido findById(Integer id){
        Pedido p = this.pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException(
            "Pedido não encontrado na base de dados"
        ));
        return p;
    }

    @Transactional
    public Pedido create(Pedido p) {
        p.setId(null); // Garante que o ID seja nulo para criar um novo pedido

        // Define a data atual se não estiver presente no pedido
        if (p.getData() == null) {
            p.setData(LocalDate.now());
        }

        Pedido p1 = p;
        p = this.pedidoRepository.save(p1);
        p.getId();
        p.setStatus(1);
        return p;
    }

    @Transactional
    public Pedido update(Pedido p){
        Pedido newPedido = findById(p.getId());
        newPedido.setUsuario(p.getUsuario());
        // newPedido.setItens(p.getItens());
        newPedido.setValorpedido(p.getValorpedido());
        newPedido.setStatus(p.getStatus());

        return this.pedidoRepository.save(newPedido);
    }

    public void delete(Integer id){
        Pedido p = findById(id);
        try{
            this.pedidoRepository.deleteById(p.getId());
        }catch(Exception e){
            throw new RuntimeException("Não é possível excluir, pois possui entidades relacionadas");
        }
    }

    public Pedido findByCliente(Integer idUsuario){
        Optional<Pedido> p = this.pedidoRepository.findByUserId(idUsuario);
        return p.orElseThrow(() -> new RuntimeException(
            "Pedido não encontrado"
        ));
    }

    public List<Pedido> findByIdCliente(Integer id){
        Optional<List<Pedido>> p = this.pedidoRepository.findByIdCliente(id);
        return p.orElseThrow(() -> new RuntimeException(
            "Pedido não encontrado"
        ));
    }

    public void atualizarStatus(Integer id, Integer status){
        Pedido p = findById(id);
        p.setStatus(status);
        this.pedidoRepository.save(p);
    }

    public String gerarBrCode(double valor){
         String url = "https://gerarqrcodepix.com.br/api/v1";

        // Construindo a URL com os parâmetros necessários
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("nome", "Matheus")
                .queryParam("cidade", "BH")
                .queryParam("chave", "10811317641")
                .queryParam("valor", String.format("%.2f", valor))
                .queryParam("saida", "br");

        // Instanciando RestTemplate para fazer a requisição
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Fazendo a requisição GET
            String response = restTemplate.getForObject(builder.toUriString(), String.class);

            // Convertendo a resposta para JSON e obtendo o valor de "brcode"
            ObjectMapper mapper = new ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode jsonResponse = mapper.readTree(response);

            return jsonResponse.get("brcode").asText();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Double> obterTotalValorPedidosPorDiaSemanaUltimaSemana() {
        List<Object[]> resultados = pedidoRepository.findTotalValorPedidosPorDiaSemanaUltimaSemana();

        Map<String, Double> totalValorPorDia = new HashMap<>();

        String[] diasSemana = { "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" };

        for (Object[] resultado : resultados) {
            int diaSemana = ((Number) resultado[0]).intValue();
            double totalValor = ((Number) resultado[1]).doubleValue();
            totalValorPorDia.put(diasSemana[diaSemana - 1], totalValor);
        }

        return totalValorPorDia;
    }

    public Double obterValorTotalPedidosDoDia() {
        return pedidoRepository.findValorTotalPedidosDoDia();
    }

    public Map<String, Integer> obterPedidosPorStatusDoDia() {
        List<Object[]> resultados = pedidoRepository.findPedidosPorStatusDoDia();

        Map<String, Integer> pedidosPorStatus = new HashMap<>();
        pedidosPorStatus.put("Aguardando pagamento", 0);
        pedidosPorStatus.put("Pago", 0);
        pedidosPorStatus.put("Finalizado", 0);

        for (Object[] resultado : resultados) {
            int status = ((Number) resultado[0]).intValue();
            int total = ((Number) resultado[1]).intValue();

            switch (status) {
                case 1 -> pedidosPorStatus.put("Aguardando pagamento", total);
                case 2 -> pedidosPorStatus.put("Pago", total);
                case 3 -> pedidosPorStatus.put("Finalizado", total);
            }
        }

        return pedidosPorStatus;
    }

    public Map<String, Double> obterTopClientesPorValorPedidoMes() {
        List<Object[]> resultados = pedidoRepository.findTopClientesPorValorPedidoMes();

        Map<String, Double> topClientes = new HashMap<>();
        for (Object[] resultado : resultados) {
            String cliente = (String) resultado[0];
            BigDecimal totalValor = (BigDecimal) resultado[1];
            topClientes.put(cliente, totalValor.doubleValue());
        }

        return topClientes;
    }

    public double obterTicketMedioPedidosMes() {
        Double ticketMedio = pedidoRepository.findTicketMedioPedidosMes();
        return ticketMedio != null ? ticketMedio : 0.0;
    }

    public Map<String, Integer> obterBebidasMaisVendidasMes() {
        List<Object[]> resultados = pedidoRepository.findBebidasMaisVendidasMes();

        Map<String, Integer> bebidasMaisVendidas = new LinkedHashMap<>();
        for (Object[] resultado : resultados) {
            String bebida = (String) resultado[0];
            Integer totalVendas = ((Number) resultado[1]).intValue();
            bebidasMaisVendidas.put(bebida, totalVendas);
        }

        return bebidasMaisVendidas;
    }
}
