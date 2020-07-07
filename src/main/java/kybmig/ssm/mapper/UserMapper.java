package kybmig.ssm.mapper;

import kybmig.ssm.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    void insertUser(UserModel user);

    List<UserModel> selectAllUser();

    UserModel selectUser(int id);

    UserModel selectOneByUsername(String username);

    void updatePassword(UserModel user);

    void updateUserNameAndNote(UserModel user);

    void deleteUser(int id);

    void updateAvatar(UserModel user);

    void updateByAdmin(UserModel user);
}
