import com.ing.baker.recipe.annotations.*;
import javax.inject.Named;

public interface CreateAccount {
    interface Outcome { }

    class AccountCreated implements Outcome { }

    @FiresEvent(oneOf = {AccountCreated.class})
    Outcome apply(@ProcessId String processId);
}
