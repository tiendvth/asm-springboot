package fpt.aptech.asmspringboot.seeder;

import fpt.aptech.asmspringboot.entity.dto.UserRegisterDto;
import fpt.aptech.asmspringboot.entity.enums.AccountRole;
import fpt.aptech.asmspringboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeeder {
    final AccountService accountService;

    public void generate() {
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .fullName("Dang Tien")
                .email("dangtien119@gmail.com")
                .phone("0357444666")
                .username("dangtien6789")
                .address("Hanoi")
                .role(AccountRole.ADMIN)
                .password("123456")
                .build();
        accountService.register(userRegisterDto);
    }
}
