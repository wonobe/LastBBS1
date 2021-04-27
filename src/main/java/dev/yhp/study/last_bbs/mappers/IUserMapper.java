package dev.yhp.study.last_bbs.mappers;

import dev.yhp.study.last_bbs.vos.user.RegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    int selectEmailCount(@Param("email") String email);
    int selectNicknameCount(@Param("nickname") String nickname);
    int selectContactCount(
            @Param("contactFirst") String contactFirst,
            @Param("contactSecond") String contactSecond,
            @Param("contactThird") String contactThird);

    void insertUser(RegisterVo registerVo);
}
