package mvc

/**
 * ShipView
 *
 * This class updates the view when the controller. This displays all the data from the model
 */

class ShipView {

    /**
     *
     * This method prints the model to the console after {@link noOfResupplies} has be calculated
     *
     * @param shipName String name of the ship
     * @param mglt  String Megalight of the ship per hour
     * @param noOfResupplies String the number of resupplies needed in terms of megalights inputted by user
     */
    fun printShipDetails(shipName: String, mglt: String, noOfResupplies: String)  {
        println(shipName)
        print("MGLT: $mglt")
        print("\tNumber of resupplies: $noOfResupplies \n")
    }

}