	package com.scm.Smart_Contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.Smart_Contact.entities.User;
import com.scm.Smart_Contact.forms.UserForm;
import com.scm.Smart_Contact.helpers.Message;
import com.scm.Smart_Contact.helpers.MessageType;
import com.scm.Smart_Contact.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class myController { 
	
	@Autowired
	private UserService userService;

    @GetMapping("/")
    public String homepage(Model model)
    {
        model.addAttribute("name", "Sneha Radhesham Dhadve");
        model.addAttribute("address", "Ram Nagar Lonar , Buldhana Pincode:443302");
        
        return "home";
    } 
    
    @GetMapping("/home")
    public String index(Model model)
    {
        model.addAttribute("name", "Sneha Radhesham Dhadve");
        model.addAttribute("address", "Ram Nagar Lonar , Buldhana Pincode:443302");
        
        return "home";
    } 
    
    @GetMapping("/about")
    public String aboutpage()
    {
        System.out.println("This is about page!");
        return "about";
    }

    @GetMapping("/services")
    public String servicespage()
    {
        System.out.println("This is services page!");
        return "services";
    }

    @GetMapping("/contact")
    public String contactpage()
    {
        System.out.println("This is contact page!");
        return "contact";
    } 

    @GetMapping("/login")
    public String loginpage()
    {
        System.out.println("This is login page!");
        return "login";
    }

     @GetMapping("/register")
    public String showRegistrationForm(Model model, Object userFrom) {
    	 UserForm userForm = new UserForm();
        model.addAttribute("userForm", userFrom);
        return "register"; 
    }
    
   //@PostMapping("/do-register")
    @RequestMapping(value="/do-register" ,method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult ,HttpSession session) {
       
    	    
    	if(rBindingResult.hasErrors())
    	{
    		return "register";
    		
    	}
    	
    	
    	
    	
    	
    	
    	
//    	User user=User.builder()
//    	.name(userForm.getName())
//    	.email(userForm.getEmail())
//    	.password(userForm.getPassword())	
//    	.about(userForm.getAbout())
//    	.phoneNumber(userForm.getPhoneNumber())
//    	.profilePic("https://media.licdn.com/dms/image/v2/D5635AQEY4kzdisA1uQ/profile-framedphoto-shrink_200_200/profile-framedphoto-shrink_200_200/0/1705666414501?e=1724047200&v=beta&t=BO6r32wc8hgMIYj6fJQFZq_q9YX4eUJjHqQ8cuYSeB4")
//    	.build();
    	User user=new User();
    	
    	user.setName(userForm.getName());
    	user.setEmail(userForm.getEmail());
    	user.setPassword(userForm.getPassword());
    	user.setAbout(userForm.getAbout());
    	user.setPhoneNumber(userForm.getPhoneNumber());
    	user.setProfilePic("https://media.licdn.com/dms/image/v2/D5635AQEY4kzdisA1uQ/profile-framedphoto-shrink_200_200/profile-framedphoto-shrink_200_200/0/1705666414501?e=1724047200&v=beta&t=BO6r32wc8hgMIYj6fJQFZq_q9YX4eUJjHqQ8cuYSeB4");
    	
    	
    User saveUser=userService.saveUser(user);
    
 Message message= Message.builder().content("Registration Successful").type(MessageType.green).build();     
      session.setAttribute("message",message);
    	
    	
        return "redirect:/register";
    }

}
