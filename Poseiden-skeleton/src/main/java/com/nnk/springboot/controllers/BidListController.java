package com.nnk.springboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.services.BidListService;

@RestController
public class BidListController {
    @Autowired
    private BidListService bidListService;

    @GetMapping("/bidList/list")
    public String home(Model model) {
        List<BidList> bidLists = bidListService.getBidLists();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";
        // return bidLists;
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return
        // list Bid
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
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
