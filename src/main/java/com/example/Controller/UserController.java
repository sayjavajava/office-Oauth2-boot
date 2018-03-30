package com.example.Controller;

import com.example.Entities.Authority;
import com.example.Entities.Permission;
import com.example.Entities.User;
import com.example.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bycrptpasswordencoder;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){

        List<Authority>  list= userService.findById(1);
        user.setPassword(bycrptpasswordencoder.encode(user.getPassword()));
        user.setAuthorities(list);

        logger.info("teting:"+ user.getAddress().getCity());

        userService.save(user);
     return user;
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        logger.info("token:logout" + authHeader);
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            logger.info("token:logout" + accessToken);
            tokenStore.removeAccessToken(accessToken);
        }
    }

    @DeleteMapping(path={"user/{id}"})
    public User delete(@PathVariable("id") long id) {
        return userService.DeleteProduct(id);
    }
   /* @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        userService.DeleteProduct(id);
        return "success";
    }*/

    @RequestMapping(value="roles/all", method = RequestMethod.GET)
    public List<Authority> listRoles(){
        return userService.allRoles();
    }
    @PostMapping ("/addroles")
    public void saveRole(@RequestBody Authority name){
        userService.saveRole(name);
    }
    @PostMapping ("/addpermissions")
    public void savePersmission(@RequestBody Permission permission){
        userService.savePermissions(permission);
    }


}
