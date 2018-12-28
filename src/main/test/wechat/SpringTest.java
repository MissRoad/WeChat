package wechat;

import com.bing.SpringBootStartApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStartApplication.class)
@Slf4j
public class SpringTest {

    @Test
    public void test() {
        log.info("hello test!");
    }
}
