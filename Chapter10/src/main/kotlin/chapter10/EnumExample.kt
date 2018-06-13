package chapter10

import chapter10.PizzaOrderStatus.*
fun main(vararg args: String) {

    var status: PizzaOrderStatus = OrderReceived(123)

    while (status !is Completed) {
        status = when (status) {
            is OrderReceived -> status.nextStatus()
            is PizzaBeingMade -> status.nextStatus()
            is OutForDelivery -> status.nextStatus()
            is Completed -> status
        }
    }
}

sealed class PizzaOrderStatus(protected val orderId: Int) {
    abstract fun nextStatus() : PizzaOrderStatus
    class OrderReceived(orderId: Int) : PizzaOrderStatus(orderId) {
        override fun nextStatus(): PizzaOrderStatus {
            return PizzaBeingMade(orderId)
        }
    }

    class PizzaBeingMade(orderId: Int) : PizzaOrderStatus(orderId) {
        override fun nextStatus(): PizzaOrderStatus {
            return OutForDelivery(orderId)
        }
    }

    class OutForDelivery(orderId: Int) : PizzaOrderStatus(orderId) {
        override fun nextStatus(): PizzaOrderStatus {
            return Completed(orderId)
        }
    }

    class Completed(orderId: Int) : PizzaOrderStatus(orderId) {
        override fun nextStatus(): PizzaOrderStatus {
            return this
        }
    }
}


