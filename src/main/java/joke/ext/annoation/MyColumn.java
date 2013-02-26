package joke.ext.annoation;

/**
 * @Author: caoxiao
 * @Date: 12-11-23 下午2:02
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyColumn {

    String columnName();

    String columnType() default "";

}
