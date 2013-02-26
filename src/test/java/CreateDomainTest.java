import utils.DeveloperUtil;

import java.io.File;


/**
 * @Author: caoxiao
 * @Date: 12-8-29 下午5:07
 */
public class CreateDomainTest {
    public static void main(String[] args) {
        /*SingleConnectionDataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost:3306/myJoke", "root", "admin", false);
        DeveloperUtil util = new DeveloperUtil();
        util.setDataSource(dataSource);
        util.setTableName("t_comments");
        util.generateEntityAndConfigure();*/

        System.out.println(new File(".").getAbsolutePath());
        DeveloperUtil util = new DeveloperUtil();
        util.setTableName("t_comments");
        util.generateCRUDByEntity("joke.domain.Comments");
    }
}
