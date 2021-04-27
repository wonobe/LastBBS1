package dev.yhp.study.last_bbs.services;

import dev.yhp.study.last_bbs.enums.user.RegisterResult;
import dev.yhp.study.last_bbs.mappers.IUserMapper;
import dev.yhp.study.last_bbs.vos.user.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
//    public static class Regex{
//        public static final String EMAIL = "";
//        public static final String NICKNAME = "";
//        public static final String NAME_FIRST = "";
//        public static final String NAME_OPTIONAL = "";
//        public static final String NAME_LAST = "";
//        public static final String CONTACT_FIRST = "";
//        public static final String CONTACT_SECOND = "";
//        public static final String CONTACT_THIRD = "";
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
    }
}
