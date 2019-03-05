import com.baidu.shiro.domain.User;
import com.baidu.shiro.service.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author: WH
 * @Date: 2019/3/1 15:59
 * @Version 1.0
 */
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class Test {

    private int[] arr = {12, 2, 23, 34, 32, 44, 324, 22, 9};

    @Resource
    private UserService userService;

    @org.junit.Test
    public void test() {
        User user = userService.getUserByUserName("Mark");
        System.out.println(user.toString());
    }

    @org.junit.Test
    public void Sort() {
        int flag = 0;
        for (int i = 0; i < arr.length - 1 && flag == 0; i++) {
            flag = 1;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = 0;
                }
            }
        }
        for (int s : arr) {
            System.out.print(s + " ");
        }

    }
}
