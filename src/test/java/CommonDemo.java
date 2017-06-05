import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatisstudy.vo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class CommonDemo {
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
    public void testSelect() throws IOException {
        List<User> users = session.selectList("test.findUsersByName", "张");
        System.out.println(users.size());
    }

    @Test
    public void testInsert() {
        User u = new User();
        u.setUsername("xx");
        u.setBirthday(new Date());
        session.insert("test.insertUser", u);
        session.commit();
    }

    @Test
    public void testUpdate() {
        User user = session.selectOne("test.findUserById", 34);
        user.setUsername("xy");
        session.update("test.updateUser", user);
        session.commit();
    }

    @Test
    public void testDel() {
        session.delete("test.deleteUser", 34);
        session.commit();
    }
}
