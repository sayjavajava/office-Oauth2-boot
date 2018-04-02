package com.example.Services;

import com.example.Entities.Permission;
import com.example.Repositories.AuthorityRepo;
import com.example.Repositories.PermissionRepo;
import com.example.Repositories.UserDao;
import com.example.Entities.Authority;
import com.example.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service(value ="userService")
@Transactional
public class UserService implements UserDetailsService {


    private UserDao userDao;


    private AuthorityRepo authorityRepo;


    private PermissionRepo permissionRepo;


    UserService(UserDao userDao,AuthorityRepo authorityRepo,PermissionRepo permissionRepo){
        this.authorityRepo=authorityRepo;
        this.permissionRepo=permissionRepo;
        this.userDao=userDao;
    }

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userId);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

      //  logger.info("loadd:" + userId +"roles:"+ role);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),true,true,true,true, getAuthorities(user.getAuthorities()));
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Authority> authlist) {
         return  authlist.stream()
                     .map(x->new SimpleGrantedAuthority(x.getName()))
                     .collect(Collectors.toList());
    }

    //testing
    private Collection<? extends GrantedAuthority> getAuthorities(
            List<Authority> roles) {

        List<String> perm = getPrivileges(roles);
        return getGrantedAuthorities(perm);
    }

  private List<String>  getPrivileges(List<Authority> roles){
      List<String> privileges = new ArrayList<>();
      List<Permission> collection = new ArrayList<>();
      for (Authority role : roles) {
          collection.addAll(role.getPermissions());
      }
      for (Permission item : collection) {
          privileges.add(item.getName());

      }
      logger.info("i am called privi");
      return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    //end


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

    public void saveRole(Authority role){
          role.setName("ROLE_"+role.getName().toUpperCase());
	     authorityRepo.save(role);
    }
    public void savePermissions(Permission permission){
        permission.setName("MANAGE_"+permission.getName().toUpperCase());
        permissionRepo.save(permission);
    }

}
