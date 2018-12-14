package recipe

import com.ing.baker.recipe.scaladsl.Event
import recipe.Ingredients._

object Events {
  val talentRequestFinalized = Event("TalentRequestFinalized", talentRequest)
  val accountNonExistent = Event("AccountNonExistent")
  val accountExistent = Event("AccountExistent", account, candidateDossier)
  val customerDossierCreated = Event("CustomerDossierCreated", customerDossier)
  val accountCreated = Event("AccountCreated", account)
  val accountEmailSent = Event("AccountEmailSent")
  val vacancyCreated = Event("VacancyCreated", vacancy)
  val candidateDossierCreated = Event("CandidateDossierCreated", candidateDossier)
  val candidateAppliedToVacancy = Event("CandidateAppliedToVacancy", vacancyApplication)
}
