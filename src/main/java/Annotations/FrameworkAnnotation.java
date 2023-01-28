package Annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import enums.*;

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotation {
	public TestCaseType type();

	public ModulePages page();

	public CategoryType[] category();

}
