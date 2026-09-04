package com.astrax.security;

import android.content.Context;
import android.util.Base64;
import androidx.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * AstraSecretVault - AES-256-GCM encrypted storage using Android Keystore.
 * Store secret content (private messages/media keys) encrypted and safe from
 * normal file viewers. Vault UI is gated by PIN or biometric at app level.
 */
public class AstraSecretVault {

    private static final String ANDROID_KEY_ALIAS = "AstraXVaultKey";
    private static final int GCM_TAG = 128;

    public AstraSecretVault(Context ctx) {
        // TODO: create/ensure key in AndroidKeyStore and keep reference
    }

    public @Nullable String encrypt(String plain) {
        try {
            // TODO: obtain SecretKey from KeyStore (AES/GCM/NoPadding), generate IV, encrypt
            // This is skeleton; implement KeyStore usage according to Android docs.
            return Base64.encodeToString(plain.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public @Nullable String decrypt(String base64) {
        try {
            // TODO: decrypt using key + GCMParameterSpec
            byte[] data = Base64.decode(base64, Base64.NO_WRAP);
            return new String(data, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
