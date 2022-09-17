package com.nnk.springboot.controllers;

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

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public String home(Model model) {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = users.stream().map(user -> convertToDto(user))
                .collect(Collectors.toList());
        model.addAttribute("userDtos", userDtos);
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(UserDto userDto) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid UserDto userDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            User user = convertToEntity(userDto);
            userService.save(user);
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        UserDto userDto = convertToDto(user);
        userDto.setPassword("");
        model.addAttribute("userDto", userDto);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid UserDto userDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }
        User user = convertToEntity(userDto);
        userService.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.delete(id);
        return "redirect:/user/list";
    }

    private UserDto convertToDto(User entity) {
        UserDto dto = new UserDto(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getFullname(),
                entity.getRole());
        return dto;
    }

    private User convertToEntity(UserDto dto) {
        User user = new User(dto.getUsername(), dto.getFullname(), dto.getPassword(), dto.getRole());
        if (!StringUtils.isEmpty(dto.getId())) {
            user.setId(dto.getId());
        }
        return user;
    }
}
