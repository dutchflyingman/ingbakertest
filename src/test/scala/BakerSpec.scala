import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.concurrent.Future

import com.ing.baker.compiler.RecipeCompiler
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import Recipes._
import akka.actor.ActorSystem
import com.ing.baker.il.CompiledRecipe
import com.ing.baker.runtime.core.{Baker, SensoryEventStatus}
import org.scalatest.concurrent.ScalaFutures
import Interactions._

import scala.concurrent.duration._
import scala.concurrent.Await

class BakerSpec extends FlatSpec with Matchers with BeforeAndAfterAll{

  implicit var actorSystem : ActorSystem = _

  override protected def beforeAll(): Unit = {
    actorSystem = ActorSystem()
  }
  override protected def afterAll(): Unit = {
    Await.ready(actorSystem.terminate(), 10.seconds)
  }

  lazy val compiledRecipe : CompiledRecipe =
    RecipeCompiler.compileRecipe(humanResourcesRecipe)



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


    baker.bake("abc", "def", 10.seconds)
    baker.processEvent("abc", Events.talentRequestFinalized) shouldBe SensoryEventStatus.Completed
  }

}
