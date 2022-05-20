import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun SymbolToImage(symbol: String): ImageVector {

    return when (symbol) {

        "d000" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_sun)
        "d100" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_cloud_sun)
        "d200" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_cloud_sun)
        "d300" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_cloud_sun)
        "d400" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_cloud)
        "d500" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_fog_sun)
        "d600" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_fog_sun)
        "d210" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light_sun)
        "d310" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light_sun)
        "d410" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light)
        "d220" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light_sun)
        "d320" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light_sun)
        "d420" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light)
        "d430" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light)
        "d240" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_thunder_sun)
        "d340" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_thunder_sun)
        "d440" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_thunder)


        "n000" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_moon)
        "n100" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_cloud_moon)
        "n200" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_cloud_moon)
        "n300" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_cloud_moon)
        "n400" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_cloud)
        "n500" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_fog_moon)
        "n600" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_fog)
        "n210" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_heavy_moon)
        "n310" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_heavy_moon)
        "n410" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light)
        "n220" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_heavy_moon)
        "n320" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_heavy_moon)
        "n420" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light)
        "n430" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light)
        "n240" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_rain_light)
        "n340" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_thunder_moon)
        "n440" -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_thunder)

        else -> ImageVector.vectorResource(id = com.example.weatherapp.R.drawable.ic_na)

    }


}