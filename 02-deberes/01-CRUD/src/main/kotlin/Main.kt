package org.example

import org.example.entities.Customer
import org.example.entities.Order
import java.text.SimpleDateFormat

fun main() {

    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    FileManager.loadCustomers()
    FileManager.loadOrders()

    while (true) {
        println(
            "----------------------------------------------------------------------------\n" +
                    "--------------------------------Order system--------------------------------\n" +
                    "----------------------------------------------------------------------------\n" +
                    "1. Customer menu\n" +
                    "2. Order menu\n" +
                    "3. Exit\n"
        )

        val selection: Int = try {
            readlnOrNull()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            0
        }

        when (selection) {
            0 -> {
                println("Invalid option")
                Utils.pause();
            }

            1 -> {
                println("----------------------------------------------------------------------------\n" +
                        "-------------------------------Customers Menu-------------------------------\n" +
                        "----------------------------------------------------------------------------\n" +
                        "1. Create customer\n" +
                        "2. Update customer\n" +
                        "3. Delete customer\n" +
                        "4. Show customers\n" +
                        "5. Show customer\n" +
                        "6. Return to main menu\n"
                )

                val selectionCustomer: Int = try {
                    readlnOrNull()?.toInt() ?: 0
                } catch (e: NumberFormatException) {
                    0
                }

                when (selectionCustomer) {
                    0 -> {
                        println("Invalid option")
                    }

                    1 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "-------------------------------Create Customer------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        println("Enter the customer ID:")
                        val customerID = readln()
                        println("Enter the customer name:")
                        val name = readln()
                        println("Enter the customer email:")
                        val email = readln()
                        println("Enter the registration date (dd-MM-yyyy):")
                        val registrationDate = dateFormat.parse(readln())
                        println("Enter if the customer is active (true/false):")
                        val active = readln().toBoolean()

                        Customer(customerID, name, email, registrationDate, active)
                    }

                    2 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "-------------------------------Update Customer------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        println("Enter the customer ID:")
                        val customerID = readln()
                        val customer = Customer.findCustomerByID(customerID)

                        if (customer != null) {
                            println(
                                "1. Update name\n" +
                                        "2. Update email\n" +
                                        "3. Update registration date\n" +
                                        "4. Update active\n"
                            )

                            val attributeToUpdate = readln().toInt()

                            println("Enter the new value:")
                            val value = readln()

                            if (customer.updateCustomer(attributeToUpdate, value)) {
                                println("Customer updated successfully")
                            } else {
                                println("Error updating customer")
                            }
                        } else {
                            println("Customer not found")
                        }
                    }

                    3 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "-------------------------------Delete Customer------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        println("Enter the customer ID:")
                        val customerID = readln()

                        if (Customer.deleteCustomerByID(customerID)) {
                            println("Customer deleted successfully")
                        } else {
                            println("Error deleting customer")
                        }
                    }

                    4 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "-------------------------------Find Customers-------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        Customer.listOfCustomers.forEach { println(it.printCustomer()) }
                        Utils.pause();
                    }

                    5 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "--------------------------------Find Customer-------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        println("Enter the customer ID:")
                        val customerID = readln()
                        val customer = Customer.findCustomerByID(customerID)

                        if (customer != null) {
                            println(customer.printCustomer())
                        } else {
                            println("Customer not found")
                        }
                        Utils.pause();
                    }

                    6 -> {
                    }
                }
            }

            2 -> {
                println("----------------------------------------------------------------------------\n" +
                        "---------------------------------Orders Menu--------------------------------\n" +
                        "----------------------------------------------------------------------------\n" +
                        "1. Create order\n" +
                        "2. Update order\n" +
                        "3. Delete order\n" +
                        "4. Show orders\n" +
                        "5. Show order\n" +
                        "6. Return to main menu\n"
                )

                val selectionOrder: Int = try {
                    readlnOrNull()?.toInt() ?: 0
                } catch (e: NumberFormatException) {
                    0
                }

                when (selectionOrder) {
                    0 -> {
                        println("Invalid option")
                    }

                    1 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "---------------------------------Create Order-------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        println("Enter the order ID:")
                        val orderID = readln()
                        println("Enter the customer ID:")
                        val customerID = readln()
                        val customer = Customer.findCustomerByID(customerID)
                        println("Enter the order date (dd-MM-yyyy):")
                        val orderDate = dateFormat.parse(readln())
                        println("Enter the total amount:")
                        val totalAmount = readln().toDouble()
                        println("Enter if the order is paid (true/false):")
                        val paid = readln().toBoolean()
                        println("Enter the description:")
                        val description = readln()

                        Order(orderID, customer!!, orderDate, totalAmount, paid, description)
                    }

                    2 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "--------------------------------Update Order--------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        println("Enter the order ID:")
                        val orderID = readln()
                        val order = Order.findOrderById(orderID)

                        if (order != null) {
                            println(
                                "1. Update customer\n" +
                                        "2. Update date\n" +
                                        "3. Update total amount\n" +
                                        "4. Update paid\n" +
                                        "5. Update description\n"
                            )

                            val attributeToUpdate = readln().toInt()

                            println("Enter the new value:")
                            val value = readln()

                            if (order.updateOrder(attributeToUpdate, value)) {
                                println("Order updated successfully")
                            } else {
                                println("Error updating order")
                            }
                        } else {
                            println("Order not found")
                        }
                    }

                    3 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "--------------------------------Delete Order--------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        println("Enter the order ID:")
                        val orderID = readln()

                        if (Order.deleteOrderById(orderID)) {
                            println("Order deleted successfully")
                        } else {
                            println("Error deleting order")
                        }
                    }

                    4 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "---------------------------------Find Orders--------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        Order.listOfOrders.forEach { println(it.printOrder()) }
                        Utils.pause();
                    }

                    5 -> {
                        println("----------------------------------------------------------------------------\n" +
                                "---------------------------------Find Order---------------------------------\n" +
                                "----------------------------------------------------------------------------\n")
                        println("Enter the order ID:")
                        val orderID = readln()
                        val order = Order.findOrderById(orderID)

                        if (order != null) {
                            println(order.printOrder())
                        } else {
                            println("Order not found")
                        }
                        Utils.pause();
                    }

                    6 -> {
                    }
                }
            }
            3 -> {
                FileManager.saveCustomers()
                FileManager.saveOrders()
                break
            }
        }
    }
}