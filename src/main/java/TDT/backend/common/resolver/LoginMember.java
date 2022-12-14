package TDT.backend.common.resolver;


import org.springframework.data.redis.core.RedisTemplate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
public @interface LoginMember {

    Class<?>[] ToRedis() default { };

}
