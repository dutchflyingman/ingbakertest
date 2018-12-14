import CreateAccount.AccountCreated
import CreateCustomerDossier.CustomerDossierCreated
import CreateVacancyApplication.CandidateAppliedToVacancy
import com.ing.baker.recipe.scaladsl.Interaction
import Events._
import Ingredients._
import PublishVacancy.VacancyCreated
import SendAccountVerificationEmail.AccountEmailSent
import ValidateAccountExists.{AccountExistent, AccountNonExistent}

import scala.util.Random

object Interactions {

  val r = new Random(System.currentTimeMillis())
  val validateAccountExists: Interaction = Interaction(
    name = "ValidateAccountExists",
    inputIngredients = Nil,
    requiredEvents = Set(talentRequestFinalized.name),
    output = Seq(accountExistent, accountNonExistent),
  )

  val validateAccountExistsImpl : ValidateAccountExists = new ValidateAccountExists {
    override def apply(processId: String): ValidateAccountExists.Outcome =
      if(r.nextBoolean()) new AccountExistent else new AccountNonExistent
  }

  val createCustomerDossier: Interaction = Interaction(
    name = "CreateCustomerDossier",
    inputIngredients = Nil,
    requiredEvents = Set(accountNonExistent.name),
    output = Seq(customerDossierCreated),
  )

  val createCustomerDossierImpl : CreateCustomerDossier = new CreateCustomerDossier {
    override def apply(processId: String): CreateCustomerDossier.Outcome =
      new CustomerDossierCreated
  }

  val createAccount: Interaction = Interaction(
    name = "CreateAccount",
    inputIngredients = Nil,
    requiredEvents = Set(accountNonExistent.name),
    output = Seq(accountCreated),
  )

  val createAccountImpl : CreateAccount = new CreateAccount {
    override def apply(processId: String): CreateAccount.Outcome =
      new AccountCreated
  }

  val sendAccountVerificationEmail: Interaction = Interaction(
    name = "SendAccountVerificationEmail",
    output = Seq(accountEmailSent),
    inputIngredients = Seq(account),
  )

  val sendAccountVerificationEmailImpl : SendAccountVerificationEmail = new SendAccountVerificationEmail {
    override def apply(processId: String): SendAccountVerificationEmail.Outcome =
      new AccountEmailSent
  }

  val publishVacancy: Interaction = Interaction(
    name = "PublishVacancy",
    inputIngredients = Seq(talentRequest),
    output = Seq(vacancyCreated)
  )

  val publishVacancyImpl : PublishVacancy = new PublishVacancy {
    override def apply(processId: String): PublishVacancy.Outcome =
      new VacancyCreated
  }

  val createVacancyApplication: Interaction = Interaction(
    name = "CreateVacancyApplication",
    inputIngredients = Seq(vacancy, candidateDossier),
    output = Seq(candidateAppliedToVacancy)
  )


  val createVacancyApplicationImpl : CreateVacancyApplication = new CreateVacancyApplication {
    override def apply(processId: String): CreateVacancyApplication.Outcome =
      new CandidateAppliedToVacancy()
  }

}
