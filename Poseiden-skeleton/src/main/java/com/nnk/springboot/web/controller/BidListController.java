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

import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.web.dto.BidListDto;
import com.nnk.springboot.web.model.BidList;

@Controller
public class BidListController {
    @Autowired
    private BidListService bidListService;

    @GetMapping("/bidList/list")
    public String home(Model model) {
        List<BidList> bidLists = bidListService.findAll();
        List<BidListDto> bidListDtos = bidLists.stream().map(bid -> convertToDto(bid)).collect(Collectors.toList());
        model.addAttribute("bidListDtos", bidListDtos);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidListDto", new BidListDto("", "", 0, null));
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidListDto bidListDto, BindingResult result) {
        if (!result.hasErrors()) {
            BidList bidList = convertToEntity(bidListDto);
            bidListService.save(bidList);
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        BidListDto bidListDto = convertToDto(bidList);
        model.addAttribute("bidListDto", bidListDto);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidListDto bidListdDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        BidList bidList = convertToEntity(bidListdDto);
        bidListService.save(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        bidListService.delete(id);
        return "redirect:/bidList/list";
    }

    private BidListDto convertToDto(BidList entity) {
        BidListDto dto = new BidListDto(entity.getAccount(), entity.getType(), entity.getBidQuantity(),
                entity.getBidListId());
        return dto;
    }

    private BidList convertToEntity(BidListDto dto) {
        BidList bidList = new BidList(dto.getAccount(), dto.getType(), dto.getBidQuantity());
        if (!StringUtils.isEmpty(dto.getBidListId())) {
            bidList.setBidListId(dto.getBidListId());
        }
        return bidList;
    }
}
