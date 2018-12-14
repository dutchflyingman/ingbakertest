package recipe

import com.ing.baker.recipe.scaladsl.Recipe
import recipe.Events.{candidateAppliedToVacancy, candidateDossierCreated, talentRequestFinalized}
import recipe.Interactions._

object Recipes {
  val humanResourcesRecipe: Recipe =
    Recipe("HumanResources")
      .withInteractions(
        validateAccountExists,
        createCustomerDossier,
        createAccount,
        sendAccountVerificationEmail,
        publishVacancy,
        createVacancyApplication
      )
      .withSensoryEvents(
        talentRequestFinalized,
        candidateDossierCreated)

}
