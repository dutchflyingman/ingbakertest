import com.ing.baker.recipe.annotations.*;
import javax.inject.Named;

public interface SendAccountVerificationEmail {

    interface Outcome { }

    class AccountEmailSent implements Outcome { }

    @FiresEvent(oneOf = {AccountEmailSent.class})
    Outcome apply(@ProcessId String processId);
}
