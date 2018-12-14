package runtime

import recipe._
import recipe.Ingredients._

object EventImplementations {
  case class AccountExistent(account : Account, candidateDossier: CandidateDossier) extends ValidateAccountExists.AccountExistent
  case class AccountNonExistent() extends ValidateAccountExists.AccountNonExistent
  case class TalentRequestFinalized(talentRequest: TalentRequest)
  case class CustomerDossierCreated(customerDossier : CustomerDossier) extends CreateCustomerDossier.CustomerDossierCreated
  case class AccountCreated(account : Account) extends CreateAccount.AccountCreated
  case class AccountEmailSent() extends SendAccountVerificationEmail.AccountEmailSent
  case class VacancyCreated(vacancy: Vacancy) extends PublishVacancy.VacancyCreated
  case class CandidateAppliedToVacancy(vacancyApplication: VacancyApplication) extends CreateVacancyApplication.CandidateAppliedToVacancy
  case class CandidateDossierCreated(candidateDossier: CandidateDossier)
}
