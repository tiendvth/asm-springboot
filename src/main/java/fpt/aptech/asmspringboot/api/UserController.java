package fpt.aptech.asmspringboot.api;

import fpt.aptech.asmspringboot.entity.dto.UserRegisterDto;
import fpt.aptech.asmspringboot.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(accountService.register(userRegisterDto));
    }
}
