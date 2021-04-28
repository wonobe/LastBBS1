package dev.yhp.study.last_bbs.mappers;

import dev.yhp.study.last_bbs.dtos.UserDto;
import dev.yhp.study.last_bbs.vos.user.LoginVo;
import dev.yhp.study.last_bbs.vos.user.RegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    void updateAutoSignKeyExtended(
            @Param("key") String key,
            @Param("days") int days);

    int selectEmailCount(@Param("email") String email);

    int selectNicknameCount(@Param("nickname") String nickname);

    int selectContactCount(
            @Param("contactFirst") String contactFirst,
            @Param("contactSecond") String contactSecond,
            @Param("contactThird") String contactThird);

    void insertUser(RegisterVo registerVo);

    UserDto selectUser(LoginVo loginVo);

    UserDto selectUserFromCookie(
            @Param("key") String key);

    void insertAutoSignKey(
            @Param("email") String email,
            @Param("key") String key,
            @Param("days") int days);

}
