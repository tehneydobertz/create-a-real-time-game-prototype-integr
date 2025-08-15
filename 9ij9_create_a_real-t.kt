import kotlin.math.*

class RealTimeGamePrototypeIntegrator(val gameWidth: Int, val gameHeight: Int) {

    private val gameObjects = mutableListOf<GameObject>()

    fun addGameObject(gameObject: GameObject) {
        gameObjects.add(gameObject)
    }

    fun removeGameObject(gameObject: GameObject) {
        gameObjects.remove(gameObject)
    }

    fun updateGame(state: GameState) {
        gameObjects.forEach { it.update(state) }
    }

    fun renderGame(state: GameState) {
        gameObjects.forEach { it.render(state) }
    }

    interface GameObject {
        fun update(state: GameState)
        fun render(state: GameState)
    }

    data class GameState(val deltaTime: Float, val time: Float)

}

class PlayerObject : RealTimeGamePrototypeIntegrator.Companion GameObject {

    private var x: Float = 0f
    private var y: Float = 0f
    private var velocityX: Float = 0f
    private var velocityY: Float = 0f

    override fun update(state: RealTimeGamePrototypeIntegrator.GameState) {
        x += velocityX * state.deltaTime
        y += velocityY * state.deltaTime
    }

    override fun render(state: RealTimeGamePrototypeIntegrator.GameState) {
        println("Rendering player at ($x, $y)")
    }

}

fun main() {
    val game = RealTimeGamePrototypeIntegrator(800, 600)
    val player = PlayerObject()
    game.addGameObject(player)

    var time = 0f
    val deltaTime = 1f / 60f

    while (true) {
        val state = RealTimeGamePrototypeIntegrator.GameState(deltaTime, time)
        game.update(state)
        game.render(state)
        time += deltaTime
        Thread.sleep((deltaTime * 1000).toLong())
    }
}