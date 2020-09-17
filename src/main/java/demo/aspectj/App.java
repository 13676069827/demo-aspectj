package demo.aspectj;

import demo.aspectj.service.AService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 *
 * </p>
 *
 * @author chenxf
 * @since 2020/9/17
 */
public class App {

    /**
     * tips  一阶段
     * Spring AOP 与 AspectJ的关系
     * @EnableAspectJAutoProxy    (doc : https://docs.spring.io/spring-framework/docs/5.1.18.RELEASE/spring-framework-reference/core.html#aop)
     * 编写注解  考虑@Retention, @Target  （doc : https://docs.spring.io/spring-framework/docs/5.1.18.RELEASE/spring-framework-reference/core.html#aop-choosing)
     * 编写切面 @Aspect (doc: https://docs.spring.io/spring-framework/docs/5.1.18.RELEASE/spring-framework-reference/core.html#aop-advice)
     *
     * 切面业务逻辑不考虑分布式, 可以利用flag标志下正在执行 (原子类 AtomicXXX)
     *
     * Thread-A ->           ->  设flag成功 -> 执行业务逻辑
     * Thread-B ->     切面  ->  设flag失败 -> 终止
     *
     *
     * 二阶段
     * 不使用Spring AOP + AspectJ, 深入学习动态代理
     * BeanPostProcessor 产生一个动态代理对象
     * 动态代理学习.
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        final AService bean = context.getBean(AService.class);

        // 不经过任何处理，方法会被调用多次, 业务就出现错误了
        // 利用注解 + 切面， 第二次保存的时候直接报访问频繁
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    bean.save();
                }
            }).start();
        }
    }
}
