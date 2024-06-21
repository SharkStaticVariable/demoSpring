package com.example.demo.service;

import com.example.demo.entity.KeysEntity;
import com.example.demo.repository.KeysRepository;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EncryptDecryptService {

    @Autowired
    public KeysRepository keysRepository;

    public static Map<String, Object> map = new HashMap<>();

    @PostConstruct
    public void init() {
        // Load keys from database if they exist
        KeysEntity keysEntity = keysRepository.findById(1).orElse(null);
        if (keysEntity != null) {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");

                byte[] publicKeyBytes = keysEntity.getPublicKey();
                X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
                PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

                byte[] privateKeyBytes = keysEntity.getPrivateKey();
                PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
                PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

                map.put("publicKey", publicKey);
                map.put("privateKey", privateKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            createKeys();
        }
    }

    public void createKeys() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            map.put("publicKey", publicKey);
            map.put("privateKey", privateKey);

            KeysEntity keysEntity = KeysEntity.builder()
                    .publicKey(publicKey.getEncoded())
                    .privateKey(privateKey.getEncoded())
                    .build();

            keysRepository.save(keysEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encryptMessage(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
            PublicKey publicKey = (PublicKey) map.get("publicKey");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypt = cipher.doFinal(plainText.getBytes());
            return new String(Base64.getEncoder().encodeToString(encrypt));
        } catch (Exception e) {

        }
        return "";
    }

    public String decryptMessage(String encryptedMessgae) {

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
            PrivateKey privateKey = (PrivateKey) map.get("privateKey");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptedMessgae));
            return new String(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}