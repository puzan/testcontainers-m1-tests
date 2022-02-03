package info.puzan.tcm1

import org.junit.jupiter.api.RepeatedTest
import org.testcontainers.containers.MockServerContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.utility.DockerImageName.parse
import java.time.Duration
import kotlin.io.path.Path


class MockServerTest {

    @RepeatedTest(10)
    fun local() {
        val armImage = ImageFromDockerfile("mockserver/mockserver:local-5.11.2", false)
            .withFileFromPath(".", Path("docker/mockserver"))

        runMockServer(armImage.get())
    }

    @RepeatedTest(10)
    fun latest() {
        runMockServer("mockserver/mockserver:mockserver-5.11.2")
    }

    @RepeatedTest(10)
    fun default() {
        runMockServer("jamesdbloom/mockserver:mockserver-5.5.4")
    }

    @RepeatedTest(10)
    fun vektory79() {
        runMockServer("ghcr.io/vektory79/mockserver-docker:v5.11.2")
    }

    private fun runMockServer(image: String) {

        MockServerContainer(parse(image).asCompatibleSubstituteFor("jamesdbloom/mockserver"))
            .waitingFor(
                Wait.forHttp("/mockserver/status")
                    .withMethod("PUT")
                    .forStatusCode(200)
                    .withStartupTimeout(Duration.ofSeconds(10))
            )
            .use { mockServer ->
                mockServer.start()
            }
    }
}