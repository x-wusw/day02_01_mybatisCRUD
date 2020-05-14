package xiaowu.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xiaowu.dao.IUserDao;
import xiaowu.domain.QueryVo;
import xiaowu.domain.User;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private IUserDao iUserDao;
    @Before
    public void init() throws Exception{
        //  1.读取配置文件，生成字节流文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //   2.获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //   3.获取sqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        //   4.获取dao代理对象
        iUserDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void destory() throws Exception{
        //提交事务
        sqlSession.commit();
        //释放资源
        inputStream.close();
        sqlSession.close();

    }
    @Test
    public void TestFindAll() throws Exception{

     //  5.执行查询方法
        List<User> users = iUserDao.findALL();
        for(User user:users){
            System.out.println(user);
        }
    }
    @Test
    //保存方法
    public void saveTest(){
        User user = new User();
        user.setUsername("Tom");
        user.setSex("男");
        user.setAddress("美国" );
        user.setBirthday(new Date());
        System.out.println("保存操作之前"+user);
        //执行方法
        iUserDao.saveUser(user);
        System.out.println("保存操作之后"+user);

    }

    @Test
    //更新方法
    public void updatTest(){
        User user = new User();
        user.setId(41);
        user.setUsername("小红");
        user.setSex("女");
        user.setAddress("湖南张沙" );
        user.setBirthday(new Date());
        //执行方法
        iUserDao.updateUser(user);

    }

    @Test
    //删除方法
    public void deleteTest(){
        //执行方法
        iUserDao.deleteUser(42);

    }
    //根据id查询用户
    @Test
    public void findById(){
        User user = iUserDao.findById(45);
        System.out.println(user);
    }
    //根据名称模糊查询
    @Test
    public void findByName(){
        List<User> user = iUserDao.findByName("%李%");
        for(User user1:user){
            System.out.println(user1);
        }
    }
    @Test
    //获取用户记录总数
    public void findTotal(){
        int count = iUserDao.findTotal();
        System.out.println(count);

    }
    @Test
    //通过queryvo语句查询
    public void findByVo(){
        User user = new User();
        QueryVo queryVo = new QueryVo();
        user.setUsername("%李%");
        queryVo.setUser(user);
        List<User> users = iUserDao.findByVo(queryVo);
        for(User user1:users){
            System.out.println(user1);
        }


    }

}
