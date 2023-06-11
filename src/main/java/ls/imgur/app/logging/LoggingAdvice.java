package ls.imgur.app.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspects: are the classes that contains advices, joinpoints and pointcuts.
 * Aspects can be configured through spring xml configuration or we can use
 * Spring AspectJ integration to define a class as Aspect using @Aspect annotation
 * 
 * JoinPoint: A specific point in the application such as method execution, exception handling,
 * changing object variable values etc. Spring supports only method execution join point.
 * JoinPoint contains the info of method that triggers the advice method or the method.
 * The exact point in execution(before method call, after method call, exception throw, 
 * field modified etc) where the advice needs to be applied.
 * 
 * Advice: Advices are the actions taken for a particular join point. In terms of programming,
 * they are methods that gets executed when a certain join point with matching pointcut is reached
 * in the application.
 * 
 * Pointcut: Pointcut are expressions that is matched with join points to determine whether advice 
 * needs to be executed or not.
 * @author vikas.rajaura
 *
 */
@Aspect
@Component
public class LoggingAdvice {

	public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);

	@Around("within(ls.imgur.app.restapi.*)")
	public Object restApisLogging(ProceedingJoinPoint jp) throws Throwable {
		LOGGER.info("Executing Method::" + prepareMethodName(jp));
		Object object = jp.proceed();
		LOGGER.info("Finished Method::" + prepareMethodName(jp));
		return object;
	}

	/**
	 * This method helps to prepare the class name and method name for a log.
	 *
	 * @param jp the join point
	 * @return String
	 */
	private String prepareMethodName(JoinPoint jp) {
		return jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();
	}	


	/**
	 * This is an advice which logs entry/exit of all methods annotated
	 * with @LogMethod annotation defined.
	 *
	 * @param joinPoint the join point
	 * @return Object
	 * @throws Throwable the throwable
	 */
	@Around("@annotation(ls.imgur.app.logging.LogMethod)")
	public Object logAnnotatedMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		LOGGER.info("Executing Method :: " + prepareMethodName(joinPoint));
		Object result = joinPoint.proceed();
		LOGGER.info("Execution Finished ::" + prepareMethodName(joinPoint));
		return result;
	}

}
