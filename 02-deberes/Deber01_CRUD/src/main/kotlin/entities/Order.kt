package org.example.entities

import org.example.Utils
import java.util.Date

class Order(
    var orderID: String,
    var customer: Customer,
    var orderDate: Date,
    var totalAmount: Double,
    var paid: Boolean,
    var description: String
) {

    init {
        listOfOrders.add(this)
        this.customer.orders.add(this)
    }

    fun printOrder(): String {
        return "Order: ${this.orderID}\n" +
                "   Customer: ${this.customer.customerID}\n" +
                "   Order date: ${Utils.dateToString(this.orderDate)}\n" +
                "   Total amount: ${this.totalAmount}\n" +
                "   Paid: ${this.paid}\n" +
                "   Description: ${this.description}"
    }

    fun updateOrder(attributeToUpdate: Int, value: String): Boolean {
        return when (attributeToUpdate) {
            1 -> { // Customer
                val customer = Customer.findCustomerByID(value)
                if (customer != null) {
                    this.customer.orders.remove(this)
                    this.customer = customer
                    customer.orders.add(this)
                    true
                } else {
                    false
                }
            }
            2 -> { // Date of order
                this.orderDate = Utils.stringToDate(value)
                true
            }
            3 -> { // Total amount
                this.totalAmount = value.toDouble()
                true
            }
            4 -> { // Paid
                this.paid = value.toBoolean()
                true
            }
            5 -> { // Description
                this.description = value
                true
            }
            else -> false
        }
    }

    companion object{
        val listOfOrders = ArrayList<Order>()

        fun findOrderById(orderId: String): Order? {
            return listOfOrders.find { it.orderID == orderId }
        }

        fun deleteOrderById(orderId: String): Boolean {
            val order = findOrderById(orderId)
            return if (order != null) {
                listOfOrders.remove(order)
                order.customer.orders.remove(order)
                true
            } else {
                false
            }
        }
    }

}