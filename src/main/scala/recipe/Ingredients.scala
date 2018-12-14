package recipe

import com.ing.baker.recipe.scaladsl.Ingredient

object Ingredients {
  case class CustomerDossier(id : Int)
  case class Account(id : Int)
  case class TalentRequest(id : Int)
  case class Vacancy(id : Int, talentRequestId : Int)
  case class CandidateDossier(id : Int)
  case class VacancyApplication(id : Int)

  val customerDossier: Ingredient[CustomerDossier] =
    Ingredient[CustomerDossier]("customerDossier")

  val account: Ingredient[Account] =
    Ingredient[Account]("account")

  val talentRequest: Ingredient[TalentRequest] =
    Ingredient[TalentRequest]("talentRequest")

  val vacancy: Ingredient[Vacancy] =
    Ingredient[Vacancy]("vacancy")

  val candidateDossier : Ingredient[CandidateDossier] =
    Ingredient[CandidateDossier]("candidateDossier")

  val vacancyApplication: Ingredient[VacancyApplication] =
    Ingredient[VacancyApplication]("vacancyApplication")

}
