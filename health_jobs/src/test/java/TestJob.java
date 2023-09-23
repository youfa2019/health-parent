import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName TestJob
 * @Description
 * @Author 传智播客
 * @Date 2019/7/6 16:24
 * @Version 1.0
 **/
public class TestJob {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-job.xml");

    }
}
