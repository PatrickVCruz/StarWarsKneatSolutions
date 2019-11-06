import areWeThereYet.SWApiCall
import java.util.*


/**
 * Main class
 * This class will start the programme
 * Main method this will ask the user for an input in MGLT and makes sure that they
 * input an integer which then calls {@link SWApiCall} which starts the programme
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