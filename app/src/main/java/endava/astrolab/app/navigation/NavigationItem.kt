package endava.astrolab.app.navigation

import endava.astrolab.app.R

const val HOME_ROUTE = "Home"
const val DAILY_IMAGE_ROUTE = "DailyImage"

sealed class NavigationItem(
    override val route: String,
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val labelId: Int,
) : AstrolabDestination(route) {
    object HomeDestination : NavigationItem(
        route = HOME_ROUTE,
        selectedIconId = R.drawable.ic_home_filled,
        unselectedIconId = R.drawable.ic_home_outlined,
        labelId = R.string.home,
    )
    object DailyImageDestination : NavigationItem(
        route = DAILY_IMAGE_ROUTE,
        selectedIconId = R.drawable.ic_image_filled,
        unselectedIconId = R.drawable.ic_image_outlined,
        labelId = R.string.daily_image,
    )
}
