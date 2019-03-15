package kai.wang.compile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kai.w
 * @des $des
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Title {
    String title() default "";

    boolean isTransulent() default false;
}
