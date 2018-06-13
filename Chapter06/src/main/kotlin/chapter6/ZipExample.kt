package chapter6

fun main(args: Array<String>) {


    val employeeIds = listOf(5, 8, 13, 21, 34, 55, 89)
    val daysInCompany = listOf(176, 145, 117, 92, 70, 51, 35, 22, 12, 5)

    println(employeeIds.zip(daysInCompany))

    println(daysInCompany.zip(employeeIds))

    val employeesToDays = employeeIds.zip(daysInCompany)

    val (employees, days) = employeesToDays.unzip()
    println(employees)
    println(days)
}