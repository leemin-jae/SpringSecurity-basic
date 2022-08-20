package com.cos.security_basic.controller;


import com.cos.security_basic.model.User;
import com.cos.security_basic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;




    @GetMapping({ "", "/" })
    public @ResponseBody
    String index() {
        return "index";
    }

//    @GetMapping("/user")
//    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principal) {
//        System.out.println("Principal : " + principal);
//        // iterator 순차 출력 해보기
//        Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
//        while (iter.hasNext()) {
//            GrantedAuthority auth = iter.next();
//            System.out.println(auth.getAuthority());
//        }
//
//        return "유저 페이지입니다.";
//    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "어드민 페이지입니다.";
    }

    //@PostAuthorize("hasRole('ROLE_MANAGER')")
    //@PreAuthorize("hasRole('ROLE_MANAGER')")
    //@Secured("ROLE_MANAGER")
    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "매니저 페이지입니다.";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/join")
    public @ResponseBody String join( User user) {
        user.setRole("ROLE_USER");
        String rawRassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawRassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }

//    @PostMapping("/joinProc")
//    public String joinProc(User user) {
//        System.out.println("회원가입 진행 : " + user);
//        String rawPassword = user.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        user.setPassword(encPassword);
//        user.setRole("ROLE_USER");
//        userRepository.save(user);
//        return "redirect:/";
//    }

}
