import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatisstudy.vo.User;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDemo {

    @Test
    public void simpleTest()  throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sf.openSession();
        User user = session.selectOne("test.findUserById", 10);
        System.out.println(user.getUsername());
    }
}
