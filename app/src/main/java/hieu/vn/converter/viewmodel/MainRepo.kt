package hieu.vn.converter.viewmodel

import hieu.vn.converter.helper.Resource
import hieu.vn.converter.model.ApiResponse
import hieu.vn.converter.network.ApiDataSource
import hieu.vn.converter.network.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiDataSource: ApiDataSource) : BaseDataSource() {

    //Using coroutines flow to get the response from
    suspend fun getConvertedData(
        access_key: String,
        from: String,
        to: String,
        amount: Double
    ): Flow<Resource<ApiResponse>> {
        return flow {
            emit(safeApiCall { apiDataSource.getConvertedRate(access_key, from, to, amount) })
        }.flowOn(Dispatchers.IO)
    }


}