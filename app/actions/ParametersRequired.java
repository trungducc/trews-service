package actions;

import common.Parameters;
import play.libs.typedmap.TypedKey;
import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@With(ParametersRequiredAction.class)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ParametersRequired {

    class Attrs {
        public static final TypedKey<Parameters> PARAMETERS_TYPED_KEY = TypedKey.create();
    }

    Class<? extends Parameters> type();

}