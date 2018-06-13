package chapter3

import com.sun.javaws.exceptions.InvalidArgumentException

fun main(args: Array<String>) {

}


interface CanAskForSalaryIncrease : HasSalary {
    fun askForIncrease(percent: Int)
}

interface HasSalary {
    val salary: Int
}

/*data class Employee(override var salary: Int): CanAskForSalaryIncrease by ProtectionProxy(){

}*/
/*

class ProtectionProxy : CanAskForSalaryIncrease {
    override fun askForIncrease(percent: Int) {
        if (percent <= 0) {
            throw RuntimeException("Zero or negative percent")
        }
        else if (percent > 15) {
            throw RuntimeException("Cannot raise salary more than 15%")
        }
        salary
    }
}*/
