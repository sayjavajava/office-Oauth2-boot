package com.example.Controller;

import com.example.Entities.Authority;
import com.example.Entities.User;
import com.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bycrptpasswordencoder;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){

        List<Authority>  list= userService.findById(1);
        user.setPassword(bycrptpasswordencoder.encode(user.getPassword()));
        user.setAuthorities(list);
        userService.save(user);
     return user;
    }

    @DeleteMapping(path={"/{id}"})
    public User delete(@PathVariable("id") long id) {
        return userService.DeleteProduct(id);
    }
   /* @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        userService.DeleteProduct(id);
        return "success";
    }*/

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<Authority> listRoles(){
        return userService.allRoles();
    }



}
