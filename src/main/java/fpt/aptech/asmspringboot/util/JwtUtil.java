package fpt.aptech.asmspringboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.sun.javafx.scene.traversal.Algorithm;
import fpt.aptech.asmspringboot.entity.Account;
import fpt.aptech.asmspringboot.entity.enums.AccountRole;
import org.springframework.security.core.userdetails.User;

import java.util.Date;

public class JwtUtil {
    public static Algorithm algorithm;
    private static JWTVerifier verifier;
    /*this is jwt secret key use to encode jwt token only backend server hold this key
     * if an attacker know this key his can modify jwt token in the right way to grant access to api
     */
    public static String JWT_SECRET_KEY = "secret";
    //some time units constant
    public static final int ONE_SECOND = 1000;
    public static final int ONE_MINUTE = ONE_SECOND * 60;
    public static final int ONE_HOUR = ONE_MINUTE * 60;
    public static final int ONE_DAY = ONE_HOUR * 24;
    public static final String ROLE_CLAIM_KEY = "role";
    private static final String DEFAULT_ISSUER = "VANTIEN";

    public static Algorithm getAlgorithm() {
        if (algorithm == null) {
            algorithm = Algorithm.HMAC256(JWT_SECRET_KEY.getBytes());
        }
        return algorithm;
    }

    public static JWTVerifier getVerifier() {
        if(verifier == null) {
            verifier = JWT.require((com.auth0.jwt.algorithms.Algorithm) getAlgorithm()).build();
        }
        return verifier;
    }

    public static DecodedJWT getDecodedJwt(String token){
        DecodedJWT decodedJWT = getVerifier().verify(token);
        return decodedJWT;
    }

    public static String generateToken(String subject, String role, String issuer, int expireAfter) {
        if(role == null || role.length() == 0) {
            return JWT.create()
                    .withSubject(subject)
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireAfter))
                    .withIssuer(issuer)
                    .sign((com.auth0.jwt.algorithms.Algorithm) getAlgorithm());
        }
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireAfter))
                .withIssuer(issuer)
                // when role n -> n user
                //.withClaim(JwtUtil.ROLE_CLAIM_KEY, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                //when role n -> 1 user
                .withClaim(JwtUtil.ROLE_CLAIM_KEY, role) //get first role in Authorities
                .sign((com.auth0.jwt.algorithms.Algorithm) getAlgorithm());
    }

    public static String generateTokenV2(Account user, int expireAfterDay) {
        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expireAfterDay * ONE_DAY))
                .withIssuer(DEFAULT_ISSUER)
                .withClaim(JwtUtil.ROLE_CLAIM_KEY, user.getRole() == AccountRole.ADMIN ? "ADMIN" : "USER")
                .withClaim("username", user.getUsername())
                .sign((com.auth0.jwt.algorithms.Algorithm) getAlgorithm());
    }
}