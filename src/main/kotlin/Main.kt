import areWeThereYet.SWApiCall
import java.util.*


/**
 * Main class
 * This class will start the programme
 */
fun main() {

    println("Please input distance in terms of MGLT: ")
    val scanner = Scanner(System.`in`)
    while (!scanner.hasNextInt()) {
        println("Please input distance in terms of MGLT: ")
        scanner.nextLine()
    }
    val userInput = scanner.nextInt()
    val apiCall = SWApiCall(userInput)
    apiCall.start()
}