package recipe;

import com.ing.baker.recipe.annotations.*;
import javax.inject.Named;

public interface SendAccountVerificationEmail {

    interface Outcome { }

    class AccountEmailSent implements Outcome { }

    @FiresEvent(oneOf = {AccountEmailSent.class})
    Outcome apply(@Named("account") Ingredients.Account account);
}
