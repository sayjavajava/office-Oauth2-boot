package com.example.Services;

import com.example.Repositories.AuthorityRepo;
import com.example.Repositories.UserDao;
import com.example.Entities.Authority;
import com.example.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service(value ="userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityRepo authorityRepo;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userId);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

      //  logger.info("loadd:" + userId +"roles:"+ role);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getAuthorities()));
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Authority> authlist) {
         return  authlist.stream()
                     .map(x->new SimpleGrantedAuthority(x.getName()))
                     .collect(Collectors.toList());
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public User DeleteProduct(long id ){

        User user = findOne(id);
        if(user !=null) {
            userDao.delete(user);}
        return user;
    }

    public User findOne(long id){
        return userDao.findById(id);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public List<Authority> findById(int id){

        Authority obj = authorityRepo.findById(id);
        List<Authority> list = new ArrayList<>();
        list.add(obj);
        System.out.println("dataa:"+ obj.getName());
        return list;
	}

	public List<Authority> allRoles(){
        return authorityRepo.findAll();
    }
}
