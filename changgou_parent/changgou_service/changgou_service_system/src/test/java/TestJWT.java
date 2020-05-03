import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

/**
 * @author chayedan666
 * @version 1.0
 * @className: TestJWT
 * @description:
 * @date: 2020/5/3
 */
public class TestJWT {
    private final static String keywords = "chayedan";

    @Test
    public void testCreatJWT(){
        JwtBuilder jwtBuilder = Jwts.builder().setId(UUID.randomUUID().toString())
                .setSubject("茶叶蛋")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, keywords);
        System.out.println(jwtBuilder.compact());
    }

    @Test
    public void testJWTjiemi(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjMTBkZWRhYS1hYzIyLTRiYzYtOGQ4MC01NjVmZDZlZGQ5ZGEiLCJzdWIiOiLojLblj7bom4siLCJpYXQiOjE1ODg0ODY0OTN9.hknx1lr6yyKJu-HCVvcuViTnFxFG4PIfW4GalGgKIRI";
        JwsHeader header = Jwts.parser().setSigningKey(keywords).parseClaimsJws(jwt).getHeader();
        Claims body = Jwts.parser().setSigningKey(keywords).parseClaimsJws(jwt).getBody();
        String signature = Jwts.parser().setSigningKey(keywords).parseClaimsJws(jwt).getSignature();
        System.out.println(header);
        System.out.println(body);
        System.out.println(signature);
    }
}
