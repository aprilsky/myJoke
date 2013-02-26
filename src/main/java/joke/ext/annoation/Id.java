package joke.ext.annoation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: caoxiao
 * @Date: 12-11-23 下午2:04
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Id {
}
