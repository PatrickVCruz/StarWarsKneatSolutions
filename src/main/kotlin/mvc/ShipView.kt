package mvc

class ShipView {

    fun printShipDetails(shipName: String, mglt: String, result: String)  {
        println(shipName)
        print("MGLT: $mglt")
        print("\tNumber of resupplies: $result \n")
    }

}