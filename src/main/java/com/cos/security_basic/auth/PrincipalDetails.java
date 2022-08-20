package com.cos.security_basic.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다.(Security ContextHolder)
//오브젝트 타입 => Authentication 타입 객체
//User오브젝트 타입 => UserDefails 타입 객체

import com.cos.security_basic.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Security Session => Authentication => UserDetails
public class PrincipalDetails implements UserDetails {

    private User user; // 콤포지션

    public PrincipalDetails(User user){
        this.user = user;
    }


    // 해당 User의 권한을 리턴하는 곳!!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //user.getRole()이 String이라 변환해야 됨

        Collection<GrantedAuthority> collect = new ArrayList<>();

        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    //이 계정 만료 되었는가? false 만료/ true 유효
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //잠겼는가
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //비밀번호를 오래 썼는가
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //활성화 여부
    public boolean isEnabled() {
        return true;
    }
}
