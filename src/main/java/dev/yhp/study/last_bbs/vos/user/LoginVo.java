package dev.yhp.study.last_bbs.vos.user;

import dev.yhp.study.last_bbs.utils.CryptoUtil;

public class LoginVo {
    private final String email;
    private final String password;
    private final String hashedPassword;

    private boolean autoSign;

    public LoginVo(String email, String password) {
        this.email = email;
        this.password = password;
        this.hashedPassword = CryptoUtil.Sha512.hash(password, null);
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public boolean isAutoSign() {
        return this.autoSign;
    }

    public void setAutoSign(boolean autoSign) {
        this.autoSign = autoSign;
    }
}
