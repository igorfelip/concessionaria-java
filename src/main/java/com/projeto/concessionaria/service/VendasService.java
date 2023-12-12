package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Carros;
import com.projeto.concessionaria.entity.Clientes;
import com.projeto.concessionaria.entity.Vendas;
import com.projeto.concessionaria.entity.Vendedores;
import com.projeto.concessionaria.exception.BadRequestException;
import com.projeto.concessionaria.mapper.VendaMapper;
import com.projeto.concessionaria.repository.VendasRepository;
import com.projeto.concessionaria.requests.VendasPostRequestBody;
import com.projeto.concessionaria.requests.VendasPutRequestBody;
import com.projeto.concessionaria.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendasService {
    private final VendasRepository vendasRepository;
    private final CarrosService carrosService;
    private final VendedoresService vendedoresService;
    private final ClientesService clientesService;

    public List<Vendas> findAll() {
        return vendasRepository.findAll();
    }

    public Vendas findById(Long id) {
        return vendasRepository.findById(id).orElseThrow(() ->
                (new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }

    @Transactional
    public Vendas save(VendasPostRequestBody venda, Integer parc) {
        Carros carro = carrosService.findById(venda.getCarro().getId());
        Vendedores vendedor = vendedoresService.findById(venda.getVendedor().getId());
        Clientes cliente = clientesService.findById(venda.getCliente().getId());

        BigDecimal parcelamento = Utils.calculaParcelamento(carro, parc);
        if (cliente.getSaldo().compareTo(parcelamento) < 0) {
            throw new BadRequestException("Saldo insuficiente");
        }
        List<Carros> comprados = cliente.getCarrosComprados();
        comprados.add(carro);
        cliente.setCarrosComprados(comprados);
        cliente.setSaldo(cliente.getSaldo().subtract(parcelamento));
        vendedor.setSalario(Utils.calculaComissao(vendedor, carro));
        return vendasRepository.save(VendaMapper.INSTANCE.toVenda(venda));
    }

    public void update(VendasPutRequestBody vendasPutRequestBody) {
        Vendas vendatrocada = findById(vendasPutRequestBody.getId());
        Vendas vendasAtualizada = VendaMapper.INSTANCE.toVendas(vendasPutRequestBody);
        vendasAtualizada.setId(vendatrocada.getId());
        vendasRepository.save(vendasAtualizada);
    }


    public void delete(Long id) {
        Vendas byId = findById(id);
        vendasRepository.delete(byId);

    }

}


