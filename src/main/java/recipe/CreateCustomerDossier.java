package recipe;

import com.ing.baker.recipe.annotations.*;
import javax.inject.Named;

public interface CreateCustomerDossier {
    interface Outcome { }

    class CustomerDossierCreated implements Outcome { }

    @FiresEvent(oneOf = {CustomerDossierCreated.class})
    Outcome apply();
}
