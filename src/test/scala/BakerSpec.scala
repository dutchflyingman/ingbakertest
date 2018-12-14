import akka.actor.ActorSystem
import com.ing.baker.compiler.RecipeCompiler
import com.ing.baker.il.CompiledRecipe
import com.ing.baker.runtime.core.{Baker, SensoryEventStatus}
import helpers.DotVisualizationHelper
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import recipe.Ingredients
import runtime.EventImplementations
import runtime.InteractionImplementations._

import scala.concurrent.Await
import scala.concurrent.duration._

class BakerSpec extends FlatSpec with Matchers with BeforeAndAfterAll{

  implicit var actorSystem : ActorSystem = _

  override protected def beforeAll(): Unit = {
    actorSystem = ActorSystem()
  }
  override protected def afterAll(): Unit = {
    Await.ready(actorSystem.terminate(), 10.seconds)
  }

  lazy val compiledRecipe : CompiledRecipe =
    RecipeCompiler.compileRecipe(recipe.Recipes.humanResourcesRecipe)



  "The human resources recipe" should "compile" in {
    val cr = compiledRecipe
  }

  it should "not produce validation errors" in {
    compiledRecipe.validationErrors shouldBe Nil
  }

//  it should "be able to create a graphical representation" in {
//    compiledRecipe.getRecipeVisualization should not be ""
//    val bytes = humanResourcesRecipeVisualization.getBytes(StandardCharsets.UTF_8)
//    Files.write(Paths.get("c:/tmp/baker.svg"), bytes)
//  }

  it should "run" in {
    val baker = new Baker
    baker.addImplementations(Seq(
      validateAccountExistsImpl,
      createCustomerDossierImpl,
      createAccountImpl,
      sendAccountVerificationEmailImpl,
      publishVacancyImpl,
      createVacancyApplicationImpl
    ))
    baker.addRecipe(compiledRecipe)

    val processId = "abc"

    baker.bake(compiledRecipe.recipeId, processId, 10.seconds)
    baker.processEvent(processId, EventImplementations.CandidateDossierCreated(Ingredients.CandidateDossier(12))) shouldBe SensoryEventStatus.Completed
    baker.processEvent(processId, EventImplementations.TalentRequestFinalized(Ingredients.TalentRequest(1234))) shouldBe SensoryEventStatus.Completed

    DotVisualizationHelper.writeSvgFile(baker.getVisualState(processId), "C:/tmp/visualstate.svg")
  }

}
