import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.inject.AbstractModule;
import play.libs.Json;

// TODO: Move this file to common or modules folder.
//  Somehow, it doesn't work at this time when I try to change name or move it to sub folders.
public class Module extends AbstractModule {

	@Override
	protected void configure() {
		Json.mapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		Json.mapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

}
