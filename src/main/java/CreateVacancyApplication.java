import com.ing.baker.recipe.annotations.*;
import javax.inject.Named;

public interface CreateVacancyApplication {

    interface Outcome { }

    class CandidateAppliedToVacancy implements Outcome { }

    @FiresEvent(oneOf = {CandidateAppliedToVacancy.class})
    Outcome apply(@ProcessId String processId);
}
