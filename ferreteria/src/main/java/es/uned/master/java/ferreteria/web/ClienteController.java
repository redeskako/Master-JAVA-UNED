package es.uned.master.java.ferreteria.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import es.uned.master.java.ferreteria.data.model.Cliente;
import es.uned.master.java.ferreteria.data.model.Pedido;
import es.uned.master.java.ferreteria.data.repository.ClienteRepository;
import es.uned.master.java.ferreteria.data.repository.PedidoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public ClienteController(ClienteRepository clienteRepository, PedidoRepository pedidoRepository){
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }


    @GetMapping
    public String getAllUsusarios(Model model){
        Iterable<Cliente> clientesIterable = this.clienteRepository.findAll();
        List<Cliente> clientes = new ArrayList<>();
        clientesIterable.forEach(clientes::add);
        clientes.sort(new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return c1.getNombre().compareTo(c2.getNombre());
            }
        });
        model.addAttribute("clientes", clientes);
        model.addAttribute("module", "clientes");
        return "clientes";
    }

    @GetMapping(path="/{id}")
    public String getUsuario(@PathVariable("id")long clienteId, Model model){
        Optional<Cliente> cliente = this.clienteRepository.findById(clienteId);
        if (cliente.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cliente no encontrado"
            );
        }
        model.addAttribute("cliente", cliente.get());
        Iterable<Pedido> pedidosIterable = this.pedidoRepository.findAllByClienteId(cliente.get().getId());
        List<Pedido> pedidos = new ArrayList<>();
        pedidosIterable.forEach(pedidos::add);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("module", "clientes");
        return "detalle_cliente";
    }
}
