package com.example.myapi.uicontroller.route

import com.example.myapi.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_siswa"

    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"

    override val titleRes = R.string.detail_siswa
}