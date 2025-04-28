package com.thoaidev.bookinghotel.controller.auth;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thoaidev.bookinghotel.dto.RegisterDto;
import com.thoaidev.bookinghotel.exceptions.GlobalExceptionHandler;
import com.thoaidev.bookinghotel.model.UserEntity;
import com.thoaidev.bookinghotel.repository.RoleRepository;
import com.thoaidev.bookinghotel.repository.UserRepository;
import com.thoaidev.bookinghotel.security.jwt.JWTAuthenticationFilter;
import com.thoaidev.bookinghotel.security.jwt.JWTGenerator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.thoaidev.bookinghotel.dto.AuthResponseDto;
import com.thoaidev.bookinghotel.dto.LoginDto;
import com.thoaidev.bookinghotel.model.Role;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final GlobalExceptionHandler globalExceptionHandler;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;


    @Autowired
    public AuthController(  AuthenticationManager authenticationManager,
                            PasswordEncoder passwordEncoder, 
                            UserRepository userRepository, 
                            RoleRepository roleRepository,
                            JWTGenerator jwtGenerator, GlobalExceptionHandler globalExceptionHandler) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtGenerator = jwtGenerator;
        this.globalExceptionHandler = globalExceptionHandler;
    }
    @PostMapping("dang-nhap")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        System.out.println("==LOGIN CONTROLLER METHOD ĐƯỢC GỌI==");
            System.out.println("==println(AuthController)Username: " + loginDto.getUsername());
            System.out.println("==println(AuthController)Password: " + loginDto.getPassword());
// //Option1:       
//             SecurityContext context = SecurityContextHolder.createEmptyContext();
//         Authentication authentication = 
//         authenticationManager.authenticate(
//             new  UsernamePasswordAuthenticationToken
//         (loginDto.getUsername(),
//          loginDto.getPassword()));
//         context.setAuthentication(authentication);
//         SecurityContextHolder.setContext(context);

//Option2:
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken
            (loginDto.getUsername(),
             loginDto.getPassword())
             );
             System.out.println("== login() authentication success!");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);

    }
    @PostMapping("dang-ky")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        System.out.println("==REGISTER CONTROLLER METHOD ĐƯỢC GỌI==");
        if(userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Email hoặc số điện thoại đã tồn tại!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setFullname(registerDto.getFullname());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));// Mã hóa password
        
            Optional<Role> roleOptional = roleRepository.findByRoleName("OWNER");
            if (roleOptional.isEmpty()) {
            return new ResponseEntity<>("Không tìm thấy vai trò USER", HttpStatus.BAD_REQUEST);
            }
            Role role = roleOptional.get();
        user.setRoles(Collections.singletonList(role));
        
        userRepository.save(user);
        return new ResponseEntity<>("Đăng ký người dùng mới thành công!!", HttpStatus.OK);
    }
    


}
