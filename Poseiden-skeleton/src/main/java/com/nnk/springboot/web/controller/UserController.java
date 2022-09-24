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

import com.nnk.springboot.service.UserService;
import com.nnk.springboot.web.dto.UserDto;
import com.nnk.springboot.web.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is the controller class for the User Entity
 * 
 * @author Rémi Deronzier
 */
@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @param model
     * @return String
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        List<User> users = userService.findAll();
        log.info("Users retrieved with length = {}", users.size());
        List<UserDto> userDtos = users.stream().map(user -> convertToDto(user))
                .collect(Collectors.toList());
        model.addAttribute("userDtos", userDtos);
        return "user/list";
    }

    /**
     * @param userDto
     * @return String
     */
    @GetMapping("/user/add")
    public String addUser(UserDto userDto) {
        return "user/add";
    }

    /**
     * @param userDto
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/user/validate")
    public String validate(@Valid UserDto userDto, BindingResult result, Model model) {
        log.info("Valid User object");
        if (!result.hasErrors()) {
            log.info("Invalid User object");
            User user = convertToEntity(userDto);
            userService.save(user);
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        log.info("User with id {} retrieved", id);
        UserDto userDto = convertToDto(user);
        userDto.setPassword("");
        model.addAttribute("userDto", userDto);
        return "user/update";
    }

    /**
     * @param id
     * @param userDto
     * @param result
     * @param model
     * @return String
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid UserDto userDto,
            BindingResult result, Model model) {
        log.info("Valid User object");
        if (result.hasErrors()) {
            log.info("Invalid User object");
            return "user/update";
        }
        User user = convertToEntity(userDto);
        userService.save(user);
        return "redirect:/user/list";
    }

    /**
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.delete(id);
        log.info("User with id {} successfully deleted", id);
        return "redirect:/user/list";
    }

    /**
     * @param entity
     * @return UserDto
     */
    private UserDto convertToDto(User entity) {
        UserDto dto = new UserDto(entity.getId(), entity.getUserName(), entity.getPassword(), entity.getFullName(),
                entity.getRole());
        return dto;
    }

    /**
     * @param dto
     * @return User
     */
    private User convertToEntity(UserDto dto) {
        User user = new User(dto.getUserName(), dto.getFullName(), dto.getPassword(), dto.getRole());
        if (!StringUtils.isEmpty(dto.getId())) {
            user.setId(dto.getId());
        }
        return user;
    }
}
