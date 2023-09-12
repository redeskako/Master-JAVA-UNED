package es.uned.master.java.ferreteria.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.uned.master.java.ferreteria.data.model.Cliente;
import es.uned.master.java.ferreteria.data.model.Pedido;
import es.uned.master.java.ferreteria.data.repository.ClienteRepository;
import es.uned.master.java.ferreteria.data.repository.PedidoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoController(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public String getAllPedidos(Model model){
        Iterable<Pedido> pedidoIterable = this.pedidoRepository.findAll();
        List<PedidoModel> pedidos = new ArrayList<>();
        pedidoIterable.forEach(pi ->{
           PedidoModel pedidoModel = new PedidoModel();
           pedidoModel.setPedidoId(pi.getId());
           pedidoModel.setClienteId(pi.getClienteId());
           Optional<Cliente> pc = this.clienteRepository.findById(pi.getClienteId());
           pedidoModel.setCliente(pc.get().getNombre());
           pedidoModel.setDetalleCliente(pi.getPedidoInfo());
           pedidos.add(pedidoModel);
        });
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("module", "pedidos");
        return "pedidos";
    }
}
