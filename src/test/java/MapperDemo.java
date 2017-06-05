import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatisstudy.mapper.UserMapper;
import org.mybatisstudy.vo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 使用mapper模式示例
 */
public class MapperDemo {
    private SqlSession session;

    @Before
    public void setUp() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        session = sqlSessionFactory.openSession();
    }

    @After
    public void close() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testFindUserById() {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserById(10);
        System.out.println(user.getUsername());
    }

    @Test
    public void testFindUserByArray() {
        UserMapper userMapper = session.getMapper(UserMapper.class);

        Object[] ary = new Object[2];
        ary[0] = 10;
        ary[1]= 16;

        List<User> userList = userMapper.selectUserByArray(ary);
        for (User u : userList) {
            System.out.println(u.getUsername());
        }
    }
}
