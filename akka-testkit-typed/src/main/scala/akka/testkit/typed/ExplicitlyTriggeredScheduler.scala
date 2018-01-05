package akka.testkit.typed

import java.util.concurrent.ThreadFactory

import akka.event.LoggingAdapter
import com.typesafe.config.Config

import scala.annotation.varargs
import scala.concurrent.duration.{ Duration, FiniteDuration }

class ExplicitlyTriggeredScheduler(config: Config, log: LoggingAdapter, tf: ThreadFactory) extends akka.testkit.ExplicitlyTriggeredScheduler(config, log, tf) {

  @varargs
  def expectNoMessageFor[T](duration: FiniteDuration, on: scaladsl.TestProbe[T]*): Unit = {
    timePasses(duration)
    on.foreach(_.expectNoMsg(Duration.Zero))
  }
}
