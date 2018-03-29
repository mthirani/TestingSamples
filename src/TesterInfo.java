import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mayankthirani on 3/29/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //used on class level
public @interface TesterInfo {

    Priority priority() default Priority.MEDIUM;

    String[] tags() default {"sales", "test"};

    String createdBy() default "Mayank";

    String lastModified() default "03/29/2018";

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

}
