package demo.aspectj.service;

import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author chenxf
 * @since 2020/9/17
 */
@Service
public class AService {

    public void save() {
        System.out.println("保存数据");
    }
}
