package pt.atp.bucketlist.objects

import pt.atp.bucketlist.R
import pt.atp.bucketlist.model.Country
import pt.atp.bucketlist.model.Place

object Places {

    val placesToVisit = listOf(
            Place("Ireland", "Road Trip" , "Dublin - Belfast (Giant's Causeway) - Sligo (Slieve League) - Galway (Moher Cliffs) - Cork",  R.drawable.irelandtrip, R.drawable.ireland),
            Place("Scotland", "North Coast 500", "Black Isle - Caithness - Easter Ross - Inverness-Shire - Sutherland - Wester Ross", R.drawable.northcoast, R.drawable.scotland),
            Place("Chile - Argentina", "Patagonia", "Carretera Austral - El Chalten - Perito Moreno - Torres del Paine", R.drawable.patagonia, R.drawable.chile),
            Place("Norway", "Road Trip", "Strandvik (Stegastein lookout) - Balestrand - Loen - Andalsnes (Trollstigen) - Hoddevik - Naustdal", R.drawable.norwaytrip, R.drawable.norway),
            Place("Switzerland", "RoadTrip", "Fribourg - Ticino - Zermatt - Schwyzer Alpen - Interlaken - Glarus - Appenzell Innerrhoden - Grindelwald - Swiss Alps", R.drawable.swissalps, R.drawable.switzerland),
            Place("Japan", "RoadTrip", "Tokyo - Kyoto - Osaka - Hiroshima - Okinawa", R.drawable.japan, R.drawable.japanflag),
            )

    val countriesToVisit = listOf(
            Country("Ireland", R.drawable.irelandtrip),
            Country("Scotland", R.drawable.northcoast),
            Country("Chile", R.drawable.patagonia),
            Country("Norway", R.drawable.norwaytrip),
            Country("Switzerland", R.drawable.swissalps),
            Country("Japan", R.drawable.japan),
    )

    val placesVisited = listOf(
            Place("Italy",  "Venice", "February 2020" , R.drawable.venice, R.drawable.italy),
            Place("Poland", "Auschwitz", "Erasmus 2020", R.drawable.auschwitz, R.drawable.poland),
            Place("Poland", "Zakopane", "Erasmus 2020", R.drawable.zakopane, R.drawable.poland),
            Place("Germany", "Berlin", "Erasmus 2020", R.drawable.berlin, R.drawable.germany),
            Place("Austria", "Salzburg", "Erasmus 2019", R.drawable.salzburg, R.drawable.austria),
            Place("Germany", "Munich", "Erasmus 2019", R.drawable.munich, R.drawable.germany),
            Place("Germany", "Dresden", "Erasmus 2019", R.drawable.dresden, R.drawable.germany),
            Place("Check Republic", "Prague", "Erasmus 2019", R.drawable.prague, R.drawable.czech),
            Place("Hungary" , "Budapest", "Erasmus 2019", R.drawable.budapest, R.drawable.hungary),
            Place("Poland", "Warsaw", "Erasmus 2019", R.drawable.warsaw, R.drawable.poland),
            Place("Norway", "Oslo", "Erasmus 2019", R.drawable.oslo, R.drawable.norway),
            Place("Poland", "Gdansk", "Erasmus 2019", R.drawable.gdansk, R.drawable.poland),
            Place("Poland", "Poznan", "Erasmus 2019", R.drawable.poznan, R.drawable.poland),
            Place("Poland", "Krakow", "Erasmus 2019", R.drawable.krakow, R.drawable.poland),
            Place("Poland", "Wroclaw", "Erasmus 2019", R.drawable.wroclaw, R.drawable.poland),
            Place("Germany", "Leipzig", "Summer 2019", R.drawable.leipzig, R.drawable.germany),
            Place("The Netherlands", "Utrecht", "Summer 2019", R.drawable.utrecht, R.drawable.netherlands),
            Place("The Netherlands", "Amsterdam", "Summer 2019", R.drawable.amsterdam, R.drawable.netherlands),
            Place("The Netherlands", "Rotterdam", "Summer 2019", R.drawable.rotterdam, R.drawable.netherlands),
            Place("Belgium", "Gent", "Summer 2019", R.drawable.gent, R.drawable.belgium),
            Place("Belgium", "Brugge", "Summer 2019", R.drawable.brugge, R.drawable.belgium),
            Place("Belgium", "Brussels", "Summer 2019", R.drawable.brussels, R.drawable.belgium),
            Place("Belgium", "Antwerp", "Summer 2019", R.drawable.antwerp, R.drawable.belgium),
            Place("USA", "Route 66", "Summer 2017", R.drawable.route66, R.drawable.usa),
            Place("Switzerland", "Geneva", "Easter Break 2016", R.drawable.switzerlandtrip, R.drawable.switzerland),
            Place("France", "Paris", "Carnival 2016", R.drawable.paris, R.drawable.france),
            Place("UK", "London", "Summer 2015", R.drawable.london, R.drawable.uk),
    )

    val countriesVisited = listOf(
            Country("Italy", R.drawable.venice),
            Country("Poland", R.drawable.zakopane),
            Country("Germany", R.drawable.berlin),
            Country("Austria", R.drawable.salzburg),
            Country("Czech Republic", R.drawable.prague),
            Country("Hungary", R.drawable.budapest),
            Country("Norway", R.drawable.oslo),
            Country("Netherlands", R.drawable.amsterdam),
            Country("Belgium", R.drawable.brugge),
            Country("USA", R.drawable.route66),
            Country("Switzerland", R.drawable.switzerlandtrip),
            Country("France", R.drawable.paris),
            Country("UK", R.drawable.london),
    )

}