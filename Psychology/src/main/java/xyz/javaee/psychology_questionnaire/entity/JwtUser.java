package xyz.javaee.psychology_questionnaire.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xyz.javaee.psychology_questionnaire.utils.ResultCode;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Zexing Zhang
 * @date 2022/5/14 1:16 AM
 * @Description JWT实体类
 */
@Component
@Data
@AllArgsConstructor
public class JwtUser extends User implements UserDetails {

    private Integer id;
    private String telephone;
    private String password;
    private String token;
    private Boolean enabled;
    private Integer admin;
    private String name;
    private ResultCode status;//登录状态
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * 通过 user 对象创建jwtUser
     */
    public JwtUser(User user) {
        if(user==null){//用户为空时创建零时无效jwt用户
            id = -1;
            telephone = "-1";
            password = "-1";
            enabled = false;
            admin = -1;
            name="";
            List<SimpleGrantedAuthority> jwtauthorities = new ArrayList<>();
            authorities = jwtauthorities;
        }else{//有效jwt用户
            id = user.getUserid();
            telephone = user.getTelephone();
            password = user.getPassword();
            enabled = true;
            admin = user.getAdmin();
            authorities = this.getRoles();
            name=user.getName();
        }
    }

    private static xyz.javaee.psychology_questionnaire.entity.JwtUser jwtUser;


    /**
     * 在非controller和service中注入service和mapper需要下注解
     */
    @PostConstruct
    public void init() {
        jwtUser = this;
    }

    public List<SimpleGrantedAuthority> getRoles() {
        String role = "";
        if (this.admin == 1) {
            role = "ADMIN";//管理员权限
        } else {
            role = "USER";//用户权限
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

    /**
     * 重写UserDetails的方法 得到用户所拥有的权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return telephone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }
}
