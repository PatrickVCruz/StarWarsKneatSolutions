package mvc

import areWeThereYet.StarShip

class ShipController(private var model: StarShip, private var view: ShipView, private val input: Int) {

    fun setResult(result: String) {
        model.noOfResupplies = result
    }

    fun updateView() {
        view.printShipDetails(model.name, model.mglt, model.noOfResupplies)
    }

    fun updateModel(model: StarShip)   {
        this.model = model
    }

    fun convertToHours() {
            val resources = model.consumbles.split(" ")
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

    fun getNoOfResupplies() {
        model.noOfResupplies = (input / (model.mglt.toInt() * model.consumablesInHours)).toString()
    }

}