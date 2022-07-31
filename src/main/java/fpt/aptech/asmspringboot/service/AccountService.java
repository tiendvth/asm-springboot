package fpt.aptech.asmspringboot.service;

import fpt.aptech.asmspringboot.entity.Account;
import fpt.aptech.asmspringboot.entity.Credential;
import fpt.aptech.asmspringboot.entity.dto.UserLoginDto;
import fpt.aptech.asmspringboot.entity.dto.UserRegisterDto;
import fpt.aptech.asmspringboot.entity.enums.AccountRole;
import fpt.aptech.asmspringboot.entity.enums.AccountStatus;
import fpt.aptech.asmspringboot.repository.AccountRepository;
import fpt.aptech.asmspringboot.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    final AccountRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public UserRegisterDto register(UserRegisterDto userRegisterDto) {
        Optional<Account> optionalUser = userRepository.findByUsername(userRegisterDto.getUsername());
        if (optionalUser.isPresent()) {
            return null;
        }
        Account account = Account.builder()
                .fullName(userRegisterDto.getFullName())
                .email(userRegisterDto.getEmail())
                .phone(userRegisterDto.getPhone())
                .address(userRegisterDto.getAddress())
                .avatar(userRegisterDto.getAvatar())
                .username(userRegisterDto.getUsername())
                .role(AccountRole.USER)
                .status(AccountStatus.ACTIVE)
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .build();
        userRepository.save(account);
        UserRegisterDto userDto = UserRegisterDto.builder()
                .fullName(userRegisterDto.getFullName())
                .email(userRegisterDto.getEmail())
                .phone(userRegisterDto.getPhone())
                .address(userRegisterDto.getAddress())
                .avatar(userRegisterDto.getAvatar())
                .username(userRegisterDto.getUsername())
                .role(AccountRole.USER)
                .id(account.getId())
                .build();
        return userDto;
    }

    public Credential login(UserLoginDto userLoginDto) {
        Account account = (Account) loadUserByUsername(userLoginDto.getUsername());
        boolean isMatched = passwordEncoder.matches(userLoginDto.getPassword(), account.getPassword());
        Optional<Account> optionalUser = userRepository.findByUsername(userLoginDto.getUsername());
        if(optionalUser.isPresent()) {
            Account optionUser = optionalUser.get();
            int expiredAfterDay = 7;
            String accessToken = JwtUtil.generateTokenV2(optionUser, expiredAfterDay );
            String refreshToken = JwtUtil.generateTokenV2(optionUser, 14);
            return Credential.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expiredAt(System.currentTimeMillis() + expiredAfterDay * 24 * 60 * 60 * 60)
                    .scope("basic_user_info")
                    .build();
        }
        throw  new UsernameNotFoundException("User not found");
      /*  if(isMatched) {
            JwtUtil.generateToken(user.get);
        }*/
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        Account user = optionalUser.get();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole() == AccountRole.ADMIN ? "ADMIN" : "USER");
        grantedAuthorityList.add(simpleGrantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorityList);
//        return new MyUserPrincipal(user.get());
    }
}
