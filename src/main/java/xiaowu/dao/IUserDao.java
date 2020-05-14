package xiaowu.dao;

import xiaowu.domain.QueryVo;
import xiaowu.domain.User;

import java.util.List;

public interface IUserDao {
    //查询所有用户
    List<User> findALL();
    //保存用户
    void saveUser(User user);
    //更新用户
    void updateUser(User user);
    //删除用户
    void deleteUser(Integer id);
    //根据id查询用户
    User findById(int id);
    //根据名称模糊查询
    List<User> findByName(String username);
    //获取用户总记录条数
    int findTotal();
    //使用queryvo作为查询语句
    List<User> findByVo(QueryVo queryVo);
}
