package com.nnk.springboot.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.service.TradeService;
import com.nnk.springboot.web.dto.TradeDto;
import com.nnk.springboot.web.model.Trade;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        List<Trade> trades = tradeService.findAll();
        log.info("Trades retrieved with length = {}", trades.size());
        List<TradeDto> tradeDtos = trades.stream().map(trade -> convertToDto(trade))
                .collect(Collectors.toList());
        model.addAttribute("tradeDtos", tradeDtos);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(TradeDto tradeDto) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid TradeDto tradedDto, BindingResult result, Model model) {
        log.info("Valid Trade object");
        if (!result.hasErrors()) {
            log.info("Invalid Trade object");
            Trade trade = convertToEntity(tradedDto);
            tradeService.save(trade);
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id);
        log.info("Trade with id {} retrieved", id);
        TradeDto tradeDto = convertToDto(trade);
        model.addAttribute("tradeDto", tradeDto);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid TradeDto tradeDto,
            BindingResult result, Model model) {
        log.info("Valid Trade object");
        if (result.hasErrors()) {
            log.info("Invalid Trade object");
            return "trade/update";
        }
        Trade trade = convertToEntity(tradeDto);
        tradeService.save(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.delete(id);
        log.info("Trade with id {} successfully deleted", id);
        return "redirect:/trade/list";
    }

    private TradeDto convertToDto(Trade entity) {
        TradeDto dto = new TradeDto(entity.getTradeId(), entity.getAccount(), entity.getType(),
                entity.getBuyQuantity());
        return dto;
    }

    private Trade convertToEntity(TradeDto dto) {
        Trade trade = new Trade(dto.getAccount(), dto.getType(), dto.getBuyQuantity());
        if (!StringUtils.isEmpty(dto.getTradeId())) {
            trade.setTradeId(dto.getTradeId());
        }
        return trade;
    }

}
