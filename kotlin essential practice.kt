
 // ------- lambda expression practice -------

fun main() {
    // Fill in the code.
    val CtoF: (Double)-> Double = { tempC -> (9.0/5.0) * tempC + 32 }
    val FtoC: (Double)-> Double = { tempF -> (5.0/9.0) * (tempF - 32) }
    val KtoC: (Double)-> Double = { it - 273.15 }
    printFinalTemperature( 27.0 , "Celsius " , "Fahrenheit" , CtoF )
	printFinalTemperature( 350.0 , "Kelvin " , "Celsius" , KtoC )
    printFinalTemperature( 10.0 , "Fahrenheit " , "Kelvin" , { (5.0/9.0) * (it-32) + 273.15 } )
}


fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}



 // ------- null handling practice -------

fun main() {
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        println("Name: $name")
        println("Age: $age")
        if(hobby != null ) {            print("likes to $hobby. ")        }
        if(referrer != null ) {            print("Has a referrer named " + referrer.name)        }
        if(referrer?.hobby != null ) {            print(", who likes to " + referrer?.hobby)        }
        println()
        println()
    }
    
}


// ------ practice ------

fun main() {
    val winningBid = Bid(5000, "Private Collector")

    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
}

class Bid(val amount: Int, val bidder: String)

fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    // Fill in the code.
    if( bid == null ) return minimumPrice
    return bid.amount
}