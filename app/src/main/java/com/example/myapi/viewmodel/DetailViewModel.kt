package com.example.myapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapi.modeldata.DataSiswa
import com.example.myapi.repositori.RepositoryDataSiswa
import com.example.myapi.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/* ---------- UI STATE ---------- */
sealed interface StatusUIDetail {
    data class Success(val siswa: DataSiswa) : StatusUIDetail
    object Error : StatusUIDetail
    object Loading : StatusUIDetail
}

/* ---------- VIEWMODEL ---------- */
class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    var statusUIDetail: StatusUIDetail by mutableStateOf(StatusUIDetail.Loading)
        private set

    // 🔥 FLAG PENTING
    var isDeleted by mutableStateOf(false)
        private set

    init {
        getSatuSiswa()
    }

    fun getSatuSiswa() {
        if (isDeleted) return   // ⛔ stop reload setelah delete

        viewModelScope.launch {
            statusUIDetail = StatusUIDetail.Loading
            statusUIDetail = try {
                val result = repositoryDataSiswa.getSatuSiswa(idSiswa)
                StatusUIDetail.Success(result)
            } catch (e: IOException) {
                StatusUIDetail.Error
            } catch (e: HttpException) {
                StatusUIDetail.Error
            }
        }
    }

    suspend fun hapusSatuSiswa() {
        val resp: Response<Void> =
            repositoryDataSiswa.hapusSatuSiswa(idSiswa)

        if (resp.isSuccessful) {
            isDeleted = true   // ✅ kunci agar tidak reload lagi
        }
    }
}