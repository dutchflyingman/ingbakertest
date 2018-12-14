import com.ing.baker.recipe.annotations.*;
import javax.inject.Named;

public interface PublishVacancy {

    interface Outcome { }

    class VacancyCreated implements Outcome { }

    @FiresEvent(oneOf = {VacancyCreated.class})
    Outcome apply(@ProcessId String processId);
}
