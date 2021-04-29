package dev.yhp.study.last_bbs.services;

import dev.yhp.study.last_bbs.dtos.UserDto;
import dev.yhp.study.last_bbs.enums.user.LoginResult;
import dev.yhp.study.last_bbs.enums.user.ModifyResult;
import dev.yhp.study.last_bbs.enums.user.RegisterResult;
import dev.yhp.study.last_bbs.mappers.IUserMapper;
import dev.yhp.study.last_bbs.utils.CryptoUtil;
import dev.yhp.study.last_bbs.vos.user.LoginVo;
import dev.yhp.study.last_bbs.vos.user.ModifyVo;
import dev.yhp.study.last_bbs.vos.user.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    public static class Config {
        public static final int AUTO_SIGN_KEY_HASH_COUNT = 10;
        public static final int AUTO_SIGN_VALID_DAYS = 7;
    }

//    public static class Regex {
//        public static final String EMAIL = "";
//        public static final String NICKNAME = "";
//        public static final String NAME_FIRST = "";
//        public static final String NAME_OPTIONAL = "";
//        public static final String NAME_LAST = "";
//        public static final String CONTACT_FIRST = "";
//        public static final String CONTACT_SECOND = "";
//        public static final String CONTACT_THIRD = "";
//
//        public static final String AUTO_SIGN_KEY = "^([0-9a-z]{128})$";
//    }

    private final IUserMapper userMapper;

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void register(RegisterVo registerVo) {
        if (this.userMapper.selectEmailCount(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_EMAIL);
            return;
        }
        if (this.userMapper.selectNicknameCount(registerVo.getNickname()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_NICKNAME);
            return;
        }
        if (this.userMapper.selectContactCount(
                registerVo.getContactFirst(),
                registerVo.getContactSecond(),
                registerVo.getContactThird()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_CONTACT);
            return;
        }

        this.userMapper.insertUser(registerVo);
        registerVo.setResult(RegisterResult.SUCCESS);
    }

    public void extendAutoSignKeyCookie(Cookie autoSignKeyCookie) {
        this.userMapper.updateAutoSignKeyExtend(Config.AUTO_SIGN_VALID_DAYS, autoSignKeyCookie.getValue());
    }

    public void expireAutoSignKeyCookie(Cookie autoSignKeyCookie) {
        this.userMapper.updateAutoSignKeyExpire(autoSignKeyCookie.getValue());
    }

    public void login(LoginVo loginVo) {
        UserDto userDto = this.userMapper.selectUser(loginVo);
        if (userDto == null) {
            loginVo.setResult(LoginResult.FAILURE);
            return;
        }
        if (userDto.getLevel() == 10) {
            loginVo.setResult(LoginResult.UNAVAILABLE);
            return;
        }
        loginVo.setResult(LoginResult.SUCCESS);
        loginVo.setUser(userDto);
    }

    public UserDto login(Cookie autoSignKeyCookie) {
        UserDto user = this.userMapper.selectUserFromCookie(autoSignKeyCookie.getValue());
        if (user == null || user.getLevel() == 10) {
            return null;
        }
        return user;
    }

    public void putAutoSignKey(UserDto user) {
        String key = String.format("%s%s%s%f",
                user.getEmail(),
                user.getNickname(),
                new SimpleDateFormat("yyyyMMdd").format(new Date()),
                Math.random());
        for (int i = 0; i < Config.AUTO_SIGN_KEY_HASH_COUNT; i++) {
            key = CryptoUtil.Sha512.hash(key, null);
        }
        this.userMapper.insertAutoSignKey(user.getEmail(), key, Config.AUTO_SIGN_VALID_DAYS);
        user.setAutoSignKey(key);
    }

    public void modify(ModifyVo modifyVo) {

        if (this.userMapper.selectNicknameCount(modifyVo.getNickname()) > 0) {
            if (!(modifyVo.getUser().getNickname().equals(modifyVo.getNickname()))){
                modifyVo.setResult(ModifyResult.DUPLICATE_NICKNAME);
                return;
            }
        }
        this.userMapper.updateUser(modifyVo);
        modifyVo.setResult(ModifyResult.SUCCESS);
    }

}
