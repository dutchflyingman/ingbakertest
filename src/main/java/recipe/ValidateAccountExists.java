package recipe;

import com.ing.baker.recipe.annotations.*;
import javax.inject.Named;

public interface ValidateAccountExists {

    interface Outcome { }

    class AccountExistent implements Outcome { }

    class AccountNonExistent implements Outcome { }

    @FiresEvent(oneOf = {AccountExistent.class, AccountNonExistent.class})
    Outcome apply();
}