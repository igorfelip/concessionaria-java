package com.projeto.concessionaria.service;

import com.projeto.concessionaria.entity.Vendedores;
import com.projeto.concessionaria.exception.BadRequestException;
import com.projeto.concessionaria.mapper.VendedoresMapper;
import com.projeto.concessionaria.repository.VendedoresRepository;
import com.projeto.concessionaria.requests.VendedoresPostRequestBody;
import com.projeto.concessionaria.requests.VendedoresPutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendedoresService {
    private final VendedoresRepository vendedoresRepository;

    public List<Vendedores> findAll() {
        return vendedoresRepository.findAll();
    }

    public Vendedores findById(Long id) {
        return vendedoresRepository.findById(id).orElseThrow(() ->
                (new BadRequestException("Vendedor não encontrado")));
    }

    public Vendedores save(VendedoresPostRequestBody vendedoresPostRequestBody) {
        return vendedoresRepository.save(VendedoresMapper.INSTANCE.toVendedores(vendedoresPostRequestBody));
    }

    public void update(VendedoresPutRequestBody vendedoresPutRequestBody) {
        Vendedores byId = findById(vendedoresPutRequestBody.getId());
        Vendedores vendedores = VendedoresMapper.INSTANCE.toVendedores(vendedoresPutRequestBody);
        vendedores.setId(byId.getId());
        vendedoresRepository.save(vendedores);
    }

    public void delete(Long id) {
        Vendedores byId = findById(id);
        vendedoresRepository.delete(byId);

    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void zeraComissao() {
        List<Vendedores> vendedores = findAll();
        vendedores.forEach(vendedores1 -> {
            BigDecimal subtract = vendedores1.getSalario().subtract(vendedores1.getSalario());
            vendedores1.setSalario(subtract.add(BigDecimal.valueOf(1300)));
            vendedoresRepository.save(vendedores1);
        });
    }
}
