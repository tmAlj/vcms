package com.wsd.annotation;

import java.lang.annotation.*;

/**
 * @author tm
 * @version 1.0.0
 * @description controller层日志记录注解
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    /**
     * 模块
     * @return
     */
    String model() default "";

    /**
     * 类型
     * @return
     */
    String type() default "";

    /**
     * 描述
     * @return
     */
    String describe() default "";
}
