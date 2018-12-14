package runtime

import recipe.SendAccountVerificationEmail.AccountEmailSent
import recipe._
import EventImplementations._
import recipe.Ingredients.{Account, CandidateDossier, Vacancy, VacancyApplication}

import scala.util.Random

object InteractionImplementations {
  private val r = new Random(System.currentTimeMillis())

  val validateAccountExistsImpl : ValidateAccountExists = new ValidateAccountExists {
    override def apply(): ValidateAccountExists.Outcome =
      if(r.nextBoolean()) EventImplementations.AccountExistent(Account(r.nextInt()), CandidateDossier(r.nextInt())) else EventImplementations.AccountNonExistent()
  }

  val createCustomerDossierImpl : CreateCustomerDossier = new CreateCustomerDossier {
    override def apply(): CreateCustomerDossier.Outcome =
      CustomerDossierCreated(Ingredients.CustomerDossier(r.nextInt()))
  }

  val createAccountImpl : CreateAccount = new CreateAccount {
    override def apply(): CreateAccount.Outcome =
      AccountCreated(Account(r.nextInt()))
  }

  val sendAccountVerificationEmailImpl : SendAccountVerificationEmail = new SendAccountVerificationEmail {
    override def apply(account: Ingredients.Account): SendAccountVerificationEmail.Outcome =
      EventImplementations.AccountEmailSent()
  }

  val publishVacancyImpl : PublishVacancy = new PublishVacancy {
    override def apply(talentRequest: Ingredients.TalentRequest): PublishVacancy.Outcome =
      VacancyCreated(Vacancy(r.nextInt(), talentRequest.id))
  }

  val createVacancyApplicationImpl : CreateVacancyApplication = new CreateVacancyApplication {
    override def apply(vacancy: Ingredients.Vacancy, candidateDossier: Ingredients.CandidateDossier): CreateVacancyApplication.Outcome =
      CandidateAppliedToVacancy(VacancyApplication(r.nextInt()))
  }
}
