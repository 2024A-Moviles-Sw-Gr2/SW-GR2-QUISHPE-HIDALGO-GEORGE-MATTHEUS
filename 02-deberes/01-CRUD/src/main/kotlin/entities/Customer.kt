package org.example.entities

import org.example.Utils
import java.util.Date

class Customer(
    val customerID: String,
    var name: String,
    var email: String,
    var registrationDate: Date,
    var active: Boolean,
    var orders: ArrayList<Order> = ArrayList()
) {

    init {
        listOfCustomers.add(this)
    }

    fun printCustomer(): String {
        return "Customer: ${this.customerID}\n" +
                "   Name: ${this.name}\n" +
                "   Email: ${this.email}\n" +
                "   Registration date: ${Utils.dateToString(this.registrationDate)}\n" +
                "   Active: ${this.active}\n" +
                "   Orders:\n" +
                this.orders.joinToString("\n") { "         ID: ${it.orderID}, Descripción: ${it.description}, Monto Total: ${it.totalAmount}" }
    }

    fun updateCustomer(attributeToUpdate: Int, value: String): Boolean {
        return when (attributeToUpdate) {
            1 -> { // Name
                this.name = value
                true
            }
            2 -> { // Email
                this.email = value
                true
            }
            3 -> { // Date of registration
                this.registrationDate = Utils.stringToDate(value)
                true
            }
            4 -> { // Active
                this.active = value.toBoolean()
                true
            }
            else -> false
        }
    }

    companion object{
        val listOfCustomers = ArrayList<Customer>()

        fun findCustomerByID(customerID: String): Customer? {
            return listOfCustomers.find { it.customerID == customerID }
        }

        fun deleteCustomerByID(customerID: String): Boolean {
            val customer = findCustomerByID(customerID)
            return if (customer != null) {
                customer.orders.forEach { Order.listOfOrders.remove(it) }
                listOfCustomers.remove(customer)
                true
            } else {
                false
            }
        }
    }
}
