import com.ing.baker.recipe.scaladsl.Recipe
import Ingredients._
import Events._
import Interactions._
import com.ing.baker.compiler.RecipeCompiler
import guru.nidi.graphviz.engine.{Format, Graphviz}
import guru.nidi.graphviz.parse.Parser

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
        candidateDossierCreated,
        candidateAppliedToVacancy)


  def humanResourcesRecipeVisualization : String = {
    val compiledRecipe = RecipeCompiler.compileRecipe(humanResourcesRecipe)
    val visualizationInDot = compiledRecipe.getRecipeVisualization
    val graph = Parser.read(visualizationInDot)
    val visualizationInSvg = Graphviz.fromGraph(graph).render(Format.SVG).toString
    visualizationInSvg
  }
}
