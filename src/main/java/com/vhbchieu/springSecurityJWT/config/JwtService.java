package com.vhbchieu.springSecurityJWT.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    //
    private static final String SECRET_KEY = "B4FEA1BCC5B424BD7689FE2FFE7FF4A234F6288C7704E3E50DA8D50290F32554EAD59358CC9AC027467C277AE9A0EECA7933A8C203BD1BB8A87500CEE707C6C150DCE062720B98BD707E3F6C4D07840030E37959057D566463894FEEBAD8AD350207249B08FEF399142B0C793F0E3786D02945FA56BD5F11CDC1D4CB1A868D79";

    public String extractUsername(String token) {
        return null;
    }

    /**
     * Lay claims tu token
     * @param token token jwt
     * @return <code>Claims</code>
     */
    private Claims extractClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())  // Là 1 khóa bí mật để ký váo JWT (dùng để tạo phần chữ kí cũng như xác minh người gửi
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Tạo Key để dùng cho các thuật toán ký tự từ SECRET_KEY
     * @return <code>Key</code> - Khóa dạng byte[]
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
