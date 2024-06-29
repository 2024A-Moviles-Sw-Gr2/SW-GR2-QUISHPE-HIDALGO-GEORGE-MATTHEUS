package org.example

import org.example.entities.Customer
import org.example.entities.Order
import java.io.*

class FileManager {
    companion object{
        private  val CUSTOMERS_FILE: File = File("src/main/resources/customers.txt")
        private  val ORDERS_FILE: File = File("src/main/resources/orders.txt")

        fun saveCustomers(): Unit{
            var customers: String = ""
            Customer.listOfCustomers.forEach{
                customers += "${it.customerID};${it.name};${it.email};${Utils.dateToString(it.registrationDate)};${it.active}\n"
            }
            CUSTOMERS_FILE.writeText(customers)
        }

        fun saveOrders(): Unit{
            var orders: String = ""
            Order.listOfOrders.forEach{
                orders += "${it.orderID};${it.customer.customerID};${Utils.dateToString(it.orderDate)};${it.totalAmount};${it.paid};${it.description}\n"
            }
            ORDERS_FILE.writeText(orders)
        }

        fun loadOrders(): Unit {
            val orders = ORDERS_FILE.readLines()
            orders.forEach {
                val order = it.split(";")
                Order(
                    order[0], Customer.findCustomerByID(order[1])!!,
                    Utils.stringToDate(order[2]), order[3].toDouble(), order[4].toBoolean(), order[5]
                )
            }
        }

        fun loadCustomers(): Unit {
            val customers = CUSTOMERS_FILE.readLines()
            customers.forEach {
                val customer = it.split(";")
                Customer(
                    customer[0],
                    customer[1],
                    customer[2],
                    Utils.stringToDate(customer[3]),
                    customer[4].toBoolean()
                )
            }
        }
    }
}