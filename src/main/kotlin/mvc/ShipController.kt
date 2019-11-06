package mvc

import areWeThereYet.StarShip

/**
 * ShipController
 * Class that acts as the controller for the model and view as part of the MVC design pattern.
 * It handles all the changes that occurs within the model and updates the view when needed.
 *
 * @param model - {@link StarShip} the current StarShip taken in from the SW-APi
 * @param view - {@link ShipView} the view that the user will see and will print out the results
 * @param input - Int, The input the user inputted in terms of Megalights
 */

class ShipController(private var model: StarShip, private var view: ShipView, private val input: Int) {
    /**
     *  This function sets the {@link StarShip#noOfResupplies} to an unknown value since, there are
     *  some ships that the number of resupplies cannot be calculated.
     *
     *  @param result the result of the calculation for number of resupplies
     *
     */
    fun setUnknown(result: String) {
        model.noOfResupplies = result
    }

    /**
     * This function updates the view @param view {@link ShipView}
     */
    fun updateView() {
        view.printShipDetails(model.name, model.mglt, model.noOfResupplies)
    }

    /**
     * This function updates the model {@link StarShip}
     */
    fun updateModel(model: StarShip)   {
        this.model = model
    }

    /**
     * This function converts the {@link StarShip#consumables} to hours, since the consumables
     * can be in days, weeks, months or years each possibility is checked in this switch statement
     * If a consumable is found to be any of them, it is converted into hours and updates
     * {@link StarShip#consumablesInHours} with the consumables in hours
     */
    fun convertToHours() {
            val resources = model.consumables.split(" ")
            var result = 0

            when (resources[1]) {
                "month", "months" -> {
                    result = ((resources[0].toInt() * 730.0008).toInt())
                }
                "day", "days" -> {
                    result = (resources[0].toInt() * 24)
                }
                "week", "weeks" -> {
                    result = (resources[0].toInt() * 168)
                }
                "year", "years" -> {
                    result = (resources[0].toInt() * 8760)
                }
            }
            model.consumablesInHours = result
        }

    /**
     * This function calculates the number of resupplies needed in terms of the user input.
     * I found the calculation by looking on the Star Wars wiki what a megalight was, which is
     * a unit of distance in space and by looking at the distance speed time formula I found it
     * was going to be speed and for time, I looked into the json object and found consumables was in
     * time but in days/weeks/months/years and needed to convert it to a standard time and chose hours.
     * So with speed and time, I could calculate distance that a current ships supplies could last and
     * then divided by the megalight input that it needed to travel.
     */
    fun getNoOfResupplies() {
        model.noOfResupplies = (input / (model.mglt.toInt() * model.consumablesInHours)).toString()
    }

}