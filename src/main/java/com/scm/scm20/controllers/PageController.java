package com.scm.scm20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm20.entities.User;
import com.scm.scm20.forms.UserForm;
import com.scm.scm20.helpers.Message;
import com.scm.scm20.helpers.MessageType;
import com.scm.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        model.addAttribute("name", "substring technologies");
        model.addAttribute("youtubeChannel", "xyzVlogs");
        model.addAttribute("github", "xyzGithub");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About Page Loading");
        return "about";
    }

    @RequestMapping("/services")
    public String servicePage() {
        System.out.println("Service Page Loading");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();

        // this is default data if we want to show default data in our form fields
        // userForm.setName("Anurag Sharma");
        // userForm.setAbout("hello ji write something ");

        model.addAttribute("userForm", userForm);

        return "register";
    }

    // processing our registration from
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("processing registration");
        System.out.println(userForm);

        if (rBindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://images.app.goo.gl/41kRFBstSeYTAoYr8");

        User savedUser = userService.saveUser(user);
        System.out.println("user saved : ");

        // access the message after registration

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);
        return "redirect:/register";
    }

}
