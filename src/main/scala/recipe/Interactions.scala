package recipe

import com.ing.baker.recipe.scaladsl.Interaction
import recipe.Events._
import recipe.Ingredients.{account, candidateDossier, talentRequest, vacancy}

import scala.util.Random

object Interactions {

  val validateAccountExists: Interaction = Interaction(
    name = "ValidateAccountExists",
    inputIngredients = Nil,
    requiredEvents = Set(talentRequestFinalized.name),
    output = Seq(accountExistent, accountNonExistent),
  )

  val createCustomerDossier: Interaction = Interaction(
    name = "CreateCustomerDossier",
    inputIngredients = Nil,
    requiredEvents = Set(accountNonExistent.name),
    output = Seq(customerDossierCreated),
  )

  val createAccount: Interaction = Interaction(
    name = "CreateAccount",
    inputIngredients = Nil,
    requiredEvents = Set(accountNonExistent.name),
    output = Seq(accountCreated),
  )

  val sendAccountVerificationEmail: Interaction = Interaction(
    name = "SendAccountVerificationEmail",
    output = Seq(accountEmailSent),
    inputIngredients = Seq(account),
  )

  val publishVacancy: Interaction = Interaction(
    name = "PublishVacancy",
    inputIngredients = Seq(talentRequest),
    output = Seq(vacancyCreated)
  )

  val createVacancyApplication: Interaction = Interaction(
    name = "CreateVacancyApplication",
    inputIngredients = Seq(vacancy, candidateDossier),
    output = Seq(candidateAppliedToVacancy)
  )




}
