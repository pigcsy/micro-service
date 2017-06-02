package com.core.support.security;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.TypeReference;
import com.core.exception.InvalidTokenException;
import com.core.exception.LogicException;
import com.core.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.jwt.crypto.sign.Signer;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationConverter {
    final String SYSTEM_ID = "system_id";
    final String SYSTEM_TYPE = "system_type";
    final String EXP = "exp";
    final String UID = "uid";
    final String NAME = "name";
    final String ADDITIONAL = "additionalInfo";
    @Value("${internal_service_access_private_key:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJnofEAu7Gt/qCjSnPN+k/Vsfal/7fQ1N024XVBLk6KGVqk4O63DGp9rrNT9YxxiDEfU7OV4wYjUAKzbRCWt8nsAqeWwk32ff046w6SkliZdtYB+hPajOf7AL50+o1v+jtmFHZEoYDmG4BktWfnlc6+DggeX7ZRj+GfnQaHAJcgwIDAQAB}")
    String INTERNAL_SERVICE_ACCESS_PUBLIC_KEY;
    @Value("${internal_service_access_private_key:MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAImeh8QC7sa3+oKNKc836T9Wx9qX/t9DU3TbhdUEuTooZWqTg7rcMan2us1P1jHGIMR9Ts5XjBiNQArNtEJa3yewCp5bCTfZ9/TjrDpKSWJl21gH6E9qM5/sAvnT6jW/6O2YUdkShgOYbgGS1Z+eVzr4OCB5ftlGP4Z+dBocAlyDAgMBAAECgYA1eBG514XcUakURjKDX27RfC8kQD/3kNoBIjWqYS5yaTWWJgBdfqYRKlrD4IwRRLCAh0syIpO9Ckxipi+uRE5FM3M256427PrE2z7eg6wQyto3Qbxx4wVAydzE4IznTY6s4THXSgSgxkD8ohkZd85B59O7TVwUYYljlYlkAhcQ2QJBANJorL64TzgFhO4OessJZzsC2P0Q23OXTs6JpN3d+XNqM/7wayNbN+mvvUPrn7qfU/bZvjehAlvhzI8N+yhoe+8CQQCncDv1pJSXReAhLAq7+qfNUztkCr3A6W5zcs9Am+fnDWd4jsb7cZYoRqhpwqnUdZ7bwEGclpc/pREfWxYelyStAkEAu652+CA7OYNi0V3FFjqrBC5EE2fn+DFtMDoC/4yyp8qB2tPlHUKOmzoRHvacIcFhCf2r+L779a1x4+DauW6JrQJANsoGBMJfEQT/aztjI9HIeOqEY58UKnz39HUcFlMt1jBPrGZj/EFV1kYQs9LMCcaXPecyURAB3ASijrUajtX8XQJBAJVjF5tY/tH/Gzp8gBW/AB4O6gK+c562a1pQUBW5oX5b/m469NLioYl+2dplvnnufIyO8tkd8zrEwZf2qiD4ah4=}")
    String INTERNAL_SERVICE_ACCESS_PRIVATE_KEY;
    private Signer signer;

    private SignatureVerifier verifier;

    public static void main(String[] args) {
        AuthorizationConverter convert = new AuthorizationConverter();
        String INTERNAL_SERVICE_ACCESS_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJnofEAu7Gt/qCjSnPN+k/Vsfal/7fQ1N024XVBLk6KGVqk4O63DGp9rrNT9YxxiDEfU7OV4wYjUAKzbRCWt8nsAqeWwk32ff046w6SkliZdtYB+hPajOf7AL50+o1v+jtmFHZEoYDmG4BktWfnlc6+DggeX7ZRj+GfnQaHAJcgwIDAQAB";
        String INTERNAL_SERVICE_ACCESS_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAImeh8QC7sa3+oKNKc836T9Wx9qX/t9DU3TbhdUEuTooZWqTg7rcMan2us1P1jHGIMR9Ts5XjBiNQArNtEJa3yewCp5bCTfZ9/TjrDpKSWJl21gH6E9qM5/sAvnT6jW/6O2YUdkShgOYbgGS1Z+eVzr4OCB5ftlGP4Z+dBocAlyDAgMBAAECgYA1eBG514XcUakURjKDX27RfC8kQD/3kNoBIjWqYS5yaTWWJgBdfqYRKlrD4IwRRLCAh0syIpO9Ckxipi+uRE5FM3M256427PrE2z7eg6wQyto3Qbxx4wVAydzE4IznTY6s4THXSgSgxkD8ohkZd85B59O7TVwUYYljlYlkAhcQ2QJBANJorL64TzgFhO4OessJZzsC2P0Q23OXTs6JpN3d+XNqM/7wayNbN+mvvUPrn7qfU/bZvjehAlvhzI8N+yhoe+8CQQCncDv1pJSXReAhLAq7+qfNUztkCr3A6W5zcs9Am+fnDWd4jsb7cZYoRqhpwqnUdZ7bwEGclpc/pREfWxYelyStAkEAu652+CA7OYNi0V3FFjqrBC5EE2fn+DFtMDoC/4yyp8qB2tPlHUKOmzoRHvacIcFhCf2r+L779a1x4+DauW6JrQJANsoGBMJfEQT/aztjI9HIeOqEY58UKnz39HUcFlMt1jBPrGZj/EFV1kYQs9LMCcaXPecyURAB3ASijrUajtX8XQJBAJVjF5tY/tH/Gzp8gBW/AB4O6gK+c562a1pQUBW5oX5b/m469NLioYl+2dplvnnufIyO8tkd8zrEwZf2qiD4ah4=";
        convert.initSign(INTERNAL_SERVICE_ACCESS_PUBLIC_KEY, INTERNAL_SERVICE_ACCESS_PRIVATE_KEY);
        String principalStr = convert.serializePrincipal(new DefaultCurrentPrincipal(1, "a", 1, 1));
        System.out.println(principalStr);
        ;
        try {
            System.out.println(RSAUtils.decryptByPrivateKey(principalStr, INTERNAL_SERVICE_ACCESS_PRIVATE_KEY));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjEsInN5c3RlbV90eXBlIjoxLCJzeXN0ZW1faWQiOjEsIm5hbWUiOiJhIiwiZXhwIjoxNDkzOTQ3MjA1NzQ5fQ.M_Y8mH7S7pVcCgE9dZuA6Vi1Qrmyk05jiiOfO8qY6yWFlvyLdSmu9yeocnsgDR8iSMzYtpJTySRBYxX8rmAxaUHjZ_hQuZ8E3_Qv9MRI8XoNstlufj5EXtqknD1CpZDa1RXdfZRPlBDIl0_ZY_nsjpZqzffOBE9wWRgySRY9ezo


        System.err.println(convert.decode(principalStr).toString());
    }

    public void initSign(String pubKey, String priKey) {
        INTERNAL_SERVICE_ACCESS_PRIVATE_KEY = priKey;
        INTERNAL_SERVICE_ACCESS_PUBLIC_KEY = pubKey;
        initSign();
    }

    @PostConstruct
    public void initSign() {
        try {
            PrivateKey privateKey = RSAUtils.getPrivate(RSAUtils.SIGN_ALGORITHM, INTERNAL_SERVICE_ACCESS_PRIVATE_KEY);
            RSAPublicKey publicKey = (RSAPublicKey) RSAUtils.getPublicKey(RSAUtils.SIGN_ALGORITHM, INTERNAL_SERVICE_ACCESS_PUBLIC_KEY);
            signer = new RsaSigner((RSAPrivateKey) privateKey);
            verifier = new RsaVerifier(publicKey);
        } catch (Exception e) {
            throw new LogicException("初始化签名失败", e);
        }

    }

    protected String encode(Map<String, Object> response) {
        String content;
        try {
            content = JSON.toJSONString(response);
        } catch (Exception e) {
            throw new LogicException("Cannot convert access token to JSON", e);
        }
        String token = JwtHelper.encode(content, signer).getEncoded();
        return token;
    }

    public String serializePrincipal(DefaultCurrentPrincipal principal) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put(NAME, principal.getName());
        response.put(SYSTEM_ID, principal.getSystemId());
        response.put(SYSTEM_TYPE, principal.getSystemType());
        response.put(UID, principal.getUid());
        response.put(EXP, new Date());
        response.put(ADDITIONAL, principal.getAdditionalInfo());
        return encode(response);
    }

    /**
     * 提取授权用户信息
     *
     * @param map
     * @return
     */
    public DefaultCurrentPrincipal convertPrincipal(String principalStr) {
        Map<String, Object> map = decode(principalStr);
        DefaultCurrentPrincipal principal = new DefaultCurrentPrincipal();
        Date exp = new Date((Long) map.get(EXP) * 1000L + 1000L * 10);
        if (exp.before(new Date())) {
            throw new LogicException("授权超时");
        }
        principal.setName(map.get(NAME) != null ? map.get(NAME).toString() : "未知");
        principal.setSystemId(Integer.parseInt(map.get(SYSTEM_ID).toString()));
        principal.setSystemType(Integer.parseInt(map.get(SYSTEM_TYPE).toString()));
        principal.setUid(Integer.parseInt(map.get(UID).toString()));
        principal.setAdditionalInfo(map.get(ADDITIONAL) == null ? new HashMap<>() : JSON.parseObject(map.get(ADDITIONAL).toString(), new TypeReference<Map<String, String>>() {
        }));
        return principal;
    }

    protected Map<String, Object> decode(String token) {
        try {
            Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
            String content = jwt.getClaims();
            Map<String, Object> map = JSON.parseObject(content);
            if (map.containsKey(EXP) && map.get(EXP) instanceof Integer) {
                Integer intValue = (Integer) map.get(EXP);
                map.put(EXP, new Long(intValue));
            }
            return map;
        } catch (Exception e) {
            throw new InvalidTokenException("Cannot convert access token to JSON", e);
        }
    }

}
