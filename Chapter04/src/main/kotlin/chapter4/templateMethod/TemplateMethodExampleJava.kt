package chapter4.templateMethod

abstract class DayRoutine {
    private fun arriveToWork() {
        println("Hi boss! I appear in the office sometimes!")
    }

    private fun drinkCoffee() {
        println("Coffee is delicious today")
    }

    abstract fun doBeforeLunch()

    private fun goToLunch() {
        println("Hamburger and chips, please!")
    }

    abstract fun doAfterLunch()

    open fun bossHook() {
        // Hope he doesn't hook me there
    }

    private fun goHome() {
        // Very important no one notices me
        println()
    }

    fun runSchedule() {
        arriveToWork()
        drinkCoffee()
        doAfterLunch()
        goToLunch()
        doAfterLunch()
        bossHook()
        goHome()
    }
}

class MondaySchedule : DayRoutine() {
    override fun doBeforeLunch() {
        println("Some pointless meeting")
        println("Code review. What this does?")
    }

    override fun doAfterLunch() {
        println("Meeting with Ralf")
        println("Telling jokes to other architects")
    }

    override fun bossHook() {
        println("Hey, can I have you for a sec in my office?")
    }
}